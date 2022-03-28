package com.epam.prejap.teatrees.block;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.prejap.teatrees.TeaTrees;
import com.epam.prejap.teatrees.game.Move;
import com.epam.prejap.teatrees.game.Playfield;
import com.epam.prejap.teatrees.game.Printer;
import com.epam.prejap.teatrees.game.Waiter;
import com.epam.prejap.teatrees.player.Player;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * This test class is intended to contain any other block tests apart from TBlock integration test.
 * Feel free to implement more!
 *
 * @author Krzysztof Jaczewski
 */

@Test
public class BlockIT {

    final Block block;
    private final String blockLines;

    private ByteArrayOutputStream outputStream;
    private TeaTrees teatrees;

    public BlockIT(Block block, String blockLines) {
        this.block = block;
        this.blockLines = blockLines;
    }

    @Factory
    static Object[] blockTests() {
        return new Object[] {
            new BlockIT(new OBlock(), "|##|\n|##|\n"),
        };
    }

    @BeforeMethod
    protected void setup() {
        outputStream = new ByteArrayOutputStream();
        Printer printer = new Printer(new PrintStream(outputStream));
        BlockFeed blockFeed = () -> block;
        Playfield playfield = new Playfield(block.rows, block.cols, blockFeed, printer);
        Waiter waiter = new Waiter(0);
        Player player = () -> Optional.of(Move.NONE);
        teatrees = new TeaTrees(playfield, waiter, player);
    }

    @Test
    public void shouldDisplayBlock() {
        //given
        var game = teatrees.play();
        //when
        String outputFromConsole = outputStream.toString();
        //then
        System.out.println(outputFromConsole);
        assertThat(outputFromConsole).contains(blockLines);
    }

}
