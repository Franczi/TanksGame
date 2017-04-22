package com.noskilljustfun.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.noskilljustfun.game.TanksGame;
import com.noskilljustfun.game.gameObjects.Block;
import com.noskilljustfun.game.gameObjects.Bullet;
import com.noskilljustfun.game.gameObjects.ObjectNames;
import com.noskilljustfun.game.gameObjects.TankEnemy;
import com.noskilljustfun.game.gameObjects.TankPlayer;
import com.noskilljustfun.game.gui.GameController;
import com.noskilljustfun.game.logic.EnvironmentCollisionManager;

import java.util.LinkedList;
import java.util.List;

public class GameScreen extends BaseScreen {

    private static final int BLOCKS_AMOUNT=11;
    private static final int BULLETS_COUNT = 20;

    private GameController controller;
    private TankPlayer player;
    private TankEnemy enemy1;
    private TankEnemy enemy2;
    private TankEnemy enemy3;
    private List<Block> gameBlocks;
    private List<Bullet> bullets;
    private int bulletCounter = 0;
    private Bullet bullet;
    private float shotTime;


    public GameScreen(TanksGame game) {
        super(game);

        player = new TankPlayer();
        enemy1 = new TankEnemy();
        enemy2 = new TankEnemy(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
        enemy3 = new TankEnemy(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/3);
        initGameBlocks();
        initBullets();
        EnvironmentCollisionManager.getInstance().setStage(stage);
        stage.addActor(player);
        stage.addActor(enemy1);
        stage.addActor(enemy2);
        stage.addActor(enemy3);
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
        handleInput(delta);
        spriteBatch.begin();
        player.update();
        enemy1.update(delta);
        enemy2.update(delta);
        enemy3.update(delta);

        updateBullets();
        stage.draw();
        spriteBatch.end();
        controller.draw();
    }

    private void updateBullets() {
        for (Bullet bullet :
                bullets) {
            bullet.update();
            EnvironmentCollisionManager.getInstance().checkForBulletCollision(bullet);
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
            if (shotTime > 0.5) {
                shoot();
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
            block = new Block(3);
            block.setPosition(new Vector2(300,100*i));
            gameBlocks.add(block);
            stage.addActor(block);
            EnvironmentCollisionManager
                    .getInstance()
                    .getWorldObjects()
                    .add(block.getBlockRectangle());
        }
        for(int i =0; i<BLOCKS_AMOUNT;i++){
            block = new Block(3);
            block.setPosition(new Vector2(1400,100*i));
            gameBlocks.add(block);
            stage.addActor(block);
            EnvironmentCollisionManager
                    .getInstance()
                    .getWorldObjects()
                    .add(block.getBlockRectangle());
        }

        for(int i =0; i<BLOCKS_AMOUNT-1;i++){
            block = new Block(2);
            block.setPosition(new Vector2(400+(i*100),800));
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

    private void shoot() {
        Bullet bullet;
        bullet = bullets.get(bulletCounter);
        bulletCounter++;
        bullet.initBullet(player.getPosition(), player.getRotation());
        stage.addActor(bullet);

        if (bulletCounter >= BULLETS_COUNT - 1) {
            bulletCounter = 0;
        }
    }

    private void initBullets() {
        Bullet bullet;
        bullets= new LinkedList<Bullet>();
        for (int i = 0; i < BULLETS_COUNT; i++) {
            bullet = new Bullet();
            bullets.add(bullet);
        }
    }


}
