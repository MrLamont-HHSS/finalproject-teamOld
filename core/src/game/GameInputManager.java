/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

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
    }
}
