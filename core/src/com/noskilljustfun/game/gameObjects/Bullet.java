package com.noskilljustfun.game.gameObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bullet extends Image {

    private static int bulletId = 0;

    private int velocity;
    private Vector2 position;
    private float rotation;
    private boolean isMoving = false;



    public Bullet(Vector2 startingPosition,float rotation) {
        super(new Texture("gameObjects/bullet.png"));
        bulletId++;
        this.rotation=rotation;
        velocity = 10;
        position= new Vector2();
        position.x = startingPosition.x;
        position.y=startingPosition.y;
        this.setOrigin(20.0f, 20.0f);
        this.setSize(40.0f, 40.0f);
        this.setPosition(position.x, position.y);
        initDirection((int)rotation);
        setName(ObjectNames.BULLET + String.valueOf(bulletId));
    }

    public Bullet() {
        super(new Texture("gameObjects/bullet.png"));
        bulletId++;
        velocity = 10;
        position=new Vector2();
        this.setOrigin(20.0f, 20.0f);
        this.setSize(40.0f, 40.0f);
        setName(ObjectNames.BULLET + String.valueOf(bulletId));

    }

    public void initBullet(Vector2 startingPosition, float rotation) {
        this.rotation = rotation;
        velocity = 10;
        position = new Vector2();
        position.x = startingPosition.x+1;
        position.y = startingPosition.y+101;
        this.setOrigin(20.0f, 20.0f);
        this.setSize(40.0f, 40.0f);
        this.setPosition(position.x, position.y);
        initDirection((int) rotation);
    }

    void initDirection(int direction){
        switch (direction){
            case ObjectDirection.UP:
                this.setRotation(90);
                break;
            case ObjectDirection.DOWN:
                this.setRotation(270);
                break;
            case ObjectDirection.RIGHT:
                this.setRotation(0);
                break;
            case ObjectDirection.LEFT:
                this.setRotation(180);
                break;
        }
    }

    public void update(){
        switch ((int)rotation){
            case ObjectDirection.UP:
                position.y+=velocity;
                break;
            case ObjectDirection.DOWN:
                position.y-=velocity;
                break;
            case ObjectDirection.RIGHT:
                position.x+=velocity;
                break;
            case ObjectDirection.LEFT:
                position.x-=velocity;
                break;
            default:
                position.x-=velocity;
                break;
        }
        this.setPosition(position.x,position.y);
    }

    public Rectangle getBulletRectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void setBulletPosition(Vector2 positionVector){
        position = positionVector;
        this.setPosition(position.x,position.y);
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
}
