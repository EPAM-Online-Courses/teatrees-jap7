package com.epam.prejap.teatrees.block;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class BlockFeed {

    private final Random rnd;
    private final List<Supplier<Block>> blocks = List.of(
         OBlock::new, UBlock::new
    );

    public BlockFeed(Random random) {
        this.rnd = random;
    }

    public Block nextBlock() {
        return blocks.get(rnd.nextInt(blocks.size())).get();
    }
}
