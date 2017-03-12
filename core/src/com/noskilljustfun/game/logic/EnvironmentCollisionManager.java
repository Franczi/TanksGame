package com.noskilljustfun.game.logic;

import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;
import java.util.List;


public class EnvironmentCollisionManager {

    List<Rectangle> worldObjects;

    private static EnvironmentCollisionManager ourInstance = new EnvironmentCollisionManager();

    public static EnvironmentCollisionManager getInstance() {
        return ourInstance;
    }

    private EnvironmentCollisionManager() {
        worldObjects = new LinkedList<Rectangle>();

    }

    //this class must be initialized on game start
    //Singleton can provide us same instance att any place in app
    void init(List<Rectangle> worldObjects){
        this.worldObjects=worldObjects;
    }

    public boolean checkForObjectCollision(Rectangle object){
        for (Rectangle rectangle : worldObjects){
            if(object.overlaps(rectangle)){
                return true;
            }
        }
        return false;
    }

    public List<Rectangle> getWorldObjects() {
        return worldObjects;
    }

    public void setWorldObjects(List<Rectangle> worldObjects) {
        this.worldObjects = worldObjects;
    }
}
