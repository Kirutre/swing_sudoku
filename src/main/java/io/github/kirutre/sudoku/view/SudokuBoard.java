package io.github.kirutre.sudoku.view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;

public class SudokuBoard extends JPanel {
    private JTextField[][] cellsTextField;

    private int textFieldWidth;
    private int textFieldHigh;
    private int textFieldMargin;
    private int textFieldFontSize;

    private Color panelBackgroundColor;
    private Color textFieldBackgroundColor;
    private Color textFieldForegroundColor;

    private Color textFieldBackgroundColorHighlighted;
    private Color textFieldForegroundColorHighlighted;

    private Color textFieldBackgroundColorSelected;
    private Color textFieldForegroundColorSelected;

    public SudokuBoard() {
    }

    private void startComponents() {
        cellsTextField = new JTextField[9][9];

        textFieldWidth = 35;
        textFieldHigh = 36;
        textFieldMargin = 4;
        textFieldFontSize = 27;

        panelBackgroundColor = Color.BLACK;
        textFieldBackgroundColor = Color.WHITE;
        textFieldForegroundColor = Color.BLACK;
        textFieldBackgroundColorHighlighted = Color.WHITE;
        textFieldForegroundColorHighlighted = Color.BLACK;
        textFieldBackgroundColorSelected = Color.WHITE;
        textFieldForegroundColorSelected = Color.BLACK;
    }

    private void createSudokuBoard() {
        final int cellsAmount = 9;
        final int divisorsAmount = 4;

        final int sudokuBoardWidth = textFieldWidth * cellsAmount + textFieldMargin * divisorsAmount;
        final int sudokuBoardHigh = textFieldHigh * cellsAmount + textFieldMargin * divisorsAmount;

        this.setLayout(null);
        this.setSize(sudokuBoardWidth, sudokuBoardHigh);
        this.setBackground(panelBackgroundColor);
    }
}
