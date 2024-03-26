/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;
import com.mycompany.normaljavaclasses.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author teoberbic
 */
public class Assets {
    Stage stage;
    // MainMenuScreen
    private Music backgroundMusic;
    private Texture backgroundImageTexture;
    private Image backgroundImage;
    private Label welcomeTextLabel;
    private Label welcomeTextLabel2;
    private Texture startButtonTexture;
    private ImageButton imageButton;
    private Label.LabelStyle bigLabelStyle;
    // CategoryPickerScreen
    private Image arrowImage;
    private Image wheel;
    private Image player1NameTagImage = new Image(new Texture(Gdx.files.internal("playerName.png")));;
    private Image player2NameTagImage;
    private Image player1CategoryTagImage;
    private Image player2CategoryTagImage;
    private Image player1IconImage;
    private Image categoryIcon1;
    private Image player2IconImage;
    private Image categoryIcon2;
    private Table tableUI;
    private Skin skinUI;
    private Label nameLabelUI;
    private TextField nameFieldUI;
    private Label player1NameLabel;
    private Label player1CategoryLabel;
    private Table tableUI2;
    private Label nameLabelUI2;
    private TextField nameFieldUI2;
    private Label player2NameLabel;
    private Label player2CategoryLabel;
    private float countdownTime = 10;
    private Label timerLabel;
    // GameScreen
    private float timer = 15;
    private Image questionLabelImage;
    private Label questionLabel;
    List<Label> answerLabels = new ArrayList<Label>();
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private ImageButton imageButton4;
    private Image player1ScoreTagImage;
    private Image player2ScoreTagImage;
    private Label player1ScoreLabel;
    private Label player2ScoreLabel;
    private Player player1;
    private Player player2;
    private Label currentPlayerScoreLabel;
    private ImageButton[] imageButtons = {imageButton, imageButton2, imageButton3, imageButton4};
    private Image scoreIcon;
    private Image scoreIcon2;
    
    // Constructor for MainMenuScreen & CategoryPickerScreen
    public Assets(Stage stage) {
        this.stage = stage;
    }
    
    // Constructor for GameScreen
    public Assets(Stage stage, Player player1, Player player2, Image player1NameTagImage, Image player2NameTagImage, Image player1CategoryTagImage, Image player2CategoryTagImage, Label player1NameLabel, Label player2NameLabel, Label player1CategoryLabel, Label player2CategoryLabel) {
        this.stage = stage;
        this.player1 = player1;
        this.player2 = player2;
        this.player1NameTagImage = player1NameTagImage;
        this.player2NameTagImage = player2NameTagImage;
        this.player1CategoryTagImage = player1CategoryTagImage;
        this.player2CategoryTagImage = player2CategoryTagImage;
        this.player1NameLabel = player1NameLabel;
        this.player2NameLabel = player2NameLabel;
        this.player1CategoryLabel = player1CategoryLabel;
        this.player2CategoryLabel = player2CategoryLabel;
    }
    
    
    /////////////////////////
    //Load Main Menu Screen//
    /////////////////////////
    public void loadMainMenuScreen() {
        // Music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        
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
        bigLabelStyle = skinUI.get("big", Label.LabelStyle.class);
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
        imageButton = new ImageButton(buttonStyle);
        imageButton.setPosition(WIDTH/2 - imageButton.getWidth()/2, HEIGHT/2 - imageButton.getHeight()/2); // Set position of the button
        stage.addActor(imageButton);
    }
    
    
    // Getters for MainMenuScreen
    public Music getBackgroundMusic() {
        return this.backgroundMusic;
    }
    
