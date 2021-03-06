package com.noskilljustfun.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.noskilljustfun.game.TanksGame;
import com.noskilljustfun.game.gameObjects.Block;
import com.noskilljustfun.game.gameObjects.Bullet;
import com.noskilljustfun.game.gameObjects.TankEnemy;
import com.noskilljustfun.game.gameObjects.TankPlayer;
import com.noskilljustfun.game.gui.GameController;
import com.noskilljustfun.game.logic.EnemyAI;
import com.noskilljustfun.game.logic.EnvironmentCollisionManager;
import com.noskilljustfun.game.utils.LevelGenerator;

import java.util.LinkedList;
import java.util.List;

public class GameScreen extends BaseScreen {

    public static final int BLOCKS_AMOUNT = 11;
    public static final int BULLETS_COUNT = 20;
    public static int bulletCounter = 0;

    private GameController controller;
    private TankPlayer player;
    private TankEnemy enemy1;
    private TankEnemy enemy2;
    private TankEnemy enemy3;
    private List<Block> gameBlocks;
    private List<Bullet> bullets;
    private Label scoreLabel;
    private Label playerLifeLabel;
    private Label ammoLabel;
    private Texture background;

    private Bullet bullet;
    private float shotTime;



    public GameScreen(TanksGame game) {
        super(game);

        player = new TankPlayer();
        enemy1 = new TankEnemy(850,900);
        enemy2 = new TankEnemy(410, 900);
        enemy3 = new TankEnemy(1290,900);
        background = new Texture("background1.jpg");
        initBullets();
        initPlayerLifeLabel();
        initScoreLabel();
        initAmmoLabel();
        LevelGenerator.getInstance().setStage(stage);
        LevelGenerator.getInstance().initLevelOne();
        EnvironmentCollisionManager.getInstance().setStage(stage);
        stage.addActor(player);
        stage.addActor(enemy1);
        stage.addActor(enemy2);
        stage.addActor(enemy3);
        controller = new GameController(spriteBatch);
        EnvironmentCollisionManager.getInstance().life = 3;
        EnvironmentCollisionManager.getInstance().score = 0;
        EnvironmentCollisionManager.getInstance().ammo = 20;
        EnemyAI.getInstance().init(stage);
    }



    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {

        super.render(delta);

        spriteBatch.begin();
        stage.getBatch().begin();
        stage.getBatch().draw(background, 370, 0, (float) 1100, (float) Gdx.graphics.getHeight());
        stage.getBatch().end();

        stage.act();
        handleInput(delta);
        updateScoreLabel();
        updatePlayerLifeLabel();
        updateAmmoLabel();


        player.update();
        enemy1.update(delta);
        enemy2.update(delta);
        enemy3.update(delta);
        enemy1.enemyShoot(delta,this.stage,this.bullets);
        enemy2.enemyShoot(delta,this.stage,this.bullets);
        enemy3.enemyShoot(delta,this.stage,this.bullets);
        updateBullets();


        if (EnvironmentCollisionManager.getInstance().life == 0 || EnvironmentCollisionManager.getInstance().ammo == 0) {
            game.setScreen(new GameOverScreen(game));
        }


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
        }

        if (controller.isShoot()) {
            if (shotTime > 0.5) {
                if (player.isCanShoot()) {
                    EnvironmentCollisionManager.getInstance().ammo--;
                    shoot();
                }
                shotTime = 0.0f;
            }
        }
    }


    private void initScoreLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        labelStyle.fontColor = Color.BLACK;
        scoreLabel = new Label("", labelStyle);
        scoreLabel.setX(20);
        scoreLabel.setY(950);
        scoreLabel.setFontScale(3);


        stage.addActor(scoreLabel);
        scoreLabel.setName("ScoreLabel");
    }
    private void initAmmoLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        labelStyle.fontColor = Color.BLACK;
        ammoLabel = new Label("", labelStyle);
        ammoLabel.setX(20);
        ammoLabel.setY(700);
        ammoLabel.setFontScale(3);


        stage.addActor(ammoLabel);
        ammoLabel.setName("AmmoLabel");
    }
    private void initPlayerLifeLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        labelStyle.fontColor = Color.BLACK;
        playerLifeLabel = new Label("", labelStyle);
        playerLifeLabel.setX(1510);
        playerLifeLabel.setY(950);
        playerLifeLabel.setFontScale(3);


        stage.addActor(playerLifeLabel);
        playerLifeLabel.setName("PlayerLiveLabel");
    }


    private void initBullet(){
        bullet=new Bullet(player.getPosition(),player.getRotation());
        stage.addActor(bullet);
    }

    private void shoot() {
        Bullet bullet;
        bullet = bullets.get(bulletCounter);
        bullet.setShotByEnemy(false);
        bullet.setMoving(true);
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

    private void updateScoreLabel(){

        scoreLabel.setText("Score: " + EnvironmentCollisionManager.getInstance().score );
        stage.act();
    }
    private void updatePlayerLifeLabel(){

        playerLifeLabel.setText("Life: " + EnvironmentCollisionManager.getInstance().life );
        stage.act();
    }
    private void updateAmmoLabel(){

        ammoLabel.setText("Ammo: " + EnvironmentCollisionManager.getInstance().ammo );
        stage.act();
    }


}
