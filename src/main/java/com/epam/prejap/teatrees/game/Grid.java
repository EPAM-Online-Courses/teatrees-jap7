package com.epam.prejap.teatrees.game;

import com.epam.prejap.teatrees.block.Block;

import java.util.*;

/**
 * Provides Playfield an api for working with 2D array.
 * @author Dominik Kaminski
 * @author Przemyslaw Szewczyk
 */
final class Grid {
    private final List<List<Byte>> grid;
    private final int rows;
    private final int cols;

    private Grid(byte[][] grid, int rows, int cols) {
        this.grid = translateMatrix(grid);
        this.rows = rows;
        this.cols = cols;
    }

    private List<List<Byte>> translateMatrix(byte[][] matrix) {
        List<List<Byte>> grid = new LinkedList<List<Byte>>();
        for (byte[] bytes : matrix) {
            List<Byte> row = new ArrayList<>(bytes.length);
            for (byte aByte : bytes) {
                row.add(aByte);
            }
            grid.add(row);
        }
        return grid;
    }

    Grid(int rows, int cols) {
        this(new byte[rows][cols], rows, cols);
    }

    Grid(byte[][] grid) {
        this(grid, grid.length, grid[0].length);
    }

    int cellAt(int row, int col) {
        return grid.get(row).get(col);
    }

    /**
     * It works like this - firstly it finds the first row (counting from the bottom) that has only zeros and sets it
     * as a bound. Secondly it counts how many times line removal occurred. Finally, it creates new lines below the bound.
     * Obviously number of created lines is equal to the number of removed lines.
     */
    void removeCompleteLines() {
        boolean[] fallingRows = new boolean[rows];
        int bound = getWorkAreaBound(fallingRows);
        int counter = getNumberOfLinesThatNeedToBeRemoved(fallingRows, bound);
        removeMarkedLines(fallingRows, bound);
        fillEmptySpace(bound, counter);
    }

    private void removeMarkedLines(boolean[] fallingRows, int bound) {
        for (int i = rows - 1; i > bound; i--)
        {
            if (!fallingRows[i]) {
                grid.remove(i);
            }
        }
    }

    private void fillEmptySpace(int bound, int counter) {
        while (counter > 1) {
            grid.add(bound, new ArrayList<>(grid.get(bound)));
            counter--;
        }
    }

    private int getNumberOfLinesThatNeedToBeRemoved(boolean[] fallingRows, int bound) {
        int counter = 0;
        for (int i = rows - 1; i >= bound; i--) {
            if (!fallingRows[i]) counter++;
        }
        return counter;
    }

    int getWorkAreaBound(boolean[] fallingRows) {
        int bound = 0;
        for (int i = rows - 1; i >= 0; i--) {
            fallingRows[i] = isHeterogeneous(i);
            if (!fallingRows[i] && grid.get(i).get(0) == 0) {
                bound = i;
                break;
            }
        }
        return bound;
    }

    private boolean isHeterogeneous(int i) {
        for (int j = 1; j < cols; j++) {
            if (!grid.get(i).get(j).equals(grid.get(i).get(j - 1))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid1 = (Grid) o;
        return rows == grid1.rows && cols == grid1.cols && grid.equals(grid1.grid);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, cols);
        result = 31 * result + Objects.hashCode(grid);
        return result;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "grid=" + grid +
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
        forEachBrick(block, (i, j, dot) -> {
            Byte aByte = grid.get(row + i).get(col + j);
            aByte = 0;
        });
    }

    void showBlock(Block block, int row, int col) {
        forEachBrick(block, (i, j, dot) -> {
            Byte aByte = grid.get(row + i).get(col + j);
            aByte = dot;
        });
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
        for (List<Byte> row : grid) {
            printer.startRow();
            printRow(row, printer);
            printer.endRow();
        }
        printer.border(cols);
    }

    private void printRow(List<Byte> row, Printer printer) {
        for (Byte aByte : row) {
            printer.print(aByte);
        }
    }

    private interface BrickAction {
        void act(int i, int j, byte dot);
    }
}
