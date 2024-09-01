package com.eugened.entity.object.organism.animal;

import com.eugened.entity.location.Cell;
import com.eugened.entity.object.ability.Edible;
import com.eugened.entity.object.ability.Movable;
import com.eugened.entity.object.ability.Reproducible;
import com.eugened.entity.object.organism.Organism;
import com.eugened.utils.GameContext;

import java.util.*;

public abstract class Animal extends Organism implements Edible, Movable, Reproducible {
    private int speed;
    private Map<Class<? extends Organism>, Double> eatingProbabilities = new HashMap<>();
    private boolean hasReproduced = false;

    public boolean hasReproduced() {
        return hasReproduced;
    }

    public void setHasReproduced(boolean hasReproduced) {
        this.hasReproduced = hasReproduced;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
                double probability = getEatingProbability(organism.getClass());

                if (new Random().nextDouble(100) < probability) {
                    currentCell.removeOrganism(organism);
                    organism.setAlive(false);
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

        long sameOrganismCounter = currentCell.getOrganisms().stream()
                .filter(organism -> organism.getClass().equals(this.getClass()) && !((Animal) organism).hasReproduced)
                .count();

        boolean willReproduce = new Random().nextBoolean();
        // TODO: вместо 10, должно загрузить макс. плотность if (sameOrganismCounter > 1 && sameOrganismCounter < getSaturation())
        if (sameOrganismCounter > 1 && sameOrganismCounter < 10 && willReproduce) {
            try {
                Animal baby = this.getClass().getDeclaredConstructor().newInstance();
                baby.setCoordinates(this.getX(), this.getY());
                currentCell.addOrganism(baby);

                baby.setHasReproduced(true);
                this.setHasReproduced(true);
                currentCell.getOrganisms().stream()
                        .filter(organism -> organism.getClass().equals(this.getClass()) && !((Animal) organism).hasReproduced())
                        .findFirst()
                        .ifPresent(organism -> ((Animal) organism).setHasReproduced(true));
            } catch (Exception e) {
                System.err.println(e + ": couldn't reproduce organism");
            }
        }
    }
}
