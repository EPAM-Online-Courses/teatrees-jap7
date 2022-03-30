package com.epam.prejap.teatrees;

import com.epam.prejap.teatrees.block.RandomBlockFeed;
import com.epam.prejap.teatrees.game.Move;
import com.epam.prejap.teatrees.game.Playfield;
import com.epam.prejap.teatrees.game.Printer;
import com.epam.prejap.teatrees.game.Waiter;
import com.epam.prejap.teatrees.player.Player;
import com.epam.prejap.teatrees.player.RandomPlayer;
import com.epam.prejap.teatrees.records.GameConcluder;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * This is the main class to run the game.
 */
public class TeaTrees {

    private final Playfield playfield;
    private final Waiter waiter;
    private final Player player;

    public TeaTrees(Playfield playfield, Waiter waiter, Player player) {
        this.playfield = playfield;
        this.waiter = waiter;
        this.player = player;
    }

    /**
     * Method running the game, executing all possible moves in a loop and implementing desired delay between each step.
     *
     * @return total score achieved in this game
     */
    public Score play() {
        boolean moved;
        int score = 0;
        do {
            moved = false;

            playfield.nextBlock();
            score++;

            boolean nextMove;
            do {
                waiter.waitForIt();
                Move move = player.nextMove().orElse(Move.NONE);
                moved |= (nextMove = playfield.move(move));
            } while (nextMove);

            playfield.removeCompleteLines();

        } while (moved);

        return new Score(score);
    }


    public static void main(String[] args) {
        int rows = 10;
        int cols = 20;
        int delay = 500;

        var feed = new RandomBlockFeed();
        var printer = new Printer(System.out);
        var playfield = new Playfield(rows, cols, feed, printer);
        var game = new TeaTrees(playfield, new Waiter(delay), new RandomPlayer());
        var score = game.play();

        GameConcluder gameConcluder = new GameConcluder();
        try {
            gameConcluder.concludeTheGame(score.points(), printer);
        } catch (URISyntaxException | IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
