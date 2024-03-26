/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;
import com.mycompany.normaljavaclasses.Player;
import com.mycompany.normaljavaclasses.Question;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.text.StringEscapeUtils;

/**
 *
 * @author teoberbic
 */
public class GameScreen implements Screen {
    private final Drop game;
    private OrthographicCamera camera;
    private Stage stage = new Stage(new FitViewport(WIDTH, HEIGHT)); // where the view of the stage is;;
    
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
    private int player1Score = 0;
    private int player2Score = 0;
    private Player currentPlayer;
    private Label currentPlayerScoreLabel;
    
    // Question Label
    private Label questionLabel;
   
    List<Label> answerLabels = new ArrayList<Label>();

    // Timer
    private float timer = 15;
    private Label timerLabel;

    // Logic Booleans
    private boolean player1RanOutOfQuestions = false;
    private boolean player2RanOutOfQuestions = false;
    private boolean player1Turn = true;
    private boolean enteredConditionAlready = false;
    private Image player1ScoreTagImage;
    private Image player2ScoreTagImage;
    private boolean player2Turn = false;
    
    private Assets assets;
    
    
    public GameScreen(final Drop game, Image player1NameTagImage, Image player2NameTagImage, Image player1CategoryTagImage, Image player2CategoryTagImage, Label player1NameLabel, Label player2NameLabel, Label player1CategoryLabel, Label player2CategoryLabel, Player player1, Player player2, List<Question> player1Questions, List<Question> player2Questions) {
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Questions = player1Questions;
        this.player2Questions = player2Questions;
        currentPlayer = player1;
        
        // All assets are in a new class for encapsualtion
        assets = new Assets(stage, player1, player2, player1NameTagImage, player2NameTagImage, player1CategoryTagImage, player2CategoryTagImage, player1NameLabel, player2NameLabel, player1CategoryLabel, player2CategoryLabel);
    }
    
    @Override
    public void show() {
        
        // Call the loadGameScreen method so they get loaded
        assets.loadGameScreen();
        questionLabel = assets.getQuestionLabel();
        answerLabels = assets.getAnswerLabels();
        player1ScoreLabel = assets.getPlayer1ScoreLabel();
        player2ScoreLabel = assets.getPlayer2ScoreLabel();
        timerLabel = assets.getTimerLabel();
        currentPlayerScoreLabel = assets.getCurrentPlayerScoreLabel();
        
        Gdx.input.setInputProcessor(stage);
        
        // Create the Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 750);
        
        ImageButton[] imageButtons = assets.getImageButtonsArray(); 
        
        // Click Listener For Each Button
        for (int i = 0; i < imageButtons.length; i++) {
            final int index = i; // Final variable required for inner class access

            // Add a click listener to the current image button
            
            imageButtons[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    // Check if the clicked answer matches the correct answer
                    
                    if (answerLabels.get(index).textEquals(currentCorrectAnswer)) {
                        // Update player's score and UI
                        currentPlayer.updateScore();
                        currentPlayerScoreLabel.setText("Player Score: " + currentPlayer.getScore());
                        System.out.print(currentPlayer.getScore());
                    }
                    
                    if (currentPlayer.equals(player1)) {
                            player1Turn = false;
                            player2Turn = true;
                            currentPlayerScoreLabel = player2ScoreLabel;
                            currentPlayer = player2;
                    } else {
                        player2Turn = false;
                        player1Turn = true;
                        currentPlayerScoreLabel = player1ScoreLabel;
                        currentPlayer = player1;
                    }
                    timer = 15;
                }
            });
        } 
    }
    
    @Override
    public void render(float delta) {
        // clear the screen with a blue color. 
        ScreenUtils.clear(0, 0, 0, 0);

        // tell the camera to update its matrices.
        camera.update();

        //while players still both have 10 questions
        
        //while (!player1RanOutOfQuestions && !player2RanOutOfQuestions) {
            if (player1Turn) {
                if (timer <= 0) {
                    player2Turn = true;
                    player1Turn = false;
                    timer = 15;
                    //enteredConditionAlready = false;
                }
                if(!enteredConditionAlready) {
                    // function assign and display question labels to buttons and pass in player 1 current question index
                    displayQuestion(player1CurrentQuestionIndex, player1Questions);
                    enteredConditionAlready = true;
                    player1CurrentQuestionIndex += 1;
                }
                timer -= Gdx.graphics.getDeltaTime();
                timerLabel.setText("Time: " + timer);
            }

            if (player2Turn) {
                if (timer <= 0) {
                    player1Turn = true;
                    player2Turn = false;
                    timer = 15;
                    enteredConditionAlready = false;
                }
                // have to fix logic here its inverted
                if(enteredConditionAlready) {
                    displayQuestion(player2CurrentQuestionIndex, player2Questions);
                    enteredConditionAlready = false;
                    player2CurrentQuestionIndex += 1;
                }

                timer -= Gdx.graphics.getDeltaTime();
                timerLabel.setText("Time: " + timer);

            }
        //}
        

        //get the current players current question index and display it to the screen along with the incorrect and correct answers
        // start the timer down from 15 seconds
        // if the player chooses the correct answer and the timer is greater than 0s then the player gains a point and move turns to the next player
        // if the player chooses the wrong answer the turn moves to the next player
        // if the player runs out of time the turn moves to the next player
        // the same is done for player 2
        // repeated until player 1 runs out of questions and player 2 runs out of questions
        // Wheel
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }
    
    public void displayQuestion(int playerCurrentQuestionIndex, List<Question> questions) {
        questionLabel.setText(StringEscapeUtils.unescapeHtml4(questions.get(playerCurrentQuestionIndex).getQuestion()));
        
        
        List<String> answers = questions.get(playerCurrentQuestionIndex).getIncorrectAnswers();
        currentCorrectAnswer = questions.get(playerCurrentQuestionIndex).getCorrectAnswer();
        answers.add(questions.get(playerCurrentQuestionIndex).getCorrectAnswer());
        Collections.shuffle(answers);
        
        
        for (int i = 0; i <= 3; i++) {
            System.out.print(answers.get(i));
            System.out.println(currentCorrectAnswer);
            answerLabels.get(i).setText(StringEscapeUtils.unescapeHtml4((answers.get(i))));
        }
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
    public void dispose() {}
}
