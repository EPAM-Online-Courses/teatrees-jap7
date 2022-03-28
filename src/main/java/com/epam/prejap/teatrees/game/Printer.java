package com.epam.prejap.teatrees.game;

import com.epam.prejap.teatrees.records.Record;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 * Responsible for printing grid, blocks and messages to console
 *
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class Printer {

    final PrintStream out;

    public Printer(PrintStream out) {
        this.out = out;
    }

    void draw(byte[][] grid) {
        clear();
        border(grid[0].length);
        for (byte[] bytes : grid) {
            startRow();
            for (byte aByte : bytes) {
                print(aByte);
            }
            endRow();
        }
        border(grid[0].length);
    }

    void clear() {
        out.print("\u001b[2J\u001b[H");
    }

    void print(byte dot) {
        out.format(dot == 0 ? " " : "#");
    }

    void startRow() {
        out.print("|");
    }

    void endRow() {
        out.println("|");
    }

    void border(int width) {
        out.println("+" + "-".repeat(width) + "+");
    }

    /**
     * Prints the concluding message about achieved score. In case of new record prints additional message.
     *
     * @param isNewBestRecord indicates whether new score is the best yet for the Player
     * @param score           user's score, got during the game
     * @param nameOfThePlayer
     */
    public void printFinalMessage(List<Record> scores, boolean isNewBestRecord, int score, String nameOfThePlayer) {
        scores.forEach(out::println);

        out.println("\nScore: " + score);
        if (isNewBestRecord) {
            out.println("Congrats! It's your new record " + nameOfThePlayer + "!");
        }
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
