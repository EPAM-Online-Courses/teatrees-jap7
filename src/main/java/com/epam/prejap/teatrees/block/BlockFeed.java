package com.epam.prejap.teatrees.block;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class BlockFeed {

    private final Random rnd = new Random();
    private final List<Supplier<Block>> blocks = List.of(
        OBlock::new,
        TBlock::new
    );

    public BlockFeed() {
    }

    public Block nextBlock(int... index) {
        if (index.length == 0) {
            return blocks.get(rnd.nextInt(blocks.size())).get();
        } else {
            return blocks.get(index[0]).get();
        }
    }

}
