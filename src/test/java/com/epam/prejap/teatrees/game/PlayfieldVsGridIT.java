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

public class PlayfieldVsGridIT {
    private final BlockFeed feed = () -> new Block(new byte[][] {{1,1},{1,1}}) {};
    private OutputStream out;
    private Playfield playfield;

    @BeforeMethod
    void setUp() {
        out = new ByteArrayOutputStream();
        playfield = new Playfield(new Grid(4,4), feed, new Printer(new PrintStream(out)));
    }

    @Test
    public void nextBlockShouldAppearOnGrid() {
        // given
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
        playfield.nextBlock();
        // when
        playfield.move(move);
        // then
        Assert.assertTrue(out.toString().endsWith(expected));
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
}
