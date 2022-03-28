package com.epam.prejap.teatrees.game;

import com.epam.prejap.teatrees.block.Block;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Provides Playfield an api for working with 2D array.
 *
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

    Grid(int rows, int cols) {
        this(new byte[rows][cols], rows, cols);
    }

    Grid(byte[][] grid) {
        this(grid, grid.length, grid[0].length);
    }

    private List<List<Byte>> translateMatrix(byte[][] matrix) {
        return IntStream.range(0, matrix.length)
                        .mapToObj(i -> IntStream.range(0, matrix[i].length)
                                                .mapToObj(j -> new Byte(matrix[i][j]))
                                                .collect(Collectors.toCollection(ArrayList::new)))
                        .collect(Collectors.toCollection(LinkedList::new));
    }

    int cellAt(int row, int col) {
        return grid.get(row).get(col);
    }

    int getNumberOfRows() {
        return rows;
    }

    int getNumberOfCols() {
        return cols;
    }

    /**
     * It removes complete lines and adds empty lines at the top of the grid.
     */
    void removeCompleteLines() {
        for (int i = rows - 1; i >= 0; i--) {
            if (isRowDestinedToBeRemoved(i)) {
                swapCompleteLineForEmptyOne(i);
                i++;
            }
        }
    }

    private boolean isRowDestinedToBeRemoved(int i) {
        return !isHeterogeneous(i) && grid.get(i).get(0) == 1;
    }

    private void swapCompleteLineForEmptyOne(int i) {
        grid.remove(i);
        addEmptyLine();
    }

    private void addEmptyLine() {
        grid.add(0, new ArrayList<>(getEmptyRow()));
    }

    private List<Byte> getEmptyRow() {
        return IntStream.range(0, cols).mapToObj(i -> new Byte((byte) 0)).collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean isHeterogeneous(int i) {
        return IntStream.range(0, cols - 1).anyMatch(j -> !grid.get(i).get(j).equals(grid.get(i).get(j + 1)));
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

    private interface BrickAction {
        void act(int i, int j, byte dot);
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
}
