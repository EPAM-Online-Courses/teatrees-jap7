package com.epam.prejap.teatrees.records;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */

public class RecordCollector {
    private List<Record> records;

    RecordCollector(List<Record> records) {
        if (records == null) throw new IllegalArgumentException();
        this.records = records;
    }

    List<Record> getRecords() {
        return new ArrayList<>(records);
    }

    void update(List<Record> newRecords) {
        if (newRecords != null) {
            records = newRecords;
        }
    }

    @Override
    public String toString() {
        return "RecordCollector{" +
                "records=" + records +
                '}';
    }
}
