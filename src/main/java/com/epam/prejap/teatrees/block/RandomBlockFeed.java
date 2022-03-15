package com.epam.prejap.teatrees.block;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class RandomBlockFeed implements BlockFeed {

    private final Random rnd = new Random();
    private final List<Supplier<Block>> blocks = List.of(
        OBlock::new, TBlock::new
    );

    public RandomBlockFeed() {
    }

    @Override
    public Block nextBlock() {
        return blocks.get(rnd.nextInt(blocks.size())).get();
    }

}
