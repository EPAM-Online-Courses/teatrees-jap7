package com.epam.prejap.teatrees.block;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class BlockFeed {

    private final Random rnd = new Random();
    private final List<Supplier<Block>> blocks = List.of(
         OBlock::new, UBlock::new
    );

    public BlockFeed() {
    }

    public Block nextBlock() {
        return blocks.get(rnd.nextInt(blocks.size())).get();
    }

    public Block nextBlock(int index) {
        return blocks.get(index).get();
    }
}
