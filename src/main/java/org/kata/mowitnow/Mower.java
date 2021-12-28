package org.kata.mowitnow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Mower {

    String position;
    String movement;
    private static final String MOVEMENT_DG = "NESW"; // North Est West South

    public static char nextOrientation(String command, String currentOrientation) {
        int indexOfCurrentOrientation = MOVEMENT_DG.indexOf(currentOrientation);

        if (command.equals("D")) {
            return (indexOfCurrentOrientation + 1 == MOVEMENT_DG.length()) ?
                    MOVEMENT_DG.charAt(0) : MOVEMENT_DG.charAt(indexOfCurrentOrientation + 1);

        }
        return (indexOfCurrentOrientation - 1 < 0) ?
                MOVEMENT_DG.charAt(MOVEMENT_DG.length() - 1) : MOVEMENT_DG.charAt(indexOfCurrentOrientation - 1);
    }

    public void move(String lawnBorders) {
        String[] commands = movement.split("");
        for (String command : commands) {
            switch (command) {
                case "D":
                case "G":
                case "A":
                default:
            }
        }
    }
}
