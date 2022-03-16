package com.epam.prejap.teatrees.block;

import com.epam.prejap.teatrees.TeaTrees;
import com.epam.prejap.teatrees.game.Playfield;
import com.epam.prejap.teatrees.game.Printer;
import com.epam.prejap.teatrees.game.Waiter;
import com.epam.prejap.teatrees.player.Player;
import com.epam.prejap.teatrees.player.RandomPlayer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.assertEquals;

@Test
public class TBlockTest {

    private TBlock tBlock;
    private ByteArrayOutputStream outputStream;
    TeaTrees teatrees;
    BlockFeed blockFeed;
    Printer printer;
    Playfield playfield;
    Waiter waiter;
    Player player;

    @BeforeMethod
    private void setup() {
        tBlock = new TBlock();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        blockFeed = new BlockFeed();
        printer = new Printer(System.out);
        playfield = new Playfield(10, 20, blockFeed, printer);
        player = new RandomPlayer();
        waiter = new Waiter(10);
        teatrees = new TeaTrees(playfield, waiter, player);
    }

    /**
     * Method that intercepts console output and checks if it matches the desired output (block shapes).
     * It plays the game using the play() method and executes the game in a default mode when you don't pass
     * a parameter to the play method, or uses a specified block when you use a parametrized method
     * (e.g. 0 if using a default square block, 1 if using T-block, etc.)
     */
    public void shouldHave3HashesInSecondRowAnd1InThirdRow(){
        //given
        var game = teatrees.play(1);
        String first2Lines = "|" + new String(" ").repeat(8) + "###" + " ".repeat(9) + "|" +"\n"
                + "|" + " ".repeat(9) + "#" + new String(" ").repeat(10) + "|";

        //when
        String[] outputFromConsole = outputStream.toString().split("\n");
        String result = outputFromConsole[1] + "\n" + outputFromConsole[2];
        //then
        assertEquals(first2Lines, result);
    }

    public void shouldHave3Columns() {
        //given
        int columnsNumber = 3;
        //when
        int actualCols = tBlock.cols();
        //then
        assertEquals(columnsNumber, actualCols);
    }

    public void shouldHave2Rows() {
        //give
        int rowsNumber = 2;
        //when
        int actualRows = tBlock.rows();
        //then
        assertEquals(rowsNumber, actualRows);
    }

    @AfterMethod
    private void cleanup() {
        System.setOut(System.out);
    }
}
