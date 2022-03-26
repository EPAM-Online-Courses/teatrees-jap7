package com.epam.prejap.teatrees.records;

import com.epam.prejap.teatrees.game.Printer;

import java.io.IOException;
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
     *
     * @param score user's score, got during the game
     * @param jsonFile a path to .json file, where all records are stored
     * @param printer an object, responsible for interaction with user via console
     */
    public void concludeTheGame(int score, Printer printer) {
        String nameOfThePlayer = printer.askForUserName(new Scanner(System.in));
        boolean isNewBestRecord = false;
        RecordHandler handler = new RecordHandler();
        try {
            isNewBestRecord = handler.handleNewRecord(new Record(nameOfThePlayer, score));
        } catch (IOException e) {
            System.err.println("Error during uploading .json file");
        }
        printer.printFinalMessage(handler.getHighScore(), isNewBestRecord, score, nameOfThePlayer);
    }
}
