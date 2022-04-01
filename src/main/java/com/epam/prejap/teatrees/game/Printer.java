package com.epam.prejap.teatrees.game;

import java.io.PrintStream;

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
     * Prints a welcome message at the beginning of the program within 3 seconds.
     */
    public void printWelcomeMsg() {
        out.println("""
                _|          _|              _|                                                   \s
                _|          _|     _|_|     _|     _|_|_|     _|_|     _|_|_|  _|_|       _|_|   \s
                _|    _|    _|   _|_|_|_|   _|   _|         _|    _|   _|    _|    _|   _|_|_|_| \s
                  _|  _|  _|     _|         _|   _|         _|    _|   _|    _|    _|   _|       \s
                    _|  _|         _|_|_|   _|     _|_|_|     _|_|     _|    _|    _|     _|_|_| \s
                """);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
