package com.epam.prejap.teatrees;

/**
 * @author Herman Kulik
 */
public class Records {
    String name;
    int score;

    public Records(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "name is " + name + " score " + score;
    }
}
