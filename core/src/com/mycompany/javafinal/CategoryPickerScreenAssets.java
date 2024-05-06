/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;

/**
 *
 * @author teoberbic
 */
// Singleton
public class CategoryPickerScreenAssets {
    
    // Declare Vars
    public static Image arrowImage;
    public static Image wheel;
    public static Image player1NameTagImage;
    public static Image player2NameTagImage;
    public static Image player1CategoryTagImage;
    public static Image player2CategoryTagImage;
    public static Image player1IconImage;
    public static Image categoryIcon1;
    public static Image player2IconImage;
    public static Image categoryIcon2;
    public static Table tableUI;
    public static Skin skinUI;
    public static Label nameLabelUI;
    public static TextField nameFieldUI;
    public static Label player1NameLabel;
    public static Label player1CategoryLabel;
    public static Table tableUI2;
    public static Label nameLabelUI2;
    public static TextField nameFieldUI2;
    public static Label player2NameLabel;
    public static Label player2CategoryLabel;
    public static float countdownTime = 10;
    public static Label timerLabel;
    public static Stage stage;
    public static CategoryPickerScreenAssets cpsa = null;
    public static Label nameLabelNote;
    public static Label errorEnterNameLabel;
    public static Sound wheelSpinSFX;
    public static Sound enterSFX;
    public static Sound enterErrorSFX;
    public static Sound categoryAppearSFX;
    public static Sound clockTickingSFX;
    public static Label spaceLabel;
    public static Label choosePlayer1Label;
    public static Label revealPlayer1Label;
    public static Label errorSameNameLabel;
    public static Label errorNameTooLongLabel;
    
    private CategoryPickerScreenAssets(){}
    
    public static CategoryPickerScreenAssets getInstance() {
        if (cpsa == null) {
            cpsa = new CategoryPickerScreenAssets();
        }
        return cpsa;
    } 
    
    public void loadAssets() { // Initialize all Assets
        stage = new Stage(new FitViewport(WIDTH, HEIGHT)); // Where the view of the stage is
        
        // Wheel
        wheel = new Image(new Texture(Gdx.files.internal("wheel3.png")));
        wheel.setOrigin(wheel.getWidth()/2, wheel.getHeight()/2); // Sets the center of image as the origin so it spins from the center
        wheel.setPosition(WIDTH/2 - wheel.getWidth()/2, Gdx.graphics.getHeight()/2 - wheel.getHeight()/2); // Where the image is on the screen
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
        
        // Arrow
        arrowImage = new Image(new Texture(Gdx.files.internal("arrow.png")));
        arrowImage.setPosition(WIDTH/2 - arrowImage.getWidth()/2 - 1, HEIGHT - 50 - arrowImage.getHeight()/2);
        stage.addActor(arrowImage);
        
         // Players Icon Image's
        player1IconImage = new Image(new Texture(Gdx.files.internal("player1Icon.png")));
        player1IconImage.setPosition(15,HEIGHT - player1IconImage.getHeight() - 5);
        stage.addActor(player1IconImage);
        player1IconImage.setVisible(false);
        
        categoryIcon1 = new Image(new Texture(Gdx.files.internal("categoryIcon.png")));
        categoryIcon1.setPosition(15,HEIGHT - player1IconImage.getHeight() - 100);
        stage.addActor(categoryIcon1);
        categoryIcon1.setVisible(false);

        player2IconImage = new Image(new Texture(Gdx.files.internal("player2Icon.png")));
        player2IconImage.setPosition(WIDTH - player2IconImage.getWidth() - player2CategoryTagImage.getWidth() +60 ,HEIGHT - player1IconImage.getHeight() - 5);
        stage.addActor(player2IconImage);
        player2IconImage.setVisible(false);

        categoryIcon2 = new Image(new Texture(Gdx.files.internal("categoryIcon.png")));
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
        nameLabelUI.setVisible(false);
        nameFieldUI = new TextField("", skinUI);
        nameFieldUI.setVisible(false);
        tableUI.add(nameLabelUI);
        tableUI.add(nameFieldUI).width(150);

        player1NameLabel = new Label("Player 1 Name: ", bigLabelStyleWhite);
        player1NameLabel.setPosition(120, HEIGHT -50);
        stage.addActor(player1NameLabel);
        player1NameLabel.setVisible(false);
        player1CategoryLabel = new Label("Player 1 Category: ", bigLabelStyleWhite);
        player1CategoryLabel.setPosition(120, HEIGHT -145);
        stage.addActor(player1CategoryLabel);
        player1CategoryLabel.setVisible(false);
        
        // Input Handling Player 2
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
        nameLabelNote.setPosition(WIDTH/2 - nameLabelNote.getWidth()/2, HEIGHT/2 + 100);
        stage.addActor(nameLabelNote);
        nameLabelNote.setVisible(false);
        
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
        choosePlayer1Label = new Label("Any Player Pick Heads or Tails To Decide Who Will Go First\n\n                        Press R Key to Reveal Coin Flip", bigLabelStyleWhite);
        choosePlayer1Label.setPosition(WIDTH/2 - choosePlayer1Label.getWidth()/2, HEIGHT/2);
        stage.addActor(choosePlayer1Label);
        choosePlayer1Label.setVisible(true);
        
        revealPlayer1Label = new Label("", bigLabelStyleWhite);
        //revealPlayer1Label.setPosition(WIDTH/2 - revealPlayer1Label.getWidth()/2, HEIGHT/2 - 100);
        stage.addActor(revealPlayer1Label);
        revealPlayer1Label.setVisible(false);
        
        // Error Same Name Label
        errorSameNameLabel = new Label("SAME NAME AS PLAYER 1\n    PICK ANOTHER NAME", bigLabelStyleRed);
        errorSameNameLabel.setPosition(WIDTH/2 - errorSameNameLabel.getWidth()/2, HEIGHT/2 - 100);
        stage.addActor(errorSameNameLabel);
        errorSameNameLabel.setVisible(false);
    }
}
