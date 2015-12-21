/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import java.util.HashMap;

/**
 *
 * @author kobed6328
 */
public class GameInput implements InputProcessor {

    private HashMap<Integer, Boolean> keys;

    public GameInput() {
        keys = new HashMap();
        keys.put(Input.Keys.W, false);
        keys.put(Input.Keys.A, false);
        keys.put(Input.Keys.S, false);
        keys.put(Input.Keys.D, false);
    }

    public boolean wPressed() {
        return keys.get(Input.Keys.W);
    }

    public boolean sPressed() {
        return keys.get(Input.Keys.S);
    }

    public boolean aPressed() {
        return keys.get(Input.Keys.A);
    }

    public boolean dPressed() {
        return keys.get(Input.Keys.D);
    }

    public void reset() {
        for (Integer key : keys.keySet()) {
            keys.put(key, false);
        }
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
