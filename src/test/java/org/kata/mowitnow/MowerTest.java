package org.kata.mowitnow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MowerTest {

    @Test
    void should_change_mower_orientation_when_receiving_D_commands() {
        // Given
        String lawnBorders = "5 5";
        String mowerPosition = "3 3 E";
        String movement = "DD";
        Mower mower = Mower.builder()
                .movementRecord(movement)
                .position(Position
                        .builder()
                        .commandLine(mowerPosition)
                        .commandXYLimitBorder(lawnBorders)
                        .build()
                        .parseCommand())
                .build();

        // When
        mower.move();

        // Then
        assertThat(mower.getPosition().toPosition()).isEqualTo("3 3 W");
    }

    @Test
    void should_change_mower_orientation_when_receiving_G_commands() {
        // Given
        String lawnBorders = "5 5";
        String mowerPosition = "3 3 W";
        String movement = "GG";
        Mower mower = Mower.builder()
                .position(Position
                        .builder()
                        .commandLine(mowerPosition)
                        .commandXYLimitBorder(lawnBorders)
                        .build()
                        .parseCommand())
                .movementRecord(movement)
                .build();

        // When
        mower.move();

        // Then
        assertThat(mower.getPosition().toPosition()).isEqualTo("3 3 E");
    }

    @ParameterizedTest
    @CsvSource({
            "0 2 W, 0 2 W",
            "5 2 E, 5 2 E",
            "2 5 N, 2 5 N",
            "2 0 S, 2 0 S",
    })
    void should_not_move_forward_when_going_out_of_borders(String mowerPosition, String expectedPosition) {
        // Given
        String lawnBorders = "5 5";
        String movement = "AA";
        Mower mower = Mower.builder()
                .position(Position
                        .builder()
                        .commandLine(mowerPosition)
                        .commandXYLimitBorder(lawnBorders)
                        .build()
                        .parseCommand())
                .movementRecord(movement)
                .build();

        // When
        mower.move();

        // Then
        assertThat(mower.getPosition().toPosition()).isEqualTo(expectedPosition);
    }

    @ParameterizedTest
    @CsvSource({
            "D,E,S",
            "D,S,W",
            "D,W,N",
            "D,N,E",
            "G,N,W",
            "G,S,E"})
    void should_change_orientation_when_moving_right_or_left(String commandMove, String currentOrientation, String expectedOrientation) {

        // Given
        Position position = Position.builder().build();

        // When
        switch (commandMove) {
            case "D":
                position = buildPosition(currentOrientation);
                position.turnRight();
                break;
            case "G":
                position = buildPosition(currentOrientation);
                position.turnLeft();
                break;
            default:
                break;
        }

        // Then
        assertThat(position.getCoordinate().getOrientation()).isEqualTo(expectedOrientation);
    }

    Position buildPosition(String currentOrientation) {
        return Position
                .builder()
                .coordinate(Coordinate
                        .builder()
                        .orientation(currentOrientation)
                        .build())
                .build();
    }

}