/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

// Imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mycompany.normaljavaclasses.APIRequestHandler;
import com.mycompany.normaljavaclasses.Category;
import com.mycompany.normaljavaclasses.Player;
import com.mycompany.normaljavaclasses.Question;
import java.util.List;

/**
 *
 * @author teoberbic
 */
public class CategoryPickerScreenHelper {
    private final Assets assets;
    private final Sound enterErrorSFX;
    private final Label errorEnterNameLabel;
    private float player1WheelTime = 3;
    
    private final Image wheel;
    private TextField nameFieldUI2;
    private Label nameLabelUI2;
    private Label nameLabelNote;
    private float player2WheelTime = 3;
    private final Sound clockTickingSFX;
    boolean doOnlyOnce = false;
    
    // User Input
    private final Sound enterSFX;
    private Label nameLabelUI;
    private final Image player1NameTagImage;
    private final Image player1IconImage;
    private Label player2NameLabel;
    private final Image player2NameTagImage;
    private final Image player2IconImage;
    
    //Spin Wheel
    private float finalRotation;
    private Sound wheelSpinSFX;
    private long wheelSpinSFXId;
    private Sound categoryAppearSFX;
    public Player player1;
    private TextField nameFieldUI;
    private List<Question> player1Questions;
    private Label player1NameLabel;
    private Label player1CategoryLabel;
    private final Image player1CategoryTagImage;
    private final Image categoryIcon1;
    private long wheelSpinSFXId2;
    public Player player2;
    private Label player2CategoryLabel;
    private final Image player2CategoryTagImage;
    private final Image categoryIcon2;
    
    //CountdownTimer
    private Label timerLabel;
    private float countdownTime = 10;
    private List<Question> player2Questions;
    private final CategoryPickerScreen categoryPickerScreen;
    private final Label spaceLabel;

    // Constructor
    public CategoryPickerScreenHelper(Assets assets, CategoryPickerScreen categoryPickerScreen) {
        this.categoryPickerScreen = categoryPickerScreen;
        this.assets = assets;
               
        this.errorEnterNameLabel = assets.getErrorEnterNameLabel();
        this.enterErrorSFX = assets.getEnterErrorSFX();
        this.clockTickingSFX = assets.getClockTickingSFX();
        
        this.wheel = assets.getWheel();
        this.nameFieldUI2 = assets.getNameFieldUI2();
        this.nameLabelUI2 = assets.getNameLabelUI2();
        this.nameLabelNote = assets.getNameLabelNote();
        this.player1IconImage = assets.getPlayer1IconImage();
        this.player2NameLabel = assets.getPlayer2NameLabel();
        this.player2NameTagImage = assets.getPlayer2NameTagImage();
        this.player2IconImage = assets.getPlayer2IconImage();
        
        // Spin Wheel
        this.wheelSpinSFX = assets.getWheelSpinSFX();
        this.categoryAppearSFX = assets.getCategoryAppearSFX();
        this.nameFieldUI = assets.getNameFieldUI();
        this.player1NameLabel = assets.getPlayer1NameLabel();
        this.player1CategoryLabel = assets.getPlayer1CategoryLabel();
        this.player1CategoryTagImage = assets.getPlayer1CategoryTagImage();
        this.categoryIcon1 = assets.getCategoryIcon1();
        this.player2CategoryLabel = assets.getPlayer2CategoryLabel();
        this.player2CategoryTagImage = assets.getPlayer2CategoryTagImage();
        this.categoryIcon2 = assets.getCategoryIcon2();
        this.enterSFX = assets.getEnterSFX();
        this.nameLabelUI = assets.getNameLabelUI();
        this.player1NameTagImage = assets.getPlayer1NameTagImage();
        
        //CountdownTimer
        timerLabel = assets.getTimerLabel();
        this.spaceLabel = assets.getSpaceLabel();
        
    }
    
    // User Input Methods
    public void enterAndNoPlayerName() {
        enterErrorSFX.play();
        errorEnterNameLabel.setVisible(true);
        Assets.errorNameTooLongLabel.setVisible(false);
    }
    
    public void enterAndPlayerNameLong() {
        enterErrorSFX.play();
        errorEnterNameLabel.setVisible(false);
        Assets.errorNameTooLongLabel.setVisible(true);
    }
    
    public void enterAndPlayer1EnteredName() {
        enterSFX.play();
        errorEnterNameLabel.setVisible(false);
        Assets.errorNameTooLongLabel.setVisible(false);
        nameLabelNote.setVisible(false);
        // you are set to the rest of the code normally
        wheel.setVisible(true);
        spaceLabel.setVisible(true);
        player1NameLabel.setText(player1NameLabel.getText() + nameFieldUI.getText()); // Update player1Name with the latest text from the TextField
        Gdx.input.setOnscreenKeyboardVisible(false); // Hide virtual keyboard 
        
        // PLAYER 1 TEXT FIELDS ARE HIDDEN
        nameFieldUI.setVisible(false);
        nameLabelUI.setVisible(false);
        
        player1NameTagImage.setVisible(true);
        player1NameLabel.setVisible(true); 
        player1IconImage.setVisible(true);
    }
    
