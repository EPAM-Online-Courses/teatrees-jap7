package com.epam.prejap.teatrees.block;

/**
 * Adds T-block shape to the game.
 *
 * @author Krzysztof Jaczewski
 * @author Marcel Sikorski
 */
final class TBlock extends Block {

    private static final byte[][] IMAGE = {
            {1, 1, 1},
            {0, 1, 0},
    };

    public TBlock() {
        super(IMAGE);
    }

}
