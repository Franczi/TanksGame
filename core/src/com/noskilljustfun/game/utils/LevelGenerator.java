package com.noskilljustfun.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noskilljustfun.game.gameObjects.Block;
import com.noskilljustfun.game.logic.EnvironmentCollisionManager;
import com.noskilljustfun.game.screens.GameScreen;

import java.util.LinkedList;
import java.util.List;


public class LevelGenerator {
    private Stage stage;
    private List<Block> gameBlocks;
    Block block = new Block();

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private static final LevelGenerator ourInstance = new LevelGenerator();

    public static LevelGenerator getInstance() {
        return ourInstance;
    }

    private LevelGenerator() {

    }

    public void initLevelOne() {
        gameBlocks = new LinkedList<Block>();

        createMargin();
        for (int i = 0; i < GameScreen.BLOCKS_AMOUNT - 2; i++) {
           createBrownBlock(410 + (i * 110),800);
            if (i>=3 && i<6){
                createBrownBlock(410 + (i * 110),700);
                createBrownBlock(410 + (i * 110),100);

            }
            else{
                createBrownBlock(410 + (i * 110),500);
                createBrownBlock(410 + (i * 110),400);
                createBrownBlock(410 + (i * 110),300);
                createYellowBlock(410 + (i * 110),100);
            }
            if (i == 1 || i == 7){
                createYellowBlock(410 + (i * 110),600);
            }else if (i == 4){
                createYellowBlock(410 + (i * 110),700);
            }
        }

    }

    private void createYellowBlock(float x, float y){
        block = new Block(3);
        block.setPosition(new Vector2(x,y));
        gameBlocks.add(block);
        stage.addActor(block);
        EnvironmentCollisionManager
                .getInstance()
                .getWorldObjects()
                .add(block.getBlockRectangle());
    }
    private void createBrownBlock(float x, float y){
        block = new Block(2);
        block.setPosition(new Vector2(x,y));
        gameBlocks.add(block);
        stage.addActor(block);
        EnvironmentCollisionManager
                .getInstance()
                .getWorldObjects()
                .add(block.getBlockRectangle());
    }

    private void createMargin() {

        for (int i = 0; i < GameScreen.BLOCKS_AMOUNT; i++) {
            block = new Block(1);
            block.setPosition(new Vector2(300, 100 * i));
            gameBlocks.add(block);
            stage.addActor(block);
            EnvironmentCollisionManager
                    .getInstance()
                    .getWorldObjects()
                    .add(block.getBlockRectangle());
        }
        for (int i = 0; i < GameScreen.BLOCKS_AMOUNT; i++) {
            block = new Block(1);
            block.setPosition(new Vector2(1400, 100 * i));
            gameBlocks.add(block);
            stage.addActor(block);
            EnvironmentCollisionManager
                    .getInstance()
                    .getWorldObjects()
                    .add(block.getBlockRectangle());
        }
    }

}
