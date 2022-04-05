package com.epam.prejap.teatrees.records;

import com.epam.prejap.teatrees.game.GameScanner;
import com.epam.prejap.teatrees.game.Printer;
import com.google.gson.JsonSyntaxException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GameConcluderTest {

    @Test
    public void gameIsConcludedSuccessfully() {
        try {
            InputStream consoleIn = System.in;
            PrintStream consoleOut = System.out;
            int score = 20;
            String name = "her";

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(name.getBytes(StandardCharsets.UTF_8));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(byteArrayOutputStream);

            System.setIn(byteArrayInputStream);
            System.setOut(printStream);
            Printer printer = new Printer(System.out);
            GameScanner scanner = new GameScanner(System.out);

            Path tmp = Files.createTempFile("temp2", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println("{'records': [{'name': 'her', 'score': 3}]}");
            GameConcluder gameConcluder = new GameConcluder();

            gameConcluder.concludeTheGame(score,printer, scanner);
            System.setIn(consoleIn);
            System.setOut(consoleOut);

            Assert.assertTrue(byteArrayOutputStream.toString().contains("Score"));
        } catch (IOException e) {
            System.err.println("Game can't conclude");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void gameIsNotConcludedDueToWrongName() {
        try {
            InputStream console = System.in;
            //given
            int score = 20;
            String name = "herr";
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(name.getBytes(StandardCharsets.UTF_8));
            System.setIn(byteArrayInputStream);
            Printer printer = new Printer(System.out);
            GameScanner scanner = new GameScanner(System.out);

            Path tmp = Files.createTempFile("temp2", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println("{'records': [{'name': 'her', 'score': 3}]}");
            JSONParser jsonParser = new JSONParser(tmp);
            GameConcluder gameConcluder = new GameConcluder();
            //when
            gameConcluder.concludeTheGame(score,  printer, scanner);
            System.setIn(console);
            //then
        } catch (IOException e) {
            System.err.println("Game can't conclude");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void gameIsNotConcludedDueToWrongFile() {
        try {
            InputStream console = System.in;
            //given
            int score = 20;
            String name = "her";
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(name.getBytes(StandardCharsets.UTF_8));
            System.setIn(byteArrayInputStream);
            Printer printer = new Printer(System.out);
            GameScanner scanner = new GameScanner(System.out);

            Path tmp = Files.createTempFile("temp2", ".jsn");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println("{'records': [{'name': 'her', 'scoree': 3}]}}");
            JSONParser jsonParser = new JSONParser(tmp);
            GameConcluder gameConcluder = new GameConcluder();
            //when
            gameConcluder.concludeTheGame(score,  printer,scanner);
            System.setIn(console);
            //then
        } catch (IOException e) {
            System.err.println("Game can't conclude");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
