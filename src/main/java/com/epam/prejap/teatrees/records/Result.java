package com.epam.prejap.teatrees.records;

import java.util.Scanner;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class Result {

    public void concludeTheGame(int score) {
        String nameOfThePlayer = getUserName(new Scanner(System.in));
        boolean isNewBestRecord = new RecordHandler().handleNewRecord(new Record(nameOfThePlayer, score));
        printFinalMessage(isNewBestRecord, score, nameOfThePlayer);

    }

    void printFinalMessage(boolean isNewBestRecord, int score, String nameOfThePlayer) {
        System.out.println("\nScore: " + score);
        if (isNewBestRecord) {
            System.out.println("Congrats! It's your new record " + nameOfThePlayer + "!");
        }
    }

    String getUserName(Scanner scanner) {
        System.out.println("Type your name (only 3 letters allowed): ");
        String nameOfThePlayer = scanner.nextLine();
        while (nameOfThePlayer.length() != 3) {
            System.out.println("Retype your name! Only 3 letters are allowed!:");
            nameOfThePlayer = scanner.nextLine();
        }
        return nameOfThePlayer;
    }
}
