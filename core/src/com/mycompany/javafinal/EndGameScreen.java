/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;
import com.mycompany.normaljavaclasses.Player;
import com.mycompany.normaljavaclasses.Question;
import java.util.List;

/**
 *
 * @author teoberbic
 */
public class EndGameScreen implements Screen{

    private Stage stage = new Stage(new FitViewport(WIDTH, HEIGHT)); // where the view of the stage is;;;
    private Assets assets;
    private final Player player1;
    private final Player player2;
    private final List<Question> player1Questions;
    private final List<Question> player2Questions;
    private OrthographicCamera camera;
    private ImageButton replayButton;
    private final Drop game;
    private Music endGameCheer;
    
    //Drop game, Image player1ScoreTagImage, Image player2ScoreTagImage, Label player1ScoreLabel, Label player2ScoreLabel, Player player1, Player player2, List<Question> player1Questions, List<Question> player2Questions
    public EndGameScreen(Drop game, Player player1, Player player2, List<Question> player1Questions, List<Question> player2Questions) {
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Questions = player1Questions;
        this.player2Questions = player2Questions;
        
        assets = new Assets(stage, player1, player2, player1Questions, player2Questions);
    }
    
    
    

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        assets.loadEndGameScreen();
        replayButton = assets.getReplayButton();
        endGameCheer = assets.getEndGameCheer();
        endGameCheer.play();
        
        // Create the Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 750);
        
        replayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
    }

    @Override
    public void render(float f) {
        // clear the screen with black 
        ScreenUtils.clear(0, 0, 0, 0);

        // tell the camera to update its matrices.
        camera.update();
        
        //showPlayer1Questions();
        
        
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

//    public void showPlayer1Questions() {
//        for
//    }
    
    @Override
    public void resize(int i, int i1) {
        stage.getViewport().update(WIDTH, HEIGHT, true);
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
        stage.dispose();
        endGameCheer.dispose();
    }
}
