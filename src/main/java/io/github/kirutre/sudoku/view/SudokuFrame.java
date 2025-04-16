package io.github.kirutre.sudoku.view;

import javax.swing.*;
import java.awt.event.*;

public class SudokuFrame extends JFrame {
    private JPanel backgroundPanel;
    private JLabel titleLabel;
    private SudokuBoard sudokuBoard;

    public SudokuFrame() {
        setContentPane(backgroundPanel);

        this.setTitle("Sudoku by Kirutre");
        this.setLocationRelativeTo(null);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
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
