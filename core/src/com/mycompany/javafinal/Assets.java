/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;
import com.mycompany.normaljavaclasses.Player;
import com.mycompany.normaljavaclasses.Question;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.text.StringEscapeUtils;

/**
 *
 * @author teoberbic
 */
public class Assets {
    Stage stage;
    // MainMenuScreen
    private Music menuBackgroundMusic;
    private Texture backgroundImageTexture;
    private Image backgroundImage;
    private Label welcomeTextLabel;
    private Label welcomeTextLabel2;
    private Texture startButtonTexture;
    private ImageButton imageButton;
    private Label.LabelStyle bigLabelStyle;
    // CategoryPickerScreen
    private boolean player1EnteredName;
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
    private Label errorEnterNameLabel;
    private Sound correctAnswerSFX;
    
    // should only be static if all classes have field
    public static Label errorNameTooLongLabel;
    private Sound incorrectAnswerSFX;
    private Sound wheelSpinSFX;
    private Sound enterSFX;
    private Sound enterErrorSFX;
    private Sound categoryAppearSFX;
    private Sound clockTickingSFX;
    private Label nameLabelNote;
    
    //EndGameScreen
    private Image winnerBanner;
    private ImageButton replayButton;
    private Texture replayButtonTexture;
    private Image correctAnswerImage;
    private Image incorrectAnswerImage;
    private Label player1GainedPointLabel;
    private Label player2GainedPointLabel;
    private Music gameBackgroundMusic;
    private Label player1TurnLabel;
    private Label player2TurnLabel;
    private List<Question> player1Questions;
    private List<Question> player2Questions;
    private ArrayList<Label> playerQLabels;
    private Skin skinUI2;
    private ArrayList<Label> playerAnsLabels;
    private Label winnerLabel;
    private Label player1QsANDAsLabel;
    private Label player2QsANDAsLabel;
    private Music endGameCheer;
    private Label spaceLabel;
    private Skin skinUI3;
    private Label choosePlayer1Label;
    private Label revealPlayer1Label;
    
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
    
