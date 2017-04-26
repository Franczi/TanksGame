package com.noskilljustfun.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.noskilljustfun.game.TanksGame;
import com.noskilljustfun.game.logic.EnvironmentCollisionManager;



public class GameOverScreen extends BaseScreen {

    private Texture gameOver;

    public GameOverScreen(TanksGame game) {
        super(game);
        gameOver = new Texture("background/Game_Over_Screen.png");

    }


    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(gameOver
                , 0
                , 0
                , Gdx.graphics.getWidth()
                , Gdx.graphics.getHeight());

        spriteBatch.end();
        handleInput();
    }

    private void handleInput() {
        if(Gdx.input.justTouched()){
            game.setScreen(new ScoreScreen(game));
        }

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


}
