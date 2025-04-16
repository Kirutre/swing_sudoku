package io.github.kirutre.sudoku.view;

import javax.swing.*;
import java.awt.event.*;

public class SudokuFrame extends JFrame {
    private JPanel backgroundPanel;
    private JLabel titleLabel;

    public SudokuFrame() {
        setContentPane(backgroundPanel);

        this.setSize(540, 420);
        this.setResizable(false);
        this.setTitle("Sudoku by Kirutre");
        this.setLocationRelativeTo(null);

        startComponents();

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void startComponents() {
        final SudokuBoard sudokuBoard = new SudokuBoard();

        sudokuBoard.setVisible(true);

        sudokuBoard.createSudokuBoard();
    }
}
