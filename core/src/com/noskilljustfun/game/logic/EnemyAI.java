package com.noskilljustfun.game.logic;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noskilljustfun.game.gameObjects.ObjectDirection;
import com.noskilljustfun.game.gameObjects.ObjectNames;
import com.noskilljustfun.game.gameObjects.TankEnemy;

import java.util.ArrayList;
import java.util.List;

public class EnemyAI {

    private Stage stage;

    private static final EnemyAI ourInstance = new EnemyAI();

    public static EnemyAI getInstance() {
        return ourInstance;
    }

    private EnemyAI() {
    }

    public Stage getStage() {
        return stage;
    }

    public void init(Stage stage) {
        this.stage = stage;
    }

    public boolean shouldShoot(TankEnemy enemy) {

        Vector2 centerPointOfEnemy = new Vector2(enemy.getX() / 2, enemy.getY() / 2);

        List<Vector2> interactionsObject = new ArrayList<Vector2>();


        switch ((int) enemy.getRotation()) {
            case ObjectDirection.UP:
                Gdx.app.log("UP", "look UP");
                for (Actor actor : stage.getActors()) {

                    if (actor.getName().contains(ObjectNames.BLOCK_WOOD) || actor.getName().contains(ObjectNames.PLAYER)) {
                        if (centerPointOfEnemy.x > actor.getX() && centerPointOfEnemy.x < actor.getX() + actor.getWidth() && centerPointOfEnemy.y > actor.getY()) {
                            //shoot
                            //interactionsObject.add(new Vector2(actor.getX(),actor.getY()));
                        }
                    }
                }
                break;
            case ObjectDirection.DOWN:
                Gdx.app.log("DOWN", "look DOWN");
                for (Actor actor : stage.getActors()) {

                    if (actor.getName().contains(ObjectNames.BLOCK_WOOD) || actor.getName().contains(ObjectNames.PLAYER)) {
                        if (centerPointOfEnemy.x > actor.getX() && centerPointOfEnemy.x < actor.getX() + actor.getWidth() && centerPointOfEnemy.y > actor.getY()) {
                            //shoot
                            //interactionsObject.add(new Vector2(actor.getX(),actor.getY()));
                        }
                    }
                }
                break;
            case ObjectDirection.LEFT:
                Gdx.app.log("LEFT", "look LEFT");
                for (Actor actor : stage.getActors()) {

                    if (actor.getName().contains(ObjectNames.BLOCK_WOOD) || actor.getName().contains(ObjectNames.PLAYER)) {
                        if (centerPointOfEnemy.x > actor.getX() && centerPointOfEnemy.x < actor.getX() + actor.getWidth() && centerPointOfEnemy.y > actor.getY()) {
                            //shoot
                            //interactionsObject.add(new Vector2(actor.getX(),actor.getY()));
                        }
                    }
                }
                break;
            case ObjectDirection.RIGHT:
                Gdx.app.log("RIGHT", "look RIGHT");
                for (Actor actor : stage.getActors()) {

                    if (actor.getName().contains(ObjectNames.BLOCK_WOOD) || actor.getName().contains(ObjectNames.PLAYER)) {
                        if (centerPointOfEnemy.x > actor.getX() && centerPointOfEnemy.x < actor.getX() + actor.getWidth() && centerPointOfEnemy.y > actor.getY()) {
                            //shoot
                            //interactionsObject.add(new Vector2(actor.getX(),actor.getY()));
                        }
                    }
                }
                break;
            default:
                Gdx.app.log("default", "look nowhere");
                return false;

        }


        return true;
    }


}
