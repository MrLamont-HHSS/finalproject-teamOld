/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsjafer.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itsjafer.game.TiledGame;
import static javafx.scene.text.Font.font;

/**
 * NOT DONE.
 *
 * @author Jafer
 */
public class MenuView {

    private TiledGame myGame;
    private Stage stage;
    private Skin skin;
    private Label title;
    private TextButton singlePlayer;
    private TextButton multiPlayer;
    private TextButton options;
    private BitmapFont font;
    private SpriteBatch spriteBatch;

    /**
     * Constructor for the splash screen
     *
     * @param g Game which called this splash screen.
     */
    public MenuView(TiledGame g) {
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);// Make the stage consume events

        createBasicSkin();
        
        singlePlayer = new TextButton("Single player", skin); // Use the initialized skin
        singlePlayer.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 4 + Gdx.graphics.getHeight() / 8);

        multiPlayer = new TextButton("Multiplayer", skin); // Use the initialized skin
        multiPlayer.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 4);

        options = new TextButton("Options", skin); // Use the initialized skin
        options.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 4 - Gdx.graphics.getHeight() / 8);

        stage.addActor(singlePlayer);
        stage.addActor(multiPlayer);
        stage.addActor(options);
        
        singlePlayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //switch to view
            }
        });

    }

    private void createBasicSkin() {
        //Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
        
        //Create label style
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.GRAY;
        skin.add("default", labelStyle);

    }

    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(stage);

        stage.act();
        stage.draw();
    }

}
