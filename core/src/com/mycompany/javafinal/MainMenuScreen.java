/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


//questionable imports
import com.badlogic.gdx.utils.viewport.FitViewport;
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;



/**
 *
 * @author teoberbic
 */
public class MainMenuScreen implements Screen{
    private final Drop game;
    private Texture startButtonTexture;
    Rectangle startButton;
    private Sound startButtonSound;
    private Stage stage = new Stage(new FitViewport(WIDTH, HEIGHT)); // where the view of the stage is;;;
    private Label welcomeText;
    private Label welcomeText2;
    private Texture backgroundImageTexture;
    private Image backgroundImage;
    private Music backgroundMusic;
    private Skin skinUI;
    private Label welcomeTextLabel;
    private Label welcomeTextLabel2;
    private ImageButton imageButton;

    OrthographicCamera camera;
    Assets assets;
    

    public MainMenuScreen(final Drop game) {
        this.game = game;
    }
    @Override
    public void show() {
        
        // All assets are in a new class for encapsualtion then call the loadCategoryPickerScreen method so they get loaded
        assets = new Assets(stage);
        assets.loadMainMenuScreen();
        backgroundMusic = assets.getBackgroundMusic();
        backgroundMusic.play();
        imageButton = assets.getImageButton();
        
        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 750);

        Gdx.input.setInputProcessor(stage); // set the stage for input to be processed through
        
        // Button Clicked Handler
        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new CategoryPickerScreen(game));
                dispose();
            }
        });
    }

    
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        backgroundMusic.dispose();
        stage.dispose();
    }

}
