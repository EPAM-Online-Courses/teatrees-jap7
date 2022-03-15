package com.epam.prejap.teatrees;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;

/**
 * @author Herman Kulik
 */
public class JsonParser {

    public static void main(String[] args) throws IOException{
        ClassLoader classLoader = JsonParser.class.getClassLoader();
        File jsonPath = new File(classLoader.getResource("score.json").getFile());

        String parsedJSONData = new String(Files.readAllBytes(jsonPath.toPath()));

        ScoreRecord jsonDataContainer = new Gson().fromJson(parsedJSONData,ScoreRecord.class);
        jsonDataContainer.getRecords().forEach(System.out::println);




    }



}
