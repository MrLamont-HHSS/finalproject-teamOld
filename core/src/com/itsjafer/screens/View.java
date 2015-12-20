/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.screens;

import com.itsjafer.game.GameLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itsjafer.model.Mario;
import com.itsjafer.model.GameWorld;

/**
 *
 * @author haidj9901
 */
public class View {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private OrthogonalTiledMapRenderer renderer;

    public View() {
        batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        viewport = new FitViewport(16, 12, camera);

        camera.update();

        renderer = new OrthogonalTiledMapRenderer(GameLoader.map, 1f / GameLoader.PPU, batch);

        AssetManager.load();
    }

    public void render(GameWorld world) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        centerCameraOnPlayer(world.getMario());
        camera.update();

        // link the batch renderer with the camera
        batch.setProjectionMatrix(camera.combined);

        // render the tile map
        renderer.setView(camera);
        renderer.render();

        // render the sprites
        batch.begin();
////        batch.draw(img, player.getX(), player.getY(), Mario.WIDTH, Mario.HEIGHT);
        if (world.getMario() != null) {
            renderMario(world.getMario());
        }
        batch.end();

    }

    private void centerCameraOnPlayer(Mario mario) {
        // move the camera to the correct position
        camera.position.x = Math.max(camera.viewportWidth / 2, mario.getX() / GameLoader.PPU);
        camera.position.x = Math.min(camera.position.x, GameLoader.levelWidth - camera.viewportWidth / 2);

        camera.position.y = Math.max(camera.viewportHeight / 2, mario.getY() / GameLoader.PPU);
        camera.position.y = Math.min(camera.position.y, GameLoader.levelHeight - camera.viewportHeight / 2);
    }

    private void renderMario(Mario mario) {
        TextureRegion marioTexture = null;
        Animation marioAnimation = null;
        if (mario.isStanding()) {
            marioTexture = AssetManager.marioStandRight;
        } else if (mario.isJumping()) {
            marioTexture = AssetManager.marioJump;
        } else if (mario.isRunning()) {

            if (mario.isFacingLeft()) {
                marioAnimation = AssetManager.marioRunLeft;
            } else {
                marioAnimation = AssetManager.marioRunRight;
            }
            System.out.println(mario.getStateTime());
            marioTexture = marioAnimation.getKeyFrame(mario.getStateTime(), true);
        }
        batch.draw(marioTexture, mario.getX() / GameLoader.PPU, mario.getY() / GameLoader.PPU, mario.getWidth() / GameLoader.PPU, mario.getHeight() / GameLoader.PPU);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
