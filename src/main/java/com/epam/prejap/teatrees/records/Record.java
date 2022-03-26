package com.epam.prejap.teatrees.records;

import java.util.Objects;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class Record implements Comparable<Record> {
    private final String name;
    private final int score;

    Record(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "<<<player: " + name + ", top score: " + score + ">>>";
    }

    @Override
    public int compareTo(Record o) {
        return o.score - this.score;
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || !obj.getClass().equals(this.getClass())) return false;
        Record record = (Record) obj;
        return this.getName().equals(record.getName());
    }

    String getName() {
        return name;
    }

    int getScore() {
        return score;
    }
}
