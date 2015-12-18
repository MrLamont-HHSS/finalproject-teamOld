/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import java.util.HashMap;

/**
 *
 * @author kobed6328
 */
public class GameInput implements InputProcessor{

    private HashMap<Integer, Boolean> thing;
    
    public GameInput()
    {
        thing = new HashMap();
        thing.put(Input.Keys.W, false);
        thing.put(Input.Keys.A, false);
        thing.put(Input.Keys.S, false);
        thing.put(Input.Keys.D, false);
    }
    
    public boolean wPressed()
    {
        return thing.get(Input.Keys.W);
    }
    public boolean sPressed()
    {
        return thing.get(Input.Keys.S);
    }
    
    public void reset()
    {
        for (Integer key: thing.keySet())
        {
            thing.put(key, false);
        }
    }
    
    @Override
    public boolean keyDown(int i) {
        thing.put(i, true);
        return false;
    }

    @Override
    public boolean keyUp(int i) {
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
