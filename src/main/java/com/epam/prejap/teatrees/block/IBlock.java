package com.epam.prejap.teatrees.block;

/**
 * @author Marta Krawczykowska, Krzysztof Dolanski
 */
final class IBlock extends Block {

    private static final byte[][] IMAGE = {
            {0, 1},
            {0, 1},
            {0, 1},
            {0, 1},
    };

    IBlock() {
        super(IMAGE);
    }

}