    // Constructor for EndGameScreen 
    public Assets(Stage stage, Player player1, Player player2, List<Question> player1Questions, List<Question> player2Questions) {
        this.stage = stage;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Questions = player1Questions;
        this.player2Questions = player2Questions;
        
    }
    /////////////////////////
    //Load Main Menu Screen//
    /////////////////////////
    public void loadMainMenuScreen() {
        // Music
        menuBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("menuMusic.mp3"));
        menuBackgroundMusic.setLooping(true);
        menuBackgroundMusic.setVolume(0.5f);
        
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
        return this.menuBackgroundMusic;
    }
    
    public ImageButton getImageButton() {
        return this.imageButton;
    }
    
    
    ///////////////////////////////
    //Load Category Picker Screen//
    //////////////////////////////
    public void loadCategoryPickerScreen() {
        //player1EnteredName = false;
        // Wheel
        wheel = new Image(new Texture(Gdx.files.internal("wheel3.png")));
        wheel.setOrigin(wheel.getWidth()/2, wheel.getHeight()/2); // sets the center of image as the origin so it spins from the center
        wheel.setPosition(WIDTH/2 - wheel.getWidth()/2, Gdx.graphics.getHeight()/2 - wheel.getHeight()/2); // where the image is on the sreen
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
        arrowImage.setPosition(WIDTH/2 - arrowImage.getWidth()/2 - 7, HEIGHT - 50 - arrowImage.getHeight()/2);
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
        Label.LabelStyle bigLabelStyleWhite = new Label.LabelStyle(skinUI.get("big", Label.LabelStyle.class));
        bigLabelStyleWhite.fontColor = Color.WHITE;
        skinUI.getFont("font-big").getData().setScale(.41f);
        
        Label.LabelStyle bigLabelStyleRed = new Label.LabelStyle(skinUI.get("big", Label.LabelStyle.class));
        bigLabelStyleRed.fontColor = Color.RED;
        skinUI.getFont("font-big").getData().setScale(.41f);

        nameLabelUI = new Label("Player 1 Enter Name: ", bigLabelStyleWhite);
        nameFieldUI = new TextField("", skinUI);
        nameFieldUI.setVisible(true);
        tableUI.add(nameLabelUI);
        tableUI.add(nameFieldUI).width(150);

        player1NameLabel = new Label("Player 1 Name :", bigLabelStyleWhite);
        player1NameLabel.setPosition(120, HEIGHT -50);
        stage.addActor(player1NameLabel);
        player1NameLabel.setVisible(false);
        player1CategoryLabel = new Label("Player 1 Category: ", bigLabelStyleWhite);
        player1CategoryLabel.setPosition(120, HEIGHT -145);
        stage.addActor(player1CategoryLabel);
        player1CategoryLabel.setVisible(false);
        
        // Player 2 (Note create a function so you dont have to make 2 of these)
        tableUI2 = new Table();
        tableUI2.setFillParent(true);
        stage.addActor(tableUI2);

        nameLabelUI2 = new Label("Player 2 Enter Name:", bigLabelStyleWhite); //label to the left
        nameLabelUI2.setVisible(false);
        nameFieldUI2 = new TextField("", skinUI); //field to capture name
        nameFieldUI2.setVisible(false);
        tableUI2.add(nameLabelUI2);
        tableUI2.add(nameFieldUI2).width(150);

        player2NameLabel = new Label("Player 2 Name: ", bigLabelStyleWhite);
        player2NameLabel.setPosition(WIDTH - 280, HEIGHT -50);
        stage.addActor(player2NameLabel);
        player2NameLabel.setVisible(false);
        player2CategoryLabel = new Label("Player 2 Category: ", bigLabelStyleWhite);
        player2CategoryLabel.setPosition(WIDTH-280, HEIGHT -145);
        stage.addActor(player2CategoryLabel);
        player2CategoryLabel.setVisible(false);
        
        // Timer + Timer Vars
        timerLabel = new Label("Countdown Until Game Start: " + countdownTime, bigLabelStyleWhite);
        timerLabel.setPosition(WIDTH/2 - 200, HEIGHT/2);
        stage.addActor(timerLabel);
        timerLabel.setVisible(false);
        
        // Error Handling
        nameLabelNote = new Label("Note: Name can only be\n   up to six characters\n\n      Hint: Be creative", bigLabelStyleRed);
        nameLabelNote.setPosition(WIDTH/2 - 100, HEIGHT/2 + 100);
        stage.addActor(nameLabelNote);
        nameLabelNote.setVisible(true);
        
        errorEnterNameLabel = new Label("Please Enter a Name", bigLabelStyleRed);
        errorEnterNameLabel.setPosition(WIDTH/2 - 100, HEIGHT/2 - 100);
        stage.addActor(errorEnterNameLabel);
        errorEnterNameLabel.setVisible(false);
        
        errorNameTooLongLabel = new Label("Name is too long", bigLabelStyleRed);
        errorNameTooLongLabel.setPosition(WIDTH/2 - 100, HEIGHT/2 - 100);
        stage.addActor(errorNameTooLongLabel);
        errorNameTooLongLabel.setVisible(false);
        
        // Sound Effects
        wheelSpinSFX = Gdx.audio.newSound(Gdx.files.internal("wheelSpinSFX.mp3"));
        enterSFX = Gdx.audio.newSound(Gdx.files.internal("enterSFX.mp3"));
        enterErrorSFX = Gdx.audio.newSound(Gdx.files.internal("enterErrorSFX.mp3"));
        categoryAppearSFX = Gdx.audio.newSound(Gdx.files.internal("categoryAppearSFX.mp3"));
        clockTickingSFX = Gdx.audio.newSound(Gdx.files.internal("clockTickingSFX.mp3"));
        
        // Press Space
        spaceLabel = new Label("PRESS SPACE TO SPIN" , bigLabelStyleWhite);
        spaceLabel.setPosition(WIDTH/2 - spaceLabel.getWidth()/2, 10);
        stage.addActor(spaceLabel);
        spaceLabel.setVisible(false);
        
        // Loading Label
        choosePlayer1Label = new Label("Any Player Pick Heads or Tails To Decide Who Will Go First", bigLabelStyleWhite);
        choosePlayer1Label.setPosition(WIDTH/2 - choosePlayer1Label.getWidth()/2, HEIGHT/2);
        stage.addActor(choosePlayer1Label);
        choosePlayer1Label.setVisible(false);
        
        revealPlayer1Label = new Label("", bigLabelStyleWhite);
        revealPlayer1Label.setPosition(15, HEIGHT - player1NameTagImage.getHeight() - player1CategoryTagImage.getHeight());
        stage.addActor(revealPlayer1Label);
        revealPlayer1Label.setVisible(false);
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
    
    public Label getErrorEnterNameLabel() {
        return errorEnterNameLabel;
    }
    
    public Sound getWheelSpinSFX() {
        return this.wheelSpinSFX;
    }
    
    public Sound getEnterSFX() {
        return this.enterSFX;
    }
    
    public Sound getEnterErrorSFX() {
        return this.enterErrorSFX;
    }
    
    public Sound getCategoryAppearSFX() {
        return this.categoryAppearSFX;
    }
    
    public Sound getClockTickingSFX() {
        return this.clockTickingSFX;
    }
    
    public Label getNameLabelNote() {
        return this.nameLabelNote;
    }
    
    public boolean getPlayer1EnteredName() {
        return this.player1EnteredName;
    }
    
    public Label getSpaceLabel() {
        return this.spaceLabel;
    }
    
    public Label getChoosePlayer1Label() {
        return this.choosePlayer1Label;
    }
    
    public Label getRevealPlayer1Label() {
        return this.revealPlayer1Label;
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
        skinUI = new Skin(Gdx.files.internal("glassy-ui.json"));
        Label.LabelStyle bigLabelStyleBlack = new Label.LabelStyle(skinUI.get("big", Label.LabelStyle.class));
        bigLabelStyleBlack.fontColor = Color.BLACK;
        skinUI.getFont("font-big").getData().setScale(.41f);
        
        Label.LabelStyle bigLabelStyleWhite = new Label.LabelStyle(skinUI.get("big", Label.LabelStyle.class));
        bigLabelStyleWhite.fontColor = Color.WHITE;
       
        skinUI2 = new Skin(Gdx.files.internal("glassy-ui.json"));
        Label.LabelStyle extraBigLabelStyleWhite = new Label.LabelStyle(skinUI2.get("big", Label.LabelStyle.class));
        extraBigLabelStyleWhite.fontColor = Color.WHITE;
        skinUI2.getFont("font-big").getData().setScale(.60f);
        
        Label.LabelStyle extraBigLabelStyleGreen = new Label.LabelStyle(skinUI2.get("big", Label.LabelStyle.class));
        extraBigLabelStyleGreen.fontColor = Color.GREEN;
        
        // Players Name Labels
        //player1NameLabel = new Label("Player 1 Name: ", bigLabelStyleWhite);
        player1NameLabel.setPosition(120, HEIGHT -50);
        //player2NameLabel = new Label("Player 2 Name: ", bigLabelStyleWhite);
        player2NameLabel.setPosition(WIDTH - 280, HEIGHT -50);
        stage.addActor(player1NameLabel); 
        stage.addActor(player2NameLabel);
        
        // Players Category Labels
        //player1CategoryLabel = new Label("Player 1 Category: ", bigLabelStyleWhite);
        player1CategoryLabel.setPosition(120, HEIGHT -145);
        //player2CategoryLabel = new Label("Player 2 Category: ", bigLabelStyleWhite);
        player2CategoryLabel.setPosition(WIDTH-280, HEIGHT -145);
        stage.addActor(player1CategoryLabel);
        stage.addActor(player2CategoryLabel);
        
        // Timer
        skinUI.getFont("font-big").getData().setScale(.75f);
        timerLabel = new Label("Time: " + timer, extraBigLabelStyleWhite);
        timerLabel.setPosition(WIDTH/2 - timerLabel.getWidth()/2, HEIGHT/2 + 100);
        stage.addActor(timerLabel);
        
        // Question Label Tag
        questionLabelImage = new Image(new Texture(Gdx.files.internal("questionImage.png")));
        questionLabelImage.setPosition(WIDTH/2 - questionLabelImage.getWidth()/2, HEIGHT/2 + questionLabelImage.getHeight() -50);
        stage.addActor(questionLabelImage);
        
        // Question Label
        skinUI.getFont("font-big").getData().setScale(.41f);
        questionLabel = new Label("", bigLabelStyleBlack);
        questionLabel.setPosition(WIDTH/2 -230, HEIGHT/2 + 250);
        stage.addActor(questionLabel);
        
        // Create an Image Buttons (create a function)
        imageButtons = new ImageButton[] {
        imageButton = createImageButtonQuestion("1", 50, HEIGHT/2 - 200),
        imageButton2 = createImageButtonQuestion("2", 50,50),
        imageButton3 = createImageButtonQuestion("3", WIDTH - 50 - 300,HEIGHT/2 - 200),
        imageButton4 = createImageButtonQuestion("4", WIDTH - 50 - 300,50)};
        
        // Answer Labels
        for (int i = 1; i <= 4; i++) {
            answerLabels.add(new Label("", bigLabelStyleBlack));
        }
        
        stage.addActor(answerLabels.get(0));
        stage.addActor(answerLabels.get(1));
        stage.addActor(answerLabels.get(2));
        stage.addActor(answerLabels.get(3));
        answerLabels.get(0).setPosition(imageButton.getX() + 10, imageButton.getY() + 75);
        answerLabels.get(1).setPosition(imageButton2.getX() + 10, imageButton2.getY() + 75);
        answerLabels.get(2).setPosition(imageButton3.getX() + 10, imageButton3.getY() + 75);
        answerLabels.get(3).setPosition(imageButton4.getX() + 10, imageButton4.getY() + 75);
        
        // Players Name Image Tag's
        player1ScoreTagImage = new Image(new Texture(Gdx.files.internal("playerScore.png")));
        player1ScoreTagImage.setPosition(0,HEIGHT - 81 - 105 -81);
        player2ScoreTagImage = new Image(new Texture(Gdx.files.internal("playerScore.png")));
        player2ScoreTagImage.setPosition(WIDTH-400,HEIGHT -81 -105 -81);
        stage.addActor(player1ScoreTagImage);
        stage.addActor(player2ScoreTagImage);
        
        // Score Labels
        player1ScoreLabel = new Label("Player 1 Score: " + player1.getScore(), bigLabelStyleWhite);
        player1ScoreLabel.setPosition(120, HEIGHT -235);
        player2ScoreLabel = new Label("Player 2 Score: " + player2.getScore(), bigLabelStyleWhite);
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
        
        // Sound Effects
        correctAnswerSFX = Gdx.audio.newSound(Gdx.files.internal("correctAnswer.mp3"));
        incorrectAnswerSFX = Gdx.audio.newSound(Gdx.files.internal("incorrectAnswer.mp3"));
        
        // Correct Answer Image
        correctAnswerImage = new Image(new Texture(Gdx.files.internal("correctAnswer.png")));
        correctAnswerImage.setPosition(WIDTH/2 - correctAnswerImage.getWidth()/2, HEIGHT/2 - 350);
        stage.addActor(correctAnswerImage);
        correctAnswerImage.setVisible(false);
        
        // Correct Answer Image
        incorrectAnswerImage = new Image(new Texture(Gdx.files.internal("incorrectAnswer.png")));
        incorrectAnswerImage.setPosition(WIDTH/2 - incorrectAnswerImage.getWidth()/2, HEIGHT/2 - 350);
        stage.addActor(incorrectAnswerImage);
        incorrectAnswerImage.setVisible(false);
        
        // Player Gained Point Label
        player1GainedPointLabel = new Label(player1.getName() + " Score: + 1" , extraBigLabelStyleGreen);
        player1GainedPointLabel.setPosition(WIDTH/2 - player1GainedPointLabel.getWidth()/2, HEIGHT/2);
        player2GainedPointLabel = new Label(player2.getName() + "  Score: + 1", extraBigLabelStyleGreen);
        player2GainedPointLabel.setPosition(WIDTH/2 -player2GainedPointLabel.getWidth()/2, HEIGHT/2);
        stage.addActor(player1GainedPointLabel);
        stage.addActor(player2GainedPointLabel);
        player1GainedPointLabel.setVisible(false);
        player2GainedPointLabel.setVisible(false);

        // Background Music
        gameBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("gameBackgroundMusic.mp3"));
        gameBackgroundMusic.setLooping(true);
        gameBackgroundMusic.setVolume(1f);
        
        // Turn Label
        player1TurnLabel = new Label(player1.getName().trim() + "'s Turn", extraBigLabelStyleWhite);
        player1TurnLabel.setPosition(WIDTH/2 - player1TurnLabel.getWidth()/2, HEIGHT/2 -60);
        player2TurnLabel = new Label(player2.getName().trim() + "'s Turn", extraBigLabelStyleWhite);
        player2TurnLabel.setPosition(WIDTH/2 - player2TurnLabel.getWidth()/2, HEIGHT/2 -60);
        stage.addActor(player1TurnLabel);
        stage.addActor(player2TurnLabel);
        player1TurnLabel.setVisible(false);
        player2TurnLabel.setVisible(false);
    }
    
    
    // Fn for Game Screen
    public ImageButton createImageButtonQuestion(String num, int x, int y) {
        
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
    
    public Sound getCorrectAnswerSFX() {
        return this.correctAnswerSFX;
    }
    
    public Sound getIncorrectAnswerSFX() {
        return this.incorrectAnswerSFX;
    }
    
    public Image getCorrectAnswerImage() {
        return this.correctAnswerImage;
    }
    
    public Image getIncorrectAnswerImage() {
        return this.incorrectAnswerImage;
    }
    
    public Label getPlayer1GainedPointLabel() {
        return this.player1GainedPointLabel;
    }
    
    public Label getPlayer2GainedPointLabel() {
        return this.player2GainedPointLabel;
    }
    
    public Music getGameBackgroundMusic() {
        return this.gameBackgroundMusic;
    }
    
    public Label getPlayer1TurnLabel() {
        return this.player1TurnLabel;
    }
    
    public Label getPlayer2TurnLabel() {
        return this.player2TurnLabel;
    }
    ///////////////////////
    //Load EndGame Screen//
    ///////////////////////
    
    public void loadEndGameScreen() {
      
        // Skin
        skinUI = new Skin(Gdx.files.internal("glassy-ui.json"));
        Label.LabelStyle bigLabelStyleWhite = new Label.LabelStyle(skinUI.get("big", Label.LabelStyle.class));
        bigLabelStyleWhite.fontColor = Color.WHITE;
        skinUI.getFont("font-big").getData().setScale(.41f);
        
        Label.LabelStyle bigLabelStyleGreen = new Label.LabelStyle(skinUI.get("big", Label.LabelStyle.class));
        bigLabelStyleGreen.fontColor = Color.GREEN;
        skinUI.getFont("font-big").getData().setScale(.41f);
        
        // Skin 2
        skinUI2 = new Skin(Gdx.files.internal("glassy-ui.json"));
        Label.LabelStyle labelStyleWhite = new Label.LabelStyle(skinUI2.get("default", Label.LabelStyle.class));
        labelStyleWhite.fontColor = Color.WHITE;
        skinUI2.getFont("font").getData().setScale(.75f);
        
        skinUI3 = new Skin(Gdx.files.internal("glassy-ui.json"));
        Label.LabelStyle extraBigLabelStyleWhite = new Label.LabelStyle(skinUI3.get("big", Label.LabelStyle.class));
        extraBigLabelStyleWhite.fontColor = Color.WHITE;
        skinUI3.getFont("font-big").getData().setScale(.60f);
        
        Label.LabelStyle labelStyleGreen = new Label.LabelStyle(skinUI2.get("default", Label.LabelStyle.class));
        labelStyleGreen.fontColor = Color.GREEN;
        skinUI2.getFont("font").getData().setScale(.75f);
        
        // Players Category Tag's
        player1CategoryTagImage = new Image(new Texture(Gdx.files.internal("categoryName.png")));
        player1CategoryTagImage.setPosition(0,HEIGHT -105);
        player2CategoryTagImage = new Image(new Texture(Gdx.files.internal("categoryName.png")));
        player2CategoryTagImage.setPosition(WIDTH- player1CategoryTagImage.getWidth() - 25,HEIGHT- 105);
        stage.addActor(player1CategoryTagImage);
        stage.addActor(player2CategoryTagImage);
        
        // Players Category Labels
        player1CategoryLabel = new Label("Player 1 Category: " +"\n" + player1.getCategory(), bigLabelStyleWhite);
        player1CategoryLabel.setPosition(120, HEIGHT -75);
        player2CategoryLabel = new Label("Player 2 Category: " +"\n" + player2.getCategory(), bigLabelStyleWhite);
        player2CategoryLabel.setPosition(WIDTH - 280, HEIGHT -75);
        stage.addActor(player1CategoryLabel);
        stage.addActor(player2CategoryLabel);
        
        // Winner Banner
        winnerBanner = new Image(new Texture(Gdx.files.internal("winnerBanner.png")));
        winnerBanner.setPosition(WIDTH/2 - winnerBanner.getWidth()/2, HEIGHT - winnerBanner.getHeight());
        stage.addActor(winnerBanner);
        
        // Winning Music
        endGameCheer = Gdx.audio.newMusic(Gdx.files.internal("endGameCheer.mp3"));
        endGameCheer.setLooping(true);
        endGameCheer.setVolume(0.5f);
        
        // Winner Label
        String winnerName = "";
        if (player1.getScore() > player2.getScore()) {
            winnerName = player1.getName();
        } else {
            winnerName = player2.getName();
        }
        winnerLabel = new Label("WINNER: " + winnerName, extraBigLabelStyleWhite);
        winnerLabel.setPosition(WIDTH/2 - 75, HEIGHT - 50);
        stage.addActor(winnerLabel);

        // Player Qs and As Text Label
        player1QsANDAsLabel = new Label(player1.getName() + "'s Questions & Correct Answers: ", bigLabelStyleWhite);
        player1QsANDAsLabel.setPosition(5, HEIGHT/2 + 200);
        stage.addActor(player1QsANDAsLabel);
        player1QsANDAsLabel.setVisible(false);
        
        player2QsANDAsLabel = new Label(player2.getName() + "'s Questions & Correct Answers: ", bigLabelStyleWhite);
        player2QsANDAsLabel.setPosition(WIDTH - 450, HEIGHT/2 + 200);
        stage.addActor(player2QsANDAsLabel);
        player2QsANDAsLabel.setVisible(false);
        
        // Score Labels
        //player1ScoreLabel = new Label("Player 1 Score: " + player1.getScore(), bigLabelStyle);
        player1ScoreLabel = new Label(player1.getName() + "'s  Score: " + player1.getScore(), bigLabelStyleWhite);
        player1ScoreLabel.setPosition(WIDTH/2 - 75, HEIGHT/2 + 75);
        player2ScoreLabel = new Label(player2.getName() + "'s Score: " + player2.getScore(), bigLabelStyleWhite);
        player2ScoreLabel.setPosition(WIDTH/2 - 75, HEIGHT/2 + 125);
        stage.addActor(player1ScoreLabel);
        stage.addActor(player2ScoreLabel);
        currentPlayerScoreLabel = player1ScoreLabel;
                
        // Replay Image Button
        replayButtonTexture = new Texture(Gdx.files.internal("replayButton.png"));
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(replayButtonTexture)); // Set button image
        replayButton = new ImageButton(buttonStyle);
        replayButton.setPosition(WIDTH/2 - replayButton.getWidth()/2, HEIGHT/2 - 150);
        stage.addActor(replayButton);
        
        // Player 1 Question Labels
        
        displayQsandAs(player1Questions, labelStyleWhite, labelStyleGreen);
        displayQsandAs(player2Questions, labelStyleWhite, labelStyleGreen);
        
        
    }
    
    public void displayQsandAs(List<Question> playerQuestions, Label.LabelStyle white, Label.LabelStyle green) {
       
        int x = 0;
        
        if (playerQuestions == player1Questions) {
            x = 25;
        } else {
            x = WIDTH/2 + 300;
        }
        
        
        for (int i = 0; i < playerQuestions.size(); i++) {
            Question currentQ = playerQuestions.get(i);
            // if current question is too long do something similar as game screen
            String currentQuestionString = currentQ.getQuestion();
            if (currentQuestionString.length() > 60) {
                currentQuestionString = adjustLabelWidth(currentQ.getQuestion(),60);
            }
            String currentA = currentQ.getCorrectAnswer();
            Label currentQLabel = new Label(StringEscapeUtils.unescapeHtml4(currentQuestionString), white);
            Label currentALabel = new Label(StringEscapeUtils.unescapeHtml4(currentA), green);
           
            stage.addActor(currentQLabel);
            if (currentQuestionString.length() > 60) {
               currentQLabel.setPosition(x, (HEIGHT/2 + 220) - i * 60);
               currentALabel.setPosition(x, ((HEIGHT/2 + 200) - i * 60));
               stage.addActor(currentALabel);
            } else if (currentQuestionString.length() > 100) {
               currentQLabel.setPosition(x, (HEIGHT/2 + 0) - i * 60);
               currentALabel.setPosition(x, ((HEIGHT/2 - 20) - i * 60));
               stage.addActor(currentALabel);
            } else {
                currentQLabel.setPosition(x, (HEIGHT/2 + 230) - i * 60); // 60 was good 
                stage.addActor(currentALabel);
                currentALabel.setPosition(x, ((HEIGHT/2 + 210) - i * 60)); // 40 was good
            }
        }
    }
    
    public String adjustLabelWidth(String text, int lengthCap) {
        
        StringBuilder stringbuilder = new StringBuilder();
        
        while (text.length() > lengthCap) {
            String currentSubString = text.substring(0, lengthCap + 1); // substring == "Hi my name "

            int lastIDXofSpace = currentSubString.lastIndexOf(" ");

            currentSubString = currentSubString.substring(0, lastIDXofSpace) + "\n";

            stringbuilder.append(currentSubString);

            text = text.substring(lastIDXofSpace + 1);

            if (text.length() < lengthCap ) {
                stringbuilder.append(text);
            }
        }
        
        text = "\n" +  "\n" + stringbuilder.toString();
           
        return text;
    }
    
    public ImageButton getReplayButton() {
        return this.replayButton;
    }
    
    public Music getEndGameCheer() {
        return this.endGameCheer;
    }
}
