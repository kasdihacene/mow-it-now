package org.kata.mowitnow.mower;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kata.mowitnow.BorderLimit;
import org.kata.mowitnow.mower.Coordinate;

@Getter
@Setter
@Builder
public class Position {

    private static final String MOVEMENT_DG = "NESW"; // North Est West South

    private Coordinate coordinate;
    private BorderLimit borderLimit;

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
                if (coordinate.getYCoordinate() + 1 <= borderLimit.getYCoordinateLimit())
                    coordinate.incrementYCoordinate();
                break;
            case "E":
                if (coordinate.getXCoordinate() + 1 <= borderLimit.getXCoordinateLimit())
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

}
