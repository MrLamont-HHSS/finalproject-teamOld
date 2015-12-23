/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.model;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import com.badlogic.gdx.math.Vector2;
import com.itsjafer.game.GameLoader;

/**
 * The actual level
 *
 * @author kobed6328
 */
public class GameWorld {

    private Mario mario;
    // The blocks with which Mario can collide
    private TiledMapTileLayer collisionBlocks;
    // The global gravity "constant" (it can change)
    private Vector2 gravity;

    /**
     * Creates the World object
     *
     * @param mario the player.
     * @param collisionBlocks the tile layer with which the player can collide.
     */
    public GameWorld(Mario mario, TiledMapTileLayer collisionBlocks, Vector2 gravity) {
        this.mario = mario;
        this.collisionBlocks = collisionBlocks;

        this.gravity = gravity;
        // mario should accelerate according to the global gravity as soon as he spawns
//        mario.setAcceleration(physicsWorld.getGravity());
    }

    /**
     * Updates all of the updateable components.
     *
     * @param delta the time factor by which the game runs.
     */
    public void update(float delta) {
        // starting collision
        mario.setAcceleration(gravity);
        mario.update(delta);
        collideMarioWithSolidBlocks();
    }
    
    private void collideMarioWithSolidBlocks()
    {
        int marioTileX = (int)(mario.getX()/(GameLoader.PPU));
        int marioTileY = (int)(mario.getY()/(GameLoader.PPU));
        
        float diffTop = 0, diffBottom = 0, diffRight = 0, diffLeft = 0;
        
        boolean collidedLeft = false;
        // CELLS TO MARIO'S LEFT
        for (int i = 0; i <= mario.getHeight()/GameLoader.PPU; i++)
        {
            if (i < mario.getHeight()/GameLoader.PPU)
            {
                if (collisionBlocks.getCell(marioTileX, marioTileY+i) != null)
                {
                    collidedLeft = true;
                }
            }
            else if (collisionBlocks.getCell(marioTileX, (int)(mario.getY() + mario.getHeight()-1)/GameLoader.PPU) != null)
            {
                collidedLeft = true;
            }
        }
        if (collidedLeft)
        {
            diffLeft = (marioTileX+1)*GameLoader.PPU-mario.getX();
        }
        
        // CELLS TO MARIO'S RIGHT
        
        marioTileX = (int)((mario.getX()+mario.getWidth())/GameLoader.PPU);
        
        boolean collidedRight = false;
        for (int i = 0; i <= mario.getHeight()/GameLoader.PPU; i++)
        {
            if (i < mario.getHeight()/GameLoader.PPU)
            {
                if (collisionBlocks.getCell(marioTileX, marioTileY+i) != null)
                {
                    collidedRight = true;
                }
            }
            else if (collisionBlocks.getCell(marioTileX, (int)(mario.getY() + mario.getHeight()-1)/GameLoader.PPU) != null)
            {
                collidedRight = true;
            }
        }
        if (collidedRight)
        {
            diffRight = mario.getX()-(marioTileX-1)*GameLoader.PPU;
        }
        
        // CELLS TO MARIO'S TOP
        
        marioTileX = (int)(mario.getX()/(GameLoader.PPU));
        marioTileY = (int)((mario.getY()+mario.getHeight())/GameLoader.PPU);
        
        boolean collidedTop = false;
        for (int i = 0; i <= mario.getWidth()/GameLoader.PPU; i++)
        {
            if (i < mario.getWidth()/GameLoader.PPU)
            {
                if (collisionBlocks.getCell(marioTileX, marioTileY+i) != null)
                {
                    collidedTop = true;
                }
            }
            else if (collisionBlocks.getCell((int)(mario.getX() + mario.getWidth()-1)/GameLoader.PPU, marioTileY) != null)
            {
                collidedTop = true;
            }
        }
        if (collidedTop)
        {
            diffTop = mario.getY()+mario.getHeight()-(marioTileY)*GameLoader.PPU;
        }
        
        // CELLS TO MARIO'S BOTTOM
        
        marioTileY = (int)(mario.getY()/GameLoader.PPU);
        
        boolean collidedBottom = false;
        for (int i = 0; i <= mario.getWidth()/GameLoader.PPU; i++)
        {
            if (i < mario.getWidth()/GameLoader.PPU)
            {
                if (collisionBlocks.getCell(marioTileX, marioTileY+i) != null)
                {
                    collidedBottom = true;
                }
            }
            else if (collisionBlocks.getCell((int)(mario.getX() + mario.getWidth()-1)/GameLoader.PPU, marioTileY) != null)
            {
                collidedBottom = true;
            }
        }
        if (collidedBottom)
        {
            diffBottom = (marioTileY+1)*GameLoader.PPU-mario.getY();
        }
        
        float diffHor = 0, diffVer = 0;
        diffHor = Math.min(diffRight, diffLeft);
        diffVer = Math.min(diffTop, diffBottom);
        
        if (diffHor < diffVer)
        {
            if (diffRight < diffLeft && diffRight != 0)
            {
                mario.setX(mario.getX()-diffRight);
            }
            else
            {
                mario.setX(mario.getX()+diffLeft);
            }
            mario.setVelocityX(0);
        }
        else if (diffHor > diffVer)
        {
            if (diffTop < diffBottom && diffTop != 0)
            {
                mario.setY(mario.getY()-diffTop);
            }
            else
            {
                mario.setY(mario.getY()+diffBottom);
            }
            mario.setVelocityY(0);
        }
        
    }

    public Mario getMario() {
        return mario;
    }

}
