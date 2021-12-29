package org.kata.mowitnow;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Coordinate {

    private Integer xCoordinate;
    private Integer yCoordinate;
    private String orientation;

    public String toPosition() {
        return xCoordinate + " " + yCoordinate + " " + orientation;
    }

    public void incrementXCoordinate() {
        xCoordinate = xCoordinate + 1;
    }

    public void decrementYCoordinate() {
        yCoordinate = yCoordinate - 1;
    }

    public void decrementXCoordinate() {
        xCoordinate = xCoordinate - 1;
    }

    public void incrementYCoordinate() {
        yCoordinate = yCoordinate + 1;
    }
}
