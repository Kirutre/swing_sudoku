package io.github.kirutre.sudoku.controller;

import io.github.kirutre.sudoku.model.Sudoku;
import io.github.kirutre.sudoku.view.SudokuFrame;

import java.util.List;

public class Main {
    public static void main(String[] args) {
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

        final Sudoku sudoku = new Sudoku(board);
        List<List<Integer>> grid = sudoku.getGrid();

        final SudokuFrame sudokuFrame = new SudokuFrame();

        sudokuFrame.setVisible(true);
    }
}
