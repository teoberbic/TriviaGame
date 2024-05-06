/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mycompany.normaljavaclasses.Player;
import com.mycompany.normaljavaclasses.Question;
import java.util.List;

/**
 *
 * @author teoberbic
 */
public class GameScreen implements Screen {
    private final Drop game;
    
    // Passed in from CategoryPickerScreen
    private Player player1;
    private Player player2;
    private List<Question> player1Questions;
    private List<Question> player2Questions;
    
    private Label player1ScoreLabel;
    private Label player2ScoreLabel;
    private String currentCorrectAnswer;
    private int player1CurrentQuestionIndex = 0;
    private int player2CurrentQuestionIndex = 0;
    private Player currentPlayer;
    private Label currentPlayerScoreLabel;
    private List<Label> answerLabels;
    private float timer = 15;
    private Label timerLabel;
    private boolean player1Turn = true;
    private boolean player2CanEnterCondition = false;
    private boolean player2Turn = false;
    private boolean showCorrectAnswerDelay = false;
    private boolean showIncorrectAnswerDelay = false;
    private OtherAssets assets;
    private Sound correctAnswerSFX;
    private Sound incorrectAnswerSFX;
    private Image correctAnswerImage;
    private Image incorrectAnswerImage;
    private Music gameBackgroundMusic;
    private Label player1TurnLabel;
    private Label player2TurnLabel;
    private GameScreenAssets gsa;
    private Stage stage;
    private GameScreenHelper gameHelperScreen;
    private Label player1CurrentIndexLabel;
    
    public GameScreen(final Drop game, Image player1NameTagImage, Image player2NameTagImage, Image player1CategoryTagImage, Image player2CategoryTagImage, Label player1NameLabel, Label player2NameLabel, Label player1CategoryLabel, Label player2CategoryLabel, Player player1, Player player2, List<Question> player1Questions, List<Question> player2Questions) {
        // Assigns all vars sent in by CategoryPickerScreen
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Questions = player1Questions;
        this.player2Questions = player2Questions;
        currentPlayer = player1;
        
        // Get the instance of GameScreenAssets but also send in the vars I need to access in the assets class
        gsa = GameScreenAssets.getInstance(player1, player2, player1NameLabel, player2NameLabel, player1CategoryLabel, player2CategoryLabel);
        gsa.loadAssets(); // Ensures all fields are initialized but instance is forced here to load
        // Sends in the vars I need to access in the helper class
        gameHelperScreen = new GameScreenHelper(gsa,this, player1, player2, player1Questions, player2Questions);
    }
    
    @Override
    public void show() {
        stage = GameScreenAssets.stage; 
        
        answerLabels = GameScreenAssets.answerLabels;
        player1ScoreLabel = GameScreenAssets.player1ScoreLabel;
        player2ScoreLabel = GameScreenAssets.player2ScoreLabel;
        timerLabel = GameScreenAssets.timerLabel;
        currentPlayerScoreLabel = GameScreenAssets.currentPlayerScoreLabel;
        correctAnswerSFX = GameScreenAssets.correctAnswerSFX;
        incorrectAnswerSFX = GameScreenAssets.incorrectAnswerSFX;
        incorrectAnswerImage = GameScreenAssets.incorrectAnswerImage;
        gameBackgroundMusic = GameScreenAssets.gameBackgroundMusic;
        gameBackgroundMusic.play();
        player1TurnLabel = GameScreenAssets.player1TurnLabel;
        player2TurnLabel = GameScreenAssets.player2TurnLabel;
        player1CurrentIndexLabel = GameScreenAssets.playerCurrentIndexLabel;
        Gdx.input.setInputProcessor(stage);
        
        ImageButton[] imageButtons = GameScreenAssets.imageButtons; 
        
        // Click Listener to Check Click For Each Button
        for (int i = 0; i < imageButtons.length; i++) {
            final int index = i; // Final variable required for inner class access

            // Adds a click listener to the current image button
            imageButtons[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    
                    // Check if the clicked answer matches the correct answer
                    if (answerLabels.get(index).textEquals(currentCorrectAnswer)) {
                        gameHelperScreen.clickEqualsCorrectAnswer(index);
                        showCorrectAnswerDelay = true;
       
                    } else {
                        // Click equals incorrect Answer
                        incorrectAnswerSFX.play(1.0f);
                        showIncorrectAnswerDelay = true;
                    }
                    
                    // Switch Players
                    if (currentPlayer.equals(player1)) {
                        currentPlayer = gameHelperScreen.updateCurrentTurn(player2ScoreLabel, player2);
                        player2Turn = true;
                        player1Turn = false;
                        
                    } else {
                        currentPlayer = gameHelperScreen.updateCurrentTurn(player1ScoreLabel, player1);
                        player1Turn = true;
                        player2Turn = false;
                        
                    }
                    // If player 2 is out of questions game is done
                    if (player2CurrentQuestionIndex == 10) {
                        gameHelperScreen.outOfQuestions(game);
                    }
                    event.handle(); // Ensures the current click is handled so two question dont appear back to back
                }
            });
        } 
    }
    
    @Override
    public void render(float delta) {
        update();
        stage.draw();
    }
    
    private void update() {
        // Clear the screen with black 
        ScreenUtils.clear(0, 0, 0, 0);
        
        if(showCorrectAnswerDelay) {
            showCorrectAnswerDelay = gameHelperScreen.showAnswerFeedBack(Gdx.graphics.getDeltaTime(), 1);
            timer = 15;
        }
        
        if(showIncorrectAnswerDelay) {
            showIncorrectAnswerDelay = gameHelperScreen.showAnswerFeedBack(Gdx.graphics.getDeltaTime(), 0);
            timer = 15;

        }
        
            // Check to see whos turn it is and respond with changes accordingly
            if (player1Turn) {
                if (timer <= 0) {
                    gameHelperScreen.timerRanOut();
                    showIncorrectAnswerDelay = true;
                    player2Turn = true;
                    player1Turn = false;
                    player2CanEnterCondition = true;
                    timer = 15;
                }
                if(!player2CanEnterCondition) {
                    currentCorrectAnswer = gameHelperScreen.displayQuestion(player1CurrentQuestionIndex, player1Questions);
                    player2CanEnterCondition = true;
                    player1CurrentQuestionIndex += 1;
                }
                
                gameHelperScreen.switchTurnLabels(player1TurnLabel, player2TurnLabel);
            }

            if (player2Turn) {
                if (timer <= 0) {
                    gameHelperScreen.timerRanOut();
                    showIncorrectAnswerDelay = true;
                    player1Turn = true;
                    player2Turn = false;
                    player2CanEnterCondition = false;
                    timer = 15;
                }
                if(player2CanEnterCondition) {
                    currentCorrectAnswer = gameHelperScreen.displayQuestion(player2CurrentQuestionIndex, player2Questions);
                    player2CanEnterCondition = false;
                    player2CurrentQuestionIndex += 1;
                }
                gameHelperScreen.switchTurnLabels(player2TurnLabel, player1TurnLabel);
            }
            player1CurrentIndexLabel.setText("Question: " + player1CurrentQuestionIndex);
            timer -= Gdx.graphics.getDeltaTime();
            timerLabel.setText("Time: " + (int)timer);
            
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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
        incorrectAnswerSFX.dispose();
        correctAnswerSFX.dispose();
        stage.dispose();
        gameBackgroundMusic.dispose();
    }

    
}
