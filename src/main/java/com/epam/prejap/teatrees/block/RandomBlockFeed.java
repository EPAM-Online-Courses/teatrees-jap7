package com.epam.prejap.teatrees.block;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class RandomBlockFeed implements BlockFeed {

    private final Random rnd;
    private final List<Supplier<Block>> blocks = List.of(
            OBlock::new,
            TBlock::new,
            LBlock::new
    );

    public RandomBlockFeed() {
        rnd = new Random();
    }

    @Override
    public Block nextBlock() {
        return blocks.get(rnd.nextInt(blocks.size())).get();
    }

}
