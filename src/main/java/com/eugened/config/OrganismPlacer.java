package com.eugened.config;

import com.eugened.constant.GameConfiguration;
import com.eugened.entity.location.Cell;
import com.eugened.entity.location.GameField;
import com.eugened.entity.object.organism.Organism;
import com.eugened.factory.OrganismPrototypeFactory;
import com.eugened.utils.GameContext;

import java.util.Random;

public class OrganismPlacer {
    private static OrganismPlacer instance;
    public static OrganismPrototypeFactory factory;

    public static void setOrganismPrototypeFactory(OrganismPrototypeFactory factory) {
        OrganismPlacer.factory = factory;
    }

    public static OrganismPlacer getInstance() {
        if (instance == null) {
            instance = new OrganismPlacer();
        }
        return instance;
    }

    public void placeOrganismRandomly(GameField gameField) {
        factory.getAllPrototypes().forEach(prototype -> {
            int counter = new Random().nextInt(GameConfiguration.MAX_ANIMALS_GENARATE) + GameConfiguration.MIN_ANIMALS_GENARATE;
            for (int i = 0; i < counter; i++) {
                placeSingleOrganismRandomly(gameField, prototype.createInstance());
            }
        });
    }

    private void placeSingleOrganismRandomly(GameField gameField, Organism organism) {
        int x = new Random().nextInt(gameField.getWidth());
        int y = new Random().nextInt(gameField.getHeight());

        Cell cell = gameField.getCellAt(x, y);
        cell.addOrganism(organism);

        organism.setCoordinates(x, y);
    }
}