    public ImageButton getImageButton() {
        return this.imageButton;
    }
    
    
    ///////////////////////////////
    //Load Category Picker Screen//
    //////////////////////////////
    public void loadCategoryPickerScreen() {
        // Wheel
        wheel = new Image(new Texture(Gdx.files.internal("wheel3.png")));
        wheel.setOrigin(wheel.getWidth() / 2, wheel.getHeight() / 2); // sets the center of image as the origin so it spins from the center
        wheel.setPosition(Gdx.graphics.getWidth() / 2 - wheel.getWidth() / 2, Gdx.graphics.getHeight() / 2 - wheel.getHeight() / 2); // where the image is on the sreen
        stage.addActor(wheel);
        wheel.setVisible(false);
        
        // Players Name Image Tag's
        player1NameTagImage = new Image(new Texture(Gdx.files.internal("playerName.png")));
        player1NameTagImage.setPosition(0,HEIGHT - 81);
        stage.addActor(player1NameTagImage);
        player1NameTagImage.setVisible(false);
        player2NameTagImage = new Image(new Texture(Gdx.files.internal("playerName.png")));
        player2NameTagImage.setPosition(WIDTH-400,HEIGHT -81);
        stage.addActor(player2NameTagImage);
        player2NameTagImage.setVisible(false);
        
        // Players Category Image Tag's
        player1CategoryTagImage = new Image(new Texture(Gdx.files.internal("categoryName.png")));
        player1CategoryTagImage.setPosition(0,HEIGHT - 81 -105);
        stage.addActor(player1CategoryTagImage);
        player1CategoryTagImage.setVisible(false);
        player2CategoryTagImage = new Image(new Texture(Gdx.files.internal("categoryName.png")));
        player2CategoryTagImage.setPosition(WIDTH-400,HEIGHT - 81 - 105);
        stage.addActor(player2CategoryTagImage);
        player2CategoryTagImage.setVisible(false);
        
        arrowImage = new Image(new Texture(Gdx.files.internal("arrow.png")));
        arrowImage.setPosition(WIDTH/2 - arrowImage.getWidth()/2, HEIGHT - 50 - arrowImage.getHeight()/2);
        stage.addActor(arrowImage);
        
         // Players Icon Image's
        player1IconImage = new Image(new Texture(Gdx.files.internal("player1Icon.png")));
        player1IconImage.setPosition(15,HEIGHT - player1IconImage.getHeight() - 5);
        stage.addActor(player1IconImage);
        player1IconImage.setVisible(false);
        
        categoryIcon1 = new Image(new Texture(Gdx.files.internal("categoryIcon1.png")));
        categoryIcon1.setPosition(15,HEIGHT - player1IconImage.getHeight() - 100);
        stage.addActor(categoryIcon1);
        categoryIcon1.setVisible(false);

        player2IconImage = new Image(new Texture(Gdx.files.internal("player2Icon.png")));
        player2IconImage.setPosition(WIDTH - player2IconImage.getWidth() - player2CategoryTagImage.getWidth() +60 ,HEIGHT - player1IconImage.getHeight() - 5);
        stage.addActor(player2IconImage);
        player2IconImage.setVisible(false);

        categoryIcon2 = new Image(new Texture(Gdx.files.internal("categoryIcon2.png")));
        categoryIcon2.setPosition(WIDTH - player2IconImage.getWidth() - player2CategoryTagImage.getWidth() +60 ,HEIGHT - player1IconImage.getHeight() - 100);
        stage.addActor(categoryIcon2);
        categoryIcon2.setVisible(false);
        
        // Input Handling Player 1
        tableUI = new Table();
        tableUI.setFillParent(true);
        stage.addActor(tableUI);

        skinUI = new Skin(Gdx.files.internal("glassy-ui.json"));
        Label.LabelStyle bigLabelStyle = skinUI.get("big", Label.LabelStyle.class);
        bigLabelStyle.fontColor = Color.WHITE;
        skinUI.getFont("font-big").getData().setScale(.41f);

        nameLabelUI = new Label("Player 1 Enter Name: ", bigLabelStyle);
        nameFieldUI = new TextField("", skinUI);
        tableUI.add(nameLabelUI);
        tableUI.add(nameFieldUI).width(150);

        player1NameLabel = new Label("Player 1 Name: ", bigLabelStyle);
        player1NameLabel.setPosition(120, HEIGHT -50);
        stage.addActor(player1NameLabel);
        player1NameLabel.setVisible(false);
        player1CategoryLabel = new Label("Player 1 Category: ", bigLabelStyle);
        player1CategoryLabel.setPosition(120, HEIGHT -145);
        stage.addActor(player1CategoryLabel);
        player1CategoryLabel.setVisible(false);
        
        // Player 2 (Note create a function so you dont have to make 2 of these)
        tableUI2 = new Table();
        tableUI2.setFillParent(true);
        stage.addActor(tableUI2);

        nameLabelUI2 = new Label("Player 2 Enter Name: ", skinUI); //label to the left
        nameLabelUI2.setVisible(false);
        nameFieldUI2 = new TextField("", skinUI); //field to capture name
        nameFieldUI2.setVisible(false);
        tableUI2.add(nameLabelUI2);
        tableUI2.add(nameFieldUI2).width(150);

        player2NameLabel = new Label("Player 2 Name: ", bigLabelStyle);
        player2NameLabel.setPosition(WIDTH - 280, HEIGHT -50);
        stage.addActor(player2NameLabel);
        player2NameLabel.setVisible(false);
        player2CategoryLabel = new Label("Player 2 Category: ", bigLabelStyle);
        player2CategoryLabel.setPosition(WIDTH-280, HEIGHT -145);
        stage.addActor(player2CategoryLabel);
        player2CategoryLabel.setVisible(false);
        
        // Timer
        timerLabel = new Label("Countdown Until Game Start: " + countdownTime, bigLabelStyle);
        timerLabel.setPosition(WIDTH/2 - 200, HEIGHT/2);
        stage.addActor(timerLabel);
        timerLabel.setVisible(false);
        
    }
    
