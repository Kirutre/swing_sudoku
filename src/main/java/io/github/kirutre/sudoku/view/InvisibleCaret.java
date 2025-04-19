package io.github.kirutre.sudoku.view;

import javax.swing.text.DefaultCaret;

public class InvisibleCaret extends DefaultCaret {
    @Override
    public boolean isVisible() {
        return false;
    }
}