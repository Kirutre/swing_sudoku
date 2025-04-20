package io.github.kirutre.sudoku.view;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DifficultyLevelFrame extends JFrame {
    private JPanel backgroundPanel;
    private JLabel titleLabel;
    private JButton easyLevelButton;
    private JButton mediumLevelButton;
    private JButton hardLevelButton;

    public DifficultyLevelFrame(SudokuBoard sudokuBoard) {
        setContentPane(backgroundPanel);

        setLocationRelativeTo(null);

        pack();
        setResizable(false);

        easyLevelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                sudokuBoard.generateSudoku();
            }
        });
    }
}
