package com.eugened.entity.object.organism;

import com.eugened.entity.location.Cell;
import com.eugened.entity.location.GameField;
import com.eugened.entity.object.GameObject;

public abstract class Organism implements GameObject {
    private Cell currentCell;
    private int x;
    private int y;

    private boolean alive = true;
    private int saturation; // Counter of max organisms on per cell

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getSaturation() {
        return saturation;
    }

    public void setSaturation(int saturation) {
        this.saturation = saturation;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Cell getCurrentCell(GameField field) {
        return field.getCellAt(getX(), getY());
    }

    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }
}
