package org.kata.mowitnow;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BorderLimit {

    private Integer xCoordinateLimit;
    private Integer yCoordinateLimit;
}
