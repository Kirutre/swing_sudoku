package io.github.kirutre.sudoku.view;

import javax.swing.*;
import java.awt.*;

public class Cell extends JTextField {
    private static final int cellSize = 36;

    private static final Font font = new Font("Microsoft JhengHei UI", Font.BOLD, 27);

    public Cell(CellColors colors, Point position) {
        setFont(font);

        setBackground(colors.background());
        setForeground(colors.foreground());
        setBorder(BorderFactory.createLineBorder(colors.panelBackground(), 1));

        setCaret(new InvisibleCaret());
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setHorizontalAlignment(JTextField.CENTER);

        setBounds(position.x, position.y , cellSize, cellSize);
        setVisible(true);
    }
}
