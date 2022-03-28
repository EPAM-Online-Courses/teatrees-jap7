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

    @BeforeMethod
    private void setup() {
        tBlock = new TBlock();
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

}
