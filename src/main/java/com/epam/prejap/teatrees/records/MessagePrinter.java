package com.epam.prejap.teatrees.records;

import java.util.Scanner;

public class MessagePrinter {
    void printFinalMessage(boolean isNewBestRecord, int score, String nameOfThePlayer) {
        System.out.println("\nScore: " + score);
        if (isNewBestRecord) {
            System.out.println("Congrats! It's your new record " + nameOfThePlayer + "!");
        }
    }

    String askForUserName(Scanner scanner) {
        System.out.println("Type your name (only 3 letters allowed): ");
        String nameOfThePlayer = scanner.nextLine();
        while (nameOfThePlayer.length() != 3) {
            System.out.println("Retype your name! Only 3 letters are allowed!:");
            nameOfThePlayer = scanner.nextLine();
        }
        return nameOfThePlayer;
    }
}
