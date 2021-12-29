package org.kata.mowitnow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LawnArea {
    private String coordinatesLawn;
    private Mower mower;


    public void addMower(Mower mower) {
        this.mower = mower;
    }

    public void mowIt() {
        mower.move(coordinatesLawn);
    }
}
