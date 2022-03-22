package com.epam.prejap.teatrees.block;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Marta Krawczykowska, Krzysztof Dolanski
 */
public class IBlockTest {


    @DataProvider
    Object[][] data() {
        return new Object[][]{{0, 0, 0}, {0, 1, 0}, {0, 2, 0}, {0, 3, 0}, {1, 0, 1}, {1, 1, 1}, {1, 2, 1}, {1, 3, 1}};
    }

    @Test(dataProvider = "data")
    public void imageShouldBeTheSameAsGivenGeometricalFigure(int dot, int first, int second) {

        // Given
        Block iBlock = new IBlock();
        // When
        byte b = iBlock.dotAt(first, second);
        // Then
        assertEquals(dot, b);
    }
}
