package com.noskilljustfun.game.screens;

import com.badlogic.gdx.Gdx;
import com.noskilljustfun.game.TanksGame;
import com.noskilljustfun.game.gameObjects.Block;
import com.noskilljustfun.game.gameObjects.Bullet;
import com.noskilljustfun.game.gameObjects.TankEnemy;
import com.noskilljustfun.game.gameObjects.TankPlayer;
import com.noskilljustfun.game.gui.GameController;
import com.noskilljustfun.game.logic.EnvironmentCollisionManager;

import java.util.LinkedList;
import java.util.List;

public class GameScreen extends BaseScreen {

    private static final int BLOCKS_AMOUNT=8;

    private GameController controller;
    private TankPlayer player;
    private TankEnemy enemy;
    private List<Block> gameBlocks;
    private List<Bullet> bullets;
    private Bullet bullet;
    private float shotTime;


    public GameScreen(TanksGame game) {
        super(game);

        player = new TankPlayer();
        enemy = new TankEnemy();
        initGameBlocks();
        initBullets();
        stage.addActor(player);
        stage.addActor(enemy);
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
        enemy.update();
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
                initBullet();
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
        
    }


}
