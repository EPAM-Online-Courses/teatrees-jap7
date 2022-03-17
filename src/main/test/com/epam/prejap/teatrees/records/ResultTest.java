package com.epam.prejap.teatrees.records;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class ResultTest {

    @Test
    public void conclusionShouldWriteCorrectMessage() {
        PrintStream console = System.out;
        ByteArrayOutputStream bArray = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bArray);
        System.setOut(printStream);

        Result result = new Result();
        result.printFinalMessage( true, 1000, "har");
        System.setOut(console);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(bArray.toString().contains("har"));
        softAssert.assertTrue(bArray.toString().contains("Congrats! It's your new record!"));
        softAssert.assertTrue(bArray.toString().contains("1000"));
    }

    @Test(dataProvider = "provideName")
    public void nameShouldBeSetInConsole(String input, String output) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

        Result result = new Result();
        String actualName = result.getUserName(new Scanner(byteArrayInputStream));

        Assert.assertEquals(actualName, output);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void rotateInEndlessLoopIfNameIsLongerThan_3_Letters() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("LONG_NAME".getBytes(StandardCharsets.UTF_8));

        Result result = new Result();
        String actualName = result.getUserName(new Scanner(byteArrayInputStream));

        Assert.assertEquals(actualName, "LONG_NAME");
    }

    @DataProvider
    public Object[][] provideName() {
        return new Object[][]{
                {"nad", "nad"},
                {"sss", "sss"},
                {"111", "111"},
                {"   ", "   "},
        };
    }


}