package com.epam.prejap.teatrees.records;

import org.testng.annotations.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.*;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class RecordTest {

    @Test
    public void recordsShouldBeEqual() {
        //given
        Record first = new Record("one", 12397);
        Record second = new Record("one", 2);
        //when
        //then
        assertEquals(second, first, "Objects are different");
    }

    @Test
    public void recordsShouldNotBeEqual() {
        //given
        Record first = new Record("two", 2);
        Record second = new Record("one", 2);
        //when
        //then
        assertNotEquals(first, second, "Objects are the same");
    }

    @Test
    public void recordShouldBePrintedCorrectly() {
        //given
        PrintStream console = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        Record record = new Record("one", 1);
        //when
        System.setOut(console);
        //then
        assertEquals(outputStream.toString(), "<<<player: one, top score: 1>>>\n", "Output is not the same");
    }

}