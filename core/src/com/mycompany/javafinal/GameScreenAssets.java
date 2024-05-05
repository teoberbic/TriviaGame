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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;
import com.mycompany.normaljavaclasses.Player;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author teoberbic
 */
public class GameScreenAssets {
    // Fields
    private static GameScreenAssets gsa = null;
    public static Stage stage;
    
    public static Image player1NameTagImage;
    public static Image player2NameTagImage;
    public static Image player1CategoryTagImage;
    public static Image player2CategoryTagImage;
    public static Label player1NameLabel;
    public static Label player2NameLabel;
    public static Label player1CategoryLabel;
    public static Label player2CategoryLabel;
    public static float timer = 15;
    public static Image questionLabelImage;
    public static Label questionLabel;
    public static List<Label> answerLabels = new ArrayList<Label>();
    public static ImageButton imageButton;
    public static ImageButton imageButton2;
    public static ImageButton imageButton3;
    public static ImageButton imageButton4;
    public static Image player1ScoreTagImage;
    public static Image player2ScoreTagImage;
    public static Label player1ScoreLabel;
    public static Label player2ScoreLabel;
    public static Player player1;
    public static Player player2;
    public static Label currentPlayerScoreLabel;
    public static ImageButton[] imageButtons = {imageButton, imageButton2, imageButton3, imageButton4};
    public static Image scoreIcon;
    public static Image scoreIcon2;
    public static Label errorEnterNameLabel;
    public static Sound correctAnswerSFX;
    
    public static Label errorNameTooLongLabel;
    public static Sound incorrectAnswerSFX;
    public static Sound wheelSpinSFX;
    public static Sound enterSFX;
    public static Sound enterErrorSFX;
    public static Sound categoryAppearSFX;
    public static Sound clockTickingSFX;
    public static Label nameLabelNote;
    
    
    public static Skin skinUI;
    public static Skin skinUI2;
    public static Label timerLabel;
    public static Image correctAnswerImage;
    public static Image player1IconImage;
    public static Image categoryIcon1;
    public static Image player2IconImage;
    public static Image categoryIcon2;
    public static Image incorrectAnswerImage;
    public static Label player1GainedPointLabel;
    public static Label player2GainedPointLabel;
    public static Music gameBackgroundMusic;
    public static Label player1TurnLabel;
    public static Label player2TurnLabel;
    public static Label playerCurrentIndexLabel;
    // Constructor
    private GameScreenAssets() {
        
    }
    
    // Only one instance
    public static GameScreenAssets getInstance(Player player1, Player player2, Label player1NameLabel, Label player2NameLabel, Label player1CategoryLabel, Label player2CategoryLabel){
        if (gsa == null) {
            gsa = new GameScreenAssets();
        }
        GameScreenAssets.player1 = player1;
        GameScreenAssets.player2 = player2;
        GameScreenAssets.player1NameLabel = player1NameLabel;
        GameScreenAssets.player2NameLabel = player2NameLabel;
        GameScreenAssets.player1CategoryLabel = player1CategoryLabel;
        GameScreenAssets.player2CategoryLabel = player2CategoryLabel;
        return gsa;
    }
    
    public void loadAssets() {
        // Initialization happens here
        stage = new Stage(new FitViewport(WIDTH, HEIGHT)); // where the view of the stage is
        
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
        player1NameLabel.setPosition(120, HEIGHT -50);
        player2NameLabel.setPosition(WIDTH - 280, HEIGHT -50);
        stage.addActor(player1NameLabel); 
        stage.addActor(player2NameLabel);
        
        // Players Category Labels
        player1CategoryLabel.setPosition(120, HEIGHT -145);
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
        answerLabels.get(0).setPosition(imageButton.getX() + 10, imageButton.getY() + 55);
        answerLabels.get(1).setPosition(imageButton2.getX() + 10, imageButton2.getY() + 55);
        answerLabels.get(2).setPosition(imageButton3.getX() + 10, imageButton3.getY() + 55);
        answerLabels.get(3).setPosition(imageButton4.getX() + 10, imageButton4.getY() + 55);
        
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
        
        categoryIcon1 = new Image(new Texture(Gdx.files.internal("categoryIcon.png")));
        categoryIcon1.setPosition(15,HEIGHT - player1IconImage.getHeight() - 100);
        stage.addActor(categoryIcon1);

        player2IconImage = new Image(new Texture(Gdx.files.internal("player2Icon.png")));
        player2IconImage.setPosition(WIDTH - player2IconImage.getWidth() - player2CategoryTagImage.getWidth() +60 ,HEIGHT - player1IconImage.getHeight() - 5);
        stage.addActor(player2IconImage);

        categoryIcon2 = new Image(new Texture(Gdx.files.internal("categoryIcon.png")));
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
        
        // Player Index Labels
        playerCurrentIndexLabel = new Label("Question: ", bigLabelStyleWhite);
        playerCurrentIndexLabel.setPosition(WIDTH/2 - playerCurrentIndexLabel.getWidth()/2 -15, HEIGHT/2 + 75);
        stage.addActor(playerCurrentIndexLabel);
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
}
