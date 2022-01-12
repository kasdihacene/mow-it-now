package org.kata.mowitnow.mower;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kata.mowitnow.mower.position.Position;

@Getter
@Setter
@Builder
public class Mower {

    // TODO : parse the movement record (build list commands according to the string command)
    private String movementRecord;
    private Position position;
    @Builder.Default
    private Boolean hasNonBlockingMove = true;

    public Mower move() {
        String[] movements = movementRecord.split("");

        for (String command : movements) {
            if (getHasNonBlockingMove()) {
                setHasNonBlockingMove(MoveCommand
                        .valueOf(command)
                        .move(position));
            }
        }
        return this;
    }

}
