import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertGCodeToEleksDraw {
    static public void main (String[] args) {
        String path = "./";
        String filetype = ".gcode";
        String marker = ".eldrw";
        String lb = "\r\n"; // line break

        int feedrate = 2500;

        String penEnd = "M3 S0";
        String penUp = "M3 S20";
        String penDown = "M3 S30";

        if (args.length > 0)
            path = args[0];
        if (args.length > 1)
            feedrate = Integer.parseInt(args[1]);
        if (args.length > 2)
            penUp = args[2];
        if (args.length > 3)
            penDown = args[3];


        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(filetype)).collect(Collectors.toList());

            for (String file : result) {
                if (file.contains(marker))
                    continue;

                String gcode = Files.readString(Paths.get(file));

                //gcode = gcode.replaceAll(".*S.*" + lb, penUp + lb);
                gcode = gcode.replaceAll(".*M.*" + lb, "");
                gcode = gcode.replaceAll(".*G0Z[0-9].*" + lb, penUp + lb);
                gcode = gcode.replaceAll(".*G1Z-.*" + lb, penDown + lb);
                gcode = gcode.replaceAll("(?!.*[XY])(Z\\d+.\\d+.*)" + lb, lb);
                gcode = gcode.replaceAll("(?!.*[XY])(F\\d+.\\d+.*)" + lb, "F" + feedrate + lb);
                gcode = gcode.replaceAll(penUp + lb + penUp, penEnd);

                Path newfile = Paths.get(file.substring(0, file.length() - filetype.length()) + marker + filetype);
                Files.writeString(newfile, gcode, StandardCharsets.UTF_8);

                System.out.println("Converted " + file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
