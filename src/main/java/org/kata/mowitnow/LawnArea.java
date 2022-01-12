package org.kata.mowitnow;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kata.mowitnow.mower.Mower;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class LawnArea {

    @Builder.Default
    private final List<Mower> mowers = new ArrayList<>();

    public void addMower(Mower mower) {
        this.mowers.add(mower);
    }

    public void mowIt() {
        // TODO : Intercept the inaccessible positions (where the precedent mowers are stopped)
        mowers.stream()
                .map(Mower::move)
                .collect(Collectors.toList());
    }
}
