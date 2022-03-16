package com.epam.prejap.teatrees.records;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */

public class ScoreRecord {
    private List<Record> records;

    public List<Record> getRecords() {
        return new ArrayList<>(records);
    }

    public void update(List<Record> newRecords) {
        if (records != null){
            records = newRecords;
        }
    }

    public ScoreRecord(List<Record> records) {
        this.records = records;
    }
}
