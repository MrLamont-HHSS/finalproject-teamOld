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
    public static void processInput(GameInput inputStream, World world)
    {
        if (inputStream.wPressed())
        {
            world.getMario().jump();
        }
        else if (inputStream.sPressed())
        {
            world.getMario().stand();
        }
        if(inputStream.APressed()){
            world.getMario().setVelocityX(-2);
        }
        else if(inputStream.DPressed()){
            world.getMario().setVelocityX(2);
        }
    }
}
