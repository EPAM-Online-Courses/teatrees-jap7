package com.epam.prejap.teatrees.records;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
class JSONParser {
    private List<Record> recordsList;
    private ScoreRecord scoreRecord;
    private File jsonFile;

    JSONParser(File fileToParse) {
        jsonFile = fileToParse;
    }


    List<Record> getRecordsList() {
        return new ArrayList<>(recordsList);
    }

    void uploadDataFromJson() throws IOException {
        String parsedJSONData = new String(Files.readAllBytes(jsonFile.toPath()));
        scoreRecord = new Gson().fromJson(parsedJSONData, ScoreRecord.class);
        recordsList = scoreRecord.getRecords();
    }

    void addNewRecordsToJSON(ScoreRecord scoreRecord) {
        if (scoreRecord == null) {
            throw new IllegalArgumentException("Score is incorrect");
        }
        try (FileWriter writer = new FileWriter(jsonFile);) {
            Gson gson = new Gson();
            gson.toJson(scoreRecord, writer);
        } catch (IOException e) {
            System.err.println("Cannot write new data to score.json");
        }
    }

    void updateScore(List<Record> record) {
        scoreRecord.update(record);
        updateJsonFile();
    }

    private void updateJsonFile() {
        clear();
        addNewRecordsToJSON(scoreRecord);
    }

    void clear() {
        try (FileWriter writer = new FileWriter(jsonFile);) {
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Cannot clear score.json file"); // TODO logging
        }
    }

}
