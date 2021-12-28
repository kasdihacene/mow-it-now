package org.kata.mowitnow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Mower {

    String position;
    String movement;

    public void move(String lawnBorders) {

    }
}
