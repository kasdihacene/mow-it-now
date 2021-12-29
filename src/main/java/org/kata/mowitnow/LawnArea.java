package org.kata.mowitnow;

import lombok.*;

@Getter
@Setter
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
