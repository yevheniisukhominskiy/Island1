package com.eugened.entity.object.organism.plant;

import com.eugened.entity.location.Cell;
import com.eugened.entity.location.GameField;
import com.eugened.entity.object.ability.Cultivable;
import com.eugened.entity.object.organism.Organism;
import com.eugened.entity.object.organism.animal.Animal;
import com.eugened.utils.GameContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public abstract class Plant extends Organism implements Cultivable {
    @Override
    public void play() {
        cultivate();
    }

    @Override
    public void cultivate() {
        Cell currentCell = getCurrentCell(GameContext.getGameField());

        long samePlantsCounter = currentCell.getOrganisms().stream()
                .filter(plant -> plant.getClass().equals(this.getClass()))
                .count();

        boolean willGrow = new Random().nextBoolean();
        if (samePlantsCounter < getSaturation() && willGrow) {
            try {
                Plant plant = this.getClass().getDeclaredConstructor().newInstance();
                plant.setCoordinates(this.getX(), this.getY());
                currentCell.addOrganism(plant);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                System.err.println("Cannot generate a plant: " + e.getMessage());
            }
        }
    }
}
