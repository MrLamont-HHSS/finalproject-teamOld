/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.itsjafer.model.GameWorld;

/**
 *
 * @author kobed6328
 */
public class GameInputManager {

    public static void processInput(InputStream inputStream, GameWorld world) {
        if (inputStream.isKeyPressed(Input.Keys.W))
        {
            world.getMario().jump();
        }
        if (inputStream.isKeyPressed(Input.Keys.D) || inputStream.isKeyPressed(Input.Keys.A))
        {
            if (inputStream.isKeyPressed(Input.Keys.D))
            {
                world.getMario().runRight();
            }
            if (inputStream.isKeyPressed(Input.Keys.A))
            {
                world.getMario().runLeft();
            }
        }
        else
        {
            world.getMario().stand();
        }
        
    }
}
