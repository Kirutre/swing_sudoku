package io.github.kirutre.sudoku.model;

import java.util.*;
import java.util.function.IntBinaryOperator;

public class Sudoku implements Quadrant<Integer> {
    private final List<List<Integer>> grid = new ArrayList<>(gridSize);

    private static final int gridSize = 9;
    private static final Limit gridLimits = new Limit(0, 0, gridSize);

    public Sudoku() {
        final int[][] board = {
                {0, 0, 7, 0, 0, 0, 9, 0, 0},
                {4, 0, 0, 0, 0, 6, 2, 0, 0},
                {0, 0, 0, 0, 5, 0, 0, 0, 8},
                {0, 0, 0, 4, 0, 0, 0, 0, 0},
                {0, 0, 3, 0, 0, 8, 0, 0, 7},
                {9, 0, 0, 7, 6, 0, 0, 5, 2},
                {0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 9, 2, 0, 0, 0},
                {5, 0, 8, 6, 1, 0, 0, 0, 0}
        };

        setGrid(board);

        solveSudoku();
    }

    public List<List<Integer>> getGrid() {
        return grid;
    }

    private void setGrid(int[][] board) {
        for (int[] row : board) {
            grid.add(new ArrayList<>(Arrays.stream(row).boxed().toList()));
        }
    }

    private boolean solveSudoku() {
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
        }, gridLimits);
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

    private void cleanSudoku() {
        travel((r,c) -> {
            grid.get(r).set(c, 0);
            return true;
        }, gridLimits);
    }

    public void generateSudoku() {
        cleanSudoku();

        final Limit firstQuadrantLimits = getCurrentQuadrant(0, 0);
        final Limit secondQuadrantLimits = getCurrentQuadrant(3, 3);
        final Limit thirdQuadrantLimits = getCurrentQuadrant(6, 6);

        Random random = new Random();

        generateQuadrant(firstQuadrantLimits, random);
        generateQuadrant(secondQuadrantLimits, random);
        generateQuadrant(thirdQuadrantLimits, random);
    }

    private void generateQuadrant(Limit limits, Random random) {
        Set<Integer> quadrantNumbers = new HashSet<>();

        travel((r, c) -> {
            int attempts = 0;
            int numberForCell;

            do {
                numberForCell = random.nextInt(9) + 1;
                attempts++;

                if (attempts > 20) {
                    return false;
                }

            } while (quadrantNumbers.contains(numberForCell) || isOnQuadrant(grid, r, c, numberForCell));

            grid.get(r).set(c, numberForCell);
            quadrantNumbers.add(numberForCell);
            return true;
        }, limits);
    }
}
