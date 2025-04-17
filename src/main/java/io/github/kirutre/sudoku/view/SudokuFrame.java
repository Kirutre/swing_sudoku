package io.github.kirutre.sudoku.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuFrame extends JFrame {
    private JPanel backgroundPanel;
    private JLabel titleLabel;

    private SudokuBoard sudokuBoard;

    public SudokuFrame() {
        setContentPane(backgroundPanel);
        setTitle("Sudoku by Kirutre");
        setLocationRelativeTo(null);

        sudokuBoard = new SudokuBoard();

        backgroundPanel.add(sudokuBoard, BorderLayout.CENTER);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        pack();
        setResizable(false);
        setVisible(true);
    }

    private void onCancel() {
        dispose();
    }
}