    // Getters for CategoryPickerScreen
    public Image getWheel() {
        return this.wheel;
    }
    
    public Image getPlayer1NameTagImage() {
        return this.player1NameTagImage;
    }
    
    public Image getPlayer2NameTagImage() {
        return this.player2NameTagImage;
    }
    
    public Image getPlayer1CategoryTagImage() {
        return this.player1CategoryTagImage;
    }
    
    public Image getPlayer2CategoryTagImage() {
        return this.player2CategoryTagImage;
    }
    
    public Image getPlayer1IconImage() {
        return this.player1IconImage;
    }
    
    public Image getCategoryIcon1() {
        return this.categoryIcon1;
    }
    
    public Image getPlayer2IconImage () {
        return this.player2IconImage;
    }
    
    public Image getCategoryIcon2() {
        return this.categoryIcon2;
    }
    
    public Label getNameLabelUI() {
        return this.nameLabelUI;
    }
    
    public TextField getNameFieldUI() {
        return this.nameFieldUI;
    }
    
    public Label getPlayer1NameLabel() {
        return this.player1NameLabel;
    }
    
    public Label getPlayer1CategoryLabel() {
        return this.player1CategoryLabel;
    }
    
    public Label getNameLabelUI2() {
        return this.nameLabelUI2;
    }
    
    public TextField getNameFieldUI2() {
        return this.nameFieldUI2;
    }
    
    public Label getPlayer2NameLabel() {
        return this.player2NameLabel;
    }
    
    public Label getPlayer2CategoryLabel() {
        return this.player2CategoryLabel;
    }
    
