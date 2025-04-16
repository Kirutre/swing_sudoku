package io.github.kirutre.sudoku.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuFrame extends JFrame {
    private JPanel backgroundPanel;
    private JLabel titleLabel;

    private SudokuBoard sudokuBoard = new SudokuBoard();

    public SudokuFrame() {
        this.setContentPane(backgroundPanel);

        this.setTitle("Sudoku by Kirutre");
        this.setLocationRelativeTo(null);

        backgroundPanel.add(sudokuBoard, BorderLayout.CENTER);

        // call onCancel() when cross is clicked
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        pack();

        this.setResizable(false);
        this.setVisible(true);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
