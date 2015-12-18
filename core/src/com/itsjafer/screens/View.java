/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.screens;

import game.GameLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itsjafer.model.Mario;
import com.itsjafer.model.World;

/**
 *
 * @author haidj9901
 */
public class View {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private OrthogonalTiledMapRenderer renderer;
    
    public View()
    {
        batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        viewport = new FitViewport(16, 12, camera);

        camera.update();

        renderer = new OrthogonalTiledMapRenderer(GameLoader.map, 1f / GameLoader.PPU, batch);

        AssetManager.load();
    }
    
    public void render(World world)
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
        batch.begin();
////        batch.draw(img, player.getX(), player.getY(), Mario.WIDTH, Mario.HEIGHT);
        renderMario(world.getMario());
        
        batch.end();
    }
    
    private void renderMario(Mario mario)
    {
        TextureRegion marioTexture = null;
        if (mario.isStanding())
        {
            marioTexture = AssetManager.marioStand;
        }
        else if (mario.isJumping())
        {
            marioTexture = AssetManager.marioJump;
        }
        
        batch.draw(marioTexture, 0f, 0f, mario.getWidth(), mario.getHeight());
    }
    
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
