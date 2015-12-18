/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

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
}
