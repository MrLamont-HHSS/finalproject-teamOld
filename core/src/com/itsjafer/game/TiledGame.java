package com.itsjafer.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.itsjafer.model.World;
import com.itsjafer.screens.View;

public class TiledGame extends ApplicationAdapter {

    private View view;
    private World world;
    private GameInput inputManager;
    
    @Override
    public void create() {
        GameLoader.loadGame();
        
        view = new View();
        world = GameLoader.generateWorld();
        
        inputManager = new GameInput();
        Gdx.input.setInputProcessor(inputManager);
        
    }

    @Override
    public void render() {
        
        world.update();
        view.render(world);
        GameInputManager.processInput(inputManager, world);
        inputManager.reset();
    }

    @Override
    public void resize(int width, int height) {
        view.resize(width, height);
    }
}
