package com.epam.prejap.teatrees.block;

/**
 * Generates a "L" block in the Tetris game - when "1" in a byte array is present then a "#" is printed,
 * otherwise a whitespace (" ") is printed.
 * @see com.epam.prejap.teatrees.game.Printer#print(byte)
 * @author Grzegorz Konopka
 * @author Artur Kozłowski
 */
final class LBlock extends Block {

    private static final byte[][] IMAGE = {
            {1, 0},
            {1, 0},
            {1, 1}
    };

    LBlock() {
        super(IMAGE);
    }
}
