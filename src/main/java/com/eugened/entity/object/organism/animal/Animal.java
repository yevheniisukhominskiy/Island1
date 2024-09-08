package com.eugened.entity.object.organism.animal;

import com.eugened.entity.location.Cell;
import com.eugened.entity.object.ability.Edible;
import com.eugened.entity.object.ability.Movable;
import com.eugened.entity.object.ability.Reproducible;
import com.eugened.entity.object.organism.Organism;
import com.eugened.entity.object.organism.plant.Plant;
import com.eugened.utils.ClassKeyMapDeserializer;
import com.eugened.utils.GameContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.*;

public abstract class Animal extends Organism implements Edible, Movable, Reproducible {
    private static int animalsEaten = 0;
    private static int plantsEaten = 0;
    private static int reproductions = 0;

    private int speed;
    @JsonDeserialize(using = ClassKeyMapDeserializer.class)
    private Map<Class<? extends Organism>, Double> eatingProbabilities = new HashMap<>();
    private boolean hasReproduced = false;

    public static int getAnimalsEaten() {
        return animalsEaten;
    }

    public static int getPlantsEaten() {
        return plantsEaten;
    }

    public static int getReproductions() {
        return reproductions;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Map<Class<? extends Organism>, Double> getEatingProbabilities() {
        return eatingProbabilities;
    }

    public void setEatingProbabilities(Map<Class<? extends Organism>, Double> eatingProbabilities) {
        this.eatingProbabilities = eatingProbabilities;
    }

    public boolean hasReproduced() {
        return hasReproduced;
    }

    public void setHasReproduced(boolean hasReproduced) {
        this.hasReproduced = hasReproduced;
    }

    @Override
    public void play() {
        if (isAlive()) {
            eat();
            reproduce();
            move();
        }
    }

    protected void setEatingProbability(Class<? extends Organism> organismClass, double probability) {
        eatingProbabilities.put(organismClass, probability);
    }

    private double getEatingProbability(Class<? extends Organism> preyClass) {
        return eatingProbabilities.getOrDefault(preyClass, 0.0);
    }



    @Override
    public void eat() {
        Cell currentCell = getCurrentCell(GameContext.getGameField());

        if (currentCell == null) {
            return;
        }

        List<Organism> organisms = currentCell.getOrganisms();
        for (Organism organism : organisms) {
            if (organism != this && eatingProbabilities.containsKey(organism.getClass())) {
                double probability = eatingProbabilities.getOrDefault(organism.getClass(), 0.0);

                if (probability > 0 && new Random().nextDouble() < probability) {
                    currentCell.removeOrganism(organism);
                    organism.setAlive(false);

                    if (organism instanceof Animal) {
                        animalsEaten++;
                    } else if (organism instanceof Plant) {
                        plantsEaten++;
                    }

                    return;
                }
            }
        }
    }

    @Override
    public void move() {
        Cell currentCell = getCurrentCell(GameContext.getGameField());

        if (currentCell == null) {
            return;
        }

        int currentX = getX();
        int currentY = getY();
        int maxMoves = getSpeed();

        Random random = new Random();
        int movesMade = 0;

        while (movesMade < maxMoves) {
            boolean validMovement = false;

            while (!validMovement) {
                int[] directions = {-1, 0, 1};
                int dx = directions[random.nextInt(directions.length)];
                int dy = directions[random.nextInt(directions.length)];

                int newX = currentX + dx;
                int newY = currentY + dy;

                if (newX >= 0 && newX < GameContext.getGameField().getWidth() &&
                        newY >= 0 && newY < GameContext.getGameField().getHeight()) {
                    Cell newCell = GameContext.getGameField().getCellAt(newX, newY);

                    currentCell.removeOrganism(this);
                    newCell.addOrganism(this);
                    setCoordinates(newX, newY);

                    currentX = newX;
                    currentY = newY;
                    currentCell = newCell;
                    validMovement = true;
                }
            }
            movesMade++;
        }
    }



    @Override
    public void reproduce() {
        if (this.hasReproduced()) {
            return;
        }

        Cell currentCell = getCurrentCell(GameContext.getGameField());

        if (currentCell == null) {
            return;
        }

        List<Organism> organismsCopy = new ArrayList<>(currentCell.getOrganisms());

        long sameOrganismCounter = currentCell.getOrganisms().stream()
                .filter(organism -> organism.getClass().equals(this.getClass()) && !((Animal) organism).hasReproduced)
                .count();

        boolean willReproduce = new Random().nextBoolean();
        if (sameOrganismCounter > 1 && sameOrganismCounter < getSaturation() && willReproduce) {
            try {
                Animal baby = this.getClass().getDeclaredConstructor().newInstance();
                baby.setCoordinates(this.getX(), this.getY());
                currentCell.addOrganism(baby);

                baby.setHasReproduced(true);
                this.setHasReproduced(true);
                organismsCopy.stream()
                        .filter(organism -> organism.getClass().equals(this.getClass()) && !((Animal) organism).hasReproduced())
                        .findFirst()
                        .ifPresent(organism -> ((Animal) organism).setHasReproduced(true));
                reproductions++;
            } catch (Exception e) {
                System.err.println(e + ": couldn't reproduce organism");
            }
        }


    }
}
