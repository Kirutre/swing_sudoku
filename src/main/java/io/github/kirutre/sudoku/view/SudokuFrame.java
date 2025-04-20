package io.github.kirutre.sudoku.view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class SudokuFrame extends JFrame {
    private JPanel backgroundPanel;
    private JLabel titleLabel;
    private JPanel buttonsPanel;
    private JButton newGameButton;
    private JButton cleanButton;
    private JButton checkButton;
    private JButton solveButton;
    private JButton createSudokuButton;

    private SudokuBoard sudokuBoard;
    private DifficultyLevelFrame difficultyLevelFrame;

    public SudokuFrame() {
        setContentPane(backgroundPanel);
        setTitle("Sudoku by Kirutre");
        setLocationRelativeTo(null);

        sudokuBoard = new SudokuBoard();

        backgroundPanel.add(sudokuBoard, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
        setResizable(false);
        setVisible(true);

        newGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (difficultyLevelFrame == null) {
                    difficultyLevelFrame = new DifficultyLevelFrame(sudokuBoard);
                }

                difficultyLevelFrame.setVisible(true);
            }
        });
    }
}