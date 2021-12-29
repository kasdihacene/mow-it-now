package org.kata.mowitnow;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@Builder
public class Position {

    private static final String MOVEMENT_DG = "NESW"; // North Est West South
    private static final String COMMAND_PATTERN = "^([0-9]+) ([0-9]+) ([A-Z])$";
    private static final String COMMAND_BORDERS_PATTERN = "^([0-9]+) ([0-9]+)$";

    private static final int MOWER_ORIENTATION = 3;
    private static final int X_COORDINATES = 1;
    private static final int Y_COORDINATES = 2;
    private static final int X_COORDINATES_LIMIT = 1;
    private static final int Y_COORDINATES_LIMIT = 2;

    private Coordinate coordinate;

    private Integer xCoordinateLimit;
    private Integer yCoordinateLimit;

    private final String commandLine;
    private final String xyLimitBorder;

    public void turnLeft() {
        int indexOfCurrentOrientation = MOVEMENT_DG.indexOf(coordinate.getOrientation());

        coordinate.setOrientation(
                String.valueOf((indexOfCurrentOrientation - 1 < 0) ?
                        MOVEMENT_DG.charAt(MOVEMENT_DG.length() - 1) :
                        MOVEMENT_DG.charAt(indexOfCurrentOrientation - 1)));
    }

    public void turnRight() {
        int indexOfCurrentOrientation = MOVEMENT_DG.indexOf(coordinate.getOrientation());

        coordinate.setOrientation(
                String.valueOf((indexOfCurrentOrientation + 1 == MOVEMENT_DG.length()) ?
                        MOVEMENT_DG.charAt(0) :
                        MOVEMENT_DG.charAt(indexOfCurrentOrientation + 1)));
    }

    public void moveForward() {
        switch (coordinate.getOrientation()) {
            case "N":
                if (coordinate.getYCoordinate() + 1 <= yCoordinateLimit)
                    coordinate.incrementYCoordinate();
                break;
            case "E":
                if (coordinate.getXCoordinate() + 1 <= xCoordinateLimit)
                    coordinate.incrementXCoordinate();
                break;
            case "S":
                if (coordinate.getYCoordinate() - 1 >= 0)
                    coordinate.decrementYCoordinate();
                break;
            case "W":
                if (coordinate.getXCoordinate() - 1 >= 0)
                    coordinate.decrementXCoordinate();
                break;
            default:
                break;
        }
    }

    public String toPosition() {
        return coordinate.toPosition();
    }

    public Position parseCommand() {
        parseOrientationCommand();
        parseBorderLimitsCommand();
        return this;
    }

    private void parseBorderLimitsCommand() {
        Pattern pattern = Pattern.compile(COMMAND_BORDERS_PATTERN);
        Matcher matcher = pattern.matcher(xyLimitBorder);

        if (!matcher.find()) {
            throw new RuntimeException("No pattern of border limits found");
        }
        xCoordinateLimit = Integer.valueOf(matcher.group(X_COORDINATES_LIMIT));
        yCoordinateLimit = Integer.valueOf(matcher.group(Y_COORDINATES_LIMIT));
    }

    private void parseOrientationCommand() {
        Pattern pattern = Pattern.compile(COMMAND_PATTERN);
        Matcher matcher = pattern.matcher(commandLine);

        if (!matcher.find()) {
            throw new RuntimeException("No pattern of mower position found");
        }

        coordinate = Coordinate.builder()
                .xCoordinate(Integer.valueOf(matcher.group(X_COORDINATES)))
                .yCoordinate(Integer.valueOf(matcher.group(Y_COORDINATES)))
                .orientation(matcher.group(MOWER_ORIENTATION))
                .build();
    }
}
