package org.kata.mowitnow;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LawnArea {
    private Mower mower;


    public void addMower(Mower mower) {
        this.mower = mower;
    }

    public void mowIt() {
        mower.move();
    }
}
