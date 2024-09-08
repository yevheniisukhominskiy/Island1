package com.eugened.entity.object.organism;

import com.eugened.entity.location.Cell;
import com.eugened.entity.location.GameField;
import com.eugened.entity.object.GameObject;
import com.eugened.entity.object.organism.animal.Animal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
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

    public Organism createInstance() {
        try {
            Organism newOrganism = this.getClass().getDeclaredConstructor().newInstance();
            newOrganism.setCoordinates(this.getX(), this.getY());

            if (newOrganism instanceof Animal) {
                Animal newAnimal = (Animal) newOrganism;
                Animal prototypeAnimal = (Animal) this;

                newAnimal.setSpeed(prototypeAnimal.getSpeed());
                newAnimal.setSaturation(prototypeAnimal.getSaturation());

                if (prototypeAnimal.getEatingProbabilities() != null) {
                    newAnimal.setEatingProbabilities(new HashMap<>(prototypeAnimal.getEatingProbabilities()));
                } else {
                    newAnimal.setEatingProbabilities(new HashMap<>()); // или другой подходящий вариант
                }
            }

            return newOrganism;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.err.println("Error creating instance: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


}
