package org.kata.mowitnow.mower;

import lombok.*;

@EqualsAndHashCode
@Setter
@Getter
@Builder
public class BorderLimit {

    private Integer xCoordinateLimit;
    private Integer yCoordinateLimit;
}
