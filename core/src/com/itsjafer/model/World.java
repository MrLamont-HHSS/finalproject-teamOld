/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author kobed6328
 */
public class World {
    
    private Mario mario;
    private Array<Rectangle> collisionBlocks;
    
    public World(Mario mario, Array<Rectangle> collisionBlocks)
    {
        this.mario = mario;
        this.collisionBlocks = collisionBlocks;
    }
    
    public void update()
    {
        
    }
    
    public Mario getMario()
    {
        return mario;
    }
    public Array<Rectangle> getCollisionBlocks()
    {
        return collisionBlocks;
    }
    
}
