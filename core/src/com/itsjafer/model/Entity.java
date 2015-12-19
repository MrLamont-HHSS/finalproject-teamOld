/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.model;

import com.badlogic.gdx.math.Rectangle;
////// //////////////////////////////////////// THIS ENTIRE CLASS IS UMM. shall we say... out to lunch.. for the moment
/**
 * Game entity
 * @author haidj9901
 */
public abstract class Entity {

    // Hitbox
    private Rectangle bounds;

    /**
     * Creates an entity.
     * @param x the x value of the bottom left corner.
     * @param y the y value of the bottom left corner.
     * @param width the width of the entity.
     * @param height the height of the entity.
     */
    public Entity(float x, float y, float width, float height) {
        // creates the hitbox to be the entire sprite
        bounds = new Rectangle(x, y, width, height);
    }
    
    //////// OLD CODE FROM HERE ON IN. VENTURE AT YOUR OWN RISK ///////////////////////////////////////
//    public void addToPosition(float x, float y)
//    {
//        bounds.x += x;
//        bounds.y += y;
//    }
//    public boolean isColliding(Entity other) {;
//        return bounds.overlaps(other.bounds);
//    }
//
//    public float getOverlapX(Entity other) {
//        float overlap = Math.min(this.bounds.x + this.bounds.width, other.bounds.x + other.bounds.width)
//                - Math.max(this.bounds.x, other.bounds.x);
//        return overlap;
//    }
//
//    public float getOverlapY(Entity other) {
//        float overlap = Math.min(this.bounds.y + this.bounds.height, other.bounds.y + other.bounds.height)
//                - Math.max(this.bounds.y, other.bounds.y);
//        return overlap;
//    }
//
//    public float getX() {
//        return bounds.x;
//    }
//
//    public float getY() {
//        return bounds.y;
//    }
//
//    public float getWidth() {
//        return bounds.width;
//    }
//
//    public float getHeight() {
//        return bounds.height;
//    }
}
