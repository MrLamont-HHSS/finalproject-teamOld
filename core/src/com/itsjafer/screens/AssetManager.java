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
import java.util.Arrays;

/**
 *
 * @author haidj9901
 */
public class AssetManager {

    private static TextureAtlas atlas;
    public static TextureRegion block;
    public static TextureRegion marioStand;
    public static TextureRegion marioStandLeft;
    public static Animation marioRun;
    public static Animation marioRunLeft;

    public static void load() {
        atlas = new TextureAtlas("mario.pack");
        block = atlas.findRegion("stoneBlock");
        marioStand = atlas.findRegion("stand");
        marioStandLeft = new TextureRegion(marioStand);
        marioStandLeft.flip(true, false);

        Array<AtlasRegion> run = atlas.findRegions("run");
        marioRun = new Animation(0.1f, run);

        run = atlas.findRegions("run");
        marioRunLeft = new Animation(0.1f, run);

        for (TextureRegion r : marioRunLeft.getKeyFrames()) {
            r.flip(true, false);

        }

    }
}
