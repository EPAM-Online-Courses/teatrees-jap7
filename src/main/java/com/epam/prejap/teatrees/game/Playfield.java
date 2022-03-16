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

    Playfield(byte[][] grid, BlockFeed feed, Printer printer) {
        this.feed = feed;
        this.printer = printer;
        this.grid = new Grid(grid);
    }

    public void nextBlock() {
        block = feed.nextBlock();
        row = 0;
        col = (grid.cols() - block.cols()) / 2;
        show();
    }

    public boolean move(Move move) {
        hide();
        boolean moved;
            switch (move) {
                case LEFT -> moveLeft();
                case RIGHT -> moveRight();
            }
            moved = moveDown();
        show();
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
                    if (newRow >= grid.rows() || newCol >= grid.cols() || grid.cellAt(newRow, newCol) > 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void hide() {
        forEachBrick((i, j, dot) -> grid.setCell(row + i, col + j, 0));
    }

    private void show() {
        forEachBrick((i, j, dot) -> grid.setCell(row + i,col + j, dot));
        printer.draw(grid.grid());
    }

    private void doMove(int rowOffset, int colOffset) {
        row += rowOffset;
        col += colOffset;
    }

    private void forEachBrick(BrickAction action) {
        for (int i = 0; i < block.rows(); i++) {
            for (int j = 0; j < block.cols(); j++) {
                var dot = block.dotAt(i, j);
                if (dot > 0) {
                    action.act(i, j, dot);
                }
            }
        }
    }

    public void removeCompleteLines() {
        grid.removeCompleteLines();
    }

    private interface BrickAction {
        void act(int i, int j, byte dot);
    }

}
