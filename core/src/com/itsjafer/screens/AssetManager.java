/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.screens;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * The graphics storage of the game
 * @author haidj9901
 */
public class AssetManager {

    // The mario spritesheet
    private static TextureAtlas atlas;
    // Various textures and animations stored in the atlas
    public static TextureRegion block;
    public static TextureRegion marioStandRight;
    public static TextureRegion marioStandLeft;
    public static TextureRegion marioJump;
    public static Animation marioRunRight;
    public static Animation marioRunLeft;

    public static void load() {
        atlas = new TextureAtlas("mario.pack");
        
        block = atlas.findRegion("stoneBlock");
        
        // The image is set to right by default
        marioStandRight = atlas.findRegion("stand");
        // Need to flip the image for left
        marioStandLeft = new TextureRegion(marioStandRight);
        marioStandLeft.flip(true, false);
        
        marioJump = atlas.findRegion("jump");

        // The images for the run animation
        Array<AtlasRegion> run = atlas.findRegions("run");
        marioRunRight = new Animation(0.1f, run);
        // duplicate the process except flip all
        run = atlas.findRegions("run");
        marioRunLeft = new Animation(0.1f, run);
        // flip each individual frame
        for (TextureRegion r : marioRunLeft.getKeyFrames()) {
            r.flip(true, false);
        }
    }
}
