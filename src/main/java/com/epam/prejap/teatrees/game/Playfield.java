package com.epam.prejap.teatrees.game;

import com.epam.prejap.teatrees.block.Block;
import com.epam.prejap.teatrees.block.BlockFeed;

public class Playfield {
    private final Grid grid;
    private final Printer printer;
    private final BlockFeed feed;

    private Block block;
    private int row;
    private int col;

    public Playfield(int rows, int cols, BlockFeed feed, Printer printer) {
        this.feed = feed;
        this.printer = printer;
        grid = new Grid(rows, cols);
    }

    public void nextBlock() {
        block = feed.nextBlock();
        row = 0;
        col = (grid.getNumberOfCols() - block.cols()) / 2;
        showBlockOnScreen();
    }

    public boolean move(Move move) {
        hideBlockOnGrid();
        boolean moved;
            switch (move) {
                case LEFT -> moveLeft();
                case RIGHT -> moveRight();
            }
            moved = moveDown();
        showBlockOnScreen();
        return moved;
    }

    private void moveRight() {
        move(0, 1);
    }

    private void moveLeft() {
        move(0, -1);
    }

    private boolean moveDown() {
        return move(1, 0);
    }

    private boolean move(int rowOffset, int colOffset) {
        boolean moved = false;
        if (isValidMove(block, rowOffset, colOffset)) {
            doMove(rowOffset, colOffset);
            moved = true;
        }
        return moved;
    }

    private boolean isValidMove(Block block, int rowOffset, int colOffset) {
        for (int i = 0; i < block.rows(); i++) {
            for (int j = 0; j < block.cols(); j++) {
                var dot = block.dotAt(i, j);
                if (dot > 0) {
                    int newRow = row + i + rowOffset;
                    int newCol = col + j + colOffset;
                    if (newRow >= grid.getNumberOfRows() || newCol >= grid.getNumberOfCols() || grid.cellAt(newRow, newCol) > 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void hideBlockOnGrid() {
        grid.hideBlock(block, row, col);
    }

    private void showBlockOnScreen() {
        grid.showBlock(block, row, col);
        grid.print(printer);
    }

    private void doMove(int rowOffset, int colOffset) {
        row += rowOffset;
        col += colOffset;
    }

    /**
     * @see Grid#removeCompleteLines()
     */
    public void removeCompleteLines() {
        grid.removeCompleteLines();
    }
}
