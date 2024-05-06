package com.mycompany.javafinal;

// Imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mycompany.normaljavaclasses.Category;

public class CategoryPickerScreen implements Screen{
    
    // Refrences to Classes
    private final Drop game;
    private Stage stage;
    private CategoryPickerScreenAssets cpsa;
    private CategoryPickerScreenHelper categoryPickerScreenHelper;

    // User Input & UI Text 
    private TextField nameFieldUI;
    private TextField nameFieldUI2;

    // Bools
    private boolean player1EnteredName;
    private boolean player1SpunWheel = false;
    private boolean player1GotCategoryBool = false;
    private boolean player2EnteredName = false;
    private boolean player2SpunWheel = false;
    private boolean player2GotCategoryBool = false;
    private boolean player1WheelTimeHappendBefore = false;
    private boolean player2WheelTimeStarted = false;
    private boolean player1WheelTimeStarted = false;
    private boolean countDownTimerCanStart = false;
    private boolean doOnlyOnce = false;
    private boolean coinShowTime = false;
    private boolean coinWasFlipped = false;
    private boolean disableCoin = false;

    // Wheel
    private Image wheel;
    private float finalRotation;
    
    // Timer Vars 
    private float player2WheelTime = 3;
    private float player1WheelTime = 3;
    
    private Sound clockTickingSFX;
    private Label errorSameNameLabel;
    
    // Constructor
    public CategoryPickerScreen (Drop game) {
        this.game = game;
        cpsa = CategoryPickerScreenAssets.getInstance(); // Singleton
    }

    @Override
    public void show() { // Initializes everything when this screen is shown, used as the consructor of LibGDX
        cpsa.loadAssets(); // All assets are in a other class for encapsualtion then call load method to load
        stage = CategoryPickerScreenAssets.stage;
        
        categoryPickerScreenHelper = CategoryPickerScreenHelper.makeCategoryPickerScreenHelper(cpsa,this); // Pass in assets into helper class

        // Still need some assets present in this window because I need a references when changes will be made to them
        wheel = CategoryPickerScreenAssets.wheel;
        nameFieldUI = CategoryPickerScreenAssets.nameFieldUI;
        nameFieldUI2 = CategoryPickerScreenAssets.nameFieldUI2;
        clockTickingSFX = CategoryPickerScreenAssets.clockTickingSFX;
        errorSameNameLabel = CategoryPickerScreenAssets.errorSameNameLabel;
        
        // Set stage as the Input Processor
        Gdx.input.setInputProcessor(stage);

        // Enter & Space Key Pressed
        stage.addListener(new InputListener() {
            
            @Override
            public boolean keyDown(InputEvent e, int key) {
                
                if(key == Keys.R & !coinShowTime & !disableCoin) { // Press R Key to Reveal Coin
                    coinShowTime = categoryPickerScreenHelper.choosePlayer1();
                }
                
                if(key == Keys.ENTER & !player1EnteredName & coinWasFlipped) { // Press Enter to Establish Player 1 Name
                    // Show error message to enter something
                    if(nameFieldUI.getText().length() == 0) {
                       categoryPickerScreenHelper.enterAndNoPlayerName();
                       errorSameNameLabel.setVisible(false);
                       return true;
                        
                    // Show error message name is too long
                    } else if(nameFieldUI.getText().length() > 6) {
                        categoryPickerScreenHelper.enterAndPlayerNameLong();
                        errorSameNameLabel.setVisible(false);
                        return true;
    
                    // No error all good
                    } else {
                        categoryPickerScreenHelper.enterAndPlayer1EnteredName();
                        player1EnteredName = true;
                        return true;
                    }
                }
                        
                if(key == Keys.ENTER & player1EnteredName & !player2EnteredName & player1WheelTimeHappendBefore) { // Press Enter to Establish Player 2 Name
                    // Show error message to enter something
                    if(nameFieldUI2.getText() == "") {
                        categoryPickerScreenHelper.enterAndNoPlayerName();
                        errorSameNameLabel.setVisible(false);
                        return true;
                    
                    // Show error message name is too long
                    } else if(nameFieldUI2.getText().length() > 7) {
                        categoryPickerScreenHelper.enterAndPlayerNameLong();
                        errorSameNameLabel.setVisible(false);
                        return true;
                    
                    // No error all good
                    } else {
                        player2EnteredName = categoryPickerScreenHelper.enterAndPlayer2EnteredName();
                        return true;
                    }
                }
            
                //Enter Key Above//
                ///////////////////
                //Space Key Below//

                if(key == Keys.SPACE & player1EnteredName & !player1GotCategoryBool & !player2GotCategoryBool & !player1SpunWheel) { // Press Space for Player 1 Wheel Spin
                      categoryPickerScreenHelper.spacePressedAndPlayer1EnteredName();
                      spinWheel();
                      player1SpunWheel = true;
                      return true; // Means the event has been handled by this listener and doesnt have to keep going down the chain of listeners
                }

                else if (key == Keys.SPACE & player2EnteredName & !player2GotCategoryBool & !player2SpunWheel) { // Press Space for Player 2 Wheel Spin
                    categoryPickerScreenHelper.spacePressedAndPlayer2EnteredName();
                    spinWheel();
                    player2SpunWheel = true;
                    return true; 
                }
            return false;
            }
        });
    }
             
