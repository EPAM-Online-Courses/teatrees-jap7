package com.epam.prejap.teatrees.records;

import java.io.File;
import java.util.Scanner;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class GameConcluder {

    public void concludeTheGame(int score, File jsonFile, MessagePrinter messagePrinter) {
        String nameOfThePlayer = messagePrinter.askForUserName(new Scanner(System.in));
        boolean isNewBestRecord = new RecordHandler(jsonFile).handleNewRecord(new Record(nameOfThePlayer, score));
        messagePrinter.printFinalMessage(isNewBestRecord, score, nameOfThePlayer);
    }
}
