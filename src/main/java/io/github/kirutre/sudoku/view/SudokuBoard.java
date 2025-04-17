package io.github.kirutre.sudoku.view;

import javax.swing.*;
import java.awt.*;

public class SudokuBoard extends JPanel {
    private JTextField[][] cellsTextField;
    private int textFieldWidth = 36;
    private int textFieldHigh = 36;
    private int textFieldMargin = 4;
    private int textFieldFontSize = 27;
    private Color panelBackgroundColor = new Color(28, 28, 120);
    private Color textFieldBackgroundColor = Color.WHITE;
    private Color textFieldForegroundColor = Color.BLACK;
    private final int cellsAmount = 9;

    public SudokuBoard() {
        setLayout(null);
        createSudokuBoard();
    }

    private void createSudokuBoard() {
        final int sideSize = (textFieldWidth * cellsAmount) + (textFieldMargin * 10); // Margen inicial y entre bloques
        setPreferredSize(new Dimension(sideSize, sideSize));
        setBackground(panelBackgroundColor);
        createCells();
        setBounds(0, 0, getPreferredSize().width, getPreferredSize().height); // Establecer bounds después de calcular el tamaño
    }

    private void createCells() {
        Font font = new Font("Microsoft JhengHei UI", Font.BOLD, textFieldFontSize);

        cellsTextField = new JTextField[cellsAmount][cellsAmount];

        int x = textFieldMargin;
        int y = textFieldMargin;

        for (int row = 0; row < cellsTextField.length; row++) {
            for (int column = 0; column < cellsTextField[row].length; column++) {
                JTextField cell = new JTextField();

                add(cell);

                cell.setBounds(x, y, textFieldWidth, textFieldHigh);
                cell.setBackground(textFieldBackgroundColor);
                cell.setForeground(textFieldForegroundColor);
                cell.setFont(font);
                cell.setEditable(false);
                cell.setCursor(new Cursor(Cursor.HAND_CURSOR));
                cell.setBorder(BorderFactory.createLineBorder(panelBackgroundColor, 1));
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setVisible(true);
                cellsTextField[row][column] = cell;

                x += textFieldWidth;
                if ((column + 1) % 3 == 0 && column < 8) {
                    x += textFieldMargin;
                }
            }
            x = textFieldMargin;
            y += textFieldHigh;
            if ((row + 1) % 3 == 0 && row < 8) {
                y += textFieldMargin;
            }
        }
    }
}