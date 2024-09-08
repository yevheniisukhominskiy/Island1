package com.eugened.utils;

import com.eugened.entity.location.GameField;
import com.eugened.entity.object.organism.animal.Animal;

import java.util.ArrayList;
import java.util.List;

public class GameContext {
    private static GameField gameField;

    public static void setGameField(GameField field) {
        gameField = field;
    }

    public static GameField getGameField() {
        return gameField;
    }
}
