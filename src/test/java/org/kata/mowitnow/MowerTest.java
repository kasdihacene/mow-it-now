package org.kata.mowitnow;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MowerTest {

    @Test
    void test() {
        // Given
        String lawnBorders = "5 5";
        String mowerPosition = "3 3 E";
        String movement = "AADAADADDA";
        Mower mower = Mower.builder()
                .position(mowerPosition)
                .movement(movement)
                .build();

        // When
        mower.move(lawnBorders);

        // Then
        assertThat(mower.getPosition()).isEqualTo("5 1 E");
    }

    @Test
    void should_move_right_to_South_orientation_when_having_current_orientation_Est() {
        // Given
        String commandMove = "D";
        String currentOrientation = "E";

        // When
        char orientation = Mower.nextOrientation(commandMove, currentOrientation);

        // Then
        assertThat(orientation).isEqualTo('S');
    }

    @Test
    void should_move_right_to_West_orientation_when_having_current_orientation_South() {
        // Given
        String commandMove = "D";
        String currentOrientation = "S";

        // When
        char orientation = Mower.nextOrientation(commandMove, currentOrientation);

        // Then
        assertThat(orientation).isEqualTo('W');
    }

    @Test
    void should_move_right_to_North_orientation_when_having_current_orientation_West() {
        // Given
        String commandMove = "D";
        String currentOrientation = "W";

        // When
        char orientation = Mower.nextOrientation(commandMove, currentOrientation);

        // Then
        assertThat(orientation).isEqualTo('N');
    }

    @Test
    void should_move_right_to_Est_orientation_when_having_current_orientation_North() {
        // Given
        String commandMove = "D";
        String currentOrientation = "N";

        // When
        char orientation = Mower.nextOrientation(commandMove, currentOrientation);

        // Then
        assertThat(orientation).isEqualTo('E');
    }
}