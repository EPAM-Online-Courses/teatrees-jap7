package com.epam.prejap.teatrees;

import com.epam.prejap.teatrees.block.BlockFeed;
import com.epam.prejap.teatrees.game.Playfield;
import com.epam.prejap.teatrees.game.Printer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayfieldTest {

    @Test(dataProvider = "gridProvider")
    public void shouldRemoveCompleteLine(byte[][] grid, byte[][] expectedGrid)
    {
        // given
        Playfield playfield = new Playfield(grid, new BlockFeed(), new Printer(System.out));
        // when
        playfield.removeCompleteLines();
        // then
        assertEquals(grid, expectedGrid);

    }

    @DataProvider
    public Object[][] gridProvider()
    {
        return new Object[][] {
                {new byte[][] {{0,0}, {1,1}}, new byte[][] {{0,0}, {0,0}}},
                {new byte[][] {{0,0,0},{1,0,1},{1,1,1}}, new byte[][] {{0,0,0},{0,0,0},{1,0,1}}},
                {new byte[][] {{0,0,0},{1,1,1},{1,0,1}}, new byte[][] {{0,0,0},{0,0,0},{1,0,1}}},
        };
    }
}