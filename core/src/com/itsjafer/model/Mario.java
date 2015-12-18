/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.model;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author lamon
 */
public class Mario {
    public static enum State  {STANDING, JUMPING}
    private Rectangle bounds;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private State state = State.STANDING;
    
    private final float MAX_Y_VEL= 0.7f;
    private final float MAX_X_VEL= 1f;
    
    public static final float WIDTH = 1;
    public static final float HEIGHT = 2;
    
    public Mario(float x, float y){
        position = new Vector2(x,y);
        velocity = new Vector2(0,0);
        acceleration = new Vector2(0,-2f);
        bounds = new Rectangle(x,y, WIDTH, HEIGHT);
    }
    
    public float getX(){
        return position.x;
    }
    
    public void land(){
        state = State.STANDING;
    }
    
    public float getY(){
        return position.y;
    }
    
    public void setVelocityX(float x){
        velocity.x = x;
    }
    
    public void setVelocityY(float y){
        velocity.y = 0;
    }
    
    public void jump(){
        if(state != State.JUMPING && velocity.y == 0){
            velocity.y = MAX_Y_VEL;
            state = State.JUMPING;
        }
    }
    
    public void update(float delta){
        velocity.mulAdd(acceleration, delta);
        if (acceleration.x == 0) {
            if (velocity.x < 0.01f && velocity.x > -0.01f) {
                velocity.x = 0;
            }
        }
        MathUtils.clamp(velocity.x, -MAX_X_VEL, MAX_X_VEL);
        MathUtils.clamp(velocity.y, -MAX_Y_VEL, MAX_Y_VEL);
        position.add(velocity);
        bounds.x = position.x;
        bounds.y = position.y;
    }
    
    public void add(float x, float y){
        position.x += x;
        position.y += y;
        bounds.x = position.x;
        bounds.y = position.y;
    }
    
    public float getXVelocity(){
        return velocity.x;
    }
    
    public Rectangle getBounds(){
        return this.bounds;
    }
    
    public float getWidth(){
        return bounds.getWidth();
    }
    
    public float getHeight(){
        return bounds.getHeight();
    }
    
    public boolean isStanding()
    {
        return state == State.STANDING;
    }
    public boolean isJumping()
    {
        return state == State.JUMPING;
    }
}
