/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import java.util.HashSet;
import java.util.Set;

/**
 * The player
 *
 * @author lamon
 */
public class Mario {

    // The hitbox
    private Rectangle bounds;

    private float width, height;
    // Actual position (x, y)
    private Vector2 position;
    // Velocity (x component, y component)
    private Vector2 velocity;
    // Acceleration (x component, y component)
    private Vector2 acceleration;

    private Body body;
    
    private float stateTime;
    
    // Some enums to keep track of animation state

    public static enum State {

        STANDING, JUMPING, RUNNING
    }

    boolean isFacingLeft;

    // current state
    private State state;

    /// OLD STUFF
//    private final float MAX_Y_VEL= 0.7f;
//    private final float MAX_X_VEL= 1f;
    public Mario(Body body, float width, float height) {
        // set up the hitbox
//        bounds = new Rectangle(x, y, width, height);
//        this.width = width;
//        this.height = height;
//        // start standing
        state = State.STANDING;
//        // the position vector
//        position = new Vector2(x, y);
        // start moving 0
        velocity = new Vector2(0, 0);
        // start accelerating 0
        acceleration = new Vector2(0, 0);
        
        this.body = body;
        
        this.width = width;
        this.height = height;

    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void setVelocityX(float x) {
        velocity.x = x;
    }

    public void setVelocityY(float y) {
        velocity.y = y;
    }

    public void runRight() {
        changeState(State.RUNNING);
        isFacingLeft = false;
//        body.applyForceToCenter(10,0, true);
        body.setLinearVelocity(3, body.getLinearVelocity().y);
    }

    public void runLeft() {
        changeState(State.RUNNING);
        isFacingLeft = true;
//        body.applyForceToCenter(-10,0, true);
        body.setLinearVelocity(-3, body.getLinearVelocity().y);
    }

    public boolean isRunning() {
        return state == State.RUNNING;
    }

    public boolean isFacingLeft() {
        return isFacingLeft;
    }
    /**
     * Changes Mario's current state
     * @param newState the new state
     */
    private void changeState(State newState)
    {
        if (state != newState)
        {
            state = newState;
            // reset the state timer only if this is a new state
            stateTime = 0;
        }
    }

    /**
     * Sets the player's state to jumping.
     */
    public void jump() {
//        if (state != State.JUMPING && velocity.y == 0) {
//            velocity.y = 4;
//            state = State.JUMPING;
//        }
        body.applyForceToCenter(0, 10, true);
        state = State.JUMPING;
    }

    /**
     * Sets the player's state to STANDING.
     */
    public void stand() {
        changeState(State.STANDING);
    }
    
    /**
     * Accelerates the player.
     *
     * @param delta the time factor by which the game runs.
     */
    public void accelerate(float delta) {
//        // Applies the acceleration vector to the velocity vector
//        velocity.mulAdd(acceleration, delta);
//        // Applies the velocity vector to the player's position vector
//        position.add(velocity);
//        // Update hitbox coordinates
//        updateBounds();
    }

    /**
     * Updates the hitbox position and dimensions.
     */
    private void updateBounds() {
//        bounds.x = position.x;
//        bounds.y = position.y;
//        bounds.width = width;
//        bounds.height = height;
    }

    /**
     * Updates Mario.
     *
     * @param delta the time factor by which the game runs.
     */
    public void update(float delta) {
        // accelerates the player taking into consideration the game time
//        accelerate(delta);
//        // updates the bounds to changed coordinates
//        updateBounds();
        stateTime += delta;
        // OLD COLLISION CODE -- STAY AS FAR AWAY AS POSSIBLE, in fact I'd recommend investing in extra-terrestrial real estate
//        velocity.mulAdd(acceleration, delta);
//        if (acceleration.x == 0) {
//            if (velocity.x < 0.01f && velocity.x > -0.01f) {
//                velocity.x = 0;
//            }
//        }
//        MathUtils.clamp(velocity.x, -MAX_X_VEL, MAX_X_VEL);
//        MathUtils.clamp(velocity.y, -MAX_Y_VEL, MAX_Y_VEL);
//        position.add(velocity);
//        bounds.x = position.x;
//        bounds.y = position.y;
//        body.setAngularVelocity(5);
    }

    public float getStateTime() {
        return stateTime;
    }

    public boolean isStanding() {
        return state == State.STANDING;
    }

    public boolean isJumping() {
        return state == State.JUMPING;
    }

    /////// OLD CODE ------------- VENTURE IN AT YOUR OWN RISK ///////////////////////////
//    public void add(float x, float y){
//        position.x += x;
//        position.y += y;
//        bounds.x = position.x;
//        bounds.y = position.y;
//    }
//    
//    public float getXVelocity(){
//        return velocity.x;
//    }
//    
//    public Rectangle getBounds(){
//        return this.bounds;
//    }
//    
    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
