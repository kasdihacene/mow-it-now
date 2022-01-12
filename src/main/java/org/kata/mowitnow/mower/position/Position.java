package org.kata.mowitnow.mower.position;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kata.mowitnow.mower.BorderLimit;

@Getter
@Setter
@Builder
public class Position {

    private static final String MOVEMENT_DG = "NESW"; // North Est West South
    private static final boolean MOVE_NOT_PROCESSED = false;
    private static final boolean MOVE_PROCESSED = true;

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

    public boolean moveForward() {
        return Orientation.valueOf(coordinate.getOrientation())
                .updateOrientation(coordinate, borderLimit);
    }

    public String toPosition() {
        return coordinate.toPosition();
    }

}
