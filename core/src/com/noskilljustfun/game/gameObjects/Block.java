package com.noskilljustfun.game.gameObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Block extends Image{
    private Vector2 position;

    public Block() {
        super();
        setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("gameObjects/block1.png"))));
        setName(ObjectNames.BLOCK_WOOD);
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
                setName(ObjectNames.BLOCK_METAL);
                break;
            case BlockType.BLOCK_2:
                setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("gameObjects/block2.png"))));
                setName(ObjectNames.BLOCK_WOOD);
                break;
            case BlockType.BLOCK_3:
                setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("gameObjects/block3.png"))));
                setName(ObjectNames.BLOCK_YELLOW);
                break;
            case BlockType.BLOCK_4:
                setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("gameObjects/block4.png"))));
                setName(ObjectNames.BLOCK_RED);
                break;
            default:
                setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("gameObjects/block3.png"))));
                break;
        }

//
//        position = new Vector2(
//                new Random().nextInt(Gdx.graphics.getWidth()-300)+300
//                ,new Random().nextInt(Gdx.graphics.getHeight()-100)+100);
        this.setOrigin(50.0f, 50.0f);
        this.setSize(100.0f, 100.0f);
//        this.setPosition(position.x, position.y);

    }

    public Rectangle getBlockRectangle(){
        return new Rectangle(position.x,position.y,getWidth(),getHeight());
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        setPosition(position.x, position.y);
    }
}
