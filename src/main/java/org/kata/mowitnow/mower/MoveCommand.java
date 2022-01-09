package org.kata.mowitnow.mower;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kata.mowitnow.mower.position.Position;

@AllArgsConstructor
@Getter
public enum MoveCommand {
    D("D") {
        @Override
        public boolean move(Position position) {
            position.turnRight();
            return NON_BLOCKING_MOVE;
        }
    },
    G("G") {
        @Override
        public boolean move(Position position) {
            position.turnLeft();
            return NON_BLOCKING_MOVE;
        }
    },
    A("A") {
        @Override
        public boolean move(Position position) {
            return position.moveForward();
        }
    };

    private static final boolean NON_BLOCKING_MOVE = true;
    private final String move;
    public abstract boolean move(Position position);
}
