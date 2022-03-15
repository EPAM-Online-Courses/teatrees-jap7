package com.epam.prejap.teatrees.scores;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Herman Kulik
 */
public class JSONParser {
    private final File jsonFile;
    private List<Record> recordsList;

    public JSONParser() {
        jsonFile = getJsonPath();
        try {
            uploadDataFromJson(jsonFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Record> getRecordsList() {
        return new ArrayList<>(recordsList);
    }

    private File getJsonPath() {
        ClassLoader classLoader = JSONParser.class.getClassLoader();
        return new File(classLoader.getResource("score.json").getFile());
    }

    private void uploadDataFromJson(File jsonFile) throws IOException {
        String parsedJSONData = new String(Files.readAllBytes(jsonFile.toPath()));

        ScoreRecord jsonDataContainer = new Gson().fromJson(parsedJSONData, ScoreRecord.class);
        recordsList = jsonDataContainer.getRecords();
    }







}
