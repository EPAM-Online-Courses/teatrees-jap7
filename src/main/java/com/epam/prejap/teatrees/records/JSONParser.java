package com.epam.prejap.teatrees.records;

import com.google.gson.Gson;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for parsing .json files into JSONObjects, used to operate on users' scores.
 * Uploads data from .json into a list, updates the list if required, stores updated list
 * into .json file, located in resources directory
 *
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
class JSONParser {
    private File externalFile;
    private List<Record> recordsList;
    private RecordCollector recordCollector;

    JSONParser() {
        try {
            File file = new File(JSONParser.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            externalFile = new File(file.getParent() + "/external.json");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    List<Record> getRecordsList() {
        return new ArrayList<>(recordsList);
    }

    void uploadDataFromExternalJson() throws IOException {// tworzy tylko tu recordCollector
        String parsedJSONData = new String(Files.readAllBytes(externalFile.toPath()));
        recordCollector = new Gson().fromJson(parsedJSONData, RecordCollector.class);
        recordsList = recordCollector.getRecords();
    }

    void addNewRecordsToJSON(RecordCollector recordCollector) {
        if (recordCollector == null) {
            throw new IllegalArgumentException("Score is incorrect");
        }
        try (FileWriter writer = new FileWriter(externalFile)) {
            Gson gson = new Gson();
            gson.toJson(recordCollector, writer);
        } catch (IOException e) {
            System.err.println("Cannot write new data to score.json");
        }
    }

    void updateScore(List<Record> record) {
        if(recordCollector == null){
            recordCollector = new RecordCollector(new ArrayList<>());
        }
        recordCollector.update(record);
        updateJsonFile();
    }

    private void updateJsonFile() { //TODO when updating file, should save in external file
        clear();
        addNewRecordsToJSON(recordCollector);
    }

    void clear() {
        try {
            if (!externalFile.exists()) {
                externalFile.createNewFile();
            } else {
                FileWriter writer = new FileWriter(externalFile);
                writer.write("");
                writer.flush();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
