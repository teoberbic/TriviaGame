/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;


//questionable imports
import com.badlogic.gdx.utils.viewport.FitViewport;
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;

/**
 *
 * @author teoberbic
 */
public class MainMenuScreen implements Screen{
    
    public static final String TITLE = "Teo's Trivia Competition";
    
    // References to Classes
    private final Drop game;
    private Stage stage = new Stage(new FitViewport(WIDTH, HEIGHT)); // Where the view of the stage is
    OtherAssets assets;
    
    // Assets
    private Music backgroundMusic;
    private ImageButton imageButton;
    
    
    public MainMenuScreen(final Drop game) {
        this.game = game;
    }
    @Override
    public void show() {
        assets = new OtherAssets(stage); // All assets are in a new class for encapsualtion
        assets.loadMainMenuScreen(); // Call the loadCategoryPickerScreen method so they get loaded
        backgroundMusic = assets.getBackgroundMusic(); // Call back assets you need still
        backgroundMusic.play();
        imageButton = assets.getImageButton();

        Gdx.input.setInputProcessor(stage); // Set the stage for input to be processed through
        
        // Button Clicked Handler (has to be in show method)
        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new CategoryPickerScreen(game)); // Once clicked change screen to CategoryPickerScreen to advance game
                dispose(); // Dispose all assets for memory freeing
            }
        });
    }

    
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        backgroundMusic.dispose();
        stage.dispose();
    }
}
