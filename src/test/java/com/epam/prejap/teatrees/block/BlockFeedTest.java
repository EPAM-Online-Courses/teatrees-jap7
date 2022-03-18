package com.epam.prejap.teatrees.block;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class BlockFeedTest {


    public void shouldContainUBlock() {
        // given
        BlockFeed blockFeed = new BlockFeed();

        // when
        var actual = blockFeed.nextBlock(0);

        // then
        assertTrue(actual instanceof UBlock);
    }
}