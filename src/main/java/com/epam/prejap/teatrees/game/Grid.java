package com.epam.prejap.teatrees.game;

import com.epam.prejap.teatrees.block.Block;

import java.util.Arrays;
import java.util.Objects;

/**
 * Provides Playfield an api for working with 2D array.
 * @author Dominik Kaminski
 * @author Przemyslaw Szewczyk
 */
final class Grid {
    private final byte[][] grid;
    private final int rows;
    private final int cols;

    private Grid(byte[][] grid, int rows, int cols) {
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

    /**
     * It works like this - firstly it finds the first row that has only zeros and sets it
     * as a bound. Secondly it counts how many times line removal occurred. Finally, it creates
     * new lines below the bound. Obviously number of created lines is equal to the number of
     * removed lines.
     */
    void removeCompleteLines() {
        int[] fallingRows = new int[rows];
        int bound = getWorkAreaBound(fallingRows);
        int counter = countRemovedLines(fallingRows, bound);
        fillEmptySpace(bound, counter);
    }

    private void fillEmptySpace(int bound, int counter) {
        while (counter > 0) {
            grid[bound + counter - 1] = new byte[cols];
            counter--;
        }
    }

    private int countRemovedLines(int[] fallingRows, int bound) {
        int counter = 0;
        for (int i = rows - 1; i >= bound; i--) {
            if (fallingRows[i] == 0) counter++;
            else grid[i + counter] = grid[i];
        }
        return counter;
    }

    private int getWorkAreaBound(int[] fallingRows) {
        int bound = 0;
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
        return bound;
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
        result = 31 * result + Arrays.deepHashCode(grid);
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

    int getNumberOfRows() {
        return rows;
    }

    int getNumberOfCols() {
        return cols;
    }

    void hideBlock(Block block, int row, int col) {
        forEachBrick(block, (i, j, dot) -> grid[row + i][col + j] = 0);
    }

    void showBlock(Block block, int row, int col) {
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

    void print(Printer printer) {
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
