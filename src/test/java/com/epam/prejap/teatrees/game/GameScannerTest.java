package com.epam.prejap.teatrees.game;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Herman Kulik
 */
public class GameScannerTest {

    @Test(dataProvider = "provideName")
    public void nameShouldBeSetInConsole(String input, String output) {
        //given
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        GameScanner scanner = new GameScanner(System.out);
        //when
        String actualName = scanner.askForUserName(new Scanner(byteArrayInputStream));
        //then
        Assert.assertEquals(actualName, output);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void rotateInEndlessLoopIfNameIsLongerThan_3_Letters() {
        //given
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("LONG_NAME".getBytes(StandardCharsets.UTF_8));
        GameScanner scanner = new GameScanner(System.out);
        //when
        String actualName = scanner.askForUserName(new Scanner(byteArrayInputStream));
        //then
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
