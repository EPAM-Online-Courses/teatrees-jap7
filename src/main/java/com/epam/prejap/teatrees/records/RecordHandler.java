package com.epam.prejap.teatrees.records;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
class RecordHandler {
    private final List<Record> recordsList;
    private final JSONParser parser;

    RecordHandler(File jsonFile) {
        this.parser = new JSONParser(jsonFile);
        try {
            parser.uploadDataFromJson();
        } catch (IOException e) {
            System.err.println("Cannot upload data from json");
        }
        this.recordsList = parser.getRecordsList();
    }

    List<Record> getHighScore() {
        Collections.sort(recordsList);
        List<Record> topResults;

        if (recordsList.size() <= 25) {
            topResults = new ArrayList<>(recordsList);
        } else {
            topResults = recordsList.subList(0, 25);
        }
        return topResults;
    }

    private boolean verifyNewRecord(Record newRecord) {
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

    private void updateScore() {
        parser.updateScore(recordsList);
    }

    private void printHighScore(List<Record> topResults) {
        topResults.forEach(System.out::println);
    }

    boolean handleNewRecord(Record record) {
        boolean isNewRecord = verifyNewRecord(record);
        updateScore();
        printHighScore(getHighScore());
        return isNewRecord;
    }
}
