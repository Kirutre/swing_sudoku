package io.github.kirutre.sudoku.controller;

import io.github.kirutre.sudoku.model.Sudoku;
import io.github.kirutre.sudoku.view.SudokuFrame;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final Sudoku sudoku = new Sudoku();
        List<List<Integer>> grid = sudoku.getGrid();

        final SudokuFrame sudokuFrame = new SudokuFrame();

        sudokuFrame.setVisible(true);
    }
}
