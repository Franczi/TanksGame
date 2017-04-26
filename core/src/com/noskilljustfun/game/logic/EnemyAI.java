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

        Vector2 centerPointOfEnemy = new Vector2(enemy.getX() + (enemy.getWidth() / 2), enemy.getY() + (enemy.getHeight() / 2));
        List<Actor> interactionsObject = new ArrayList<Actor>();

        switch ((int) enemy.getRotation()) {
            case ObjectDirection.UP:
                for (Actor actor : stage.getActors()) {
                    if (centerPointOfEnemy.x > actor.getX() && centerPointOfEnemy.x < actor.getX() + actor.getWidth() && centerPointOfEnemy.y < actor.getY()) {
                        interactionsObject.add(actor);
                        }
                }
                break;
            case ObjectDirection.DOWN:
                for (Actor actor : stage.getActors()) {
                    if (centerPointOfEnemy.x > actor.getX() && centerPointOfEnemy.x < actor.getX() + actor.getWidth() && centerPointOfEnemy.y > actor.getY()) {
                        interactionsObject.add(actor);
                        }
                }
                break;
            case ObjectDirection.LEFT:
                for (Actor actor : stage.getActors()) {
                    if (centerPointOfEnemy.y > actor.getY() && centerPointOfEnemy.y < actor.getY() + actor.getHeight() && centerPointOfEnemy.x > actor.getX()) {
                        interactionsObject.add(actor);
                        }

                }
                break;
            case ObjectDirection.RIGHT:
                for (Actor actor : stage.getActors()) {
                    if (centerPointOfEnemy.y > actor.getY() && centerPointOfEnemy.y < actor.getY() + actor.getHeight() && centerPointOfEnemy.x < actor.getX()) {
                        interactionsObject.add(actor);
                        }

                }
                break;
            default:
                Gdx.app.log("default", "look nowhere");
                return false;

        }

        Gdx.app.log("Tank " + String.valueOf(enemy.getId()) + " size", String.valueOf(interactionsObject.size()));
        for (Actor actor :
                interactionsObject) {
            if (actor.getName().equals(ObjectNames.PLAYER) || actor.getName().equals(ObjectNames.BLOCK_WOOD)) {
                return true;
            }
        }
        Gdx.app.log("Tank " + String.valueOf(enemy.getId()) + " size", "nothing");
        interactionsObject.clear();
        return false;
    }


}
