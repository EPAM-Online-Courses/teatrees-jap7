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
     * @param result indicates whether new score is the best yet for the Player
     * @param score  user's score, got during the game
     * @param name
     */
    public void printFinalMessage(List<Record> scores, boolean result, int score, String name) {
        scores.forEach(out::println);

        out.println("\nScore: " + score);
        if (result) {
            out.println("Congrats! It's your new record " + name + "!");
        }
    }


}
