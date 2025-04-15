package io.github.kirutre.sudoku.model;

import java.util.*;
import java.util.function.BiPredicate;

public class Sudoku {
    final private List<List<Integer>> grid;
    final private int gridSize = 9;

    public Sudoku (int[][] board) {
        grid = new ArrayList<>(gridSize);

        for (int[] row : board) {
            grid.add(new ArrayList<>(Arrays.stream(row).boxed().toList()));
        }

        solveSudoku();
    }

    public List<List<Integer>> getGrid () {
        return grid;
    }

    private boolean solveSudoku () {
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

    private boolean isNumberValid (int row, int column, int value) {
        return !(isNumberInRow(row, value) || isNumberOnColumn(column, value) || isNumberOnQuadrant(row, column, value));
    }

    private boolean isNumberInRow (int row, int value) {
        return grid.get(row).contains(value);
    }

    private boolean isNumberOnColumn (int column, int value) {
        for (List<Integer> row : grid) {
            if (row.get(column).equals(value)) {
                return true;
            }
        }

        return false;
    }

    private boolean isNumberOnQuadrant (int row, int column, int value) {
        final Limit limits = getCurrentQuadrant(row, column);

        return !travel((r, c) -> grid.get(r).get(c) != value, limits);
    }

    private Limit getCurrentQuadrant (int row, int column) {
        final int quadrantSize = 3;
        final int minRow = (row / 3) * 3;
        final int minColumn = (column / 3) * 3;

        return new Limit(minRow, minColumn, quadrantSize);
    }

    private boolean travel (BiPredicate<Integer, Integer> function, Limit limits) {
        final int maxRow = limits.minRow() + limits.size();
        final int maxColumn = limits.minColumn() + limits.size();

        for (int row = limits.minRow(); row < maxRow; row++) {
            for (int column = limits.minColumn(); column < maxColumn; column++) {
                if (!function.test(row, column)) {
                    return false;
                }
            }
        }
        return true;
    }
}
