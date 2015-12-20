/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.game;

import com.itsjafer.model.World;

/**
 *
 * @author kobed6328
 */
public class GameInputManager {

    public static void processInput(GameInput inputStream, World world) {
        if (inputStream.wPressed()) {
            world.getMario().jump();
        } else if (inputStream.sPressed()) {
            world.getMario().stand();
        }
        if (inputStream.aPressed()) {
            world.getMario().setVelocityX(-2);
            world.getMario().runLeft();
        } else if (inputStream.dPressed()) {
            world.getMario().setVelocityX(2);
            world.getMario().runRight();

        }
    }
}
