package com.eugened.entity.location;

public class GameField {
    private int height;
    private int width;
    private Cell[][] cells;

    public GameField(int height, int width) {
        this.height = height;
        this.width = width;
        this.cells = new Cell[height][width];
        initializeCells();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getCells() {
        return cells;
    }

    private void initializeCells() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell();
            }
        }
    }

    // Проверка на корректность координат
    public Cell getCellAt(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return cells[x][y];
        } else {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
    }
}
