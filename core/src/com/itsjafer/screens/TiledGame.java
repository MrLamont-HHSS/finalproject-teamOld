package com.itsjafer.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itsjafer.model.Mario;

public class TiledGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture img;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Mario player;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Array<Rectangle> collisionBlocks;
    private int levelWidth;
    private int levelHeight;
    public static final int PPU = 16;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("stand.png");
        camera = new OrthographicCamera();
        viewport = new FitViewport(16, 12, camera);

        camera.update();
        player = new Mario(3, 4);

        map = new TmxMapLoader().load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1f / PPU, batch);
        renderer.setView(camera);

        // create the collision rectangles for our map based on the map layer
        collisionBlocks = new Array<Rectangle>();
        // get collision layer
        TiledMapTileLayer solidBlocks = (TiledMapTileLayer) map.getLayers().get("Collision");

        // get the dimensions of the map
        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);
        int tileWidth = map.getProperties().get("tilewidth", Integer.class) / PPU;
        int tileHeight = map.getProperties().get("tileheight", Integer.class) / PPU;

        levelWidth = mapWidth;
        levelHeight = mapHeight;
        

        // loop through all of the cells, find a tile and make a rectangle
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                if (solidBlocks.getCell(x, y) != null) {
                    Rectangle r = new Rectangle(x, y, tileWidth, tileHeight);
                    System.out.println("x: " + r.x + "   y: " + r.y + "   tw: " + tileWidth + "   th: " + tileHeight);
                    collisionBlocks.add(r);
                }
            }
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Keys.D)) {
            player.setVelocityX(0.2f);
        } else if (Gdx.input.isKeyPressed(Keys.A)) {
            player.setVelocityX(-0.2f);
        } else {
            player.setVelocityX(0);
        }

        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            player.jump();
        }

        // move the player
        player.update(Gdx.graphics.getDeltaTime());



        for (Rectangle r : collisionBlocks) {

            if (player.getBounds().overlaps(r)) {
                float overX = Math.min(r.getX() + r.getWidth(), player.getX() + player.getWidth()) - Math.max(r.getX(), player.getX());
                float overY = Math.min(r.getY() + r.getHeight(), player.getY() + player.getHeight()) - Math.max(r.getY(), player.getY());
                if (player.getXVelocity() == 0) {
                    if (player.getY() < r.getY()) {
                        player.add(0, -overY);
                    } else {
                        player.add(0, overY);
                        player.land();
                    }
                    player.setVelocityY(0);
                }

                if (overX < overY) {
                    if (player.getX() < r.getX()) {
                        player.add(-overX, 0);
                    } else {
                        player.add(overX, 0);
                    }
                } else {
                    if (player.getY() < r.getY()) {
                        player.add(0, -overY);
                    } else {
                        player.add(0, overY);
                        player.land();
                    }
                    player.setVelocityY(0);
                }
            }
        }

        // move the camera to the correct position
        camera.position.x = Math.max(camera.viewportWidth / 2, player.getX());
        camera.position.x = Math.min(camera.position.x, levelWidth - camera.viewportWidth / 2);

        camera.position.y = Math.max(camera.viewportHeight / 2, player.getY());
        camera.position.y = Math.min(camera.position.y, levelHeight - camera.viewportHeight /2);

        camera.update();

        // link the batch renderer with the camera
        batch.setProjectionMatrix(camera.combined);

        // render the tile map
        renderer.setView(camera);
        renderer.render();

        // render the sprites
        batch.begin();
        batch.draw(img, player.getX(), player.getY(), Mario.WIDTH, Mario.HEIGHT);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
