package io.github.kirutre.sudoku.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Sudoku implements Quadrant<Integer> {
    private final List<List<Integer>> grid = new ArrayList<>(gridSize);

    private static final int gridSize = 9;

    public Sudoku(int[][] board) {
        setSudoku(board);
        solveSudoku();
    }

    public List<List<Integer>> getGrid() {
        return grid;
    }

    private void setSudoku(int[][] board) {
        for (int[] row : board) {
            grid.add(new ArrayList<>(Arrays.stream(row).boxed().toList()));
        }
    }

    private boolean solveSudoku() {
        final Limit limits = new Limit(0, 0, gridSize);

        return travel((r, c) -> {
            if (grid.get(r).get(c) == 0) {
                for (int value = 1; value <= gridSize; value++) {
                    if (isNumberValid(r, c, value)) {
                        grid.get(r).set(c, value);

                        if (solveSudoku()) {
                            return true;
                        } else {
                            grid.get(r).set(c, 0);
                        }
                    }
                }
                return false;
            }
            return true;
        }, limits);
    }

    private boolean isNumberValid(int row, int column, int value) {
        return !(isNumberInRow(row, value) || isNumberOnColumn(column, value) || isOnQuadrant(grid, row, column, value));
    }

    private boolean isNumberInRow(int row, int value) {
        return grid.get(row).contains(value);
    }

    private boolean isNumberOnColumn(int column, int value) {
        for (List<Integer> row : grid) {
            if (row.get(column).equals(value)) {
                return true;
            }
        }

        return false;
    }
}
