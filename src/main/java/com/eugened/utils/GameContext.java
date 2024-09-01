package com.eugened.utils;

import com.eugened.entity.location.GameField;

public class GameContext {
    private static GameField gameField;

    public static void setGameField(GameField field) {
        gameField = field;
    }

    public static GameField getGameField() {
        return gameField;
    }
}
