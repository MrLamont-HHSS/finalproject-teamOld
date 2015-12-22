/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itsjafer.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import java.util.HashMap;

/**
 *
 * @author Dmitry
 */
public class InputStream implements InputProcessor{
    
    private HashMap<Integer, Boolean> keys;
    
    public InputStream()
    {
        keys = new HashMap();
        
        keys.put(Input.Keys.D, false);
    }
    
    public boolean isKeyPressed(Integer keyCode)
    {
        if (keys.containsKey(keyCode))
        {
            return keys.get(keyCode);
        }
        return false;
    }

    @Override
    public boolean keyDown(int i) {
        keys.put(i, true);
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        keys.put(i, false);
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
    
    
    
}
