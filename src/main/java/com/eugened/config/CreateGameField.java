package com.eugened.config;

import com.eugened.constant.GameConfiguration;
import com.eugened.entity.location.GameField;

public class CreateGameField {
    private static CreateGameField instance;
    private GameField gameField;

    private CreateGameField() {
        // Используем значения из GameConfiguration для инициализации игрового поля
        this.gameField = new GameField(GameConfiguration.HEIGHT, GameConfiguration.WIDTH);
    }

    public static CreateGameField getInstance() {
        if (instance == null) {
            instance = new CreateGameField();
        }
        return instance;
    }

    public GameField getGameField() {
        return gameField;
    }
}

