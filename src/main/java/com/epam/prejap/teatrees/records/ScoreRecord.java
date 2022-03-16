package com.epam.prejap.teatrees.records;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */

class ScoreRecord {
    private List<Record> records;

    List<Record> getRecords() {
        return new ArrayList<>(records);
    }

    void update(List<Record> newRecords) {
        if (records != null) {
            records = newRecords;
        }
    }

    ScoreRecord(List<Record> records) {
        if (records != null)
            this.records = records;
    }
}
