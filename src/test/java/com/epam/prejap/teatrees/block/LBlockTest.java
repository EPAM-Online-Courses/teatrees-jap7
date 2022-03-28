package com.epam.prejap.teatrees.block;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class LBlockTest {

    Block lBlock = new LBlock();

    public void rows_ShouldReturn3() {
        int expected = 3;
        int actual = lBlock.rows();
        assertEquals(actual, expected);
    }

    public void cols_shouldReturn2() {
        int expected = 2;
        int actual = lBlock.cols();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "dotPositions")
    public void dotAtShouldReturnCorrectValue(int row, int col, int expected, String message) {
        int actual = lBlock.dotAt(row, col);
        assertEquals(actual, expected, message);
    }

    @DataProvider
    public static Object[][] dotPositions() {
        return new Object[][]{
                {0, 0, 1, "Incorrect dotAt(0, 0)"},
                {0, 1, 0, "Incorrect dotAt(0, 1)"},
                {1, 0, 1, "Incorrect dotAt(1, 0)"},
                {1, 1, 0, "Incorrect dotAt(1, 1)"},
                {2, 0, 1, "Incorrect dotAt(2, 0)"},
                {2, 1, 1, "Incorrect dotAt(2, 1)"}
        };
    }
}
