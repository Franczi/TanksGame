package com.noskilljustfun.game.gameObjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Random;

public class Block extends Image{
    private Vector2 position;

    public Block() {
        super();
        setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("gameObjects/block1.png"))));

        position = new Vector2(500, 500);
        this.setOrigin(50.0f, 50.0f);
        this.setSize(100.0f, 100.0f);
        this.setPosition(position.x, position.y);
    }

    public Block(int type){
        super();

        switch (type){
            case BlockType.BLOCK_1:
                setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("gameObjects/block1.png"))));
                break;
            case BlockType.BLOCK_2:
                setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("gameObjects/block2.png"))));
                break;
            case BlockType.BLOCK_3:
                setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("gameObjects/block3.png"))));
                break;
            case BlockType.BLOCK_4:
                setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("gameObjects/block4.png"))));
                break;
            default:
                setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("gameObjects/block3.png"))));
                break;
        }


        position = new Vector2(
                new Random().nextInt(Gdx.graphics.getWidth()-300)+300
                ,new Random().nextInt(Gdx.graphics.getHeight()-100)+100);
        this.setOrigin(50.0f, 50.0f);
        this.setSize(100.0f, 100.0f);
        this.setPosition(position.x, position.y);

    }

    public Rectangle getBlockRectangle(){
        return new Rectangle(position.x,position.y,getWidth(),getHeight());
    }

    @Override
    public boolean remove() {
        return super.remove();
    }
}
