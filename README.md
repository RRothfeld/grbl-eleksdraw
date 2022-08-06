# grbl-eleksdraw

Converts Vectric 3D (X, Y, and Z) GRBL-postprocessed, mm-based gcode to a Eleksdraw-compatible gcode format.

`args[0]` Default feedrate: 5000\
`args[1]` Default pen ending up command: M3 S10\
`args[2]` Default pen up command: M3 S25\
`args[3]` Default pen down height: M3 S40
`args[4]` Default path: "./"\

All of these arguments are optional.

## Ressources
- Eleksdraw machine
- Eleksdraw driver
- GRBL-Plotter

## Conversions
For all gcode files in current folder, create Eleksdra-compatible copy by:
- Removing all M-containing command lines
- Replacing all high Z-only commands with pen up
- Replacing all low Z-only commands with pen down
- Removing all remaining Z inputs from XYZ commands
- Replace all feedrates with input feedrate
- Replace last double pen up to ending pen up command

## Eleksdraw information
Safe drawing boundary box: 245\*170 mm 

Maximum x-axis range: 0-255 mm\
Safe x-axis range: 5-250 mm

Maximum y-axis range: -40-140 mm\
Safe x-axis range: -35-135 mm

## Vectric Aspire information
Vectric coordinate system should be centered on the workpiece and zeroed to the material surface with the workpiece size equal to the safe drawing boundary box.

### Process for gcode creation
- Write desired text/import shapes/...
- Right click and convert text to shapes
- Select all
- Deselect enclosed inner shapes
- Union remaining selection
- Apply toolpath for, e.g., contour or filling

### Continued process within GRBL-Plotter
- Run conversion from Z-including gcode to EleksDraw-compatible gcode
- Load compatible gcode into GRBL-Plotter
- Apply center-bottom origin XY and, if required, offset