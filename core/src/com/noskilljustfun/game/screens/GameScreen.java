package com.noskilljustfun.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.noskilljustfun.game.TanksGame;
import com.noskilljustfun.game.gameObjects.Block;
import com.noskilljustfun.game.gameObjects.Bullet;
import com.noskilljustfun.game.gameObjects.TankPlayer;
import com.noskilljustfun.game.gui.GameController;
import com.noskilljustfun.game.logic.EnvironmentCollisionManager;

import java.util.LinkedList;
import java.util.List;

public class GameScreen extends BaseScreen {

    private static final int BLOCKS_AMOUNT=8;

    private GameController controller;
    private TankPlayer player;
    private List<Block> gameBlocks;
    private List<Bullet> bullets;
    private Bullet bullet;
    private float shotTime;
    private TiledMap levelMap;
    private TiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    public GameScreen(TanksGame game) {
        super(game);

        player = new TankPlayer();
        //initGameBlocks();
        initBullets();
        stage.addActor(player);
        controller = new GameController(spriteBatch);
        levelMap = new TmxMapLoader().load("maps/background2.tmx");
        camera = new OrthographicCamera();


        mapRenderer = new OrthogonalTiledMapRenderer(levelMap,2/1f);
    }



    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {

        super.render(delta);
        stage.act();
        handleInput(delta);
        spriteBatch.begin();
        camera.setToOrtho(false,Gdx.graphics.getHeight()*2,Gdx.graphics.getWidth());

        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();
        player.update();
        if(bullet!=null)
        bullet.update();
        stage.draw();
        spriteBatch.end();
        controller.draw();
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

    public void handleInput(float dt) {
        shotTime += dt;

        if (controller.isUp()) {
            player.moveUp();
        } else if (controller.isDown()) {
            player.moveDown();
        } else if (controller.isRight()) {
            player.moveRight();
        } else if (controller.isLeft()) {
            player.moveLeft();
        } else if (controller.isShoot()) {
            if (shotTime > 1.0) {
                Gdx.app.log("shoot", "pif-paf");
                initBullets();
                shotTime = 0.0f;
            }
        } else if (controller.isBoost()) {
            Gdx.app.log("boost", "tank is now boosted");
        }
    }

    private void initGameBlocks(){
        gameBlocks = new LinkedList<Block>();
        Block block;
        for(int i =0; i<BLOCKS_AMOUNT;i++){
            block = new Block((i%4)+1);
            gameBlocks.add(block);
            stage.addActor(block);

            EnvironmentCollisionManager
                    .getInstance()
                    .getWorldObjects()
                    .add(block.getBlockRectangle());
        }
    }

    private void initBullet(){
        bullet=new Bullet(player.getPosition(),player.getRotation());
        stage.addActor(bullet);
    }

    private void initBullets() {
        bullets= new LinkedList<Bullet>();
        for (int i = 0; i < 10; i++) {
            bullets.add(new Bullet());
        }
    }


}
