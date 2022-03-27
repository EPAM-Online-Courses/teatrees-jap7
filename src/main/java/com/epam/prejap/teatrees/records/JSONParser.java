package com.epam.prejap.teatrees.records;

import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private final Path externalFile;

    JSONParser(Path externalFile) {
        this.externalFile = externalFile;
    }

    List<Record> uploadJsonData() throws IOException {
        List<Record> recordsList;
        try {
            recordsList = uploadDataFromExternalJson();
        } catch (IOException e) {
            recordsList = uploadDataFromInternalJson();
        }
        return recordsList;
    }

    private List<Record> uploadDataFromExternalJson() throws IOException {
        String parsedJSONData = new String(Files.readAllBytes(externalFile));
        RecordCollector recordCollector = new Gson().fromJson(parsedJSONData, RecordCollector.class);
        return recordCollector.getRecords();

    }

    private List<Record> uploadDataFromInternalJson() throws IOException {
        String jsonInString = searchInsideJar();
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

    void updateScores(List<Record> scores) {
        RecordCollector recordCollector = new RecordCollector(scores);
        clearExternalJsonFile();
        addNewRecordsToJSON(recordCollector);
    }

    void clearExternalJsonFile() {
        try (FileWriter writer = new FileWriter(externalFile.toFile())) {
            if (!Files.exists(externalFile)) {
                Files.createFile(externalFile);
            } else {
                writer.write("");
                writer.flush();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    void addNewRecordsToJSON(RecordCollector recordCollector) {
        try (FileWriter writer = new FileWriter(externalFile.toFile())) {
            Gson gson = new Gson();
            gson.toJson(recordCollector, writer);
        } catch (IOException e) {
            System.err.println("Cannot write new data to score.json");
        }
    }

}
