package io.github.kirutre.sudoku.model;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiPredicate;

public class Sudoku {
    final private List<List<Integer>> grid;
    final private int gridSize = 9;

    public Sudoku (int[] @NotNull [] board) {
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
        }, 0, 0, gridSize, gridSize);
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
        final int cellSize = 3;
        final QuadrantLimit limits = getCurrentQuadrant(row, column);

        return !travel((r, c) -> {
            return r < limits.minRow() || r >= limits.minRow() + cellSize ||
                    c < limits.minColumn() || c >= limits.minColumn() + cellSize ||
                    grid.get(r).get(c) != value;
        }, limits.minRow(), limits.minColumn(), limits.minRow() + cellSize, limits.minColumn() + cellSize);
    }

    private QuadrantLimit getCurrentQuadrant (int row, int column) {
        final int minRow = (row / 3) * 3;
        final int minColumn = (column / 3) * 3;

        return new QuadrantLimit(minRow, minColumn);
    }

    private boolean travel (BiPredicate<Integer, Integer> function, int starRow, int starColumn, int endRow, int endColumn) {
        for (int row = starRow; row < endRow; row++) {
            for (int column = starColumn; column < endColumn; column++) {
                if (!function.test(row, column)) {
                    return false;
                }
            }
        }
        return true;
    }
}
