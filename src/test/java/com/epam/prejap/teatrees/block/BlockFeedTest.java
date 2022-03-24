package com.epam.prejap.teatrees.block;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BlockFeedTest {


    @Test
    public void shouldContainUBlock() {
        // given
        BlockFeed blockFeed = new BlockFeed();

        // when
        var actual = blockFeed.nextBlock(1);

        // then
        assertTrue(actual instanceof IBlock);
    }

}