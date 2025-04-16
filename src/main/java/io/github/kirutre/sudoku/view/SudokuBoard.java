package io.github.kirutre.sudoku.view;

import javax.swing.*;
import java.awt.*;
import java.nio.file.DirectoryNotEmptyException;

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
        startComponents();
        createSudokuBoard();
    }

    private void startComponents() {
        cellsTextField = new JTextField[9][9];

        textFieldWidth = 36;
        textFieldHigh = 36;
        textFieldMargin = 4;
        textFieldFontSize = 27;

        panelBackgroundColor = new Color(28, 28, 120);
        textFieldBackgroundColor = Color.WHITE;
        textFieldForegroundColor = Color.BLACK;
        textFieldBackgroundColorHighlighted = new Color(14,14,29);
        textFieldForegroundColorHighlighted = Color.BLACK;
        textFieldBackgroundColorSelected = new Color(40,50,150);
        textFieldForegroundColorSelected = Color.BLACK;
    }

    private void createSudokuBoard() {
        final int cellsAmount = 9;

        final int sudokuBoardWidth = (textFieldWidth * cellsAmount) + (textFieldMargin * textFieldMargin);
        final int sudokuBoardHigh = (textFieldHigh * cellsAmount) + (textFieldMargin * textFieldMargin);


        this.setLayout(new GridLayout(cellsAmount, cellsAmount, textFieldMargin, textFieldMargin));
        this.setPreferredSize(new Dimension(sudokuBoardWidth, sudokuBoardHigh));
        this.setBackground(panelBackgroundColor);

        createCells();
    }

    private void createCells() {
        Font font = new Font("Microsoft JhengHei UI", Font.BOLD, textFieldFontSize);

        for (int row = 0; row < cellsTextField.length; row++) {
            for (int column = 0; column < cellsTextField[row].length; column++) {
                JTextField cell = new JTextField();

                cell.setBackground(textFieldBackgroundColor);
                cell.setForeground(textFieldForegroundColor);
                cell.setFont(font);
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setBorder(BorderFactory.createLineBorder(panelBackgroundColor, 1));
                cell.setEditable(false);
                cell.setCursor(new Cursor(Cursor.HAND_CURSOR));

                cellsTextField[row][column] = cell;

                this.add(cell);
            }
        }
    }
}
