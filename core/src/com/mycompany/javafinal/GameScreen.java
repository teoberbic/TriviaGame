/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    private Stage stage;
    
    // Passed in from CategoryPickerScreen
    private final Image player1NameTagImage;
    private final Image player2NameTagImage;
    private Image player1CategoryTagImage;
    private Image player2CategoryTagImage;
    private Label player1NameLabel;
    private Label player2NameLabel;
    private Label player1CategoryLabel;
    private Label player2CategoryLabel;
    private Player player1;
    private Player player2;
    
    // Question & Answer Labels
    private Label questionLabel;
    private Image questionLabelImage;
    private Texture answerBtn1Texture;
    private Texture answerBtn1TextureHover;
    
    // Skin
    private Skin skinUI;
    private Texture answerBtn2Texture;
    private Texture answerBtn2TextureHover;
    private Texture answerBtn3Texture;
    private Texture answerBtn3TextureHover;
    private Texture answerBtn4Texture;
    private Texture answerBtn4TextureHover;
    
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
   
    
    private Label answerLabel1;
    private Label answerLabel2;
    private Label answerLabel3;
    private Label answerLabel4;
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
    
    public GameScreen(final Drop game, Image player1NameTagImage, Image player2NameTagImage, Image player1CategoryTagImage, Image player2CategoryTagImage, Label player1NameLabel, Label player2NameLabel, Label player1CategoryLabel, Label player2CategoryLabel, Player player1, Player player2, List<Question> player1Questions, List<Question> player2Questions) {
        this.game = game;
        this.player1NameTagImage = player1NameTagImage;
        this.player2NameTagImage = player2NameTagImage;
        this.player1CategoryTagImage = player1CategoryTagImage;
        this.player2CategoryTagImage = player2CategoryTagImage;
        this.player1NameLabel = player1NameLabel;
        this.player2NameLabel = player2NameLabel;
        this.player1CategoryLabel = player1CategoryLabel;
        this.player2CategoryLabel = player2CategoryLabel;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Questions = player1Questions;
        this.player2Questions = player2Questions;
        currentPlayer = player1;
    }
 
    @Override
    public void show() {
        
        
        // Stage
        stage = new Stage(new FitViewport(WIDTH, HEIGHT)); // where the view of the stage is
        Gdx.input.setInputProcessor(stage);
        
        // Create the Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 750);
        
        //Put all assets in a new class for readibility and call a load method so they get loaded
        
        // Players Name Tag's 
        player1NameTagImage.setPosition(0,HEIGHT - 81);
        player2NameTagImage.setPosition(WIDTH-400,HEIGHT -81);
        stage.addActor(player1NameTagImage);
        stage.addActor(player2NameTagImage);
        
        // Players Category Tag's
        player1CategoryTagImage.setPosition(0,HEIGHT - 81 -105);
        player2CategoryTagImage.setPosition(WIDTH-400,HEIGHT - 81 - 105);
        stage.addActor(player1CategoryTagImage);
        stage.addActor(player2CategoryTagImage);
                        
        // Players Name Labels
        player1NameLabel.setPosition(120, HEIGHT -50);
        player2NameLabel.setPosition(WIDTH - 280, HEIGHT -50);
        stage.addActor(player1NameLabel); 
        stage.addActor(player2NameLabel);
        
        // Players Category Labels
        player1CategoryLabel.setPosition(120, HEIGHT -145);
        player2CategoryLabel.setPosition(WIDTH-280, HEIGHT -145);
        stage.addActor(player1CategoryLabel);
        stage.addActor(player2CategoryLabel);
        
        // Skin
        skinUI = new Skin(Gdx.files.internal("uiskin.json"));
        skinUI.getFont("default-font").getData().setScale(2.0f);
        skinUI = new Skin(Gdx.files.internal("glassy-ui.json"));
        Label.LabelStyle bigLabelStyle = skinUI.get("big", Label.LabelStyle.class);
        bigLabelStyle.fontColor = Color.WHITE;
        skinUI.getFont("font-big").getData().setScale(.41f);
        
        // Timer
        timerLabel = new Label("Time: " + timer, bigLabelStyle);
        timerLabel.setPosition(WIDTH/2 - 50, HEIGHT/2);
        stage.addActor(timerLabel);
        
        // Create an Image Buttons (create a function)
        answerBtn1Texture = new Texture(Gdx.files.internal("btnImage1.png"));
        answerBtn1TextureHover = new Texture(Gdx.files.internal("btnImage1Hover.png"));
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(answerBtn1Texture)); // Set button image when its up
        buttonStyle.imageOver = new TextureRegionDrawable(new TextureRegion(answerBtn1TextureHover)); // Set button image when its hovered
        ImageButton imageButton = new ImageButton(buttonStyle);
        imageButton.setPosition(50, HEIGHT/2 - 200); // Set position of the button
        stage.addActor(imageButton);
        
        answerBtn2Texture = new Texture(Gdx.files.internal("btnImage2.png"));
        answerBtn2TextureHover = new Texture(Gdx.files.internal("btnImage2Hover.png"));
        ImageButton.ImageButtonStyle buttonStyle2 = new ImageButton.ImageButtonStyle();
        buttonStyle2.imageUp = new TextureRegionDrawable(new TextureRegion(answerBtn2Texture)); // Set button image when its up
        buttonStyle2.imageOver = new TextureRegionDrawable(new TextureRegion(answerBtn2TextureHover)); // Set button image when its hovered
        ImageButton imageButton2 = new ImageButton(buttonStyle2);
        imageButton2.setPosition(50,50); // Set position of the button
        stage.addActor(imageButton2);
        
        answerBtn3Texture = new Texture(Gdx.files.internal("btnImage3.png"));
        answerBtn3TextureHover = new Texture(Gdx.files.internal("btnImage3Hover.png"));
        ImageButton.ImageButtonStyle buttonStyle3 = new ImageButton.ImageButtonStyle();
        buttonStyle3.imageUp = new TextureRegionDrawable(new TextureRegion(answerBtn3Texture)); // Set button image when its up
        buttonStyle3.imageOver = new TextureRegionDrawable(new TextureRegion(answerBtn3TextureHover)); // Set button image when its hovered
        ImageButton imageButton3 = new ImageButton(buttonStyle3);
        imageButton3.setPosition(WIDTH - 50 - imageButton3.getWidth(),HEIGHT/2 - 200); // Set position of the button
        stage.addActor(imageButton3);
        
        answerBtn4Texture = new Texture(Gdx.files.internal("btnImage4.png"));
        answerBtn4TextureHover = new Texture(Gdx.files.internal("btnImage4Hover.png"));
        ImageButton.ImageButtonStyle buttonStyle4 = new ImageButton.ImageButtonStyle();
        buttonStyle4.imageUp = new TextureRegionDrawable(new TextureRegion(answerBtn4Texture)); // Set button image when its up
        buttonStyle4.imageOver = new TextureRegionDrawable(new TextureRegion(answerBtn4TextureHover)); // Set button image when its hovered
        ImageButton imageButton4 = new ImageButton(buttonStyle4);
        imageButton4.setPosition(WIDTH - 50 - imageButton3.getWidth(),50); // Set position of the button
        stage.addActor(imageButton4);
        
        // Question Label Tag
        questionLabelImage = new Image(new Texture(Gdx.files.internal("questionImage.png")));
        questionLabelImage.setPosition(WIDTH/2 - questionLabelImage.getWidth()/2, HEIGHT/2 + questionLabelImage.getHeight() -50);
        stage.addActor(questionLabelImage);
        
        // Question Label
        questionLabel = new Label("", bigLabelStyle);
        questionLabel.setPosition(WIDTH/2 -200, HEIGHT/2 + 300);
        stage.addActor(questionLabel);
        
        // Answer Labels 
        for (int i = 1; i <= 4; i++) {
            answerLabels.add(new Label("", bigLabelStyle));
        }
        
        stage.addActor(answerLabels.get(0));
        stage.addActor(answerLabels.get(1));
        stage.addActor(answerLabels.get(2));
        stage.addActor(answerLabels.get(3));
        answerLabels.get(0).setPosition(imageButton.getX() + 10, imageButton.getY() + 30);
        answerLabels.get(1).setPosition(imageButton2.getX() + 10, imageButton2.getY() + 30);
        answerLabels.get(2).setPosition(imageButton3.getX() + 10, imageButton3.getY() + 30);
        answerLabels.get(3).setPosition(imageButton4.getX() + 10, imageButton4.getY() + 30);
        
        // Players Name Image Tag's
        player1ScoreTagImage = new Image(new Texture(Gdx.files.internal("playerScore.png")));
        player1ScoreTagImage.setPosition(0,HEIGHT - 81 - 105 -81);
        player2ScoreTagImage = new Image(new Texture(Gdx.files.internal("playerScore.png")));
        player2ScoreTagImage.setPosition(WIDTH-400,HEIGHT -81 -105 -81);
        stage.addActor(player1ScoreTagImage);
        stage.addActor(player2ScoreTagImage);
        
        // Score Labels
        player1ScoreLabel = new Label("Player 1 Score: " + player1.getScore(), bigLabelStyle);
        player1ScoreLabel.setPosition(120, HEIGHT -235);
        player2ScoreLabel = new Label("Player  2 Score: " + player2.getScore(), bigLabelStyle);
        player2ScoreLabel.setPosition(WIDTH-280, HEIGHT -235);
        stage.addActor(player1ScoreLabel);
        stage.addActor(player2ScoreLabel);
        currentPlayerScoreLabel = player1ScoreLabel;
        
        
        // Click Listener For Each Button
        ImageButton[] imageButtons = {imageButton, imageButton2, imageButton3, imageButton4};
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
        ScreenUtils.clear(0, 0, 1, 0);

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
            answerLabels.get(i).setText(answers.get(i));
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
