package com.epam.prejap.teatrees.records;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Herman Kulik
 * @author Andrzej Sadlek
 */
public class RecordHandlerTest {

    @Test
    public void recordShouldBeVerified() {
        try{
        Record newRecord = new Record("reh", 16);
        Path tmp = Files.createTempFile("temp2", ".json");
        PrintStream ps = new PrintStream(tmp.toString());
        ps.println("{'records': [{'name': 'reh', 'score': 12}]}");

        RecordHandler recordHandler = new RecordHandler(tmp.toFile());
        Assert.assertTrue(recordHandler.handleNewRecord(newRecord));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}