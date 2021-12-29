package org.kata.mowitnow;

import lombok.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Position {

    private static final String MOVEMENT_DG = "NESW"; // North Est West South
    private static final String COMMAND_PATTERN = "^([0-9]+) ([0-9]+) ([A-Z])$";
    private static final int MOWER_ORIENTATION = 3;
    private static final int X_COORDINATES = 1;
    private static final int Y_COORDINATES = 2;

    private Integer xCoordinate;
    private Integer yCoordinate;
    private String orientation;
    private final String commandLine;

    public String nextPositionOrientation(String command) {
        int indexOfCurrentOrientation = MOVEMENT_DG.indexOf(orientation);

        if (command.equals("D")) {
            orientation = String.valueOf((indexOfCurrentOrientation + 1 == MOVEMENT_DG.length()) ?
                    MOVEMENT_DG.charAt(0) : MOVEMENT_DG.charAt(indexOfCurrentOrientation + 1));
        }

        if (command.equals("G")) {
            orientation = String.valueOf((indexOfCurrentOrientation - 1 < 0) ?
                    MOVEMENT_DG.charAt(MOVEMENT_DG.length() - 1) : MOVEMENT_DG.charAt(indexOfCurrentOrientation - 1));
        }

        return orientation;
    }

    public String toPosition() {
        return xCoordinate + " " + yCoordinate + " " + orientation;
    }

    public void move() {
        switch (orientation) {
            case "N":
                yCoordinate = yCoordinate + 1;
                break;
            case "E":
                xCoordinate = xCoordinate + 1;
                break;
            case "S":
                yCoordinate = yCoordinate - 1;
                break;
            case "W":
                xCoordinate = xCoordinate - 1;
                break;
            default:
                break;
        }
    }

    public Position parseCommand() {
        Pattern pattern = Pattern.compile(COMMAND_PATTERN);
        Matcher matcher = pattern.matcher(commandLine);

        if (!matcher.find()) {
            throw new RuntimeException("No pattern of mower position found");
        }
        xCoordinate = Integer.valueOf(matcher.group(X_COORDINATES));
        yCoordinate = Integer.valueOf(matcher.group(Y_COORDINATES));
        orientation = matcher.group(MOWER_ORIENTATION);
        return this;
    }
}
