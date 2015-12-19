/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author kobed6328
 */
public class World {
    
    private Mario mario;
    private Array<Rectangle> collisionBlocks;
    
    private Vector2 gravity;
    
    public World(Mario mario, Array<Rectangle> collisionBlocks, Vector2 gravity)
    {
        this.mario = mario;
        this.collisionBlocks = collisionBlocks;
        
        this.gravity = gravity;
        mario.setAcceleration(gravity);
    }
    
    public void update(float delta)
    {
        mario.update(delta);
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
