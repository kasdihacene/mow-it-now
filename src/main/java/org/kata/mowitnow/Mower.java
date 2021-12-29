package org.kata.mowitnow;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Mower {

    private String movement;
    private Position position;

    public void move(String lawnBorders) {
        String[] commands = movement.split("");

        for (String command : commands) {
            switch (command) {
                case "D": {
                    position.turnRight();
                    break;
                }
                case "G": {
                    position.turnLeft();
                    break;
                }
                case "A": {
                    position.moveForward();
                    break;
                }
                default:
                    break;
            }
        }
    }

}
