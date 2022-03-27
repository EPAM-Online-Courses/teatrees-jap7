package com.epam.prejap.teatrees.records;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author Herman Kulik
 */
@Test
public class JSONParserTest {

    public void shouldUploadDataFromExternalFile() {
        Path tmp = null;
        try {
            tmp = Files.createTempFile("temp", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println("{'records': [{'name': 'her', 'score': 3}]}");
            JSONParser parser = new JSONParser(tmp);
            List<Record> records = parser.uploadJsonData();
            Assert.assertEquals(records.toString(), "[<<<player: her, top score: 3>>>]");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void exceptionShouldBeThrownIfNotJsonExtension(){
        Path path = Path.of("/tmp/new.jsn");
        new JSONParser(path);
    }

    public void shouldUploadDataFromInternalFile() {
        try {
            Path wrongExternal = Path.of("/tmp/nonexisting.json");
            JSONParser parser = new JSONParser(wrongExternal);
            List<Record> records = parser.uploadJsonData();
            assertEquals(records.toString(), "[<<<player: zum, top score: 2>>>, <<<player: bum, top score: 1>>>, <<<player: ang, top score: 1>>>, <<<player: she, top score: 0>>>, <<<player: gif, top score: 0>>>, <<<player: ouy, top score: 0>>>, <<<player: huh, top score: 0>>>, <<<player: kul, top score: 0>>>, <<<player: sad, top score: 2>>>, <<<player: ken, top score: 3>>>, <<<player: ben, top score: 10>>>, <<<player: myl, top score: 3>>>, <<<player: got, top score: 2>>>, <<<player: hel, top score: 2>>>, <<<player: geg, top score: 2>>>, <<<player: zun, top score: 1>>>, <<<player: luk, top score: 1>>>, <<<player: chi, top score: 1>>>, <<<player: usa, top score: 1>>>, <<<player: ser, top score: 3>>>, <<<player: ham, top score: 3>>>, <<<player: 666, top score: 3>>>, <<<player: pol, top score: 3>>>, <<<player: dol, top score: 2>>>, <<<player: iha, top score: 1>>>, <<<player: hey, top score: 0>>>, <<<player: don, top score: 1>>>, <<<player: non, top score: 1>>>, <<<player: gub, top score: 1>>>, <<<player: BUG, top score: 1>>>]");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void existingFileShouldBeCleared() {
        try {
            Path tmp = Files.createTempFile("temp", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println("{'records': [{'name': 'her', 'score': 3}]}");
            JSONParser parser = new JSONParser(tmp);
            parser.clearExternalJsonFile();
            Assert.assertEquals(Files.size(tmp), 0);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void fileShouldBeCreated(){
        Path tmp = Path.of("/tmp/new.json");
        JSONParser parser = new JSONParser(tmp);
        parser.clearExternalJsonFile();
        Assert.assertTrue(Files.exists(tmp));
    }

    public void newScoreShouldBeWrittenToExternalFile() {
        List<Record> records = new ArrayList<>() {{
            add(new Record("John", 10));
            add(new Record("Sarah", 10));
            add(new Record("Lui", 10));
        }};

        Path tmp = null;
        try {
            tmp = Files.createTempFile("temp", ".json");
            JSONParser parser = new JSONParser(tmp);
            parser.updateScores(records);
            Assert.assertTrue(Files.readAllLines(tmp).toString().contains("[{\"records\":[{\"name\":\"John\",\"score\":10},{\"name\":\"Sarah\",\"score\":10},{\"name\":\"Lui\",\"score\":10}]}]"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}