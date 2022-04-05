package com.epam.prejap.teatrees.game;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Help to interact with user.
 * Responsible for taking username, from user's input,
 * to use it later for printing purposes.
 *
 * @author Herman Kulik
 */
public class GameScanner {
    private PrintStream out;

    public GameScanner(PrintStream printStream) {
        out = printStream;
    }

    /**
     * Asks Player for his name that can be only 3 characters long.
     *
     * @param scanner and object, responsible for scanning Player's output
     */
    public String askForUserName(Scanner scanner) {
        out.println("Type your name (only 3 letters allowed): ");
        String nameOfThePlayer = scanner.nextLine();
        while (nameOfThePlayer.length() != 3) {
            out.println("Retype your name! Only 3 letters are allowed!:");
            nameOfThePlayer = scanner.nextLine();
        }
        return nameOfThePlayer;
    }
}
