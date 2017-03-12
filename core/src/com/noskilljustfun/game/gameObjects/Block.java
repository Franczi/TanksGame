package com.noskilljustfun.game.gameObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Block extends Image{
    private Vector2 position;

    public Block() {
        super(new Texture("gameObjects/block1.png"));
        position = new Vector2(500, 500);
        this.setOrigin(50.0f, 50.0f);
        this.setSize(100.0f, 100.0f);
        this.setPosition(position.x, position.y);
    }
}
