package com.epam.prejap.teatrees.game;

import com.epam.prejap.teatrees.block.Block;
import com.epam.prejap.teatrees.block.BlockFeed;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Dominik Kaminski
 * @author Przemyslaw Szewczyk
 */
public class PlayfieldVsGridIT {
    private final BlockFeed feed = () -> new Block(new byte[][] {{1,1},{1,1}}) {};
    private OutputStream out;

    @BeforeMethod
    void setUp() {
        out = new ByteArrayOutputStream();
    }

    @Test
    public void nextBlockShouldAppearOnGrid() {
        // given
        Playfield playfield = new Playfield(new Grid(4,4), feed, new Printer(new PrintStream(out)));
        String expected = """
                +----+
                | ## |
                | ## |
                |    |
                |    |
                +----+
                """;
        // when
        playfield.nextBlock();
        // then
        Assert.assertTrue(out.toString().contains(expected));
    }

    @Test(dataProvider = "blockMovementShouldBeRepresentedOnGridDP")
    public void blockMovementShouldBeRepresentedOnGrid(Move move, String expected) {
        // given
        Playfield playfield = new Playfield(new Grid(4,4), feed, new Printer(new PrintStream(out)));
        playfield.nextBlock();
        // when
        playfield.move(move);
        // then
        Assert.assertTrue(out.toString().endsWith(expected));
    }

    @Test(dataProvider = "shouldRemoveCompleteLinesDP")
    public void shouldRemoveCompleteLines(byte[][] actualPrimitive, byte[][] expectedPrimitive) {
        // given
        Grid actual = new Grid(actualPrimitive);
        Grid expected = new Grid(expectedPrimitive);
        Playfield playfield = new Playfield(actual, feed, new Printer(new PrintStream(out)));
        // when
        playfield.removeCompleteLines();
        // then
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "shouldNotRemoveAnyLinesDP")
    public void shouldNotRemoveAnyLines(byte[][] actualPrimitive, byte[][] expectedPrimitive) {
        // given
        Grid actual = new Grid(actualPrimitive);
        Grid expected = new Grid(expectedPrimitive);
        Playfield playfield = new Playfield(actual, feed, new Printer(new PrintStream(out)));
        // when
        playfield.removeCompleteLines();
        // then
        Assert.assertEquals(actual, expected);
    }

    @DataProvider
    Object[][] blockMovementShouldBeRepresentedOnGridDP() {
        return new Object[][] {
            {Move.LEFT, """
                +----+
                |    |
                |##  |
                |##  |
                |    |
                +----+
                """},
            {Move.RIGHT, """
                +----+
                |    |
                |  ##|
                |  ##|
                |    |
                +----+
                """},
            {Move.NONE, """
                +----+
                |    |
                | ## |
                | ## |
                |    |
                +----+
                """},
        };
    }

    @DataProvider
    Object[][] shouldRemoveCompleteLinesDP() {
        return new Object[][] {
            {new byte[][]{{0,0,0,0}, {0,0,0,0}, {1,1,1,1}, {1,1,0,1}},
                    new byte[][]{{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {1,1,0,1}}},
            {new byte[][]{{0,0,0,0}, {0,0,1,1}, {1,1,1,1}, {1,1,1,1}},
                    new byte[][]{{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,1,1}}},
            {new byte[][]{{0,0,0,0}, {1,1,1,1}, {1,0,0,0}, {1,1,1,1}},
                    new byte[][]{{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {1,0,0,0}}},
        };
    }

    @DataProvider
    Object[][] shouldNotRemoveAnyLinesDP() {
        return new Object[][] {
                {new byte[][]{{0,0,0,0}, {0,0,1,1}, {1,0,1,1}, {1,1,0,1}},
                        new byte[][]{{0,0,0,0}, {0,0,1,1}, {1,0,1,1}, {1,1,0,1}}},
                {new byte[][]{{0,0,0,0}, {0,0,1,1}, {0,1,1,0}, {0,0,1,1}},
                        new byte[][]{{0,0,0,0}, {0,0,1,1}, {0,1,1,0}, {0,0,1,1}}},
        };
    }
}
