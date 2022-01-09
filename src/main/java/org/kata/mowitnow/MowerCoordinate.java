package org.kata.mowitnow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class MowerCoordinate {

    private final Coordinate coordinate;
    private final String movement;
}
