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
}