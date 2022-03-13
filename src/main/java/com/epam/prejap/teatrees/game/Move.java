package com.epam.prejap.teatrees.game;

public enum Move {

    NONE(' '),
    LEFT('h'),
    RIGHT('l'),
    ;

    private final int key;

    Move(int key) {
        this.key = key;
    }

    public static Move of(int key) {
        for (var move : values()) {
            if (move.key == key) {
                return move;
            }
        }
        return NONE;
    }
}
