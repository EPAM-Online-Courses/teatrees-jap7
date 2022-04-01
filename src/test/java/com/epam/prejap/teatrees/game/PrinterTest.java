package com.epam.prejap.teatrees.game;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrinterTest {
    private static PrintStream capturedPrintStream;
    private static ByteArrayOutputStream output;

    @BeforeMethod
    private void setupStreams() {
        output = new ByteArrayOutputStream();
        capturedPrintStream = new PrintStream(output);
    }

    @Test
    public void shouldPrintWelcomeMsg() {
        // given
        Printer printer = new Printer(capturedPrintStream);
        String expected = """
                _|          _|              _|                                                   \s
                _|          _|     _|_|     _|     _|_|_|     _|_|     _|_|_|  _|_|       _|_|   \s
                _|    _|    _|   _|_|_|_|   _|   _|         _|    _|   _|    _|    _|   _|_|_|_| \s
                  _|  _|  _|     _|         _|   _|         _|    _|   _|    _|    _|   _|       \s
                    _|  _|         _|_|_|   _|     _|_|_|     _|_|     _|    _|    _|     _|_|_| \s
                \n""";
        // when
        printer.printWelcomeMsg();
        // then
        Assert.assertEquals(output.toString(), expected);
    }

    @Test(dependsOnMethods = "shouldPrintWelcomeMsg")
    public void shouldDisplayWelcomeMsgForMin3Sec() {
        // given
        Printer printer = new Printer(capturedPrintStream);
        // when
        long start = System.currentTimeMillis();
        printer.printWelcomeMsg();
        long finnish = System.currentTimeMillis();
        long timeElapsed = finnish - start;
        // then
        Assert.assertTrue(timeElapsed >= 3000);
    }
}
