package com.epam.prejap.teatrees.records;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
@Test
public class RecordHandlerIT {

    @Test
    public void recordShouldBeVerifiedAsNewRecord() {
        try {
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

    @Test
    public void recordShouldNotBeVerifiedAsNewRecord() {
        try {
            Record newRecord = new Record("reh", 10);
            Path tmp = Files.createTempFile("temp2", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println("{'records': [{'name': 'reh', 'score': 12}]}");

            RecordHandler recordHandler = new RecordHandler(tmp.toFile());
            Assert.assertFalse(recordHandler.handleNewRecord(newRecord));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void newRecordShouldBeWrittenToFile() {
        try {
            Record newRecord = new Record("one", 1000);

            Path tmp = Files.createTempFile("temp2", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println("{'records': [{'name': 'one', 'score': 1}]}");

            RecordHandler recordHandler = new RecordHandler(tmp.toFile());
            recordHandler.handleNewRecord(newRecord);
            List<String> allLines = Files.readAllLines(tmp);

            Assert.assertTrue(allLines.get(0).contains("{\"records\":[{\"name\":\"one\",\"score\":1000}]}"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void LackOfNewRecordShouldNotBeWrittenToFile() {
        try {
            Record notANewRecord = new Record("one", 1);

            Path tmp = Files.createTempFile("temp2", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println("{'records': [{'name': 'one', 'score': 1000}]}");

            RecordHandler recordHandler = new RecordHandler(tmp.toFile());
            recordHandler.handleNewRecord(notANewRecord);
            List<String> allLines = Files.readAllLines(tmp);

            Assert.assertFalse(allLines.get(0).contains("{\"records\":[{\"name\":\"one\",\"score\":1}]}"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newRecordShouldBeAddedIfMissing() {
        try {
            Record freshScore = new Record("one", 1);

            Path tmp = Files.createTempFile("tempus", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println("{'records': [{'name': 'two', 'score': 1}]}");

            RecordHandler recordHandler = new RecordHandler(tmp.toFile());
            recordHandler.handleNewRecord(freshScore);
            List<String> allLines = Files.readAllLines(tmp);

            Assert.assertTrue(allLines.get(0).contains("{\"name\":\"one\",\"score\":1}"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void numberOfTopResultsIsLessThanTwentyFiveWithLessThanTwentyFiveRecords() {
        try {
            Path tmp = Files.createTempFile("tempusix", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println("{'records': [{'name': 'one', 'score': 1},{'name': 'two', 'score': 1},{'name': 'three', 'score': 1},{'name': 'four', 'score': 1}]}");
            RecordHandler recordHandler = new RecordHandler(tmp.toFile());
            Assert.assertTrue(recordHandler.getHighScore().size() <= 25);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void numberOfTopResultsIsEqualTwentyFiveWhenMoreThanTwentyFiveRecords() {
        try {
            Path tmp = Files.createTempFile("tempusix2", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            String recordList = "{'records': [";
            for (int i = 0; i < 28; i++) {
                recordList += ("{'name': 'one', 'score': 1},");
            }
            recordList = recordList.substring(0, recordList.length() - 1);
            recordList += "]}";
            ps.println(recordList);

            RecordHandler recordHandler = new RecordHandler(tmp.toFile());
            Assert.assertTrue(recordHandler.getHighScore().size() == 25);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
