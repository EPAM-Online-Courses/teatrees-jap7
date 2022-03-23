package com.epam.prejap.teatrees.block;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.*;

@Test
public class BlockFeedTest {

    public void shouldContainUBlock() {
        // given
        Random random = Mockito.mock(Random.class);
        BlockFeed blockFeed = new BlockFeed(random);
        Mockito.when(random.nextInt(2)).thenReturn(1);

        // when
        var actual = blockFeed.nextBlock();

        // then
        assertTrue(actual instanceof UBlock);
    }
}
