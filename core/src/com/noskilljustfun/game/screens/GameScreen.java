package com.noskilljustfun.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.noskilljustfun.game.TanksGame;
import com.noskilljustfun.game.gameObjects.Block;
import com.noskilljustfun.game.gameObjects.Bullet;
import com.noskilljustfun.game.gameObjects.TankPlayer;
import com.noskilljustfun.game.gui.GameController;
import com.noskilljustfun.game.logic.EnvironmentCollisionManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameScreen extends BaseScreen {

    private static final int BLOCKS_AMOUNT=8;

    private GameController controller;
    private TankPlayer player;
    private List<Block> gameBlocks;
    private List<Bullet> bullets;
    private Bullet bullet;
    private float shotTime;
    private TiledMap levelMap;
    private TiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;
    TiledMapTileLayer collisionLayer;
    MapObjects objects;
    List<Rectangle> tiles;
    public GameScreen(TanksGame game) {
        super(game);

        player = new TankPlayer();
        //initBullets();
        controller = new GameController(spriteBatch);
        levelMap = new TmxMapLoader().load("maps/background5.tmx");
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mapRenderer = new OrthogonalTiledMapRenderer(levelMap);
        MapProperties prop = levelMap.getProperties();
        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);

        int mapPixelWidth = mapWidth * tilePixelWidth;
        int mapPixelHeight = mapHeight * tilePixelHeight;
        Gdx.app.log("width+heigth", mapPixelHeight + "+" + mapPixelWidth);

        collisionLayer = (TiledMapTileLayer) levelMap.getLayers().get("boundries");
//        objects = collisionLayer.getObjects();

        //     getTiles();

    }



    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {

        super.render(delta);
        handleInput(delta);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();
        mapRenderer.setView(camera);

        mapRenderer.render();

        spriteBatch.begin();
        player.update();
        player.draw(spriteBatch);
        spriteBatch.end();


        //       if(bullet!=null)
//        bullet.update();
//        //stage.draw();
//        for (Rectangle rectangle : tiles) {
//
//            if (Intersector.overlaps(rectangle, player.getBoundingRectangle())) {
//                Gdx.app.log("Collision", "kolizja!!one1");
//            }
//        }
        controller.draw();
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

    public void handleInput(float dt) {
        shotTime += dt;

        if (controller.isUp()) {
            player.moveUp();
        } else if (controller.isDown()) {
            player.moveDown();
        } else if (controller.isRight()) {
            player.moveRight();
        } else if (controller.isLeft()) {
            player.moveLeft();
        } else if (controller.isShoot()) {
            if (shotTime > 1.0) {
                Gdx.app.log("shoot", "pif-paf");
                initBullets();
                shotTime = 0.0f;
            }
        } else if (controller.isBoost()) {
            Gdx.app.log("boost", "tank is now boosted");
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

    private void initBullet(){
        bullet=new Bullet(player.getPosition(),player.getRotation());
        stage.addActor(bullet);
    }

    private void initBullets() {
        bullets= new LinkedList<Bullet>();
        for (int i = 0; i < 10; i++) {
            bullets.add(new Bullet());
        }
    }

    private void getTiles() {
        TiledMapTileLayer layer = (TiledMapTileLayer) levelMap.getLayers().get("boundries");
        tiles = new ArrayList<Rectangle>();
        tiles.clear();
        Rectangle rect;
        for (int y = 0; y <= layer.getWidth(); y++) {
            for (int x = 0; x <= layer.getHeight(); x++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);

                if (cell != null) {
                    rect = new Rectangle();
                    rect.set(x, y, 50, 50);
                    tiles.add(rect);
                }
            }
        }
        Gdx.app.log("boundries size", String.valueOf(tiles.size()));
    }

}
