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
import com.mycompany.normaljavaclasses.Question;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.text.StringEscapeUtils;

/**
 *
 * @author teoberbic
 */
public class OtherAssets {
    Stage stage;
    // MainMenuScreen
    private Music menuBackgroundMusic;
    private Texture backgroundImageTexture;
    private Image backgroundImage;
    private Label welcomeTextLabel;
    private Label welcomeTextLabel2;
    private Texture startButtonTexture;
    private Label.LabelStyle bigLabelStyle;
    // CategoryPickerScreen
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
    // GameScreen
    private float timer = 15;
    private Image questionLabelImage;
    private Label questionLabel;
    List<Label> answerLabels = new ArrayList<Label>();
    private ImageButton imageButton;
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
    
    public static Label errorNameTooLongLabel;
    
    //EndGameScreen
    private Image winnerBanner;
    private ImageButton replayButton;
    private Texture replayButtonTexture;
    private List<Question> player1Questions;
    private List<Question> player2Questions;
    private Skin skinUI2;
    private Label winnerLabel;
    private Label player1QsANDAsLabel;
    private Label player2QsANDAsLabel;
    private Music endGameCheer;
    private Skin skinUI3;
    
    // Constructor for MainMenuScreen & CategoryPickerScreen
    public OtherAssets(Stage stage) {
        this.stage = stage;
    }
    
    // Constructor for GameScreen
    public OtherAssets(Stage stage, Player player1, Player player2, Image player1NameTagImage, Image player2NameTagImage, Image player1CategoryTagImage, Image player2CategoryTagImage, Label player1NameLabel, Label player2NameLabel, Label player1CategoryLabel, Label player2CategoryLabel) {
        //this.stage = stage;
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
    public OtherAssets(Stage stage, Player player1, Player player2, List<Question> player1Questions, List<Question> player2Questions) {
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
        player2CategoryLabel.setPosition(WIDTH - player1CategoryTagImage.getWidth() + 95, HEIGHT -75);
        stage.addActor(player1CategoryLabel);
        stage.addActor(player2CategoryLabel);
        
        // Players Category Icons
        categoryIcon1 = new Image(new Texture(Gdx.files.internal("categoryIcon.png")));
        categoryIcon1.setPosition(15,HEIGHT - player2CategoryTagImage.getHeight() + 20);
        stage.addActor(categoryIcon1);
        
        categoryIcon2 = new Image(new Texture(Gdx.files.internal("categoryIcon.png")));
        categoryIcon2.setPosition(WIDTH - player2CategoryTagImage.getWidth(),HEIGHT - player1CategoryTagImage.getHeight() + 20);
        stage.addActor(categoryIcon2);
        
        // Winner Banner
        winnerBanner = new Image(new Texture(Gdx.files.internal("winnerBanner.png")));
        winnerBanner.setPosition(WIDTH/2 - winnerBanner.getWidth()/2, HEIGHT - winnerBanner.getHeight());
        stage.addActor(winnerBanner);
        
        // Winning Music
        endGameCheer = Gdx.audio.newMusic(Gdx.files.internal("endGameCheer.mp3"));
        endGameCheer.setLooping(true);
        endGameCheer.setVolume(0.5f);
        
        // Winner Label
        int height = 50;
        String winnerName = "";
        if (player1.getScore() > player2.getScore()) {
            winnerName = player1.getName();
        } else if (player1.getScore() < player2.getScore()) {
            winnerName = player2.getName();
        } else {
            winnerName = "TIE\nPLAY AGAIN";
            height = 90;
        }
        
        winnerLabel = new Label("WINNER: " + winnerName, extraBigLabelStyleWhite);
        winnerLabel.setPosition(WIDTH/2 - winnerLabel.getWidth()/2, HEIGHT - height);
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
        player1ScoreLabel = new Label(player1.getName().trim() + "'s Score: " + player1.getScore(), bigLabelStyleWhite);
        player1ScoreLabel.setPosition(WIDTH/2 - player1ScoreLabel.getWidth()/2, HEIGHT/2 + 75);
        player2ScoreLabel = new Label(player2.getName().trim() + "'s Score: " + player2.getScore(), bigLabelStyleWhite);
        player2ScoreLabel.setPosition(WIDTH/2 - player2ScoreLabel.getWidth()/2, HEIGHT/2 + 125);
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
            Label currentALabel = new Label("Correct Answer: " + StringEscapeUtils.unescapeHtml4(currentA), green);
           
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
