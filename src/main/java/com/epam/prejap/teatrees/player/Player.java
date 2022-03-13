package com.epam.prejap.teatrees.player;

import com.epam.prejap.teatrees.game.Move;
import java.util.Optional;

public interface Player {

    Optional<Move> nextMove();

}
