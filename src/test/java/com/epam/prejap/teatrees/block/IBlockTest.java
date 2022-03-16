package com.epam.prejap.teatrees.block;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @author Marta Krawczykowska, Krzysztof Dolanski
 */
public class IBlockTest {


    @Test
    public void imageShouldBeTheSameAsGivenGeometricalFigure(){

        // Given
        Block iBlock = new IBlock();
        // When
        int i0j0 = 0;
        int i1j0 = 0;
        int i2j0 = 0;
        int i3j0 = 0;
        int i0j1 = 1;
        int i1j1 = 1;
        int i2j1 = 1;
        int i3j1 = 1;
        byte b = iBlock.dotAt(0, 0);
        byte b1 = iBlock.dotAt(1, 0);
        byte b2 = iBlock.dotAt(2, 0);
        byte b3 = iBlock.dotAt(3, 0);
        byte b4 = iBlock.dotAt(0, 1);
        byte b5 = iBlock.dotAt(1, 1);
        byte b6 = iBlock.dotAt(2, 1);
        byte b7 = iBlock.dotAt(3, 1);
        // Then
        SoftAssert sf = new SoftAssert();
        sf.assertEquals(i0j0, b);
        sf.assertEquals(i1j0, b1);
        sf.assertEquals(i2j0, b2);
        sf.assertEquals(i3j0, b3);
        sf.assertEquals(i0j1, b4);
        sf.assertEquals(i1j1, b5);
        sf.assertEquals(i2j1, b6);
        sf.assertEquals(i3j1, b7);
        sf.assertAll();

    }
}
