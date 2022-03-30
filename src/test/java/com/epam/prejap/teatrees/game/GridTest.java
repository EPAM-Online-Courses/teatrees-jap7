package com.epam.prejap.teatrees.game;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.testng.Assert.*;

/**
 * @author Dominik Kaminski
 * @author Przemyslaw Szewczyk
 */
public class GridTest {

    @Test(dataProvider = "gridWithCompleteLinesProvider")
    public void shouldRemoveCompleteLines(Grid grid, Grid expectedGrid)
    {
        // given
        // when
        grid.removeCompleteLines();
        // then
        assertEquals(grid, expectedGrid);
    }

    @Test(dataProvider = "gridWithoutCompleteLinesProvider")
    public void shouldNotRemoveAnyLines(Grid grid, Grid expectedGrid)
    {
        // given
        // when
        grid.removeCompleteLines();
        // then
        assertEquals(grid, expectedGrid);
    }

    @Test
    public void shouldPrintProperly() {
        // given
        OutputStream out = new ByteArrayOutputStream();
        Grid grid = new Grid(new byte[][] {{0,0,0}, {1,1,0}, {1,1,0}});
        String expected = """
                +---+
                |   |
                |## |
                |## |
                +---+
                """;
        // when
        grid.print(new Printer(new PrintStream(out)));
        // then
        Assert.assertTrue(out.toString().endsWith(expected));
    }

    @DataProvider
    public Object[][] gridWithCompleteLinesProvider()
    {
        return new Object[][] {
                {new Grid(new byte[][] {{0,0}, {1,1}}), new Grid(new byte[][] {{0,0}, {0,0}})},
                {new Grid(new byte[][] {{0,0,0},{1,0,1},{1,1,1}}), new Grid(new byte[][] {{0,0,0},{0,0,0},{1,0,1}})},
                {new Grid(new byte[][] {{0,0,0},{1,1,1},{1,0,1}}), new Grid(new byte[][] {{0,0,0},{0,0,0},{1,0,1}})},
                {new Grid(new byte[][] {{0,1,0},{1,1,1},{1,1,1}}), new Grid(new byte[][] {{0,0,0},{0,0,0},{0,1,0}})},
                {new Grid(new byte[][] {{1,1,1},{1,0,1},{1,1,1}}), new Grid(new byte[][] {{0,0,0},{0,0,0},{1,0,1}})},
        };
    }

    @DataProvider
    public Object[][] gridWithoutCompleteLinesProvider()
    {
        return new Object[][] {
                {new Grid(new byte[][] {{0,0}, {1,0}}), new Grid(new byte[][] {{0,0}, {1,0}})},
                {new Grid(new byte[][] {{0,0,0},{1,0,1},{0,1,1}}), new Grid(new byte[][] {{0,0,0},{1,0,1},{0,1,1}})},
                {new Grid(new byte[][] {{0,0,0},{0,0,1},{1,0,1}}), new Grid(new byte[][] {{0,0,0},{0,0,1},{1,0,1}})},
        };
    }
}
