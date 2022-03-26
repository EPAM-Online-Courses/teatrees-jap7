package com.epam.prejap.teatrees.records;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
class RecordHandler {
    private List<Record> recordsList;
    private final JSONParser parser;

    RecordHandler() {
        this.parser = new JSONParser();
    }

    boolean handleNewRecord(Record newGameScore) throws IOException {
        uploadJsonData();
        boolean isNewRecord = verifyScore(newGameScore);
        deployJsonData();

        return isNewRecord;
    }

    void uploadJsonData() throws IOException {
        try {
            recordsList = parser.uploadDataFromExternalJson();
        } catch (IOException e) {
            recordsList = parser.uploadDataFromInternalJson();
        }
    }

    void deployJsonData() {
        parser.updateScores(recordsList);

    }

    private boolean verifyScore(Record newGameScore) {
        if (recordsList.contains(newGameScore)) {
            int indexOfOld = recordsList.indexOf(newGameScore);
            Record oldGameScore = recordsList.get(indexOfOld);
            if (newGameScore.getScore() > oldGameScore.getScore()) {
                recordsList.set(indexOfOld, newGameScore);
                return true;
            }
        } else {
            recordsList.add(newGameScore);
        }
        return false;
    }

    List<Record> getHighScore() {
        Collections.sort(recordsList);
        List<Record> topScores;

        if (recordsList.size() <= 25) {
            topScores = new ArrayList<>(recordsList);
        } else {
            topScores = new ArrayList<>(recordsList.subList(0, 25));
        }
        return topScores;
    }

}
