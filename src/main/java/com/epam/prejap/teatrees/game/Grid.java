package com.epam.prejap.teatrees.game;

import com.epam.prejap.teatrees.block.Block;

import java.util.Arrays;
import java.util.Objects;

/**
 * Provides Playfield an api for working with 2D array.
 * @author dominik_kaminski
 * @author przemyslaw_szewczyk
 */
final class Grid {
    private final byte[][] grid;
    private final int rows;
    private final int cols;

    Grid(byte[][] grid, int rows, int cols) {
        this.grid = grid;
        this.rows = rows;
        this.cols = cols;
    }

    Grid(int rows, int cols) {
        this(new byte[rows][cols], rows, cols);
    }

    Grid(byte[][] grid) {
        this(grid, grid.length, grid[0].length);
    }

    int cellAt(int row, int col) {
        return grid[row][col];
    }

    public void removeCompleteLines() {
        int bound = 0;
        int[] fallingRows = new int[rows];
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 1; j < cols; j++) {
                if (grid[i][j] != grid[i][j - 1]) {
                    fallingRows[i] = 1;
                    break;
                }
            }
            if (fallingRows[i] == 0 && grid[i][0] == 0) {
                bound = i;
                break;
            }
        }

        int counter = 0;
        for (int i = rows - 1; i >= bound; i--) {
            if (fallingRows[i] == 0) counter++;
            else grid[i + counter] = grid[i];
        }

        while (counter > 0) {
            grid[bound + counter - 1] = new byte[cols];
            counter--;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid1 = (Grid) o;
        return rows == grid1.rows && cols == grid1.cols && Arrays.deepEquals(grid, grid1.grid);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, cols);
        result = 31 * result + Arrays.hashCode(grid);
        return result;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "grid=" + Arrays.deepToString(grid) +
                ", rows=" + rows +
                ", cols=" + cols +
                '}';
    }

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }

    public void hideBlock(Block block, int row, int col) {
        forEachBrick(block, (i, j, dot) -> grid[row + i][col + j] = 0);
    }

    public void showBlock(Block block, int row, int col) {
        forEachBrick(block, (i, j, dot) -> grid[row + i][col + j] = dot);
    }

    private void forEachBrick(Block block, BrickAction action) {
        for (int i = 0; i < block.rows(); i++) {
            for (int j = 0; j < block.cols(); j++) {
                var dot = block.dotAt(i, j);
                if (dot > 0) {
                    action.act(i, j, dot);
                }
            }
        }
    }

    public void print(Printer printer) {
        printer.clear();
        printer.border(cols);
        for (byte[] bytes : grid) {
            printer.startRow();
            for (byte aByte : bytes) {
                printer.print(aByte);
            }
            printer.endRow();
        }
        printer.border(cols);
    }

    private interface BrickAction {
        void act(int i, int j, byte dot);
    }
}
