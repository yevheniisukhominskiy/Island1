package com.eugened.config;

import com.eugened.constant.GameConfiguration;
import com.eugened.entity.location.Cell;
import com.eugened.entity.location.GameField;
import com.eugened.factory.OrganismPrototypeFactory;
import com.eugened.task.AnimalLifecycleTask;
import com.eugened.task.GameLoop;
import com.eugened.task.PlantGrowTask;
import com.eugened.task.StatsTask;
import com.eugened.utils.GameContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AppConfigurator {
    private static AppConfigurator instance;

    private final ObjectsScanner objectsScanner = ObjectsScanner.getInstance();
    private final PrototypeLoader prototypeLoader = PrototypeLoader.getInstance();
    private final OrganismPrototypeFactory organismPrototypeFactory = OrganismPrototypeFactory.getInstance();
    private final OrganismPlacer organismPlacer = OrganismPlacer.getInstance();
    private final GameLoop gameLoop = new GameLoop(organismPrototypeFactory);

    private AppConfigurator() {
        OrganismPlacer.setOrganismPrototypeFactory(organismPrototypeFactory);
    }

    public static AppConfigurator getInstance() {
        if (instance == null) {
            instance = new AppConfigurator();
        }
        return instance;
    }

    public void init() {
        registerPrototypes();
        initializeGameField();
        placeOrganismsOnField();
        startGameLoop();
    }

    public void registerPrototypes() {
        objectsScanner.getAllGameObjectsClasses()
                .stream()
                .map(prototypeLoader::loadPrototype)
                .forEach(organismPrototypeFactory::registerPrototype);
    }

    private void initializeGameField() {
        GameField gameField = CreateGameField.getInstance().getGameField();
        GameContext.setGameField(gameField);
    }

    private void placeOrganismsOnField() {
        organismPlacer.placeOrganismRandomly(GameContext.getGameField());
    }

    private void startGameLoop() {
        gameLoop.start();
    }
}
