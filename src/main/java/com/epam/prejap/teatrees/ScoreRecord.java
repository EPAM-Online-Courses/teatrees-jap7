package com.epam.prejap.teatrees;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Herman Kulik
 */

public class ScoreRecord {
    private List<Records> records = new ArrayList<>();

    public List<Records> getRecords() {
        return records;
    }

    public ScoreRecord(List<Records> records) {
        this.records = records;
    }
}
