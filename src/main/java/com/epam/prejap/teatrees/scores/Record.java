package com.epam.prejap.teatrees.scores;

/**
 * @author Herman Kulik
 */
public class Record implements Comparable<Record>{
    String name;
    int score;

    public Record(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "name is " + name + " score " + score;
    }

    @Override
    public int compareTo(Record o) {
        return o.score - this.score;
    }
}
