package com.epam.prejap.teatrees.block;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

@Test
public class UBlockTest {

    public void printedBlockShouldHaveTwoRows() {
        // given

        // when
        UBlock uBlock = new UBlock();

        // then
        assertEquals(uBlock.image.length, 2);
    }

    public void printedBlockShouldHaveThreeColumnsInEachRow() {
        // given

        // when
        UBlock uBlock = new UBlock();

        // then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(uBlock.image[0].length, 3);
        softAssert.assertEquals(uBlock.image[1].length, 3);
        softAssert.assertAll();
    }

    public void printedBlockShouldHaveUShape() {
        // given

        // when
        UBlock uBlock = new UBlock();

        // then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(uBlock.dotAt(0, 0), 1);
        softAssert.assertEquals(uBlock.dotAt(0, 1), 0);
        softAssert.assertEquals(uBlock.dotAt(0, 2), 1);
        softAssert.assertEquals(uBlock.dotAt(1, 0), 1);
        softAssert.assertEquals(uBlock.dotAt(1, 1), 1);
        softAssert.assertEquals(uBlock.dotAt(1, 2), 1);
        softAssert.assertAll();
    }
}
