package org.kata.template;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InitTest {

    @Test
    public void should_run_template_test() {
        // Given
        String givenString = "hello";

        // When
        String result = Template.run(givenString);

        // Then
        String expectedString = "hello";
        assertThat(result).isEqualTo(expectedString);
    }
}
