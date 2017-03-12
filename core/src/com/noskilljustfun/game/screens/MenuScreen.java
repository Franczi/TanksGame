package com.noskilljustfun.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.noskilljustfun.game.TanksGame;

public class MenuScreen extends BaseScreen {

    private Texture startButton;


    public MenuScreen(TanksGame game) {
        super(game);
        startButton = new Texture("buttons/start.png");
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(startButton
                , (Gdx.graphics.getWidth()/2)-200
                , (Gdx.graphics.getHeight()/2)-200
                , 400
                , 400);

        spriteBatch.end();
        handleInput();
    }

    private void handleInput() {
        if(Gdx.input.justTouched()){
            game.setScreen(new GameScreen(game));
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
