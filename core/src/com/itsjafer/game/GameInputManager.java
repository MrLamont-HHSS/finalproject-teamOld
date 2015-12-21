/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.game;

import com.itsjafer.model.GameWorld;

/**
 *
 * @author kobed6328
 */
public class GameInputManager {

    public static void processInput(GameInput inputStream, GameWorld world) {
        if (inputStream.wPressed()) {
            world.getMario().jump();
        } if (inputStream.sPressed()) {
            world.getMario().stand();
        }
        if (inputStream.aPressed()) {
//            world.getMario().setVelocityX(-2);
            world.getMario().runLeft();
        } if (inputStream.dPressed()) {
//            world.getMario().setVelocityX(2);
            world.getMario().runRight();
        } if (inputStream.wPressed())
        {
            world.getMario().jump();
        } if (inputStream.aReleased())
        {
            world.getMario().stand();
        } if (inputStream.dReleased())
        {
            world.getMario().stand();
        }
    }
}
