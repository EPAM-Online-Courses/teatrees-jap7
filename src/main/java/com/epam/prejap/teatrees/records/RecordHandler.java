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
public class RecordHandler {
    private final File jsonFile = new File(Objects.requireNonNull(RecordHandler.class.getClassLoader().getResource("score.json")).getFile());
    private final List<Record> recordsList;
    private final JSONParser parser;

    public RecordHandler() {
        this.parser = new JSONParser(jsonFile);
        this.recordsList = parser.getRecordsList();
    }

    public List<Record> getHighScore() {
        Collections.sort(recordsList);
        List<Record> topResults;

        if (recordsList.size() <= 25) {
            topResults = new ArrayList<>(recordsList);
        } else {
            topResults = recordsList.subList(0, 25);
        }

        topResults.forEach(System.out::println); //TODO remove
        return topResults;
    }

    public boolean verifyNewRecord(Record newRecord) { // verifies new record, returns true if you beat a record
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

    public void updateStore() {
        parser.updateStore(recordsList);
    }

    public boolean handleNewRecord(Record record) {
        //dodajemy nowy record do list
        boolean result = verifyNewRecord(record);
        //dodaje nowy record do bazy Store
        updateStore();
        // pokazuje odswiezone 25 rekordow
        getHighScore();

        return result;
    }
}
