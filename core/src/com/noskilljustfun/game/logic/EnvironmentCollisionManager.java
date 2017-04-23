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


public class EnvironmentCollisionManager {

    List<Rectangle> worldObjects;
    List<Bullet> bullets;
    List<Block> blocks;
    Stage stage;

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
    void init(List<Rectangle> worldObjects){
        this.worldObjects=worldObjects;
    }

    public boolean checkForObjectCollision(TankPlayer object, int nextX, int nextY) {
        Rectangle rectangle = object.getPlayerTankRectangle();
        rectangle.setPosition(nextX,nextY);
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
        rectangle.setPosition(nextX,nextY);
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

                if(!actor.getName().equals(bullet.getName()) ) {
                    if (stage.getRoot().findActor(bullet.getName()) != null) {
                        stage.getRoot().findActor(bullet.getName()).remove();
                        bullet.setMoving(false);
                    }
                }

                if (actor.getName().equals(ObjectNames.ENEMY)) {
                    if (!bullet.isShotByEnemy()) {
                        actor.remove();
                        ((TankEnemy) actor).setCanShoot(false);

                    }
                }
                if (actor.getName().equals(ObjectNames.PLAYER)) {
                    ((TankPlayer) actor).setCanShoot(false);
                    // TODO: 23.04.2017 bw end game or decrement life points
                    ((TankPlayer) actor).respawn();
                    stage.addActor(actor);

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
