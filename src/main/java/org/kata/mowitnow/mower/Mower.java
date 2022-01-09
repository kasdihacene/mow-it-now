package org.kata.mowitnow.mower;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Mower {

    private static final String MOVE_RIGHT_COMMAND = "D";
    private static final String MOVE_LEFT_COMMAND = "G";
    private static final String MOVE_FORWARD_COMMAND = "A";

    private String movementRecord;
    private Position position;

    public void move() {
        String[] movements = movementRecord.split("");

        for (String command : movements) {
            switch (command) {
                case MOVE_RIGHT_COMMAND: {
                    position.turnRight();
                    break;
                }
                case MOVE_LEFT_COMMAND: {
                    position.turnLeft();
                    break;
                }
                case MOVE_FORWARD_COMMAND: {
                    position.moveForward();
                    break;
                }
                default:
                    break;
            }
        }
    }

}
