package com.epam.prejap.teatrees.records;

import com.epam.prejap.teatrees.game.Printer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * Summing up game flow, interacting with a user, using {@link Printer}
 * Start .json parsing, by {@link RecordHandler} methods invocation
 * Ties {@link RecordHandler} logic with {@link Printer} logic, to print appropriate message for user
 *
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class GameConcluder {

    /**
     * @param score    user's score, got during the game
     * @param printer  an object, responsible for interaction with user via console
     */
    public void concludeTheGame(int score, Printer printer) throws URISyntaxException, IOException {
        String nameOfThePlayer = printer.askForUserName(new Scanner(System.in));

        Path external = Path.of(new File(JSONParser.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent() + "/external.json");
        JSONParser parser = new JSONParser(external);
        List<Record> allJsonRecords = parser.uploadJsonData();

        RecordHandler handler = new RecordHandler(allJsonRecords);
        boolean isNewRecord = handler.addNewScore(new Record(nameOfThePlayer, score));
        parser.updateScores(handler.getRecordsList());

        printer.printFinalMessage(handler.getHighScore(), isNewRecord, score, nameOfThePlayer);
    }
}
