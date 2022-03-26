# grbl-eleksdraw

Converts Vectric 3D (X, Y, and Z) GRBL-postprocessed, mm-based gcode to a Eleksdraw-compatible gcode format.

Default pen up command: M3 S20\
Default pen down height: M3 S30

## Ressources
- Eleksdraw machine
- Eleksdraw driver
- GRBL-Plotter

## Conversions
For all gcode files in current folder, create Eleksdra-compatible copy by:
- Removing all M-containing command lines
- Replacing all high Z-only commands with pen up
- Replacing all low Z-only commands with pen down
- Inserting pen up before all lines with high Z commands
- Remove all Z commands from all command lines

## Eleksdraw information
Maximum x-axis range: 0-255 mm\
Safe x-axis range: 5-250 mm

Maximum y-axis range: -40-140 mm\
Safe x-axis range: -35-135 mm