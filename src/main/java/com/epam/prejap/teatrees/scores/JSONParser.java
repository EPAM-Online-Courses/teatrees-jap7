package com.epam.prejap.teatrees.scores;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class JSONParser {
    private List<Record> recordsList;
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
        ScoreRecord jsonDataContainer = new Gson().fromJson(parsedJSONData, ScoreRecord.class);
        recordsList = jsonDataContainer.getRecords();
    }

    void updateJsonFile(ScoreRecord scoreRecord) {
        clear();
        addNewRecordsToJSON(scoreRecord);
    }

    private void addNewRecordsToJSON(ScoreRecord scoreRecord){
        try(FileWriter writer = new FileWriter(jsonFile);){
            Gson gson = new Gson();
            gson.toJson(scoreRecord, writer);
        } catch (IOException e) {
            System.err.println("Cannot write new data to score.json");
        }

    }

    private void clear() {

        try (FileWriter writer = new FileWriter(jsonFile);){
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Cannot clear score.json file");
        }
    }

    public static void main(String[] args) {
//        File jsonFile = new File(Objects.requireNonNull(RecordHandler.class.getClassLoader().getResource("score.json")).getFile());
//        JSONParser parser = new JSONParser(jsonFile);
//        parser.updateJsonFile(new ArrayList<>());
    }

}
