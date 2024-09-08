package com.eugened.task;

import com.eugened.config.AppConfigurator;
import com.eugened.constant.GameConfiguration;
import com.eugened.factory.OrganismPrototypeFactory;
import com.eugened.utils.GameContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameLoop {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final ExecutorService executor = Executors.newFixedThreadPool(3);
    private final OrganismPrototypeFactory factory;

    private int currentTick = 0;
    private final int maxTicks = GameConfiguration.COUNT_TICK;
    private boolean isGameRunning = true;

    public GameLoop(OrganismPrototypeFactory factory) {
        this.factory = factory;
    }

    public void start() {
        scheduler.scheduleAtFixedRate(() -> {
            if (currentTick < maxTicks && isGameRunning) {
                System.out.println("Tick #" + (currentTick + 1));

                executor.submit(new PlantGrowTask(factory));
                executor.submit(new AnimalLifecycleTask());
                executor.submit(new StatsTask());

                currentTick++;
            } else {
                shutdown();
            }
        }, 0, GameConfiguration.TIME_TICK, TimeUnit.SECONDS);
    }

    public void shutdown() {
        try {
            scheduler.shutdown();
            executor.shutdown();

            if (!scheduler.awaitTermination(20, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }

            if (!executor.awaitTermination(20, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            executor.shutdownNow();
        }
        System.out.println("Game over!");
    }

}
