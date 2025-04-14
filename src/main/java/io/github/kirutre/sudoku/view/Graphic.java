package io.github.kirutre.sudoku.view;

import java.util.List;

public class Graphic {

    public Graphic(final List<List<Integer>> grid) {
        printGrid(grid);
    }

    private void printGrid (List<List<Integer>> grid) {
        final int gridSize = 9;

        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                System.out.print(grid.get(row).get(column) + " ");
            }

            System.out.println();
        }
    }
}
