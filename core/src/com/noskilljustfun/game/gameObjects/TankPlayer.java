package com.noskilljustfun.game.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TankPlayer extends Image {

    private Vector2 position;
    private int velocity;


    public TankPlayer() {
        super(new Texture("tank.png"));
        velocity = 5;
        position = new Vector2(400, 400);
        this.setOrigin(50.0f, 50.0f);
        this.setSize(100.0f, 100.0f);
        this.setPosition(position.x, position.y);
    }


    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void moveUp() {
        this.setRotation(180);
        position.y += velocity;
    }


    public void moveDown() {
        position.y -= velocity;
        this.setRotation(0);
    }


    public void moveLeft() {
        position.x -= velocity;
        setRotation(270);

    }

    public void moveRight() {
        position.x += velocity;
        setRotation(90);
    }

    public void update() {
        this.setPosition(position.x, position.y);
    }
}
