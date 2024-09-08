package com.eugened;

import com.eugened.config.AppConfigurator;
import com.eugened.constant.GameConfiguration;
import com.eugened.entity.location.Cell;
import com.eugened.entity.location.GameField;
import com.eugened.entity.object.organism.Organism;
import com.eugened.entity.object.organism.animal.Animal;
import com.eugened.entity.object.organism.animal.herbivore.creatures.Horse;
import com.eugened.entity.object.organism.animal.predator.creatures.Wolf;
import com.eugened.entity.object.organism.plant.Plant;
import com.eugened.entity.object.organism.plant.creatures.Grass;
import com.eugened.factory.OrganismPrototypeFactory;
import com.eugened.utils.GameContext;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AppConfigurator.getInstance().init();
    }

    private static void printAllAnimalsInCells(GameField gameField) {
        Cell[][] cells = gameField.getCells();

        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                Cell cell = cells[x][y];
                System.out.println("Cell (" + x + ", " + y + "):");

                for (Organism organism : cell.getOrganisms()) {
                    if (organism instanceof Animal) {
                        Animal animal = (Animal) organism;
                        System.out.println("  " + animal);
                    }
                }
            }
        }
    }

    public static void resetReproductionFlags(List<Organism> organisms) {
        for (Organism organism : organisms) {
            if (organism instanceof Animal) {
                ((Animal) organism).setHasReproduced(false);
            }
        }
    }

    public static List<String> getAnimalCoordinates(GameField field) {
        List<String> coordinates = new ArrayList<>();

        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
                Cell cell = field.getCellAt(x, y);
                for (Organism organism : cell.getOrganisms()) {
                    if (organism instanceof Animal) {
                        coordinates.add("(" + organism.getX() + ", " + organism.getY() + ")");
                    }
                }
            }
        }
        return coordinates;
    }
}
