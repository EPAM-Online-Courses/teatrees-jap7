package com.epam.prejap.teatrees.records;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
class RecordHandler {
    private final List<Record> recordsList;

    RecordHandler(List<Record> recordsList) {
        this.recordsList = recordsList;
    }

    /**
     * @param newGameScore
     * @return true if score is a new record
     */
    boolean addNewScore(Record newGameScore) {
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

    List<Record> getRecordsList() {
        return new ArrayList<>(recordsList);
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
