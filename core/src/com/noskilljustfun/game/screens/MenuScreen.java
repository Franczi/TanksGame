package com.noskilljustfun.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.noskilljustfun.game.TanksGame;
import com.noskilljustfun.game.logic.EnvironmentCollisionManager;

public class MenuScreen extends BaseScreen {

    private Texture startButton;
    private Texture background;
    private Texture logo;
    private Texture tank;


    public MenuScreen(TanksGame game) {
        super(game);
        startButton = new Texture("buttons/start.png");
        background = new Texture("background3.png");
        logo = new Texture("logo1.png");
        tank = new Texture("tank.png");

        EnvironmentCollisionManager.getInstance().life = 3;
        EnvironmentCollisionManager.getInstance().score = 0;
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(background
                , 0
                , 0
                , Gdx.graphics.getWidth()
                , Gdx.graphics.getHeight()
        );
        spriteBatch.draw(logo
                , (Gdx.graphics.getWidth() / 2) - 600
                , (Gdx.graphics.getHeight()) - 400
                , 1200
                , 300);


        spriteBatch.draw(startButton
                , (Gdx.graphics.getWidth()/2)-175
                , (Gdx.graphics.getHeight()/2)-275
                , 350
                , 350);

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
