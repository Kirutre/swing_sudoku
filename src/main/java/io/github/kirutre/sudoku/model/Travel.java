package io.github.kirutre.sudoku.model;

import java.util.function.BiPredicate;

public interface Travel {
    default boolean travel(BiPredicate<Integer, Integer> function, Limit limits) {
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
