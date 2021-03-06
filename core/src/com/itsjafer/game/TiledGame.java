package com.itsjafer.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.itsjafer.model.GameWorld;
import com.itsjafer.screens.View;

/**
 * Main libGDX class which contains the game loop and all of the big game
 * objects.
 *
 * @author Dmitry
 */
public class TiledGame extends ApplicationAdapter {

    float prevY;
    // The visual components of IN GAME
    private View gameView;
    // The actual game world
    private GameWorld world;
    // The input getter for IN GAME
    private InputStream inputStream;
    /**
     * Creates the TiledGame class.
     */
    @Override
    public void create() {
        // Loads the data from the tiled map and sets up basic game info
        GameLoader.loadGame();
        // Uses the info from GameLoader.loadGame() and creates the world
        world = GameLoader.generateWorld();

        gameView = new View();

        inputStream = new InputStream();
        Gdx.input.setInputProcessor(inputStream);
        // sets up the game input
    }

    /**
     * The game loop.
     */
    @Override
    public void render() {
        // Reads input and acts accordingly
        GameInputManager.processInput(inputStream, world);
        // Updates all game components
        world.update(Gdx.graphics.getDeltaTime());
        // Draws the game
        gameView.render(world);
        
    }

    /**
     * Resizes the game window.
     *
     * @param width the new width of the window.
     * @param height the new height of the window.
     */
    @Override
    public void resize(int width, int height) {
        gameView.resize(width, height);
    }
}
