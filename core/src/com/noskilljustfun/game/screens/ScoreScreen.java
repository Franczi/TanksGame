package com.noskilljustfun.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.noskilljustfun.game.TanksGame;
import com.noskilljustfun.game.gui.GameController;
import com.noskilljustfun.game.logic.EnvironmentCollisionManager;
import com.badlogic.gdx.graphics.Texture;



public class ScoreScreen extends BaseScreen {


    private Label scoreTitleLabel;
    private Texture finalBackground;

    Viewport viewport;
    OrthographicCamera camera;
    Stage stage;

    public ScoreScreen(TanksGame game) {
        super(game);
        finalBackground = new Texture("background/finalScore.png");
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);
        initRestartButton();
        initScoreLabel();


    }



    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        updateScoreLabel();
        spriteBatch.draw(finalBackground
                ,0
                ,0
                , Gdx.graphics.getWidth()
                , Gdx.graphics.getHeight());
        scoreTitleLabel.draw(spriteBatch,1);


        spriteBatch.end();
        stage.draw();

     //  handleInput();
    }

    private void handleInput() {
        if(Gdx.input.justTouched()){
            game.setScreen(new MenuScreen(game));
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

    private void initScoreLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        labelStyle.fontColor = Color.BLACK;
        scoreTitleLabel = new Label("404: Connection Error",labelStyle);

        scoreTitleLabel.setX(60);
        scoreTitleLabel.setY(Gdx.graphics.getHeight() - 130 );
        scoreTitleLabel.setFontScale(5);


        stage.addActor(scoreTitleLabel);

        scoreTitleLabel.setName("FinalScoreTitleLabel");

    }
    private void updateScoreLabel(){
        scoreTitleLabel.setText("Your score: "+ EnvironmentCollisionManager.getInstance().score);

        stage.act();
    }
    private void initRestartButton(){
    final Image resetButton = new Image(new Texture("restartButton.png"));
        resetButton.setSize(300, 150);
        resetButton.setPosition(130,260);
        resetButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        stage.addActor(resetButton);
    }

}
