package io.github.kirutre.sudoku.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class SudokuBoard extends JPanel {
    private final List<List<Cell>> cellsTextField = new ArrayList<>();

    private static final int textFieldMargin = 4;

    private static final int cellsAmount = 9;
    private static final int cellSize = 36;

    private static final Color panelBackgroundColor = new Color(28, 28, 120);
    private final CellColors colors = new CellColors(Color.WHITE, Color.BLACK, panelBackgroundColor);

    public SudokuBoard() {
        setLayout(null);
        createSudokuBoard();
    }

    private void createSudokuBoard() {
        final int sideSize = (cellSize * cellsAmount) + (textFieldMargin * (cellsAmount + 1));

        setPreferredSize(new Dimension(sideSize, sideSize));
        setBackground(panelBackgroundColor);
        setBounds(0, 0, sideSize, sideSize);

        createCells();
    }

    private void createCells() {
        for (int row = 0; row < cellsAmount; row++) {
            final List<Cell> cellsRow = new ArrayList<>();

            for (int column = 0; column < cellsAmount; column++) {
                final Point position = calculatePosition(row, column);

                final Cell cell = new Cell(colors, position);

                add(cell);

                cellsRow.add(cell);
            }

            cellsTextField.add(cellsRow);
        }
    }

    private Point calculatePosition(int row, int column) {
        int rowQuadrant = row / 3;
        int columnQuadrant = column / 3;

        int x = textFieldMargin + (column * cellSize) + (columnQuadrant * textFieldMargin);
        int y = textFieldMargin + (row * cellSize) + (rowQuadrant * textFieldMargin);

        return new Point(x, y);
    }
}