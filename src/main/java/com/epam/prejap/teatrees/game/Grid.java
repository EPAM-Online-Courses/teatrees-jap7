package com.epam.prejap.teatrees.game;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author dominik_kaminski
 * @author przemyslaw_szewczyk
 */
record Grid(byte[][] grid, int rows, int cols) {
    Grid(int rows, int cols) {
        this(new byte[rows][cols], rows, cols);
    }

    Grid(byte[][] grid) {
        this(grid, grid.length, grid[0].length);
    }

    int cellAt(int row, int col) {
        return grid[row][col];
    }

    void setCell(int row, int col, int value) {
        grid[row][col] = (byte) value;
    }

    void removeCompleteLines() {
        for (int i = rows - 1; i >= 0; i--)
        {
            if (isRowComplete(i))
            {
                dropRows(i - 1);
                i++;
            }
        }
    }

    private void dropRows(int i) {
        while (i >= 0)
        {
            for (int j = 0; j < cols; j++)
            {
                grid[i + 1][j] = grid[i][j];
            }
            i--;
        }
        for (int j = 0; j < cols; j++)
        {
            grid[0][j] = 0;
        }
    }

    private boolean isRowComplete(int row) {
        for (int i = 0; i < cols; i++)
        {
            if (grid[row][i] == 0) return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid1 = (Grid) o;
        return rows == grid1.rows && cols == grid1.cols && gridMatrixEquals(grid, grid1.grid);
    }

    private boolean gridMatrixEquals(byte[][] grid1, byte[][] grid2) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid1[i][j] != grid2[i][j]) return false;
            }
        }
        return true;
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
}
