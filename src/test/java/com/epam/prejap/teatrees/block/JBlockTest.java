package com.epam.prejap.teatrees.block;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Marta Krawczykowska, Krzysztof Dolanski
 */
public class JBlockTest {


    @DataProvider
    Object[][] data() {
        return new Object[][]{
                {0, 0, 0},
                {0, 1, 0},
                {1, 2, 0},
                {1, 0, 1},
                {1, 1, 1},
                {1, 2, 1},
        };
    }

    @Test(dataProvider = "data")
    public void imageShouldBeTheSameAsGivenGeometricalFigure(int dot, int first, int second) {

        // Given
        Block jBlock = new JBlock();
        // When
        byte b = jBlock.dotAt(first, second);
        // Then
        assertEquals(dot, b);
    }
}
