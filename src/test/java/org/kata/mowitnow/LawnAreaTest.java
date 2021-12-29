package org.kata.mowitnow;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LawnAreaTest {

    @Test
    void should_return_new_position_when_mow_a_lawn_area_with_one_mower() {
        // Given
        String coordLawn = "5 5";
        String mowerPosition = "1 2 N";
        String movement = "GAGAGAGAA";
        Mower mower = Mower.builder()
                .movementRecord(movement)
                .position(Position
                        .builder()
                        .commandLine(mowerPosition)
                        .commandXYLimitBorder(coordLawn)
                        .build()
                        .parseCommand())
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
        String coordLawn = "5 5";
        String mowerPosition = "3 3 E";
        String movement = "AADAADADDA";
        Mower mower = Mower.builder()
                .movementRecord(movement)
                .position(Position
                        .builder()
                        .commandLine(mowerPosition)
                        .commandXYLimitBorder(coordLawn)
                        .build()
                        .parseCommand())
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
