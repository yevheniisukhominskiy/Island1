package com.eugened.task;

import com.eugened.entity.location.GameField;
import com.eugened.entity.object.organism.animal.Animal;
import com.eugened.utils.GameContext;

public class StatsTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Statistics for the current tick:");
        System.out.println("Animals eaten: " + Animal.getAnimalsEaten());
        System.out.println("Plants eaten: " + Animal.getPlantsEaten());
        System.out.println("Reproduction events: " + Animal.getReproductions());
        System.out.println();
    }
}
