package com.noskilljustfun.game.utils;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Kacper Wysocki on 23.04.2017.
 */

public class LevelGenerator {
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private static final LevelGenerator ourInstance = new LevelGenerator();

    public static LevelGenerator getInstance() {
        return ourInstance;
    }

    private LevelGenerator() {
    }

}
