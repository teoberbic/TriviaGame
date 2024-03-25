/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


//questionable imports
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;



/**
 *
 * @author teoberbic
 */
public class MainMenuScreen extends UserInterface{
    private final Drop game;
    private Texture startButtonTexture;
    Rectangle startButton;
    private Sound startButtonSound;
    //private Stage stage;
    private Label welcomeText;
    private Label welcomeText2;
    private Texture backgroundImageTexture;
    private Image backgroundImage;
    private Music backgroundMusic;
    private Skin skinUI;
    private Label welcomeTextLabel;
    private Label welcomeTextLabel2;

    OrthographicCamera camera;

    public MainMenuScreen(final Drop game) {
        this.game = game;
    }
    @Override
    public void show() {

        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 750);
        
        // Music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();

        // Stage
        //stage = new Stage(new FitViewport(WIDTH, HEIGHT)); // where the view of the stage is
        Gdx.input.setInputProcessor(stage); // set the stage for input to be processed through
        
        //Put all assets in a new class for readibility and call a load method so they get loaded
        
        //Background Image
        backgroundImageTexture = new Texture(Gdx.files.internal("background.jpeg"));
        backgroundImage = new Image(backgroundImageTexture);
        backgroundImage.setPosition(-50,0);
        stage.addActor(backgroundImage);

        // Welcome Label's
        skinUI = new Skin(Gdx.files.internal("glassy-ui.json"));
        Label.LabelStyle bigLabelStyle = skinUI.get("big", Label.LabelStyle.class);
        bigLabelStyle.fontColor = Color.WHITE;
        skinUI.getFont("font-big").getData().setScale(.41f);
        
        welcomeTextLabel = new Label("Welcome to Teo's Trivia Game", bigLabelStyle);
        welcomeTextLabel.setPosition(WIDTH/2 - 180, HEIGHT/2 + 200);
        stage.addActor(welcomeTextLabel);
        welcomeTextLabel2 = new Label("Press the button to begin", bigLabelStyle);
        welcomeTextLabel2.setPosition(WIDTH/2 - 140, HEIGHT/2 + 150);
        stage.addActor(welcomeTextLabel2);
        
        // Create an Image Button
        startButtonTexture = new Texture(Gdx.files.internal("mainMenuStartButton.png"));
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(startButtonTexture)); // Set button image
        ImageButton imageButton = new ImageButton(buttonStyle);
        imageButton.setPosition(WIDTH/2 - imageButton.getWidth()/2, HEIGHT/2 - imageButton.getHeight()/2); // Set position of the button
        stage.addActor(imageButton);

        
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
        //stage.draw();
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
