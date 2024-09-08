package com.eugened.task;

import com.eugened.entity.location.Cell;
import com.eugened.entity.location.GameField;
import com.eugened.entity.object.organism.Organism;
import com.eugened.entity.object.organism.animal.Animal;
import com.eugened.utils.GameContext;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AnimalLifecycleTask implements Runnable {
    private static final ExecutorService animalExecutor = Executors.newFixedThreadPool(10);
    @Override
    public void run() {
        List<Animal> animals = GameContext.getGameField().getAllAnimals();

        for (Animal animal : animals) {
            animalExecutor.submit(animal::play);
        }
    }

    public static void shutdown() {
        animalExecutor.shutdown();
        try {
            if (!animalExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                animalExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            animalExecutor.shutdownNow();
        }
    }
}
