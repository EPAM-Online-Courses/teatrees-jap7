package com.epam.prejap.teatrees.game;

import java.io.PrintStream;
import java.util.Scanner;
//TODO javadocs
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
        out.format(dot == 0 ? " " :"#");
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

    public void printFinalMessage(boolean isNewBestRecord, int score, String nameOfThePlayer) {
        System.out.println("\nScore: " + score);
        if (isNewBestRecord) {
            System.out.println("Congrats! It's your new record " + nameOfThePlayer + "!");
        }
    }

    public String askForUserName(Scanner scanner) {
        System.out.println("Type your name (only 3 letters allowed): ");
        String nameOfThePlayer = scanner.nextLine();
        while (nameOfThePlayer.length() != 3) {
            System.out.println("Retype your name! Only 3 letters are allowed!:");
            nameOfThePlayer = scanner.nextLine();
        }
        return nameOfThePlayer;
    }
}
