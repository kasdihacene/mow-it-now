package org.kata.mowitnow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Mower {

    private String movement;
    private Position position;

    public void move(String lawnBorders) {
        String[] commands = movement.split("");

        for (String command : commands) {
            switch (command) {
                case "D": {
                    position.nextPositionOrientation("D");
                    break;
                }
                case "G": {
                    position.nextPositionOrientation("G");
                    break;
                }
                case "A": {
                    position.move();
                    break;
                }
                default:
                    break;
            }
        }
    }

}
