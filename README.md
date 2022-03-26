# grbl-eleksdraw

Converts Vectric 3D (X, Y, and Z) GRBL-postprocessed, mm-based gcode to a Eleksdraw-compatible gcode format.

Vectric coordinate system should be centered on the workpiece and zeroed to the material surface.

`args[0]` Default path: "./"\
`args[1]` Default feedrate: 2500\
`args[2]` Default pen up command: M3 S20\
`args[3]` Default pen down height: M3 S30

All of these arguments are optional.

Ending pen up command: M3 S0

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
Safe drawing box boundary: 245\*170 mm 

Maximum x-axis range: 0-255 mm\
Safe x-axis range: 5-250 mm

Maximum y-axis range: -40-140 mm\
Safe x-axis range: -35-135 mm