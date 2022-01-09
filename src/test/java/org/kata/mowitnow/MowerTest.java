package org.kata.mowitnow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.kata.mowitnow.mower.BorderLimit;
import org.kata.mowitnow.mower.position.Coordinate;
import org.kata.mowitnow.mower.Mower;
import org.kata.mowitnow.mower.position.Position;

import static org.assertj.core.api.Assertions.assertThat;

class MowerTest {

    @Test
    void should_change_mower_orientation_when_receiving_D_commands() {
        // Given
        String movement = "DD";
        Mower mower = Mower.builder()
                .movementRecord(movement)
                .position(Position
                        .builder()
                        .coordinate(Coordinate.builder().xCoordinate(3).yCoordinate(3).orientation("E").build())
                        .borderLimit(BorderLimit.builder().xCoordinateLimit(5).yCoordinateLimit(5).build())
                        .build())
                .build();

        // When
        mower.move();

        // Then
        assertThat(mower.getPosition().toPosition()).isEqualTo("3 3 W");
    }

    @Test
    void should_change_mower_orientation_when_receiving_G_commands() {
        // Given
        String movement = "GG";
        Mower mower = Mower.builder()
                .position(Position
                        .builder()
                        .coordinate(Coordinate.builder().xCoordinate(3).yCoordinate(3).orientation("W").build())
                        .borderLimit(BorderLimit.builder().xCoordinateLimit(5).yCoordinateLimit(5).build())
                        .build())
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
        String movement = "AA";
        String[] positionTokens = mowerPosition.split(" ");
        Mower mower = Mower.builder()
                .position(Position
                        .builder()
                        .coordinate(Coordinate.builder()
                                .xCoordinate(Integer.valueOf(positionTokens[0]))
                                .yCoordinate(Integer.valueOf(positionTokens[1]))
                                .orientation(positionTokens[2])
                                .build())
                        .borderLimit(BorderLimit.builder().xCoordinateLimit(5).yCoordinateLimit(5).build())
                        .build())
                .movementRecord(movement)
                .build();

        // When
        mower.move();

        // Then
        assertThat(mower.getPosition().toPosition()).isEqualTo(expectedPosition);
    }

    @ParameterizedTest
    @CsvSource({
            "AAAGAA, 1 4 N, 5, 5, 1 5 N",
            "AAAAAA, 0 10 E, 4, 12, 4 10 E",
    })
    void should_lock_the_mower_move_after_going_outside_the_lawn(String movement, String mowerPosition, Integer xCoordinateLimit, Integer yCoordinateLimit, String expectedPosition) {
        // Given

        String[] positionTokens = mowerPosition.split(" ");
        Mower mower = Mower.builder()
                .position(Position
                        .builder()
                        .coordinate(Coordinate.builder()
                                .xCoordinate(Integer.valueOf(positionTokens[0]))
                                .yCoordinate(Integer.valueOf(positionTokens[1]))
                                .orientation(positionTokens[2])
                                .build())
                        .borderLimit(BorderLimit.builder().xCoordinateLimit(xCoordinateLimit).yCoordinateLimit(yCoordinateLimit).build())
                        .build())
                .movementRecord(movement)
                .build();

        // When
        mower.move();

        // Then
        assertThat(mower.getHasNonBlockingMove()).isFalse();
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