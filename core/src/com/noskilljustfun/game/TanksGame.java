package com.noskilljustfun.game;

import com.badlogic.gdx.Game;
import com.noskilljustfun.game.screens.MenuScreen;

public class TanksGame extends Game {

    private boolean paused;

    @Override
    public void create() {
        this.setScreen(new MenuScreen(this));
    }

    public void update(){}

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
