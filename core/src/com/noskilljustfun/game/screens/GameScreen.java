package com.noskilljustfun.game.screens;

import com.badlogic.gdx.Gdx;
import com.noskilljustfun.game.TanksGame;
import com.noskilljustfun.game.gameObjects.TankPlayer;
import com.noskilljustfun.game.gui.GameController;

public class GameScreen extends BaseScreen {

    private GameController controller;
    private TankPlayer player;
    public GameScreen(TanksGame game) {
        super(game);
        player = new TankPlayer();
        stage.addActor(player);
        controller = new GameController(spriteBatch);
    }


    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act();
        controller.draw();
        handleInput();

        spriteBatch.begin();
        player.update();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void handleInput() {
        if (controller.isUp()) {
            Gdx.app.log("Controller", "Go up boy!");
            player.moveUp();
        } else if (controller.isDown()) {
            Gdx.app.log("Controller", "Go down boy!");
            player.moveDown();
        } else if (controller.isRight()) {
            Gdx.app.log("Controller", "Go right boy!");
            player.moveRight();
        } else if (controller.isLeft()) {
            Gdx.app.log("Controller", "Go left boy!");
            player.moveLeft();
        }
    }
}
