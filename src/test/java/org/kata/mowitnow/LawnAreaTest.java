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
                .position(mowerPosition)
                .movement(movement)
                .build();
        LawnArea lawnArea = LawnArea.builder()
                .coordLawn(coordLawn)
                .build();

        // When
        lawnArea.addMower(mower);
        lawnArea.mowIt();

        // Then
        assertThat(mower.getPosition()).isEqualTo("1 3 N");
    }

}
