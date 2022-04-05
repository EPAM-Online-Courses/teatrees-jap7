package com.epam.prejap.teatrees.records;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Herman Kulik
 */
@Test
public class RecordHandlerTest {

    @Test
    public void newScoreShouldBeAdded() {
        SoftAssert softAssert = new SoftAssert();
        List<Record> scores = new ArrayList<>() {{
            add(new Record("John", 10));
            add(new Record("Sarah", 10));
            add(new Record("Lui", 10));
        }};
        RecordHandler handler = new RecordHandler(scores);
        Record score = new Record("Ben", 10);
        handler.addNewScore(score);
        softAssert.assertTrue(handler.getRecordsList().contains(score));
        softAssert.assertTrue(handler.getRecordsList().size() == 4);
        softAssert.assertAll();
    }

    @Test
    public void newScoreShouldBeUpdated() {
        SoftAssert softAssert = new SoftAssert();
        List<Record> scores = new ArrayList<>() {{
            add(new Record("John", 10));
            add(new Record("Sarah", 10));
            add(new Record("Lui", 10));
        }};
        RecordHandler handler = new RecordHandler(scores);
        Record score = new Record("Lui", 100);
        handler.addNewScore(score);
        softAssert.assertTrue(scores.contains(score));
        softAssert.assertTrue(scores.size() == 3);
        softAssert.assertTrue(scores.toString().contains("<<<player: Lui, top score: 100>>>"));
        softAssert.assertAll();
    }

    @Test
    public void newScoreShouldNotBeAdded() {
        SoftAssert softAssert = new SoftAssert();
        List<Record> scores = new ArrayList<>() {{
            add(new Record("John", 10));
            add(new Record("Sarah", 10));
            add(new Record("Lui", 10));
        }};
        RecordHandler handler = new RecordHandler(scores);
        Record score = new Record("Lui", 1);
        handler.addNewScore(score);

        softAssert.assertTrue(scores.size() == 3);
        softAssert.assertFalse(scores.toString().contains("<<<player: Lui, top score: 1>>>"));
        softAssert.assertAll();
    }

    @Test
    public void shouldReturn_25_TopScores() {
        List<Record> scores = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            scores.add(new Record("" + i, i));
        }
        RecordHandler handler = new RecordHandler(scores);
        Assert.assertEquals(handler.getHighScore().size(), 25);
    }

    public void shouldReturnLessThan_25_Scores() {
        List<Record> scores = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            scores.add(new Record("" + i, i));
        }
        RecordHandler handler = new RecordHandler(scores);
        Assert.assertEquals(handler.getHighScore().size(), scores.size());
    }

}
