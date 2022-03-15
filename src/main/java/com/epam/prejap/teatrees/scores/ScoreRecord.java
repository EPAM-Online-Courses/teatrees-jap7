package com.epam.prejap.teatrees.scores;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Herman Kulik
 */

public class ScoreRecord {
    private List<Record> records;

    public List<Record> getRecords() {
        return new ArrayList<>(records);
    }

    public ScoreRecord(List<Record> records) {
        this.records = records;
    }
}
