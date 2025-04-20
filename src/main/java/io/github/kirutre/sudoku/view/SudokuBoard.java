package io.github.kirutre.sudoku.view;

import io.github.kirutre.sudoku.model.Limit;
import io.github.kirutre.sudoku.model.Quadrant;
import io.github.kirutre.sudoku.model.Sudoku;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

public class SudokuBoard extends JPanel implements Quadrant<Cell> {
    private final List<List<Cell>> cellsTextField = new ArrayList<>();

    private static final int textFieldMargin = 4;

    private static final int cellsAmount = 9;
    private static final int cellSize = 36;

    private static final Color panelBackgroundColor = new Color(20,20,60);
    private static final Color cellForegroundColor = new Color(58,4,0);
    private static final CellColor cellColor = new CellColor(Color.WHITE, cellForegroundColor, panelBackgroundColor);

    private static final Color selectedCellBackground = new Color(0,57,104);
    private static final CellColor selectedCellColor = new CellColor(selectedCellBackground, Color.WHITE, Color.WHITE);

    private static final Color highlightedCellBackground = new Color(219,242,244);

    public SudokuBoard() {
        setLayout(null);
        createSudokuBoard();
    }

    private void createSudokuBoard() {
        final int sideSize = (cellSize * cellsAmount) + (textFieldMargin * (cellsAmount + 1));

        setPreferredSize(new Dimension(sideSize, sideSize));
        setBackground(panelBackgroundColor);
        setBounds(0, 0, sideSize, sideSize);

        createCells();
    }

    private void createCells() {
        for (int row = 0; row < cellsAmount; row++) {
            List<Cell> cellsRow = new ArrayList<>();

            for (int column = 0; column < cellsAmount; column++) {
                Position position = calculatePosition(row, column);

                Cell cell = new Cell(cellColor, position);

                add(cell);

                cellsRow.add(cell);

                eventHandling(cell);
            }

            cellsTextField.add(cellsRow);
        }
    }

    private Position calculatePosition(int row, int column) {
        int rowQuadrant = row / 3;
        int columnQuadrant = column / 3;

        int x = textFieldMargin + (column * cellSize) + (columnQuadrant * textFieldMargin);
        int y = textFieldMargin + (row * cellSize) + (rowQuadrant * textFieldMargin);

        return new Position(x, y);
    }

    private void eventHandling(Cell cell) {
        MouseAdapter mouseEvent = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cellPressed(cell);
            }
        };

        KeyAdapter keyEvent = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() >= '1' && e.getKeyChar() <= '9') {
                    cell.setText(String.valueOf(e.getKeyChar()));
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
                    cell.setText("");
                }
            }
        };

        cell.addMouseListener(mouseEvent);
        cell.addKeyListener(keyEvent);
    }

    private void cellPressed(Cell cell) {
        Optional<Position> coordinates = getCellCoordinates(cell);

        coordinates.ifPresent(selectedCellCoordinates -> setCellColors(cell, selectedCellCoordinates));
    }

    private void setCellColors(Cell cell, Position cellCoordinates) {
        travel((r, c) -> {
            cellsTextField.get(r).get(c).setBackground(cellColor.background());
            cellsTextField.get(r).get(c).setForeground(cellColor.foreground());
            cellsTextField.get(r).get(c).setBorder(BorderFactory.createLineBorder(cellColor.margin(), 1));
            return true;
        }, new Limit(0, 0, cellsAmount));


        for (int row = 0; row < cellsAmount; row++) {
            Cell currentCell = cellsTextField.get(row).get(cellCoordinates.y());

            currentCell.setBackground(highlightedCellBackground);
        }

        for (int column = 0; column < cellsAmount; column++) {
            Cell currentCell = cellsTextField.get(cellCoordinates.x()).get(column);

            currentCell.setBackground(highlightedCellBackground);
        }

        final Limit quadrantLimits = getCurrentQuadrant(cellCoordinates.x(), cellCoordinates.y());

        travel((r, c) -> {
            cellsTextField.get(r).get(c).setBackground(highlightedCellBackground);
            return true;
        }, quadrantLimits);

        cell.setBackground(selectedCellColor.background());
        cell.setForeground(selectedCellColor.foreground());
        cell.setBorder(BorderFactory.createLineBorder(selectedCellColor.margin(), 2));
    }

    private Optional<Position> getCellCoordinates(Cell cell) {
        return IntStream.range(0, cellsAmount)
                .boxed()
                .flatMap(row -> IntStream.range(0, cellsAmount)
                        .filter(column -> cellsTextField.get(row).get(column).equals(cell))
                        .mapToObj(column -> new Position(row, column)))
                .findFirst();
    }

    public void generateSudoku() {
        Sudoku sudoku = new Sudoku();

        sudoku.generateSudoku();

        List<List<Integer>> grid = sudoku.getGrid();

        for (int i = 0; i < cellsAmount; i++) {
            for (int j = 0; j < cellsAmount; j++) {
                if (grid.get(i).get(j) != 0) {
                    cellsTextField.get(i).get(j).setText(String.valueOf(grid.get(i).get(j)));
                }
            }
        }
    }
}