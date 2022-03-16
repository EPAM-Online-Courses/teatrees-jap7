package com.epam.prejap.teatrees.scores;

import java.util.Objects;

/**
 * @author Andrzej Sadlek
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

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return  true;
        if(obj == null || !obj.getClass().equals(this.getClass())) return false;
        Record record = (Record) obj;
        return this.getName().equals(record.getName());
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
