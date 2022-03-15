package com.epam.prejap.teatrees.block;

/**
 * @author Marta Krawczykowska, Krzysztof Dolanski
 */
public final class IBlock extends Block {

    private static final byte[][] IMAGE = {
            {0, 1},
            {0, 1},
            {0, 1},
            {0, 1},
    };

    public IBlock() {
        super(IMAGE);
    }

}
