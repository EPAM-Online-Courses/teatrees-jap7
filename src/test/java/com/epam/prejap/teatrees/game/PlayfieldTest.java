package com.epam.prejap.teatrees.game;

import com.epam.prejap.teatrees.block.BlockFeed;
import com.epam.prejap.teatrees.game.Playfield;
import com.epam.prejap.teatrees.game.Printer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class PlayfieldTest {

    @Test(dataProvider = "gridWithCompleteLinesProvider")
    public void shouldRemoveCompleteLines(byte[][] grid, byte[][] expectedGrid)
    {
        // given
        Playfield playfield = new Playfield(grid, new BlockFeed(), new Printer(System.out));
        // when
        playfield.removeCompleteLines();
        // then

        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < grid.length; i++) {
            softAssert.assertEquals(grid[i], expectedGrid[i]);
        }
        softAssert.assertAll();

    }

    @Test(dataProvider = "gridWithoutCompleteLinesProvider")
    public void shouldNotRemoveAnyLines(byte[][] grid, byte[][] expectedGrid)
    {
        // given
        Playfield playfield = new Playfield(grid, new BlockFeed(), new Printer(System.out));
        // when
        playfield.removeCompleteLines();
        // then

        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < grid.length; i++) {
            softAssert.assertEquals(grid[i], expectedGrid[i]);
        }
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] gridWithCompleteLinesProvider()
    {
        return new Object[][] {
                {new byte[][] {{0,0}, {1,1}}, new byte[][] {{0,0}, {0,0}}},
                {new byte[][] {{0,0,0},{1,0,1},{1,1,1}}, new byte[][] {{0,0,0},{0,0,0},{1,0,1}}},
                {new byte[][] {{0,0,0},{1,1,1},{1,0,1}}, new byte[][] {{0,0,0},{0,0,0},{1,0,1}}},
                {new byte[][] {{0,1,0},{1,1,1},{1,1,1}}, new byte[][] {{0,0,0},{0,0,0},{0,1,0}}},
        };
    }

    @DataProvider
    public Object[][] gridWithoutCompleteLinesProvider()
    {
        return new Object[][] {
                {new byte[][] {{0,0}, {1,0}}, new byte[][] {{0,0}, {1,0}}},
                {new byte[][] {{0,0,0},{1,0,1},{0,1,1}}, new byte[][] {{0,0,0},{1,0,1},{0,1,1}}},
                {new byte[][] {{0,0,0},{0,0,1},{1,0,1}}, new byte[][] {{0,0,0},{0,0,1},{1,0,1}}},
        };
    }
}