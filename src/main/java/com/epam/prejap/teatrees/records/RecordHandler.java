package com.epam.prejap.teatrees.records;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
        try {
            parser.uploadDataFromExternalJson(); //TODO szuka zewnetrzny plik
            recordsList = parser.getRecordsList();
        } catch (IOException e) {
            try {
                String jsonInString = searchInsideJar();
                recordsList = parseFromJsonString(jsonInString);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.err.println("Cannot upload data from json");// TODO to szuka w jarze
        }
    }

    private List<Record> parseFromJsonString(String jsonInString) {
        Gson g = new Gson();
        RecordCollector recordCollector = g.fromJson(jsonInString, RecordCollector.class);
        return recordCollector.getRecords();
    }


    private String searchInsideJar() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("score.json");
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + "score.json");
        }
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
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
