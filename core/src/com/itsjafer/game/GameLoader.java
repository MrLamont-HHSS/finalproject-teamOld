/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.itsjafer.model.Mario;
import com.itsjafer.model.World;

/**
 *
 * @author kobed6328
 */
public class GameLoader {
    
    public static TiledMap map;
    public static int levelWidth;
    public static int levelHeight;
    public static int tileWidth;
    public static int tileHeight;
    
    public static final int PPU = 16;
    
    public static void loadGame()
    {
        map = new TmxMapLoader().load("map.tmx");
        
        // get the dimensions of the map
        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);
        
        tileWidth = map.getProperties().get("tilewidth", Integer.class) / PPU;
        tileHeight = map.getProperties().get("tileheight", Integer.class) / PPU;

        levelWidth = mapWidth;
        levelHeight = mapHeight;
        
    }
    
    public static World generateWorld()
    {
        return new World(getMario(), getCollisionLayer());
    }
    
    private static Mario getMario()
    {
        float width = Float.parseFloat(map.getLayers().get("Mario").getObjects().get(0).getProperties().get("width").toString());
        float height = Float.parseFloat(map.getLayers().get("Mario").getObjects().get(0).getProperties().get("height").toString());
        
        float x = Float.parseFloat(map.getLayers().get("Mario").getObjects().get(0).getProperties().get("x").toString());
        float y = Float.parseFloat(map.getLayers().get("Mario").getObjects().get(0).getProperties().get("y").toString()) + height;
        
        return new Mario(x, y, width, height);
    }
    
    private static Array<Rectangle> getCollisionLayer()
    {
        Array<Rectangle> collisionBlocks = new Array();
        
        TiledMapTileLayer solidBlocks = (TiledMapTileLayer) map.getLayers().get("Collision");// loop through all of the cells, find a tile and make a rectangle
        for (int x = 0; x < levelWidth; x++) {
            for (int y = 0; y < levelHeight; y++) {
                if (solidBlocks.getCell(x, y) != null) {
                    Rectangle r = new Rectangle(x, y, tileWidth, tileHeight);
                    collisionBlocks.add(r);
                }
            }
        }
        
        return collisionBlocks;
    }
}
