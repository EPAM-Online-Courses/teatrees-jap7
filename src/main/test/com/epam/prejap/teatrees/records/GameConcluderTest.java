package com.epam.prejap.teatrees.records;

import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameConcluderTest {

    @Test
    public void gameIsConcludedSuccessfully() {
        try {
            InputStream console = System.in;
            //given
            int score = 20;
            String name = "her";
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(name.getBytes(StandardCharsets.UTF_8));
            System.setIn(byteArrayInputStream);
            MessagePrinter messagePrinter = new MessagePrinter();
            Path tmp = Files.createTempFile("temp2", ".json");
            PrintStream ps = new PrintStream(tmp.toString());
            ps.println("{'records': [{'name': 'her', 'score': 3}]}");
            JSONParser jsonParser = new JSONParser(tmp.toFile());
            GameConcluder gameConcluder = new GameConcluder();
            //when
            gameConcluder.concludeTheGame(score, tmp.toFile(), messagePrinter);
            System.setIn(console);
            //then
        } catch (IOException e) {
            System.err.println("Game can't conclude");
        }
    }

}