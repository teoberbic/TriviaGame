/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    private Player currentPlayer;
    private Label currentPlayerScoreLabel;
    
    private Label questionLabel;
   
    List<Label> answerLabels = new ArrayList<Label>();

    // Timer
    private float timer = 15;
    private Label timerLabel;
    private float showAnswerImageTime = 1;

    // Logic Booleans
    private boolean player1RanOutOfQuestions = false;
    private boolean player2RanOutOfQuestions = false;
    private boolean player1Turn = true;
    private boolean enteredConditionAlready = false;
    private boolean player2Turn = false;
    private boolean showCorrectAnswerDelay = false;
    private boolean showIncorrectAnswerDelay = false;
    
    private Assets assets;
    private Sound correctAnswerSFX;
    private Sound incorrectAnswerSFX;
    private boolean playerTurnEnded;
    private float playerTurnBufferTime = 3;
    private Image correctAnswerImage;
    private Image incorrectAnswerImage;
    private Label player1GainedPointLabel;
    private Label player2GainedPointLabel;
    private Music gameBackgroundMusic;
    private Label player1TurnLabel;
    private Label player2TurnLabel;
    
    
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
        correctAnswerSFX = assets.getCorrectAnswerSFX();
        incorrectAnswerSFX = assets.getIncorrectAnswerSFX();
        correctAnswerImage = assets.getCorrectAnswerImage();
        incorrectAnswerImage = assets.getIncorrectAnswerImage();
        player1GainedPointLabel = assets.getPlayer1GainedPointLabel();
        player2GainedPointLabel = assets.getPlayer2GainedPointLabel();
        gameBackgroundMusic = assets.getGameBackgroundMusic();
        gameBackgroundMusic.play();
        player1TurnLabel = assets.getPlayer1TurnLabel();
        player2TurnLabel = assets.getPlayer2TurnLabel();
        Gdx.input.setInputProcessor(stage);
        
        ImageButton[] imageButtons = assets.getImageButtonsArray(); 
        
        // Click Listener For Each Button
        for (int i = 0; i < imageButtons.length; i++) {
            final int index = i; // Final variable required for inner class access

            // Adds a click listener to the current image button
            imageButtons[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    
                    // Check if the clicked answer matches the correct answer
                    if (answerLabels.get(index).textEquals(currentCorrectAnswer)) {
                        // Update player's score and UI
                        correctAnswerSFX.play(1.0f);
                        currentPlayer.updateScore();
                        currentPlayerScoreLabel.setText("Player Score: " + currentPlayer.getScore());
                        System.out.print(currentPlayer.getScore());
                        
                        // Set true that a correct answer was clicked
                        showCorrectAnswerDelay = true;
                        if (currentPlayer == player1) {
                            player1GainedPointLabel.setVisible(true);
                        } else {
                            player2GainedPointLabel.setVisible(true);
                        }
                        
                    } else {
                        incorrectAnswerSFX.play(1.0f);
                        
                        // Set true that a incorrect answer was clicked
                        showIncorrectAnswerDelay = true;
                    }
                    
                    // wait a second then switch
                    playerTurnEnded = true;
                    
                    
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
                    if (player2CurrentQuestionIndex == 9 ) {
                        stage.dispose();
                        if (player1.getScore() > player2.getScore()) {
                            game.setScreen(new EndGameScreen(game, player1, player2, player1Questions, player2Questions));
                            gameBackgroundMusic.stop();
                        } else {
                            game.setScreen(new EndGameScreen(game, player1, player2, player1Questions, player2Questions));
                            gameBackgroundMusic.stop();
                        }
                        
                    }
                    
                }
            });
        } 
    }
    
    @Override
    public void render(float delta) {
        // clear the screen with black 
        ScreenUtils.clear(0, 0, 0, 0);

        // tell the camera to update its matrices.
        //camera.update();
        
        if(showCorrectAnswerDelay) {
            showAnswerFeedback(Gdx.graphics.getDeltaTime(), 1);
        }
        
        if(showIncorrectAnswerDelay) {
            showAnswerFeedback(Gdx.graphics.getDeltaTime(), 0);
        }
        
        //while players still both have 10 questions
        //while (!player1RanOutOfQuestions && !player2RanOutOfQuestions) {
        
            if (player1Turn) {
                if (timer <= 0) {
                    showIncorrectAnswerDelay = true;
                    player2Turn = true;
                    player1Turn = false;
                    incorrectAnswerSFX.play(1.0f);
                    timer = 15;
                    //enteredConditionAlready = false;
                }
                if(!enteredConditionAlready) {
                    // function assign and display question labels to buttons and pass in player 1 current question index
                    displayQuestion(player1CurrentQuestionIndex, player1Questions);
                    enteredConditionAlready = true;
                    player1CurrentQuestionIndex += 1;
                    System.out.print("PLAYER 1 CURRENT INDEX:" + player1CurrentQuestionIndex);
                }
                
                player2TurnLabel.setVisible(false);
                player1TurnLabel.setVisible(true);
                timer -= Gdx.graphics.getDeltaTime();
                timerLabel.setText("Time: " + (int)timer);
            }

            if (player2Turn) {
                if (timer <= 0) {
                    showIncorrectAnswerDelay = true;
                    player1Turn = true;
                    player2Turn = false;
                    incorrectAnswerSFX.play(1.0f);
                    timer = 15;
                    enteredConditionAlready = false;
                }
                // have to fix logic here its inverted
                if(enteredConditionAlready) {
                    displayQuestion(player2CurrentQuestionIndex, player2Questions);
                    enteredConditionAlready = false;
                    player2CurrentQuestionIndex += 1;
                    System.out.print("PLAYER 2 CURRENT INDEX:" + player2CurrentQuestionIndex);
                }
                player2TurnLabel.setVisible(true);
                player1TurnLabel.setVisible(false);
                timer -= Gdx.graphics.getDeltaTime();
                timerLabel.setText("Time: " + (int)timer);

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
    
    public void showAnswerFeedback(float dt, int answer) {
        timer = 15;
        if (answer == 1) {
            correctAnswerImage.setVisible(true);
            
            showAnswerImageTime -= dt;

            if (showAnswerImageTime <= 0) {
                correctAnswerImage.setVisible(false);
                showCorrectAnswerDelay = false;
                showAnswerImageTime = 1;
                player1GainedPointLabel.setVisible(false);
                player2GainedPointLabel.setVisible(false);
            }
            
        } else {
            incorrectAnswerImage.setVisible(true);

            showAnswerImageTime -= dt;

            if (showAnswerImageTime <= 0) {
                incorrectAnswerImage.setVisible(false);
                showIncorrectAnswerDelay = false;
                showAnswerImageTime = 1;
            }
        }
        
    }
    
    public void displayQuestion(int playerCurrentQuestionIndex, List<Question> questions) {
        String question = StringEscapeUtils.unescapeHtml4(questions.get(playerCurrentQuestionIndex).getQuestion());
        //String testString = "Hi my name is teo and i like ice cream what are you doing ate bye boy and";
        
        if (question.length() > 35) 
            question = adjustLabelWidth(question, 35);
        
        questionLabel.setText(StringEscapeUtils.unescapeHtml4(question));
        
        
        List<String> answers = questions.get(playerCurrentQuestionIndex).getIncorrectAnswers();
        currentCorrectAnswer = questions.get(playerCurrentQuestionIndex).getCorrectAnswer();
        answers.add(questions.get(playerCurrentQuestionIndex).getCorrectAnswer());
        Collections.shuffle(answers);
        
        
        for (int i = 0; i <= 3; i++) {
            System.out.print(answers.get(i));
            System.out.println(currentCorrectAnswer);
            String currentAnswer = answers.get(i);
            
            if (currentAnswer.length() > 80) {
                System.out.print("ANSWER LONG");
            }
            if (currentAnswer.length() > 23) {
                currentAnswer = adjustLabelWidth(currentAnswer, 23);
            }
            
            answerLabels.get(i).setText(StringEscapeUtils.unescapeHtml4(currentAnswer));
        }
    }
    
    public String adjustLabelWidth(String text, int lengthCap) {
        
        StringBuilder stringbuilder = new StringBuilder();
        
        while (text.length() > lengthCap) {
            String currentSubString = text.substring(0, lengthCap + 1); // substring == "Hi my name "

            //error here
            int lastIDXofSpace = currentSubString.lastIndexOf(" ");
            System.out.print("here: " + lastIDXofSpace);

            currentSubString = currentSubString.substring(0, lastIDXofSpace) + "\n"; // code breaks here sometimes, caught it at length 36

            stringbuilder.append(currentSubString);

            text = text.substring(lastIDXofSpace + 1);

            if (text.length() < lengthCap ) {
                stringbuilder.append(text);
            }
        }
        
        text = stringbuilder.toString();
           
        return text;
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
