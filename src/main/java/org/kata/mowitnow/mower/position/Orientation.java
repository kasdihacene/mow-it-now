package org.kata.mowitnow.mower.position;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kata.mowitnow.mower.BorderLimit;

@AllArgsConstructor
@Getter
public enum Orientation {
    N("N") {
        @Override
        public boolean updateOrientation(Coordinate coordinate, BorderLimit borderLimit) {
            if (coordinate.getYCoordinate() + 1 <= borderLimit.getYCoordinateLimit()) {
                coordinate.incrementYCoordinate();
                return true;
            } else return MOVE_NOT_PROCESSED;
        }
    },
    E("E") {
        @Override
        public boolean updateOrientation(Coordinate coordinate, BorderLimit borderLimit) {
            if (coordinate.getXCoordinate() + 1 <= borderLimit.getXCoordinateLimit()) {
                coordinate.incrementXCoordinate();
                return true;
            } else return MOVE_NOT_PROCESSED;
        }
    },
    S("S") {
        @Override
        public boolean updateOrientation(Coordinate coordinate, BorderLimit borderLimit) {
            if (coordinate.getYCoordinate() - 1 >= 0) {
                coordinate.decrementYCoordinate();
                return true;
            } else return MOVE_NOT_PROCESSED;
        }
    },
    W("W") {
        @Override
        public boolean updateOrientation(Coordinate coordinate, BorderLimit borderLimit) {
            if (coordinate.getXCoordinate() - 1 >= 0) {
                coordinate.decrementXCoordinate();
                return true;
            } else return MOVE_NOT_PROCESSED;
        }
    };

    private static final boolean MOVE_NOT_PROCESSED = false;
    private final String orientation;

    public abstract boolean updateOrientation(Coordinate coordinate, BorderLimit borderLimit);
}
