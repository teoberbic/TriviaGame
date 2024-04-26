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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;
import com.mycompany.normaljavaclasses.Category;

public class CategoryPickerScreen implements Screen{
    private final Drop game;
    public static final String TITLE = "Teo's Trivia Competition";

    // User Input & UI Text 
    private TextField nameFieldUI;
    private TextField nameFieldUI2;

    // Player 1 Fields
    private boolean player1EnteredName;
    private boolean player1SpunWheel = false;
    private boolean player1GotCategoryBool = false;

    // Player 2 Fields
    //private boolean player2TextFieldAppeared = false;
    private boolean player2EnteredName = false;
    private boolean player2SpunWheel = false;
    private boolean player2GotCategoryBool = false;

    // Wheel
    private Image wheel;
    private float finalRotation;
    
    // Timer Vars 
    private float player2WheelTime = 3;
    private float player1WheelTime = 3;
    private boolean player1WheelTimeHappendBefore = false;
    private boolean player2WheelTimeStarted = false;
    private boolean player1WheelTimeStarted = false;
    private boolean countDownTimerCanStart = false;
    private Sound clockTickingSFX;
    boolean doOnlyOnce = false;
    
    private Stage stage = new Stage(new FitViewport(WIDTH, HEIGHT)); // where the view of the stage is;;
    private Assets assets;
    
    
    private CategoryPickerScreenHelper categoryPickerScreenHelper;

    // Constructor
    public CategoryPickerScreen (Drop game) {
        this.game = game;
    }

    @Override
    public void show() { //initializes everything when this screen is shown, used as the consructor of LibGDX
        
        // All assets are in a new class for encapsualtion then call the loadCategoryPickerScreen method so they get loaded
        assets = new Assets(stage);
        assets.loadCategoryPickerScreen();
        categoryPickerScreenHelper = new CategoryPickerScreenHelper(assets, this);

        // Still need some assets present in this window because I need a references when changes will be made to them
        wheel = assets.getWheel();
        nameFieldUI = assets.getNameFieldUI();
        nameFieldUI2 = assets.getNameFieldUI2();
        clockTickingSFX = assets.getClockTickingSFX();
        
        // Set stage as the Input Processor
        Gdx.input.setInputProcessor(stage);

        // Enter & Space Key Pressed
        stage.addListener(new InputListener() {
            
            @Override
            public boolean keyDown(InputEvent e, int key) {
                
//                if(key == Keys.R) {
//                    categoryPickerScreenHelper.choosePlayer1();
//                }
                if(key == Keys.ENTER & !player1EnteredName) {
                    
                    if(nameFieldUI.getText().length() == 0) {
                        //show error message to enter something
                       categoryPickerScreenHelper.enterAndNoPlayerName();
                
                        return true;
                        
                    } else if(nameFieldUI.getText().length() > 6) {
                        categoryPickerScreenHelper.enterAndPlayerNameLong();
                        return true;
    
                    } else {
                        categoryPickerScreenHelper.enterAndPlayer1EnteredName();
                        player1EnteredName = true;
                        return true;
                    }
                }
                        
                if(key == Keys.ENTER & player1EnteredName & !player2EnteredName & player1WheelTimeHappendBefore) {// PLAYER 2 ENTERED ENTER
                    if(nameFieldUI2.getText() == "") {
                        categoryPickerScreenHelper.enterAndNoPlayerName();
                        return true;
                    
                    } else if(nameFieldUI2.getText().length() > 7) {
                        categoryPickerScreenHelper.enterAndPlayerNameLong();
                        return true;
                        
                    } else {
                        player2EnteredName = categoryPickerScreenHelper.enterAndPlayer2EnteredName();
                        //player2EnteredName = true;
                        return true;
                        
                    }
                }
            
                //Enter Key Above//
                ///////////////////
                //Space Key Below//

                if(key == Keys.SPACE & player1EnteredName & !player1GotCategoryBool & !player2GotCategoryBool & !player1SpunWheel) { // SPACE PRESSED AND PLAYER 1 ENTERED NAME
                      categoryPickerScreenHelper.spacePressedAndPlayer1EnteredName();

                      spinWheel();
                      player1SpunWheel = true;
                      return true; // means the event has been handled by this listener and doesnt have to keep going down the chain of listeners
                }

                else if (key == Keys.SPACE & player2EnteredName & !player2GotCategoryBool & !player2SpunWheel) { // SPACE PRESSED AND PLAYER 2 ENTERED NAME
                    categoryPickerScreenHelper.spacePressedAndPlayer2EnteredName();
                    
                    spinWheel();
                    player2SpunWheel = true;
                    return true; // means the event has been handled by this listener and doesnt have to keep going down the chain of listeners
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

    private void spinWheel() {
        // Spin the wheel with a random duration and rotation
        
        float duration = MathUtils.random(2.0f, 6.0f); // To adjust the duration
        float rotation = 360 * MathUtils.random(5.5f, 12.5f); // To adjust the rotation range 

        wheel.addAction(Actions.sequence(Actions.rotateBy(rotation, duration),Actions.run(new Runnable() {
            @Override
            public void run() {

                finalRotation = wheel.getRotation();
                System.out.println("FINAL ROTATION: " + finalRotation);

                // Handle category selection after the wheel stops spinning
                Category selectedCategory = categoryPickerScreenHelper.determineSelectedCategory(finalRotation);

                if (player1SpunWheel & !player1GotCategoryBool) { // PLAYER 1 DOES NOT HAVE CATEGORY YET SO THIS SPIN IS TO BE ASSIGNED FOR PLAYER 1
                    
                    categoryPickerScreenHelper.player1SpunWheel(selectedCategory);
                    player1SpunWheel = true;
                    player1GotCategoryBool = true;
                    // PLAYER 1 GOT CATEGORY ASSIGNED SO MAKE PLAYER 2S TEXT BOX APPEAR
                    //player2TextFieldAppeared = true;

                    //waits 3 seconds and then set it to false
                    player1WheelTimeStarted = true;

                } else if (player2SpunWheel & !player2GotCategoryBool){ // IF PLAYER 1 ALREADY HAS CATEGORY THEN THIS SPIN IS TO BE ASSIGNED TO PLAYER 2
                    categoryPickerScreenHelper.player2SpunWheel(selectedCategory);

                    player2GotCategoryBool = true;

                    // wait a second
                    player2WheelTimeStarted = true;
                }
            }
        })));
    }   

    public void player1WheelTime(float dt) {
        player1WheelTime = categoryPickerScreenHelper.player1WheelTime(dt);
        if(player1WheelTime < 0) {
            player1WheelTimeHappendBefore = true;
        }
    }
    
    public void player2WheelTime(float dt) {
         player2WheelTime = categoryPickerScreenHelper.player2WheelTime(dt);
            if(player2WheelTime < 0 & !doOnlyOnce) {
                countDownTimerCanStart = true;
                doOnlyOnce = true;
            }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose () {
        stage.dispose();
        clockTickingSFX.dispose();
    }

    private void update() {
        ScreenUtils.clear(0, 0, 0, 0);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

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
}
