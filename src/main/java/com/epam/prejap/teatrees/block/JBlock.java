package com.epam.prejap.teatrees.block;

/**
 * @author Marta Krawczykowska, Krzysztof Dolanski
 */
final class JBlock extends Block {

    private static final byte[][] IMAGE = {
            {0, 1},
            {0, 1},
            {1, 1},
    };

    JBlock() {
        super(IMAGE);
    }

}
