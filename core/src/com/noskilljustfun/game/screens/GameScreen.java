package com.noskilljustfun.game.screens;

import com.noskilljustfun.game.TanksGame;
import com.noskilljustfun.game.gameObjects.Block;
import com.noskilljustfun.game.gameObjects.TankPlayer;
import com.noskilljustfun.game.gui.GameController;
import com.noskilljustfun.game.logic.EnvironmentCollisionManager;

import java.util.LinkedList;
import java.util.List;

public class GameScreen extends BaseScreen {

    private static final int BLOCKS_AMOUNT=8;

    private GameController controller;
    private TankPlayer player;
    private List<Block> gameBlocks;

    public GameScreen(TanksGame game) {
        super(game);
        player = new TankPlayer();
        initGameBlocks();
        stage.addActor(player);
        controller = new GameController(spriteBatch);
    }


    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act();
        controller.draw();
        handleInput();

        spriteBatch.begin();
        player.update();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void handleInput() {
        if (controller.isUp()) {
            player.moveUp();
        } else if (controller.isDown()) {
            player.moveDown();
        } else if (controller.isRight()) {
            player.moveRight();
        } else if (controller.isLeft()) {
            player.moveLeft();

        }
    }

    private void initGameBlocks(){
        gameBlocks = new LinkedList<Block>();
        Block block;
        for(int i =0; i<BLOCKS_AMOUNT;i++){
            block = new Block((i%4)+1);
            gameBlocks.add(block);
            stage.addActor(block);
            EnvironmentCollisionManager
                    .getInstance()
                    .getWorldObjects()
                    .add(block.getBlockRectangle());
        }
    }

}