/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.model;

/**
 *
 * @author kobed6328
 */
public class World {
    
    private Mario mario;
    
    public World()
    {
        mario = new Mario(0, 0);
    }
    
    public void update()
    {
        
    }
    
    public Mario getMario()
    {
        return mario;
    }
    
}
