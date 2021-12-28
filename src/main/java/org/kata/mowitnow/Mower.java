package org.kata.mowitnow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@Builder
public class Mower {

    private static final int MOWER_ORIENTATION = 3;
    private String position;
    private String movement;
    private static final String MOVEMENT_DG = "NESW"; // North Est West South
    private static final String COMMAND_PATTERN = "^([1-9]+) ([1-9]+) ([A-Z])$";

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
        Pattern pattern = Pattern.compile(COMMAND_PATTERN);

        String[] commands = movement.split("");
        for (String command : commands) {

            String currentOrientation = getCurrentOrientation(pattern);

            switch (command) {
                case "D": {
                    updateOrientation(currentOrientation, "D");
                    break;
                }
                case "G": {
                    updateOrientation(currentOrientation, "G");
                    break;
                }
                default:
                    break;
            }
        }
    }

    private void updateOrientation(String currentOrientation, String d) {
        char nextOrientation = nextOrientation(d, currentOrientation);
        position = position.replace(currentOrientation.charAt(0), nextOrientation);
    }

    private String getCurrentOrientation(Pattern pattern) {
        Matcher matcher = pattern.matcher(position);
        if (matcher.find())
            return matcher.group(MOWER_ORIENTATION);
        throw new RuntimeException("No pattern of mower position found");
    }
}
