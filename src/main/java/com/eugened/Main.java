package com.eugened;

import com.eugened.entity.location.Cell;
import com.eugened.entity.location.GameField;
import com.eugened.entity.object.organism.Organism;
import com.eugened.entity.object.organism.animal.Animal;
import com.eugened.entity.object.organism.animal.herbivore.creatures.Horse;
import com.eugened.entity.object.organism.animal.predator.creatures.Wolf;
import com.eugened.utils.GameContext;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameField field = new GameField(10, 10);
        GameContext.setGameField(field);
        Cell cell = field.getCellAt(2, 2);

        Organism wolf1 = new Wolf();
        Organism wolf2 = new Wolf();
        Organism horse = new Horse();

        wolf1.setCoordinates(2, 2);
        wolf2.setCoordinates(2, 2);
        horse.setCoordinates(2, 2);

        cell.addOrganism(wolf1);
        cell.addOrganism(wolf2);
        cell.addOrganism(horse);

        Cell current = wolf1.getCurrentCell(field);

        List<Organism> organisms = new ArrayList<>(current.getOrganisms());
        System.out.println("Organisms in cell: " + organisms);
        //resetReproductionFlags(organisms);

        for (Organism organism : organisms) {
            organism.play();
        }

        List<Organism> global = current.getOrganisms();
        System.out.println("Organisms in cell: " + global);

        // Получаем координаты всех животных
        List<String> animalCoordinates = getAnimalCoordinates(field);

        // Выводим координаты
        System.out.println("Animal coordinates:");
        for (String coordinate : animalCoordinates) {
            System.out.println(coordinate);
        }
    }

    public static void resetReproductionFlags(List<Organism> organisms) {
        for (Organism organism : organisms) {
            if (organism instanceof Animal) {
                ((Animal) organism).setHasReproduced(false);
            }
        }
    }

    // Метод для получения координат всех животных
    public static List<String> getAnimalCoordinates(GameField field) {
        List<String> coordinates = new ArrayList<>();

        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
                Cell cell = field.getCellAt(x, y);
                for (Organism organism : cell.getOrganisms()) {
                    if (organism instanceof Animal) { // Проверяем, что организм является животным
                        coordinates.add("(" + organism.getX() + ", " + organism.getY() + ")");
                    }
                }
            }
        }
        return coordinates;
    }
}
