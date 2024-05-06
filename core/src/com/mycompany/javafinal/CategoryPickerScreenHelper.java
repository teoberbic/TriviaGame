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
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;
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
    private final Sound enterErrorSFX;
    private final Label errorEnterNameLabel;
    private float player1WheelTime = 3;
    private final Image wheel;
    private final TextField nameFieldUI2;
    private final Label nameLabelUI2;
    private final Label nameLabelNote;
    private float player2WheelTime = 3;
    private final Sound clockTickingSFX;
    private boolean doOnlyOnce = false;
    private final Sound enterSFX;
    private final Label nameLabelUI;
    private final Image player1NameTagImage;
    private final Image player1IconImage;
    private final Label player2NameLabel;
    private final Image player2NameTagImage;
    private final Image player2IconImage;
    private float finalRotation;
    private final Sound wheelSpinSFX;
    private long wheelSpinSFXId;
    private final Sound categoryAppearSFX;
    public Player player1;
    private final TextField nameFieldUI;
    private List<Question> player1Questions;
    private final Label player1NameLabel;
    private final Label player1CategoryLabel;
    private final Image player1CategoryTagImage;
    private final Image categoryIcon1;
    private long wheelSpinSFXId2;
    public Player player2;
    private final Label player2CategoryLabel;
    private final Image player2CategoryTagImage;
    private final Image categoryIcon2;
    private final Label timerLabel;
    private float countdownTime = 10;
    private List<Question> player2Questions;
    private final CategoryPickerScreen categoryPickerScreen;
    private final Label spaceLabel;
    private boolean executed = false;
    private final Label choosePlayer1Label;
    private final Label revealPlayer1Label;
    private float coinTimer = 3;
    private final Label errorSameNameLabel;
    private final CategoryPickerScreenAssets cpsa;


    // Constructor
    private CategoryPickerScreenHelper(CategoryPickerScreenAssets cpsa, CategoryPickerScreen categoryPickerScreen) {
        this.categoryPickerScreen = categoryPickerScreen;
        this.cpsa = cpsa;
        this.errorEnterNameLabel = CategoryPickerScreenAssets.errorEnterNameLabel;
        this.enterErrorSFX = CategoryPickerScreenAssets.enterErrorSFX;
        this.clockTickingSFX = CategoryPickerScreenAssets.clockTickingSFX;
        this.wheel = CategoryPickerScreenAssets.wheel;
        this.nameFieldUI2 = CategoryPickerScreenAssets.nameFieldUI2;
        this.nameLabelUI2 = CategoryPickerScreenAssets.nameLabelUI2;
        this.nameLabelNote = CategoryPickerScreenAssets.nameLabelNote;
        this.player1IconImage = CategoryPickerScreenAssets.player1IconImage;
        this.player2NameLabel = CategoryPickerScreenAssets.player2NameLabel;
        this.player2NameTagImage = CategoryPickerScreenAssets.player2NameTagImage;
        this.player2IconImage = CategoryPickerScreenAssets.player2IconImage;
        this.wheelSpinSFX = CategoryPickerScreenAssets.wheelSpinSFX;
        this.categoryAppearSFX = CategoryPickerScreenAssets.categoryAppearSFX;
        this.nameFieldUI = CategoryPickerScreenAssets.nameFieldUI;
        this.player1NameLabel = CategoryPickerScreenAssets.player1NameLabel;
        this.player1CategoryLabel = CategoryPickerScreenAssets.player1CategoryLabel;
        this.player1CategoryTagImage = CategoryPickerScreenAssets.player1CategoryTagImage;
        this.categoryIcon1 = CategoryPickerScreenAssets.categoryIcon1;
        this.player2CategoryLabel = CategoryPickerScreenAssets.player2CategoryLabel;
        this.player2CategoryTagImage = CategoryPickerScreenAssets.player2CategoryTagImage;
        this.categoryIcon2 = CategoryPickerScreenAssets.categoryIcon2;
        this.enterSFX = CategoryPickerScreenAssets.enterSFX;
        this.nameLabelUI = CategoryPickerScreenAssets.nameLabelUI;
        this.player1NameTagImage = CategoryPickerScreenAssets.player1NameTagImage;
        this.timerLabel = CategoryPickerScreenAssets.timerLabel;
        this.spaceLabel = CategoryPickerScreenAssets.spaceLabel;
        this.choosePlayer1Label = CategoryPickerScreenAssets.choosePlayer1Label;
        this.revealPlayer1Label = CategoryPickerScreenAssets.revealPlayer1Label;
        this.errorSameNameLabel = CategoryPickerScreenAssets.errorSameNameLabel;
    }
    
    // Static Factory Method
    public static CategoryPickerScreenHelper makeCategoryPickerScreenHelper(CategoryPickerScreenAssets cpsa, CategoryPickerScreen categoryPickerScreen) {
        return new CategoryPickerScreenHelper(cpsa, categoryPickerScreen);
    }
    
    // User Input Methods
    public void enterAndNoPlayerName() {
        revealPlayer1Label.setVisible(false);
        enterErrorSFX.play();
        errorEnterNameLabel.setVisible(true);
        CategoryPickerScreenAssets.errorNameTooLongLabel.setVisible(false);
    }
    
    public void enterAndPlayerNameLong() {
        revealPlayer1Label.setVisible(false);
        enterErrorSFX.play();
        errorEnterNameLabel.setVisible(false);
        CategoryPickerScreenAssets.errorNameTooLongLabel.setVisible(true);
    }
    
    public void enterAndPlayer1EnteredName() {
        revealPlayer1Label.setVisible(false);
        enterSFX.play();
        errorEnterNameLabel.setVisible(false);
        CategoryPickerScreenAssets.errorNameTooLongLabel.setVisible(false);
        nameLabelNote.setVisible(false);
        wheel.setVisible(true);
        spaceLabel.setVisible(true);
        player1NameLabel.setText(player1NameLabel.getText() + nameFieldUI.getText()); // Update player1Name with the latest text from the TextField
        Gdx.input.setOnscreenKeyboardVisible(false); // Hide virtual keyboard 
        nameFieldUI.setVisible(false);
        nameLabelUI.setVisible(false);
        player1NameTagImage.setVisible(true);
        player1NameLabel.setVisible(true); 
        player1IconImage.setVisible(true);
        nameFieldUI.setDisabled(true);
    }
    
    public boolean enterAndPlayer2EnteredName() {
        if (nameFieldUI2.getText().equals(player1.getName())) {
            enterErrorSFX.play();
            // Add label to show to the screen to tell player 2 change name that is not player 1s
            errorSameNameLabel.setVisible(true);
            CategoryPickerScreenAssets.errorNameTooLongLabel.setVisible(false);
            errorEnterNameLabel.setVisible(false);
            return false;
        } else {
            errorSameNameLabel.setVisible(false);
            errorEnterNameLabel.setVisible(false);
            CategoryPickerScreenAssets.errorNameTooLongLabel.setVisible(false);
            nameLabelNote.setVisible(false);
            enterSFX.play();
            wheel.setVisible(true);
            spaceLabel.setVisible(true);
            player2NameLabel.setText(player2NameLabel.getText() + nameFieldUI2.getText());
            Gdx.input.setOnscreenKeyboardVisible(false);
            nameFieldUI2.setVisible(false);
            nameLabelUI2.setVisible(false);
            player2NameTagImage.setVisible(true);
            player2NameLabel.setVisible(true);
            player2IconImage.setVisible(true);
            nameFieldUI2.setDisabled(true);
            return true; // Return true indicating everything is done
        }
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
        
        // Use of Static Factory
        player1 = Player.makePlayer(nameFieldUI.getText().trim(), 0, selectedCategory); 

        Thread th = new Thread(() -> {
            player1Questions = APIRequestHandler.makeRequest("10", player1.getCategory().getCategoryNumber(), "easy");
        });
        th.start();
       
        player1NameLabel.setVisible(true);
        player1CategoryLabel.setText("Player 1 Category: \n" + player1.getCategory());
        player1CategoryTagImage.setVisible(true);
        player1CategoryLabel.setVisible(true);
        categoryIcon1.setVisible(true);
        Gdx.input.setOnscreenKeyboardVisible(true);
    }
    
    public void player2SpunWheel(Category selectedCategory) {
        wheelSpinSFX.stop(wheelSpinSFXId2);
        categoryAppearSFX.play();
        
        // Use of Static Factory
        player2 = Player.makePlayer(nameFieldUI2.getText(), 0, selectedCategory);
        player2CategoryLabel.setText("Player 2 Category: \n" + player2.getCategory());
        player2CategoryTagImage.setVisible(true);
        player2CategoryLabel.setVisible(true);
        categoryIcon2.setVisible(true);
    }
    
    public void startCountdownTimer(float dt, Drop game) {
        timerLabel.setVisible(true);
            countdownTime -= dt;
            timerLabel.setText("Countdown Until Game Start: " + (int) countdownTime);
            // Put this condition check here for API request purposes because API requires a certain amt of time between requests
            
            if(countdownTime <= 5 & !executed) {
                executed = true;
                Thread th = new Thread(() -> {
                    player2Questions = APIRequestHandler.makeRequest("10", player2.getCategory().getCategoryNumber(), "easy");
                });
                th.start();
                // API request will take less than 5 seconds to be called
            }
            
            if(countdownTime <= 0) {
                game.setScreen(new GameScreen(game, player1NameTagImage, player2NameTagImage, player1CategoryTagImage, player2CategoryTagImage, player1NameLabel, player2NameLabel, player1CategoryLabel, player2CategoryLabel, player1, player2, player1Questions, player2Questions));
                categoryPickerScreen.dispose();
            }
    }
    

    public Category determineSelectedCategory(float finalRotation) {
        // Logic to determine the selected category based on the wheel's final rotation
        // Divide the wheel into sectors, and each sector corresponds to a category
        Category[] categories = {Category.GENERAL_KNOWLEDGE, Category.FILM, Category.MUSIC, Category.VIDEO_GAMES, Category.SCIENCE, 
                                Category.COMPUTERS, Category.MYTHOLOGY,Category.SPORTS, Category.GEOGRAPHY, Category.HISTORY, 
                                Category.CELEBERTIES, Category.VEHICLES};
        
        return categories[(int)(finalRotation / (360/categories.length))];
    }

    public boolean choosePlayer1() {
        double random = Math.random();
        String coin = "";
        if (random >= .49999) {
            coin = "HEADS";
        } else {
            coin = "TAILS";
        }
        revealPlayer1Label.setText(coin);
        revealPlayer1Label.setPosition(WIDTH/2 - revealPlayer1Label.getWidth()/2, HEIGHT/2 - 100);
        revealPlayer1Label.setVisible(true);
        choosePlayer1Label.setVisible(false);
        return true;

    }

    public boolean coinTime(float deltaTime) {
        coinTimer -= deltaTime;
        if (coinTimer <= 0) {
            nameFieldUI.setVisible(true);
            nameLabelUI.setVisible(true);
            nameLabelNote.setVisible(true);
            return false;
        }
        return true;
    }
        
}
