package io.github.kirutre.sudoku.view;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.Cursor;
import java.awt.Font;
import java.util.Objects;

public class Cell extends JTextField {
    private static final int cellSize = 36;

    private static final Font font = new Font("Microsoft JhengHei UI", Font.BOLD, 27);

    private final Position position;

    public Cell(CellColor cellColor, Position position) {
        this.position = position;

        setFont(font);

        setBackground(cellColor.background());
        setForeground(cellColor.foreground());
        setBorder(BorderFactory.createLineBorder(cellColor.margin(), 1));

        setEditable(false);
        setCaret(new InvisibleCaret());
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setHorizontalAlignment(JTextField.CENTER);

        setBounds(position.x(), position.y(), cellSize, cellSize);
        setVisible(true);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Cell cell = (Cell) object;
        return Objects.equals(position, cell.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