    @Override
    public void render (float delta) {
        update();
        stage.draw();
    }

    // Code Adapted from https://www.youtube.com/watch?v=F3-lK_-PQr0 & https://www.youtube.com/watch?v=emz_ggOHMuo
    
    private void spinWheel() {
        // Spin the wheel with a random duration and rotation
        
        float duration = MathUtils.random(2.0f, 6.0f); // To adjust the duration
        float rotation = 360 * MathUtils.random(5.5f, 12.5f); // To adjust the rotation range 

        wheel.addAction(Actions.sequence(Actions.rotateBy(rotation, duration),Actions.run(new Runnable() {
            @Override
            public void run() {

                finalRotation = wheel.getRotation();

                // Handle category selection after the wheel stops spinning
                Category selectedCategory = categoryPickerScreenHelper.determineSelectedCategory(finalRotation);

                if (player1SpunWheel & !player1GotCategoryBool) { // PLAYER 1 DOES NOT HAVE CATEGORY YET SO THIS SPIN IS TO BE ASSIGNED FOR PLAYER 1
                    categoryPickerScreenHelper.player1SpunWheel(selectedCategory);
                    player1SpunWheel = true;
                    player1GotCategoryBool = true;
                    player1WheelTimeStarted = true;

                } else if (player2SpunWheel & !player2GotCategoryBool){ // IF PLAYER 1 ALREADY HAS CATEGORY THEN THIS SPIN IS TO BE ASSIGNED TO PLAYER 2
                    categoryPickerScreenHelper.player2SpunWheel(selectedCategory);
                    player2GotCategoryBool = true;
                    player2WheelTimeStarted = true;
                }
            }
        })));
    }   
    
    // Fn to show Player 1 Wheel Time After spin
    public void player1WheelTime(float dt) {
        player1WheelTime = categoryPickerScreenHelper.player1WheelTime(dt);
        if(player1WheelTime < 0) {
            player1WheelTimeHappendBefore = true;
        }
    }
    
    // Fn to show Player 1 Wheel Time After spin
    public void player2WheelTime(float dt) {
         player2WheelTime = categoryPickerScreenHelper.player2WheelTime(dt);
            if(player2WheelTime < 0 & !doOnlyOnce) {
                countDownTimerCanStart = true;
                doOnlyOnce = true;
            }
    }
    
    private void update() {
        ScreenUtils.clear(0, 0, 0, 0);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        // Timer to show Coin Flip Result
        if(coinShowTime) {
            coinShowTime = categoryPickerScreenHelper.coinTime(Gdx.graphics.getDeltaTime());
            if(!coinShowTime) {
               coinWasFlipped = true;
               disableCoin = true;
            }
                
        }
        // Timer to show player 1 their category on the wheel before hiding wheel
        if (player1WheelTimeStarted & !player1WheelTimeHappendBefore) {
            player1WheelTime(Gdx.graphics.getDeltaTime());
        }
            
        
        // Timer to show player 2 their category on the wheel before hiding wheel
        if (player2WheelTimeStarted) {
            player2WheelTime(Gdx.graphics.getDeltaTime());
        }
        
        // Timer to show until game start
        if (countDownTimerCanStart) {
            categoryPickerScreenHelper.startCountdownTimer(Gdx.graphics.getDeltaTime(), game);
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose () {
        stage.dispose();
        clockTickingSFX.dispose();
    }

    
}