    public void enterAndPlayer2EnteredName() {
        errorEnterNameLabel.setVisible(false);
        Assets.errorNameTooLongLabel.setVisible(false);
        nameLabelNote.setVisible(false);
        enterSFX.play();
        wheel.setVisible(true);
        spaceLabel.setVisible(true);
        player2NameLabel.setText(player2NameLabel.getText() + nameFieldUI2.getText());
        Gdx.input.setOnscreenKeyboardVisible(false);



        // PLAYER 2 TEXT FIELDS ARE HIDDEN
        nameFieldUI2.setVisible(false);
        nameLabelUI2.setVisible(false);

        player2NameTagImage.setVisible(true);
        player2NameLabel.setVisible(true);
        player2IconImage.setVisible(true);
    }
    
    public void spacePressedAndPlayer1EnteredName() {
        wheelSpinSFXId = wheelSpinSFX.play(1.0f);
        wheelSpinSFX.setLooping(wheelSpinSFXId, true);
        spaceLabel.setVisible(false);
    }
    
    public void spacePressedAndPlayer2EnteredName() {
        wheelSpinSFXId2 = wheelSpinSFX.play(1.0f);
        wheelSpinSFX.setLooping(wheelSpinSFXId2, true);
        spaceLabel.setVisible(false);
    }
    
    // Wheel Time Methods
    public float player1WheelTime(float dt) {
        
        player1WheelTime -= dt;

        if(player1WheelTime < 0) {
            wheel.setVisible(false);
            nameLabelUI2.setVisible(true);
            nameFieldUI2.setVisible(true);
            nameLabelNote.setVisible(true);
        }
        
        return player1WheelTime;
    }
    
    public float player2WheelTime(float dt) {
        player2WheelTime -= dt;
        if(player2WheelTime < 0 & !doOnlyOnce) {
            clockTickingSFX.play();
            wheel.setVisible(false);
            doOnlyOnce = true;
        }
        
        return player2WheelTime;
            
    }
    
    // WheelSpun Methods
    public void player1SpunWheel(Category selectedCategory){
        wheelSpinSFX.stop(wheelSpinSFXId);
        categoryAppearSFX.play();
        player1 = new Player(nameFieldUI.getText(), 0, selectedCategory);

        //run();
        player1Questions = APIRequestHandler.makeRequest("10", player1.getCategory().getCategoryNumber(), "easy");
        
        for (Question q: player1Questions) {
            System.out.print(q.getQuestion());
        }

        System.out.println("Player 1 (" + player1.getName() + " )Selected Category: " + player1.getCategory());
        player1NameLabel.setVisible(true);
        player1CategoryLabel.setText("Player 1 Category: \n" + player1.getCategory());
        player1CategoryTagImage.setVisible(true);
        player1CategoryLabel.setVisible(true);
        categoryIcon1.setVisible(true);

        //waits 3 seconds and then set it to false
        Gdx.input.setOnscreenKeyboardVisible(true);

    }
    
    public void player2SpunWheel(Category selectedCategory) {
        wheelSpinSFX.stop(wheelSpinSFXId2);
        categoryAppearSFX.play();
        player2 = new Player(nameFieldUI2.getText(), 0, selectedCategory);
        player2CategoryLabel.setText("Player 2 Category: \n" + player2.getCategory());
        System.out.println("Player 2 Selected Category: " + player2.getCategory());

        player2CategoryTagImage.setVisible(true);
        player2CategoryLabel.setVisible(true);
        categoryIcon2.setVisible(true);
    }
    
    public void startCountdownTimer(float dt, Drop game) {
        timerLabel.setVisible(true);
            countdownTime -= dt;
            timerLabel.setText("Countdown Until Game Start: " + (int) countdownTime);
            if(countdownTime <= 0) {
                // Put it here for API request purposes
                player2Questions = APIRequestHandler.makeRequest("10", player2.getCategory().getCategoryNumber(), "easy");
                for (Question q: player2Questions) {
                    System.out.print(q.getQuestion());
                }
                categoryPickerScreen.dispose();
                game.setScreen(new GameScreen(game, player1NameTagImage, player2NameTagImage, player1CategoryTagImage, player2CategoryTagImage, player1NameLabel, player2NameLabel, player1CategoryLabel, player2CategoryLabel, player1, player2, player1Questions, player2Questions));
            }
    }
    

    public Category determineSelectedCategory(float finalRotation) {
        // Logic to determine the selected category based on the wheel's final rotation
        // Divide the wheel into sectors, and each sector corresponds to a category
        if (finalRotation >= 0 && finalRotation <= 29.99999) {
            return Category.GENERAL_KNOWLEDGE;
        } else if (finalRotation >= 30 && finalRotation <= 59.99999) {
            return Category.FILM;
        } else if (finalRotation >= 60 && finalRotation <= 89.99999) {
            return Category.MUSIC;
        } else if (finalRotation >= 90 && finalRotation <= 119.99999) {
            return Category.VIDEO_GAMES;
        } else if (finalRotation >= 120 && finalRotation <= 149.99999) {
            return Category.SCIENCE;
        } else if (finalRotation >= 150 && finalRotation <= 179.99999) {
            return Category.COMPUTERS;
        } else if (finalRotation >= 180 && finalRotation <= 209.99999) {
            return Category.MYTHOLOGY;
        } else if (finalRotation >= 210 && finalRotation <= 239.99999) {
            return Category.SPORTS;
        } else if (finalRotation >= 240 && finalRotation <= 269.99999) {
            return Category.GEOGRAPHY;
        } else if (finalRotation >= 270 && finalRotation <= 299.99999) {
            return Category.HISTORY;
        } else if (finalRotation >= 300 && finalRotation <= 329.99999) {
            return Category.CELEBERTIES;
        } else if (finalRotation >= 330 && finalRotation <= 359.99999) {
            return Category.VEHICLES;
        }
        return null;
    }
}
