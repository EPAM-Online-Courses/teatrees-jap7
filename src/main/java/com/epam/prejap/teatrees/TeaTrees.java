package com.epam.prejap.teatrees;

import com.epam.prejap.teatrees.block.BlockFeed;
import com.epam.prejap.teatrees.game.Move;
import com.epam.prejap.teatrees.game.Playfield;
import com.epam.prejap.teatrees.game.Printer;
import com.epam.prejap.teatrees.game.Waiter;
import com.epam.prejap.teatrees.player.Player;
import com.epam.prejap.teatrees.player.RandomPlayer;

public class TeaTrees {

    private final Playfield playfield;
    private final Waiter waiter;
    private final Player player;

    public TeaTrees(Playfield playfield, Waiter waiter, Player player) {
        this.playfield = playfield;
        this.waiter = waiter;
        this.player = player;
    }

    public Score play(int... index) {
        boolean moved;
        int score = 0;
        do {
            moved = false;

            if (index.length == 0) {
                playfield.nextBlock();
            } else {
                playfield.nextBlock(index[0]);
            }
            score++;

            boolean nextMove;
            do {
                waiter.waitForIt();
                Move move = player.nextMove().orElse(Move.NONE);
                moved |= (nextMove = playfield.move(move));
            } while (nextMove);

        } while (moved);

        return new Score(score);
    }

    public static void main(String[] args) {
        int rows = 10;
        int cols = 20;
        int delay = 500;

        var feed = new BlockFeed();
        var printer = new Printer(System.out);
        var playfield = new Playfield(rows, cols, feed, printer);
        var game = new TeaTrees(playfield, new Waiter(delay), new RandomPlayer());

        var score = game.play();

        System.out.println("Score: " + score.points());
    }

}
