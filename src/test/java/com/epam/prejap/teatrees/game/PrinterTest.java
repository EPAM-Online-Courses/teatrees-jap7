package com.epam.prejap.teatrees.game;

import com.epam.prejap.teatrees.records.Record;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;



public class PrinterTest {

    @Test
    public void conclusionShouldWriteCorrectMessage() {
        //given
        List<Record> records = new ArrayList<>() {{
            add(new Record("John", 10));
            add(new Record("Sarah", 10));
            add(new Record("Lui", 10));
        }};

        PrintStream console = System.out;
        ByteArrayOutputStream bArray = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bArray);
        System.setOut(printStream);
        Printer printer = new Printer(System.out);
        //when
        printer.printFinalMessage(records, true, 1000, "har");
        System.setOut(console);
        //then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(bArray.toString().contains("har"));
        softAssert.assertTrue(bArray.toString().contains("Congrats! It's your new record har!"));
        softAssert.assertTrue(bArray.toString().contains("1000"));
        softAssert.assertAll();

    }

    @Test(dataProvider = "provideName")
    public void nameShouldBeSetInConsole(String input, String output) {
        //given
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Printer printer = new Printer(System.out);
        //when
        String actualName = printer.askForUserName(new Scanner(byteArrayInputStream));
        //then
        Assert.assertEquals(actualName, output);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void rotateInEndlessLoopIfNameIsLongerThan_3_Letters() {
        //given
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("LONG_NAME".getBytes(StandardCharsets.UTF_8));
        Printer printer = new Printer(System.out);
        //when
        String actualName = printer.askForUserName(new Scanner(byteArrayInputStream));
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