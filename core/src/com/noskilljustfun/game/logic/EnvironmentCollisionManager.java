package com.noskilljustfun.game.logic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noskilljustfun.game.gameObjects.Block;
import com.noskilljustfun.game.gameObjects.Bullet;
import com.noskilljustfun.game.gameObjects.ObjectNames;
import com.noskilljustfun.game.gameObjects.TankEnemy;
import com.noskilljustfun.game.gameObjects.TankPlayer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class EnvironmentCollisionManager {

    List<Rectangle> worldObjects;
    List<Bullet> bullets;
    List<Block> blocks;
    Stage stage;
    private Random random = new Random();
    public int score = 0;
    public int life = 3;
    public int ammo = 20;

    private static EnvironmentCollisionManager ourInstance = new EnvironmentCollisionManager();

    public static EnvironmentCollisionManager getInstance() {
        return ourInstance;
    }

    private EnvironmentCollisionManager() {
        worldObjects = new LinkedList<Rectangle>();
        bullets = new LinkedList<Bullet>();
        blocks = new LinkedList<Block>();
    }

    //this class must be initialized on game start
    //Singleton can provide us same instance att any place in app
    void init(List<Rectangle> worldObjects) {
        this.worldObjects = worldObjects;
    }

    public boolean checkForObjectCollision(TankPlayer object, int nextX, int nextY) {
        Rectangle rectangle = object.getPlayerTankRectangle();
        rectangle.setPosition(nextX, nextY);
        for (Actor worldObjects : stage.getActors()) {
            boolean collision = new Rectangle(worldObjects.getX(), worldObjects.getY(), worldObjects.getWidth(), worldObjects.getHeight()).overlaps(rectangle);
            if (collision) {
                if (!worldObjects.getName().equals(object.getName()))
                    return true;
            }
        }
        return false;
    }

    public boolean checkForObjectCollision(TankEnemy object, int nextX, int nextY) {
        Rectangle rectangle = object.getEnemyTankRectangle();
        rectangle.setPosition(nextX, nextY);
        for (Actor actor : stage.getActors()) {
            boolean collision = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight()).overlaps(rectangle);
            if (actor.getName().equals(ObjectNames.ENEMY)) {
                if (((TankEnemy) actor).getId() != object.getId()) {
                    if (collision) {
                        return true;
                    }
                }
            }
            if (collision) {
                if (!actor.getName().equals(object.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void checkForBulletCollision(Bullet bullet) {

        if (!bullet.isMoving()) return;

        Rectangle bulletRect = bullet.getBulletRectangle();
        for (Actor actor : stage.getActors()) {
            boolean collision = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight()).overlaps(bulletRect);

            if (collision) {
                if (!actor.getName().equals(bullet.getName())
                        && !actor.getName().equals(ObjectNames.BLOCK_YELLOW)
                        && !actor.getName().equals(ObjectNames.BLOCK_RED)
                        && !actor.getName().equals(ObjectNames.BLOCK_METAL)
                        && !actor.getName().equals(ObjectNames.ENEMY)) {

                    actor.remove();
                    if (actor.getName().contains(ObjectNames.BULLET)) {
                        ((Bullet) actor).setMoving(false);
                    }
                }

                if (!actor.getName().equals(bullet.getName())) {
                    if (stage.getRoot().findActor(bullet.getName()) != null) {
                        stage.getRoot().findActor(bullet.getName()).remove();
                        bullet.setMoving(false);
                    }
                }

                if (actor.getName().equals(ObjectNames.ENEMY)) {
                    if (!bullet.isShotByEnemy()) {
                        actor.remove();
                        score += 10;
                        ammo += 5;
                        ((TankEnemy) actor).setCanShoot(false);
                        if (life != 0){
                            double respawnPosition = 0.0f;
                            respawnPosition = 1 + (4 - 1) * random.nextDouble();
                            if (respawnPosition <=2.0){
                                ((TankEnemy) actor).respawn(850,900);
                            }else if (respawnPosition <=3.0 && respawnPosition > 2.0){
                                ((TankEnemy) actor).respawn(410, 900);
                            }else {
                                ((TankEnemy) actor).respawn(1290,900);
                            }
                            stage.addActor(actor);
                        }

                    }
                }
                if (actor.getName().equals(ObjectNames.PLAYER)) {
                    ((TankPlayer) actor).setCanShoot(false);
                    life--;
                    if (life > 0) {
                        ((TankPlayer) actor).respawn();
                        stage.addActor(actor);
                    }
                }


            }
        }

    }


    public List<Rectangle> getWorldObjects() {
        return worldObjects;
    }

    public void setWorldObjects(List<Rectangle> worldObjects) {
        this.worldObjects = worldObjects;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
