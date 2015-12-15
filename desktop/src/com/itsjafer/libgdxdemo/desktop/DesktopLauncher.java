package com.itsjafer.libgdxdemo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
;;;;;;;;;;;;;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.itsjafer.libgdxdemo.MyGdxGame;

;;;

;;;;;;;;;;;;;;;;;;;;;;;;;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new MyGdxGame(), config);
    }
}
