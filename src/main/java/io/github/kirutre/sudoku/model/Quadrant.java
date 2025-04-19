package io.github.kirutre.sudoku.model;

import java.util.List;

public interface Quadrant<T> extends Travel {
    default boolean isOnQuadrant(List<List<T>> grid, int row, int column, T value) {
        final Limit limits = getCurrentQuadrant(row, column);

        return !travel((r, c) -> !grid.get(r).get(c).equals(value), limits);
    }

    default Limit getCurrentQuadrant(final int row, final int column) {
        final int quadrantSize = 3;
        final int minRow = (row / 3) * 3;
        final int minColumn = (column / 3) * 3;

        return new Limit(minRow, minColumn, quadrantSize);
    }
}
