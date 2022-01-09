package org.kata.mowitnow.commandpattern;

import lombok.Builder;
import org.kata.mowitnow.BorderLimit;
import org.kata.mowitnow.Coordinate;
import org.kata.mowitnow.MowerCoordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
public class CommandParser {

    private static final String MOVEMENT_DG = "NESW"; // North Est West South
    private static final String COMMAND_PATTERN = "^([0-9]+) ([0-9]+) ([A-Z])$";
    private static final String COMMAND_BORDERS_PATTERN = "^([0-9]+) ([0-9]+)$";

    private static final int MOWER_ORIENTATION = 3;
    private static final int X_COORDINATES = 1;
    private static final int Y_COORDINATES = 2;
    private static final int X_COORDINATES_LIMIT = 1;
    private static final int Y_COORDINATES_LIMIT = 2;

    private final String commandsFile;

    public BorderLimit getBorderLimit() {
        String[] commands = commandsFile.split("\n");
        if (commands.length == 0) throw new RuntimeException("No command found on the file");

        String commandXYLimitBorder = commands[0];
        Pattern pattern = Pattern.compile(COMMAND_BORDERS_PATTERN);
        Matcher matcher = pattern.matcher(commandXYLimitBorder);

        if (!matcher.find()) {
            throw new RuntimeException("No pattern of border limits found");
        }

        return BorderLimit
                .builder()
                .xCoordinateLimit(Integer.valueOf(matcher.group(X_COORDINATES_LIMIT)))
                .yCoordinateLimit(Integer.valueOf(matcher.group(Y_COORDINATES_LIMIT)))
                .build();
    }

    public List<MowerCoordinate> getMowersCoordinates() {
        String[] commands = commandsFile.split("\n");
        if (commands.length == 0) throw new RuntimeException("No command found on the file");

        String[] copyCommands = Arrays.copyOfRange(commands, 1, commands.length);
        List<MowerCoordinate> coordinates = new ArrayList<>();
        for (int indexReader = 0; indexReader < copyCommands.length; indexReader = nextMowerCommandBlock(indexReader)) {

            Pattern pattern = Pattern.compile(COMMAND_PATTERN);
            Matcher matcher = pattern.matcher(copyCommands[indexReader]);

            if (!matcher.find()) {
                throw new RuntimeException("No pattern of mower position found");
            }

            coordinates.add(
                    MowerCoordinate.builder()
                            .coordinate(Coordinate.builder()
                                    .xCoordinate(Integer.valueOf(matcher.group(X_COORDINATES)))
                                    .yCoordinate(Integer.valueOf(matcher.group(Y_COORDINATES)))
                                    .orientation(matcher.group(MOWER_ORIENTATION))
                                    .build())
                            .movement(copyCommands[indexReader + 1])
                            .build());
        }

        return coordinates;
    }

    private int nextMowerCommandBlock(int indexReader) {
        indexReader += 2;
        return indexReader;
    }
}
