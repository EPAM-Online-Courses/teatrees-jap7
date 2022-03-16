package com.epam.prejap.teatrees.scores;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class JSONParser {
    private List<Record> recordsList;
    private ScoreRecord storeRecord;
    File jsonFile;

    public JSONParser(File fileToParse) {
        jsonFile = fileToParse;
        try {
            uploadDataFromJson();
        } catch (IOException e) {
            System.err.println("cannot upload from score.json" + e.getMessage());
        }
    }


    public List<Record> getRecordsList() {
        return new ArrayList<>(recordsList);
    }

    private void uploadDataFromJson() throws IOException {
        String parsedJSONData = new String(Files.readAllBytes(jsonFile.toPath()));
        storeRecord = new Gson().fromJson(parsedJSONData, ScoreRecord.class);
        recordsList = storeRecord.getRecords();
    }

    private void addNewRecordsToJSON(ScoreRecord scoreRecord){
        try(FileWriter writer = new FileWriter(jsonFile);){
            Gson gson = new Gson();
            gson.toJson(scoreRecord, writer);
        } catch (IOException e) {
            System.err.println("Cannot write new data to score.json");
        }

    }

    void updateStore(List<Record> record){
        storeRecord.update(record);
    }

    void updateJsonFile() {
        clear();
        addNewRecordsToJSON(storeRecord);
    }

    private void clear() {

        try (FileWriter writer = new FileWriter(jsonFile);){
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Cannot clear score.json file");
        }
    }

}
