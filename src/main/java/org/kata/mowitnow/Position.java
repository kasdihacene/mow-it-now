package org.kata.mowitnow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Position {

    private Integer xCoordinate;
    private Integer yCoordinate;
    private String orientation;

    public String toPosition() {
        return xCoordinate + " " + yCoordinate + " " + orientation;
    }

    public Position move() {
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
    return this;
    }
}
