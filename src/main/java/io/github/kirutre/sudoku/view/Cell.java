package io.github.kirutre.sudoku.view;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.Cursor;
import java.awt.Font;

public class Cell extends JTextField {
    private static final int cellSize = 36;

    private static final Font font = new Font("Microsoft JhengHei UI", Font.BOLD, 27);

    public Cell(CellColor cellColor, Position position) {
        setFont(font);

        setBackground(cellColor.background());
        setForeground(cellColor.foreground());
        setBorder(BorderFactory.createLineBorder(cellColor.margin(), 1));

        setCaret(new InvisibleCaret());
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setHorizontalAlignment(JTextField.CENTER);

        setBounds(position.x(), position.y(), cellSize, cellSize);
        setVisible(true);
    }
}
