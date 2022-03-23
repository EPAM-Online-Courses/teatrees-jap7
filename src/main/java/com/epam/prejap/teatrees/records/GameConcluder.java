package com.epam.prejap.teatrees.records;

import com.epam.prejap.teatrees.game.Printer;

import java.io.File;
import java.util.Scanner;

/**
 * Summing up game flow, interacting with a user, using {@link MessagePrinter}
 * Start .json parsing, by {@link RecordHandler} methods invocation
 * Ties {@link RecordHandler} logic with {@link MessagePrinter} logic, to print appropriate message for user
 *
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class GameConcluder {

    /**
     *
     * @param score user's score, got during the game
     * @param jsonFile a path to .json file, where all records are stored
     * @param printer an object, responsible for interaction with user via console
     */
    public void concludeTheGame(int score, File jsonFile, Printer printer) {
        String nameOfThePlayer = printer.askForUserName(new Scanner(System.in));
        boolean isNewBestRecord = new RecordHandler(jsonFile).handleNewRecord(new Record(nameOfThePlayer, score));
        printer.printFinalMessage(isNewBestRecord, score, nameOfThePlayer);
    }
}
