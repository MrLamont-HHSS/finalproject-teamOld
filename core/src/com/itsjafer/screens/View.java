/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.screens;

import com.badlogic.gdx.Gdx;
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
import static com.itsjafer.screens.TiledGame.PPU;
import javax.swing.Renderer;

/**
 *
 * @author haidj9901
 */
public class View {
    private SpriteBatch batch;
    private Texture img;
    private OrthographicCamera camera;
    private Viewport viewport;
    private OrthogonalTiledMapRenderer renderer;
    
    private TiledMap map;
    private int levelWidth;
    private int levelHeight;
    
    public View()
    {
        batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        viewport = new FitViewport(16, 12, camera);

        camera.update();

        map = new TmxMapLoader().load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1f / PPU, batch);

        // get the dimensions of the map
        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);
        int tileWidth = map.getProperties().get("tilewidth", Integer.class) / PPU;
        int tileHeight = map.getProperties().get("tileheight", Integer.class) / PPU;

        levelWidth = mapWidth;
        levelHeight = mapHeight;
    }
    
    public void render()
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        // move the camera to the correct position
//        camera.position.x = Math.max(camera.viewportWidth / 2, player.getX());
//        camera.position.x = Math.min(camera.position.x, levelWidth - camera.viewportWidth / 2);
//
//        camera.position.y = Math.max(camera.viewportHeight / 2, player.getY());
//        camera.position.y = Math.min(camera.position.y, levelHeight - camera.viewportHeight /2);

        camera.update();

        // link the batch renderer with the camera
        batch.setProjectionMatrix(camera.combined);

        // render the tile map
        renderer.setView(camera);
        renderer.render();

        // render the sprites
//        batch.begin();
////        batch.draw(img, player.getX(), player.getY(), Mario.WIDTH, Mario.HEIGHT);
//        batch.end();
    }
    
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
