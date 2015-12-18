package com.itsjafer.libgdxdemo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import game.TiledGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new TiledGame(), config);
    }
}
