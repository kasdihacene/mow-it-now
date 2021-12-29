package org.kata.mowitnow;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MowerTest {

    @Test
    void should_change_mower_orientation_when_receiving_D_commands() {
        // Given
        String lawnBorders = "5 5";
        String mowerPosition = "3 3 E";
        String movement = "DD";
        Mower mower = Mower.builder()
                .movement(movement)
                .position(Position
                        .builder()
                        .commandLine(mowerPosition)
                        .build()
                        .parseCommand())
                .build();

        // When
        mower.move(lawnBorders);

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
                        .build()
                        .parseCommand())
                .movement(movement)
                .build();

        // When
        mower.move(lawnBorders);

        // Then
        assertThat(mower.getPosition().toPosition()).isEqualTo("3 3 E");
    }

    @Test
    void should_move_right_to_South_orientation_when_having_current_orientation_Est() {
        // Given
        String commandMove = "D";
        String currentOrientation = "E";

        // When
        String orientation = buildPosition(currentOrientation).nextPositionOrientation(commandMove);

        // Then
        assertThat(orientation).isEqualTo("S");
    }

    private Position buildPosition(String currentOrientation) {
        return Position.builder().orientation(currentOrientation).build();
    }

    @Test
    void should_move_right_to_West_orientation_when_having_current_orientation_South() {
        // Given
        String commandMove = "D";
        String currentOrientation = "S";

        // When
        String orientation = buildPosition(currentOrientation).nextPositionOrientation(commandMove);

        // Then
        assertThat(orientation).isEqualTo("W");
    }

    @Test
    void should_move_right_to_North_orientation_when_having_current_orientation_West() {
        // Given
        String commandMove = "D";
        String currentOrientation = "W";

        // When
        String orientation = buildPosition(currentOrientation).nextPositionOrientation(commandMove);

        // Then
        assertThat(orientation).isEqualTo("N");
    }

    @Test
    void should_move_right_to_Est_orientation_when_having_current_orientation_North() {
        // Given
        String commandMove = "D";
        String currentOrientation = "N";

        // When
        String orientation = buildPosition(currentOrientation).nextPositionOrientation(commandMove);

        // Then
        assertThat(orientation).isEqualTo("E");
    }

    @Test
    void should_move_left_to_West_orientation_when_having_current_orientation_North() {
        // Given
        String commandMove = "G";
        String currentOrientation = "N";

        // When
        String orientation = buildPosition(currentOrientation).nextPositionOrientation(commandMove);

        // Then
        assertThat(orientation).isEqualTo("W");
    }

    @Test
    void should_move_left_to_Est_orientation_when_having_current_orientation_South() {
        // Given
        String commandMove = "G";
        String currentOrientation = "S";

        // When
        String orientation = buildPosition(currentOrientation).nextPositionOrientation(commandMove);

        // Then
        assertThat(orientation).isEqualTo("E");
    }
}