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
    private RecordCollector recordCollector;
    private File jsonFile;

    JSONParser(File fileToParse) {
        jsonFile = fileToParse;
    }


    List<Record> getRecordsList() {
        return new ArrayList<>(recordsList);
    }

    void uploadDataFromJson() throws IOException {
        String parsedJSONData = new String(Files.readAllBytes(jsonFile.toPath()));
        recordCollector = new Gson().fromJson(parsedJSONData, RecordCollector.class);
        recordsList = recordCollector.getRecords();
    }

    void addNewRecordsToJSON(RecordCollector recordCollector) {
        if (recordCollector == null) {
            throw new IllegalArgumentException("Score is incorrect");
        }
        try (FileWriter writer = new FileWriter(jsonFile);) {
            Gson gson = new Gson();
            gson.toJson(recordCollector, writer);
        } catch (IOException e) {
            System.err.println("Cannot write new data to score.json");
        }
    }

    void updateScore(List<Record> record) {
        recordCollector.update(record);
        updateJsonFile();
    }

    private void updateJsonFile() {
        clear();
        addNewRecordsToJSON(recordCollector);
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
