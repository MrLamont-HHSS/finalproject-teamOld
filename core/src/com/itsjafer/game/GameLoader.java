/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.itsjafer.model.GameWorld;
import com.itsjafer.model.Mario;

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
    
    private static World physicsWorld;
    
    public static void loadGame() {
        map = new TmxMapLoader().load("map.tmx");

        // get the dimensions of the map
        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);

        tileWidth = map.getProperties().get("tilewidth", Integer.class) / PPU;
        tileHeight = map.getProperties().get("tileheight", Integer.class) / PPU;

        levelWidth = mapWidth;
        levelHeight = mapHeight;

    }
    
    public static GameWorld generateWorld() {
        float gravX = Float.parseFloat(map.getProperties().get("GravityX").toString());
        float gravY = Float.parseFloat(map.getProperties().get("GravityY").toString());
        
        physicsWorld = new World(new Vector2(gravX, gravY), true);
        return new GameWorld(getMario(), getCollisionLayer(), physicsWorld);
    }

    private static Mario getMario() {
        float width = map.getLayers().get("Mario").getObjects().get(0).getProperties().get("width", Float.class)/PPU;
        float height = map.getLayers().get("Mario").getObjects().get(0).getProperties().get("height", Float.class)/PPU;

        float x = map.getLayers().get("Mario").getObjects().get(0).getProperties().get("x", Float.class);
        float y = map.getLayers().get("Mario").getObjects().get(0).getProperties().get("y", Float.class) + height;
        
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        
        //body definition
        
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(x/PPU, y/PPU);
//        bodyDef.position.set(0, 2);
        //box shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2, height/2, new Vector2(width/2, height/2), (float)Math.toRadians(0));
//        shape.setAsBox(2, 2, new Vector2(1, 1), (float)Math.toRadians(0));
        // fixture definition
        fixtureDef.shape = shape;
//        fixtureDef.restitution = 0.1f;
        
        Body marioBody = physicsWorld.createBody(bodyDef);
        marioBody.createFixture(fixtureDef);
        
        shape.dispose();
        
        return new Mario(marioBody, width, height);
    }
    
    private static TiledMapTileLayer getCollisionLayer() {
        TiledMapTileLayer solidBlocks = (TiledMapTileLayer) map.getLayers().get("Collision");// loop through all of the cells, find a tile and make a rectangle

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        
        bodyDef.type = BodyType.StaticBody;
        
        PolygonShape groundShape = new PolygonShape();
        
        for (MapObject object: map.getLayers().get("testCollisionLayer").getObjects().getByType(PolygonMapObject.class))
        {
//            Rectangle rect = ((RectangleMapObject)object).getRectangle();
//            bodyDef.position.set(rect.getX()/PPU, rect.getY()/PPU);
//            groundShape.setAsBox(rect.getWidth()/2/PPU, rect.getHeight()/2/PPU, new Vector2(rect.getWidth()/2/PPU, rect.getHeight()/2/PPU), 0);
            float[] vertices = ((PolygonMapObject)object).getPolygon().getVertices();
            for (int i = 0; i < vertices.length; i ++)
            {
                vertices[i] /= PPU;
                System.out.println(vertices[i]);
            }
            bodyDef.position.set(((PolygonMapObject)object).getPolygon().getX()/PPU, ((PolygonMapObject)object).getPolygon().getY()/PPU);
            
            groundShape.set(vertices);
            fixtureDef.shape = groundShape;
            physicsWorld.createBody(bodyDef).createFixture(fixtureDef);
        }
        for (MapObject object: map.getLayers().get("testCollisionLayer").getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            bodyDef.position.set(rect.getX()/PPU, rect.getY()/PPU);
            groundShape.setAsBox(rect.getWidth()/2/PPU, rect.getHeight()/2/PPU, new Vector2(rect.getWidth()/2/PPU, rect.getHeight()/2/PPU), 0);
            fixtureDef.shape = groundShape;
            physicsWorld.createBody(bodyDef).createFixture(fixtureDef);
        }
        return solidBlocks;
    }
}
