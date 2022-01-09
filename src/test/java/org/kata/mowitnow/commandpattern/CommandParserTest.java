package org.kata.mowitnow.commandpattern;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kata.mowitnow.BorderLimit;
import org.kata.mowitnow.MowerCoordinate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class CommandParserTest {

    private static final String INSTRUCTIONS_FILE_TXT = "instructions/file1.txt";

    @Test
    void should_return_border_limit_command_from_the_file() throws IOException {
        String commands = Files.readString(Paths.get(INSTRUCTIONS_FILE_TXT));

        CommandParser commandParser = CommandParser.builder()
                .commandsFile(commands)
                .build();

        BorderLimit borderLimit = commandParser.getBorderLimit();
        commandParser.getMowersCoordinates();

        Assertions.assertThat(borderLimit).isEqualTo(BorderLimit.builder()
                .xCoordinateLimit(5)
                .yCoordinateLimit(5)
                .build());

    }

    @Test
    void should_return_two_mower_commands_from_the_file() throws IOException {
        String commands = Files.readString(Paths.get("instructions/file1.txt"));

        CommandParser commandParser = CommandParser.builder()
                .commandsFile(commands)
                .build();

        List<MowerCoordinate> mowersCoordinates = commandParser.getMowersCoordinates();

        Assertions.assertThat(mowersCoordinates).hasSize(2);

    }

}