    public Label getTimerLabel() {
        return this.timerLabel;
    }
    
    
    ////////////////////
    //Load Game Screen//
    ////////////////////
    public void loadGameScreen() {
        // Players Name Tag's 
        player1NameTagImage = new Image(new Texture(Gdx.files.internal("playerName.png")));
        player1NameTagImage.setPosition(0,HEIGHT - 81);
        player2NameTagImage = new Image(new Texture(Gdx.files.internal("playerName.png")));
        player2NameTagImage.setPosition(WIDTH-400,HEIGHT -81);
        stage.addActor(player1NameTagImage);
        stage.addActor(player2NameTagImage);
        
        // Players Category Tag's
        player1CategoryTagImage = new Image(new Texture(Gdx.files.internal("categoryName.png")));
        player1CategoryTagImage.setPosition(0,HEIGHT - 81 -105);
        player2CategoryTagImage = new Image(new Texture(Gdx.files.internal("categoryName.png")));
        player2CategoryTagImage.setPosition(WIDTH-400,HEIGHT - 81 - 105);
        stage.addActor(player1CategoryTagImage);
        stage.addActor(player2CategoryTagImage);
        
        // Skin
        skinUI = new Skin(Gdx.files.internal("uiskin.json"));
        skinUI.getFont("default-font").getData().setScale(2.0f);
        skinUI = new Skin(Gdx.files.internal("glassy-ui.json"));
        Label.LabelStyle bigLabelStyle = skinUI.get("big", Label.LabelStyle.class);
        bigLabelStyle.fontColor = Color.WHITE;
        skinUI.getFont("font-big").getData().setScale(.41f);
        
        // Players Name Labels
        
        //player1NameLabel = new Label("Player 1 Name: ", bigLabelStyle);
        player1NameLabel.setPosition(120, HEIGHT -50);
        //player2NameLabel = new Label("Player 2 Name: ", bigLabelStyle);
        player2NameLabel.setPosition(WIDTH - 280, HEIGHT -50);
        stage.addActor(player1NameLabel); 
        stage.addActor(player2NameLabel);
        
        // Players Category Labels
        
        //player1CategoryLabel = new Label("Player 1 Category: ", bigLabelStyle);
        player1CategoryLabel.setPosition(120, HEIGHT -145);
        //player2CategoryLabel = new Label("Player 2 Category: ", bigLabelStyle);
        player2CategoryLabel.setPosition(WIDTH-280, HEIGHT -145);
        stage.addActor(player1CategoryLabel);
        stage.addActor(player2CategoryLabel);
        
        // Timer
        timerLabel = new Label("Time: " + timer, bigLabelStyle);
        timerLabel.setPosition(WIDTH/2 - 50, HEIGHT/2);
        stage.addActor(timerLabel);
        
        // Question Label Tag
        questionLabelImage = new Image(new Texture(Gdx.files.internal("questionImage.png")));
        questionLabelImage.setPosition(WIDTH/2 - questionLabelImage.getWidth()/2, HEIGHT/2 + questionLabelImage.getHeight() -50);
        stage.addActor(questionLabelImage);
        
        // Question Label
        questionLabel = new Label("", bigLabelStyle);
        questionLabel.setPosition(WIDTH/2 -200, HEIGHT/2 + 300);
        stage.addActor(questionLabel);
        
        // Create an Image Buttons (create a function)
        imageButtons = new ImageButton[] {
        imageButton = createImageButton("1", 50, HEIGHT/2 - 200),
        imageButton2 = createImageButton("2", 50,50),
        imageButton3 = createImageButton("3", WIDTH - 50 - 300,HEIGHT/2 - 200),
        imageButton4 = createImageButton("4", WIDTH - 50 - 300,50)};
        
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
        
        // Players Icon Image's
        player1IconImage = new Image(new Texture(Gdx.files.internal("player1Icon.png")));
        player1IconImage.setPosition(15,HEIGHT - player1IconImage.getHeight() - 5);
        stage.addActor(player1IconImage);
        
        categoryIcon1 = new Image(new Texture(Gdx.files.internal("categoryIcon1.png")));
        categoryIcon1.setPosition(15,HEIGHT - player1IconImage.getHeight() - 100);
        stage.addActor(categoryIcon1);

        player2IconImage = new Image(new Texture(Gdx.files.internal("player2Icon.png")));
        player2IconImage.setPosition(WIDTH - player2IconImage.getWidth() - player2CategoryTagImage.getWidth() +60 ,HEIGHT - player1IconImage.getHeight() - 5);
        stage.addActor(player2IconImage);

        categoryIcon2 = new Image(new Texture(Gdx.files.internal("categoryIcon2.png")));
        categoryIcon2.setPosition(WIDTH - player2IconImage.getWidth() - player2CategoryTagImage.getWidth() +60 ,HEIGHT - player1IconImage.getHeight() - 100);
        stage.addActor(categoryIcon2);
        
        scoreIcon = new Image(new Texture(Gdx.files.internal("score.png")));
        scoreIcon.setPosition(15,HEIGHT - player1IconImage.getHeight() - 190);
        stage.addActor(scoreIcon);
        
        scoreIcon2 = new Image(new Texture(Gdx.files.internal("score.png")));
        scoreIcon2.setPosition(WIDTH - player2IconImage.getWidth() - player2CategoryTagImage.getWidth() +60,HEIGHT - player2IconImage.getHeight() - 190);
        stage.addActor(scoreIcon2);
    }
    
    
    // Fn for Game Screen
    public ImageButton createImageButton(String num, int x, int y) {
        
        Texture answerBtnTexture = new Texture(Gdx.files.internal("btnImage" + num + ".png"));
        Texture answerBtnTextureHover = new Texture(Gdx.files.internal("btnImage" + num + "Hover.png"));
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(answerBtnTexture)); // Set button image when its up
        buttonStyle.imageOver = new TextureRegionDrawable(new TextureRegion(answerBtnTextureHover)); // Set button image when its hovered
        ImageButton imageButton = new ImageButton(buttonStyle);
        imageButton.setPosition(x,y); // Set position of the button
        stage.addActor(imageButton);
        
        return imageButton;
    }
    
    
    // Getters for Game Screen
    public Label getQuestionLabel() {
        return this.questionLabel;
    }
    
    public List<Label> getAnswerLabels(){
        
        return answerLabels;
    }
    
    public Label getPlayer1ScoreLabel(){
        return player1ScoreLabel;
    }
    
    public Label getPlayer2ScoreLabel(){
        return player2ScoreLabel;
    }
    
    public ImageButton[] getImageButtonsArray() {
        return this.imageButtons;
    }
    
    public Label getCurrentPlayerScoreLabel() {
        return this.currentPlayerScoreLabel;
    }
}
