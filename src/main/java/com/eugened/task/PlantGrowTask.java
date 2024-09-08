package com.eugened.task;

import com.eugened.entity.location.Cell;
import com.eugened.entity.location.GameField;
import com.eugened.entity.object.organism.plant.Plant;
import com.eugened.entity.object.organism.plant.creatures.Grass;
import com.eugened.factory.OrganismPrototypeFactory;
import com.eugened.utils.GameContext;

import java.util.Random;

public class PlantGrowTask implements Runnable {
    private final OrganismPrototypeFactory factory;

    public PlantGrowTask(OrganismPrototypeFactory factory) {
        this.factory = factory;
    }


    @Override
        public void run() {
            GameField gameField = GameContext.getGameField();

            for (int x = 0; x < gameField.getWidth(); x++) {
                for (int y = 0; y < gameField.getHeight(); y++) {
                    if (new Random().nextDouble() < 0.4) {
                        Cell cell = gameField.getCellAt(x, y);

                        Plant plant = (Plant) factory.create(Grass.class);
                        cell.addOrganism(plant);
                        plant.setCoordinates(x, y);
                    }
                }
            }
        }
    }
