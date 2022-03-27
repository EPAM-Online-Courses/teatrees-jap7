package com.epam.prejap.teatrees.records;

import com.epam.prejap.teatrees.game.Printer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * Sums up game flow, interacting with a user, using {@link Printer}.
 *
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class GameConcluder {

    /**
     * Starts parsing .json file, using {@link JSONParser} object, and provides parsed elements.
     * Provides parsed elements {@link Record} to {@link RecordHandler}, for further statistic operations.
     * Passes top scores to {@link Printer}, to display results on screen.
     *
     * @param score   number of game points
     * @param printer object, responsible for displaying game result
     * @throws URISyntaxException if external path cannot be received
     * @throws IOException        if .json file cannot be parsed
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
