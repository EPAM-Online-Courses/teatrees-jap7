package com.epam.prejap.teatrees.block;
/**
 * Adds U-block shape to the game.
 *
 * @author Maciej Drobot
 * @author Malgorzata Lasota
 */

final class UBlock extends Block {

    private static final byte[][] IMAGE = {
            {1, 0, 1},
            {1, 1, 1},
    };

    public UBlock() {
        super(IMAGE);
    }
}
