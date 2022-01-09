package org.kata.mowitnow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kata.mowitnow.command.CommandParser;
import org.kata.mowitnow.mower.BorderLimit;
import org.kata.mowitnow.mower.Mower;
import org.kata.mowitnow.mower.position.MowerCoordinate;
import org.kata.mowitnow.mower.position.Position;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class LawnAreaTest {

    private static final String INSTRUCTIONS_FILE_TXT = "instructions/file1.txt";

    private CommandParser commandParser;

    @BeforeEach
    void setup() throws IOException {
        commandParser = CommandParser.builder()
                .commandsFile(Files.readString(Paths.get(INSTRUCTIONS_FILE_TXT)))
                .build();
    }

    @Test
    void should_return_new_position_when_mow_a_lawn_area_with_one_mower() {
        // Given
        int firstMower = 0;
        MowerCoordinate mowerCoordinate = commandParser.getMowersCoordinates().get(firstMower);
        BorderLimit borderLimit = commandParser.getBorderLimit();

        Mower mower = Mower.builder()
                .movementRecord(mowerCoordinate.getMovement())
                .position(Position
                        .builder()
                        .coordinate(mowerCoordinate.getCoordinate())
                        .borderLimit(borderLimit)
                        .build())
                .build();

        LawnArea lawnArea = LawnArea.builder()
                .build();

        // When
        lawnArea.addMower(mower);
        lawnArea.mowIt();

        // Then
        assertThat(mower.getPosition().toPosition()).isEqualTo("1 3 N");
    }

    @Test
    void should_return_new_position_when_mow_a_lawn_area_from_another_start_position() {
        // Given
        int SecondMower = 1;
        MowerCoordinate mowerCoordinate = commandParser.getMowersCoordinates().get(SecondMower);
        BorderLimit borderLimit = commandParser.getBorderLimit();

        Mower mower = Mower.builder()
                .movementRecord(mowerCoordinate.getMovement())
                .position(Position
                        .builder()
                        .coordinate(mowerCoordinate.getCoordinate())
                        .borderLimit(borderLimit)
                        .build())
                .build();

        LawnArea lawnArea = LawnArea.builder()
                .build();

        // When
        lawnArea.addMower(mower);
        lawnArea.mowIt();

        // Then
        assertThat(mower.getPosition().toPosition()).isEqualTo("5 1 E");
    }
}
