package com.epam.prejap.teatrees.records;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class ScoreRecordTest {
    List<Record> records = new ArrayList<>();

    @BeforeMethod
    public void setUp() {
        records.add(new Record("one", 1));
        records.add(new Record("two", 2));
        records.add(new Record("three", 3));
    }

    @Test
    public void recordsShouldBeEqual() {
        RecordCollector scoreRecord = new RecordCollector(records);
        Assert.assertEquals(records, scoreRecord.getRecords());
    }

    @Test
    public void recordsShouldNotBeUpdated() {
        RecordCollector scoreRecord = new RecordCollector(records);
        scoreRecord.update(null);
        Assert.assertEquals(scoreRecord.getRecords(), records);
    }

    @Test
    public void recordsShouldBeUpdated() {
        RecordCollector scoreRecord = new RecordCollector(records);

        List<Record> newRecords = new ArrayList<>(records);
        newRecords.add(new Record("four", 4));

        scoreRecord.update(newRecords);
        Assert.assertNotEquals(scoreRecord.getRecords(), records);
    }

}
