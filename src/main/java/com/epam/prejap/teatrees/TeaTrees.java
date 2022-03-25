package com.epam.prejap.teatrees;

import com.epam.prejap.teatrees.block.BlockFeed;
import com.epam.prejap.teatrees.game.Move;
import com.epam.prejap.teatrees.game.Playfield;
import com.epam.prejap.teatrees.game.Printer;
import com.epam.prejap.teatrees.game.Waiter;
import com.epam.prejap.teatrees.player.Player;
import com.epam.prejap.teatrees.player.RandomPlayer;
import com.epam.prejap.teatrees.records.GameConcluder;
import com.epam.prejap.teatrees.records.RecordCollector;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class TeaTrees {

    private final Playfield playfield;
    private final Waiter waiter;
    private final Player player;

    public TeaTrees(Playfield playfield, Waiter waiter, Player player) {
        this.playfield = playfield;
        this.waiter = waiter;
        this.player = player;
    }

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

        } while (moved);

        return new Score(score);
    }


    public static void main(String[] args) throws IOException {
        int rows = 10;
        int cols = 20;
        int delay = 500;

        var feed = new BlockFeed();
        var printer = new Printer(System.out);
        var playfield = new Playfield(rows, cols, feed, printer);
        var game = new TeaTrees(playfield, new Waiter(delay), new RandomPlayer());

        //var score = game.play();

        GameConcluder gameConcluder = new GameConcluder();

        String jsonString = game.getFileFromResourceAsStream("score.json");
        System.out.println(jsonString);
        Gson g = new Gson();
        RecordCollector recordCollector = g.fromJson(jsonString, RecordCollector.class);

        File json = new File(Objects.requireNonNull(TeaTrees.class.getClassLoader().getResource("score.json")).getFile());
        gameConcluder.concludeTheGame(1, json, printer);
    }

    private String getFileFromResourceAsStream(String fileName) throws IOException {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        String text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return text;
        }

    }
}
