package com.noskilljustfun.game.gui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameController {

    Viewport viewport;
    Stage stage;
    boolean up, down, left, right, shoot, boost; // in game screen we just checking state of button
    OrthographicCamera camera;

    public GameController(SpriteBatch batch) {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        initButtons();
    }

    private void initButtons() {

        //=== initialize table====
        Table table = new Table();
        table.left().bottom();


        //==== initialize buttons(texture, size), add on click events====
        Image upBtn = new Image(new Texture("buttons/up.png"));
        upBtn.setSize(120, 120);
        upBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                up = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                up = false;
            }
        });

        Image leftBtn = new Image(new Texture("buttons/left.png"));
        leftBtn.setSize(120, 120);
        leftBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                left = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                left = false;
            }
        });

        final Image rightBtn = new Image(new Texture("buttons/right.png"));
        rightBtn.setSize(120, 120);
        rightBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                right = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                right = false;
            }
        });
        final Image downBtn = new Image(new Texture("buttons/down.png"));
        downBtn.setSize(120, 120);
        downBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                down = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                down = false;
            }
        });

        final Image shootBtn = new Image(new Texture("buttons/a.png"));
        shootBtn.setSize(120, 120);
        shootBtn.setPosition(Gdx.graphics.getWidth()-300,100);
        shootBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                shoot = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                shoot = false;
            }
        });


        final Image boostBtn = new Image(new Texture("buttons/b.png"));
        boostBtn.setSize(120, 120);
        boostBtn.setPosition(Gdx.graphics.getWidth()-200,200);
        boostBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                shoot = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                shoot = false;
            }
        });



        //Add buttons to table. We initialize table, we need 3x3.
        // Next Step is to push our images to *-cell (look below)
        // Effect presented below.
        // [ ][*][ ]
        // [*][ ][*]
        // [ ][*][ ]

        //first row
        table.add();
        table.add(upBtn).size(upBtn.getWidth(), upBtn.getHeight());
        table.add();
        table.row();
        //secoand row
        table.add(leftBtn).size(leftBtn.getWidth(), leftBtn.getHeight());
        table.add();
        table.add(rightBtn).size(rightBtn.getWidth(), rightBtn.getHeight());
        table.row();
        //last row
        table.add();
        table.add(downBtn).size(downBtn.getWidth(), downBtn.getHeight());
        table.add();
        table.row();

        stage.addActor(table);
        stage.addActor(shootBtn);
        stage.addActor(boostBtn);
    }

    public void draw() {
        stage.draw();
    }

    public void resize(){

    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isShoot() {
        return shoot;
    }

    public boolean isBoost() {
        return boost;
    }
}
