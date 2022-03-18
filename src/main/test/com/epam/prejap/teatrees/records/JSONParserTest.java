package com.epam.prejap.teatrees.records;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class JSONParserTest {

    @Test
    public void fileShouldBeCleared() {
        try {
            //given
            Path tmp = Files.createTempFile("temp", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println(1234);
            JSONParser parser = new JSONParser(tmp.toFile());
            //when
            parser.clear();
            //then
            Assert.assertEquals(Files.size(tmp), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dataShouldBeUploadedFromJsonFile() {
        try {
            //given
            Path tmp = Files.createTempFile("temp2", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println("{'records': [{'name': 'her', 'score': 3}]}");
            JSONParser jsonParser = new JSONParser(tmp.toFile());
            //when
            jsonParser.uploadDataFromJson();
            //then
            Assert.assertTrue(jsonParser.getRecordsList().get(0).getName().equals("her") && jsonParser.getRecordsList().get(0).getScore() == 3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expectedExceptions = com.google.gson.JsonSyntaxException.class)
    public void dataShouldNotBeUploadedFromJsonFile() {
        try {
            //given
            Path tmp = Files.createTempFile("temp2", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println("Sample Text");
            JSONParser jsonParser = new JSONParser(tmp.toFile());
            //when
            jsonParser.uploadDataFromJson();
            //then
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newRecordShouldBeAddedToJSON() {
        try {
            //given
            Record record = new Record("her", 3);
            List<Record> records = new ArrayList<>();
            records.add(record);
            RecordCollector scoreRecord = new RecordCollector(records);
            Path tmp = Files.createTempFile("temp3", ".json");
            JSONParser jsonParser = new JSONParser(tmp.toFile());
            //when
            jsonParser.addNewRecordsToJSON(scoreRecord);
            String content = Files.readString(tmp, StandardCharsets.US_ASCII);
            //then
            Assert.assertEquals(content, "{\"records\":[{\"name\":\"her\",\"score\":3}]}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void nullShouldNotBeAddedToJSONAndThrowsException() {
        try {
            //given
            Path tmp = Files.createTempFile("temp3", ".json");
            JSONParser jsonParser = new JSONParser(tmp.toFile());
            //when
            jsonParser.addNewRecordsToJSON(null);
            String content = Files.readString(tmp, StandardCharsets.US_ASCII);
            //then
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO newRecordShouldNotBeAddedToJSON()
}
