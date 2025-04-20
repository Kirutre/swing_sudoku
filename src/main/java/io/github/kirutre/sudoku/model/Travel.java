package io.github.kirutre.sudoku.model;

import java.util.function.BiPredicate;
import java.util.function.IntBinaryOperator;

public interface Travel {
    default boolean travel(BiPredicate<Integer, Integer> function, Limit limits) {
        return travel(function, limits, (r, c) -> 1);
    }

    default boolean travel(BiPredicate<Integer, Integer> function, Limit limits, IntBinaryOperator columnIncrement) {
        final int maxRow = limits.minRow() + limits.size();
        final int maxColumn = limits.minColumn() + limits.size();

        for (int row = limits.minRow(); row < maxRow; row++) {
            for (int column = limits.minColumn(); column < maxColumn; column += columnIncrement.applyAsInt(row, column)) {
                if (!function.test(row, column)) {
                    if (columnIncrement.applyAsInt(row, column) < 0) {
                        column--;

                        if (column < limits.minColumn() - 5) {
                            System.err.println("An unexpected error has occurred");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}