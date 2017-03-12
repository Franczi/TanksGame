package com.noskilljustfun.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.noskilljustfun.game.TanksGame;


public abstract class BaseScreen implements Screen {

    private OrthographicCamera orthographicCamera;
    protected Stage stage ;
    protected SpriteBatch spriteBatch;
    protected TanksGame game;

    public BaseScreen(TanksGame game) {
        this.game = game;
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        orthographicCamera.update();
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth()
                                                ,Gdx.graphics.getHeight()
                                                ,orthographicCamera));


        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        orthographicCamera.update();
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.dispose();

    }
}
