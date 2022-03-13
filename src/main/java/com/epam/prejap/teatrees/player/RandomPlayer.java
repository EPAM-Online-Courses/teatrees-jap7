package com.epam.prejap.teatrees.player;

import com.epam.prejap.teatrees.game.Move;
import java.util.Optional;
import java.util.Random;

public class RandomPlayer implements Player {

    private final Random random = new Random();

    @Override
    public Optional<Move> nextMove() {
        return Optional.of(Move.values()[random.nextInt(Move.values().length - 1)]);
    }

}
