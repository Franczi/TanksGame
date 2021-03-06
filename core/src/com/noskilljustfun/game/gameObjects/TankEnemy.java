package com.noskilljustfun.game.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.noskilljustfun.game.logic.EnemyAI;
import com.noskilljustfun.game.logic.EnvironmentCollisionManager;
import com.noskilljustfun.game.screens.GameScreen;

import java.util.List;
import java.util.Random;

public class TankEnemy extends Image {

    private static int enemyIdUpdater = 0;
    private int id;
    private Vector2 position;
    private int velocity;
    private Random random = new Random();
    private float moveTime;
    private int moveCounter = 0;
    private float shootCounter;
    private int counterMove = -1;
    private double bulletTime = 0.0d;
    private boolean canShoot = true;
    private boolean shouldShoot = false;


    public boolean isCanShoot() {
        return canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public TankEnemy() {
        super(new Texture("oponentTank.png"));
        id = enemyIdUpdater++;
        velocity = 5;
        position = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        setName(ObjectNames.ENEMY);
        this.setOrigin(50.0f, 50.0f);
        this.setSize(100.0f, 100.0f);
        this.setPosition(position.x, position.y);

    }

    public TankEnemy(float x, float y) {
        super(new Texture("oponentTank.png"));
        id = enemyIdUpdater++;
        setName(ObjectNames.ENEMY);
        velocity = 5;
        position = new Vector2(x, y);
        this.setOrigin(50.0f, 50.0f);
        this.setSize(100.0f, 100.0f);
        this.setPosition(position.x, position.y);
    }


    public void respawn(float x,float y){
        position = new Vector2(x,y);
        setRotation(180);
        this.setPosition(position.x, position.y);
        setX(position.x);
        setY(position.y);
        canShoot=true;

    }



    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void moveUp() {
        this.setRotation(180);
        if ((int) (position.y + getImageHeight() + velocity) <= Gdx.graphics.getHeight()) {
            if (!checkPlayerObjectCollision((int) (position.x), (int) position.y + velocity)) {
                position.y += velocity;
            }
        }
    }

    public void moveDown() {
        if ((position.y - velocity) >= 0) {
            if (!checkPlayerObjectCollision((int) (position.x), (int) (position.y - velocity))) {
                position.y -= velocity;
            }
        }
        this.setRotation(0);
    }


    public void moveLeft() {
        if ((position.x - velocity) >= 0) {
            if (!checkPlayerObjectCollision((int) (position.x - velocity), (int) position.y)) {
                position.x -= velocity;
            }
        }
        setRotation(270);

    }

    public void moveRight() {
        if ((int) (position.x + getWidth() + velocity) <= Gdx.graphics.getWidth()) {
            if (!checkPlayerObjectCollision((int) (position.x + velocity), (int) (position.y))) {
                position.x += velocity;
            }
        }
        setRotation(90);

    }

    public void update(float delta) {
        moveTime += delta;


        if (moveTime > 0.2) {
            if (moveCounter >= 20 || moveCounter == 0) {
                moveTime = 0.0f;
                counterMove = random.nextInt(4);
                moveCounter = 0;
            }
        }
        if (counterMove == 0) {
            moveUp();
            moveCounter++;
        } else if (counterMove == 1) {
            moveDown();
            moveCounter++;
        } else if (counterMove == 2) {
            moveRight();
            moveCounter++;
        } else if (counterMove == 3) {
            moveLeft();
            moveCounter++;
        }
        this.setPosition(position.x, position.y);
    }

    public void enemyShoot(float delta, Stage stage, List<Bullet> bullets) {
        shootCounter += delta;
        bulletTime = 1 + (5 - 1) * random.nextDouble();
        if (canShoot) {
            if (shootCounter > bulletTime) {
                if (EnemyAI.getInstance().shouldShoot(this)) {
                    Bullet bullet;
                    bullet = bullets.get(GameScreen.bulletCounter);
                    bullet.setShotByEnemy(true);
                    bullet.setMoving(true);
                    GameScreen.bulletCounter++;
                    bullet.initBullet(this.getPosition(), this.getRotation());
                    stage.addActor(bullet);

                    if (GameScreen.bulletCounter > GameScreen.BULLETS_COUNT - 1) {
                        GameScreen.bulletCounter = 0;
                    }
                    shootCounter = 0.0f;
                    bulletTime = 0.0f;
                }
            }
        }
    }

    public Rectangle getEnemyTankRectangle() {

        return new Rectangle(position.x, position.y, getWidth(), getHeight());
    }

    public void randomMove(){
        this.setPosition(position.x, position.y);

    }

    boolean checkPlayerObjectCollision(int x, int y){
        return EnvironmentCollisionManager
                .getInstance()
                .checkForObjectCollision(
                        this,x,y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isShouldShoot() {
        return shouldShoot;
    }

    public void setShouldShoot(boolean shouldShoot) {
        this.shouldShoot = shouldShoot;
    }
}
