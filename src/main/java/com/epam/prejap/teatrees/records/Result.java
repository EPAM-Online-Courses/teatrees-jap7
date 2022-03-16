package com.epam.prejap.teatrees.records;

import java.util.Scanner;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class Result {

    public void concludeTheGame(int score) {
        String nameOfThePlayer = getUserName();
        boolean isNewBestRecord = new RecordHandler().handleNewRecord(new Record(nameOfThePlayer, score));
        System.out.println("\nScore: " + score);
        if (isNewBestRecord) {
            System.out.println("Congrats! It's your new record " + nameOfThePlayer + "!");
        }
    }

    private String getUserName() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Type your name (only 3 letters allowed): ");
        String nameOfThePlayer = scanner.nextLine();
        while (nameOfThePlayer.length() != 3) {
            System.out.println("Retype your name! Only 3 letters are allowed!:");
            nameOfThePlayer = scanner.nextLine();
        }
        return nameOfThePlayer;
    }
}
