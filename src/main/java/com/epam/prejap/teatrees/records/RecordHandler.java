package com.epam.prejap.teatrees.records;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
class RecordHandler {
    private final File jsonFile = new File(Objects.requireNonNull(RecordHandler.class.getClassLoader().getResource("score.json")).getFile());
    private final List<Record> recordsList;
    private final JSONParser parser;

    RecordHandler() {
        this.parser = new JSONParser(jsonFile);
        this.recordsList = parser.getRecordsList();
    }

    private List<Record> getHighScore() {
        Collections.sort(recordsList);
        List<Record> topResults;

        if (recordsList.size() <= 25) {
            topResults = new ArrayList<>(recordsList);
        } else {
            topResults = recordsList.subList(0, 25);
        }
        return topResults;
    }

    private boolean verifyNewRecord(Record newRecord) { // verifies new record, returns true if you beat a record
        if (recordsList.contains(newRecord)) {
            Record oldRecord = recordsList.get(recordsList.indexOf(newRecord));
            if (newRecord.getScore() > oldRecord.getScore()) {
                recordsList.remove(oldRecord);
                recordsList.add(newRecord);
                return true;
            }
        } else {
            recordsList.add(newRecord);
        }
        return false;
    }

    private void updateStore() {
        parser.updateStore(recordsList);
    }

    private void printHighScore(List<Record> topResults) {
        topResults.forEach(System.out::println);
    }

    boolean handleNewRecord(Record record) {
        boolean isNewRecord = verifyNewRecord(record);
        updateStore();
        printHighScore(getHighScore());
        return isNewRecord;
    }
}
