/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itsjafer.model.Block;
import com.itsjafer.model.Mario;
import com.itsjafer.model.World;

/**
 *
 * @author haidj9901
 */
public class WorldRenderer {

    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;
    private World world;
    private Mario player;
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private OrthogonalTiledMapRenderer r;

    public WorldRenderer(World w) {
        world = w;
        player = world.getPlayer();

        camera = new OrthographicCamera();
        viewport = new FitViewport(30, 30, camera);
        batch = new SpriteBatch();
        r = new OrthogonalTiledMapRenderer(world.getMap());

        camera.position.x = V_WIDTH / 2f;
        camera.position.y = V_HEIGHT / 2f;
    }

    public void render(float deltaTime) {
        Gdx.gl20.glClearColor(0, 0, 0, 0);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.x = Math.max(player.getX()/16, 8);
        camera.update();

        r.setView(camera);
        r.render();
        AssetManager.load();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (Block b : world.getBlocks()) {
            batch.draw(AssetManager.block, b.getX(), b.getY());
        }
        if (player.getState() == Mario.State.STANDING) {
            if (player.isFacingLeft()) {
                batch.draw(AssetManager.marioStandLeft, player.getX(), player.getY());
            } else {
                batch.draw(AssetManager.marioStand, player.getX(), player.getY());
            }
        } else if (player.getState() == Mario.State.RUNNING) {
            if (player.isFacingLeft()) {
                batch.draw(AssetManager.marioRunLeft.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            } else {
                batch.draw(AssetManager.marioRun.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
            }
        } else {
            batch.draw(AssetManager.marioJump, player.getX(), player.getY());
        }

        batch.end();

    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
