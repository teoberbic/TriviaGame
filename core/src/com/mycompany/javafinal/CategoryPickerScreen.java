package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;
import com.mycompany.normaljavaclasses.APIRequestHandler;
import com.mycompany.normaljavaclasses.Category;
import com.mycompany.normaljavaclasses.Player;
import com.mycompany.normaljavaclasses.Question;
import java.util.List;
import javax.swing.Timer;


public class CategoryPickerScreen implements Screen{
    private final Drop game;
    public Player player1;
    public Player player2;
    public static final String TITLE = "Teo's Trivia Competition";
    private OrthographicCamera camera;

    // User Input & UI Text 
    private Label player1NameLabel;
    private Label player1CategoryLabel;
    private Label nameLabelUI;
    private Table tableUI;
    private Skin skinUI;
    private TextField nameFieldUI;
    private Label player2NameLabel;
    private Label player2CategoryLabel;
    private Label nameLabelUI2;
    private Table tableUI2;
    private TextField nameFieldUI2;


    // Player 1 Fields
    private boolean player1TextFieldAppeared;
    private boolean player1EnteredName = false;
    private boolean player1SpunWheel = false;
    private boolean player1GotCategoryBool = false;

    // Player 2 Fields
    private boolean player2TextFieldAppeared = false;
    private boolean player2EnteredName = false;
    private boolean player2SpunWheel = false;
    private boolean player2GotCategoryBool = false;

    // Images
    private Image arrowImage;
    private Image player1NameTagImage;
    private Image player2NameTagImage;
    private Image player1CategoryTagImage;
    private Image player2CategoryTagImage;
    private Image player1IconImage;
    private Image player2IconImage;

    // Wheel
    private Stage stage = new Stage(new FitViewport(WIDTH, HEIGHT)); // where the view of the stage is;;
    private Image wheel;
    private float finalRotation;
    private boolean wheelSpun = false;

    // Timer 
    private Timer timer;
    private Label timerLabel;
    private float countdownTime = 10;
    private float player2WheelTime = 3;
    private float player1WheelTime = 3;
    private boolean player1WheelTimeHappendBefore = false;
    private boolean player2WheelTimeStarted = false;
    private boolean player1WheelTimeStarted = false;
    private boolean countDownTimerCanStart = false;
    private Image categoryIcon1;
    private Image categoryIcon2;
    private Image wheelLight;
    private List<Question> player1Questions;
    private List<Question> player2Questions;
    
    private Assets assets;

    // Constructor
    public CategoryPickerScreen (Drop game) {
        this.game = game;
    }

    @Override
    public void show() { //initializes everything when this screen is shown, used as the consructor of LibGDX

        // All assets are in a new class for encapsualtion then call the loadCategoryPickerScreen method so they get loaded
        assets = new Assets(stage);
        assets.loadCategoryPickerScreen();
        
        // Still need some assets present in this window because I need a reference because changes will be made to them
        wheel = assets.getWheel();
        player1NameTagImage = assets.getPlayer1NameTagImage();
        player2NameTagImage = assets.getPlayer2NameTagImage();
        player1CategoryTagImage = assets.getPlayer1CategoryTagImage();
        player2CategoryTagImage = assets.getPlayer2CategoryTagImage();
        player1IconImage = assets.getPlayer1IconImage();
        categoryIcon1 = assets.getCategoryIcon1();
        player2IconImage = assets.getPlayer2IconImage();
        categoryIcon2 = assets.getCategoryIcon2();
        nameLabelUI = assets.getNameLabelUI();
        nameLabelUI2 = assets.getNameLabelUI2();
        nameFieldUI = assets.getNameFieldUI();
        nameFieldUI2 = assets.getNameFieldUI2();
        player1NameLabel = assets.getPlayer1NameLabel();
        player1CategoryLabel = assets.getPlayer1CategoryLabel();
        player2NameLabel = assets.getPlayer2NameLabel();
        player2CategoryLabel = assets.getPlayer2CategoryLabel();
        timerLabel = assets.getTimerLabel();
        
        // Set stage as the Input Processor
        Gdx.input.setInputProcessor(stage);

        // Create the Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 750);

        // Enter & Space Key pressed
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent e, int key) {
                if(key == Keys.ENTER & nameFieldUI.getText() != "" & !player1EnteredName) { // PLAYER 1 ENTERED ENTER
                    wheel.setVisible(true);
                    player1NameLabel.setText(player1NameLabel.getText() + nameFieldUI.getText()); // Update player1Name with the latest text from the TextField
                    Gdx.input.setOnscreenKeyboardVisible(false); // Hide virtual keyboard 

                    // PLAYER 1 TEXT FIELDS ARE HIDDEN
                    nameFieldUI.setVisible(false);
                    nameLabelUI.setVisible(false);


                    player1EnteredName = true;
                    
                    player1NameTagImage.setVisible(true);
                    player1NameLabel.setVisible(true); 
                    player1IconImage.setVisible(true);

                    return true; // means the event has been handled by this listener and doesnt have to keep going down the chain of listeners

                } else if(key == Keys.ENTER & nameFieldUI2.getText() != "" & player2TextFieldAppeared & !player2EnteredName) { // PLAYER 2 ENTERED ENTER
                    wheel.setVisible(true);
                    player2NameLabel.setText(player2NameLabel.getText() + nameFieldUI2.getText());
                    Gdx.input.setOnscreenKeyboardVisible(false);



                    // PLAYER 2 TEXT FIELDS ARE HIDDEN
                    nameFieldUI2.setVisible(false);
                    nameLabelUI2.setVisible(false);


                    player2EnteredName = true;
                    
                    player2NameTagImage.setVisible(true);
                    player2NameLabel.setVisible(true);
                    player2IconImage.setVisible(true);
                    return true;
                }

                //Enter Key Above//
                ///////////////////
                //Space Key Below//

                if(key == Keys.SPACE & player1EnteredName & !player1GotCategoryBool & !player2GotCategoryBool & !player1SpunWheel) { // SPACE PRESSED AND PLAYER 1 ENTERED NAME
                    player1SpunWheel = true;
                    spinWheel();
                    return true; // means the event has been handled by this listener and doesnt have to keep going down the chain of listeners
                }

                else if (key == Keys.SPACE & player2EnteredName & !player2GotCategoryBool & !player2SpunWheel) { // SPACE PRESSED AND PLAYER 2 ENTERED NAME
                    player2SpunWheel = true;
                    spinWheel();
                    return true; // means the event has been handled by this listener and doesnt have to keep going down the chain of listeners
                }
                return false;
            }
        });
    }

    @Override
    public void render (float delta) {
        // clear the screen with black
        ScreenUtils.clear(0, 0, 0, 0);

        // tell the camera to update its matrices.
        camera.update();

        // Wheel
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        if (player1WheelTimeStarted && !player1WheelTimeHappendBefore) {
            player1WheelTime -= Gdx.graphics.getDeltaTime();

            if(player1WheelTime < 0) {
                wheel.setVisible(false);
                player1WheelTimeHappendBefore = true;
                nameLabelUI2.setVisible(true);
                nameFieldUI2.setVisible(true);
            }
        }
        // Timer to show second player their category on the wheel before hiding wheel
        if (player2WheelTimeStarted) {
            player2WheelTime -= Gdx.graphics.getDeltaTime();
            if(player2WheelTime < 0) {

                wheel.setVisible(false);
                countDownTimerCanStart = true;
            }
        }

        // Timer to show until game start
        if (countDownTimerCanStart) {
            timerLabel.setVisible(true);
            countdownTime -= Gdx.graphics.getDeltaTime();
            timerLabel.setText("Countdown Until Game Start: " + countdownTime);
            if(countdownTime <= 0) {
                // Put it here for API request purposes
                player2Questions = APIRequestHandler.makeRequest("10", player2.getCategory().getCategoryNumber(), "easy");
                dispose();
                game.setScreen(new GameScreen(game, player1NameTagImage, player2NameTagImage, player1CategoryTagImage, player2CategoryTagImage, player1NameLabel, player2NameLabel, player1CategoryLabel, player2CategoryLabel, player1, player2, player1Questions, player2Questions));
            }
        }
    }

    private void spinWheel() {
        // Spin the wheel with a random duration and rotation
        float duration = MathUtils.random(2.0f, 6.0f); // Adjust the duration
        float rotation = 360 * MathUtils.random(5.5f, 12.5f); // Adjust the rotation range 

        wheel.addAction(Actions.sequence(Actions.rotateBy(rotation, duration),Actions.run(new Runnable() {
            @Override
            public void run() {

                finalRotation = wheel.getRotation();
                System.out.println(finalRotation);

                // Handle category selection after the wheel stops spinning
                Category selectedCategory = determineSelectedCategory();

                if (player1SpunWheel & player1GotCategoryBool == false) { // PLAYER 1 DOES NOT HAVE CATEGORY YET SO THIS SPIN IS TO BE ASSIGNED FOR PLAYER 1
                    player1 = new Player(nameFieldUI.getText(), 0, selectedCategory);
                    player1Questions = APIRequestHandler.makeRequest("10", player1.getCategory().getCategoryNumber(), "easy");

                    System.out.println("Player 1 (" + player1.getName() + " )Selected Category: " + player1.getCategory());
                    player1NameLabel.setVisible(true);
                    player1CategoryLabel.setText("Player 1 Category: \n" + player1.getCategory());
                    player1CategoryTagImage.setVisible(true);
                    player1CategoryLabel.setVisible(true);
                    categoryIcon1.setVisible(true);
                    player1SpunWheel = true;
                    player1GotCategoryBool = true;


                    //waits 3 seconds and then set it to false
                    player1WheelTimeStarted = true;
                    Gdx.input.setOnscreenKeyboardVisible(true);
                    
                    // PLAYER 1 GOT CATEGORY ASSIGNED SO MAKE PLAYER 2S TEXT BOX APPEAR
                    player2TextFieldAppeared = true;

                } else if (player2SpunWheel & player2GotCategoryBool == false){ // IF PLAYER 1 ALREADY HAS CATEGORY THEN THIS SPIN IS TO BE ASSIGNED TO PLAYER 2
                    player2 = new Player(nameFieldUI2.getText(), 0, selectedCategory);
                    player2GotCategoryBool = true;
                    player2CategoryLabel.setText("Player 2 Category: \n" + player2.getCategory());
                    System.out.println("Player 2 Selected Category: " + player2.getCategory());

                    player2CategoryTagImage.setVisible(true);
                    player2CategoryLabel.setVisible(true);
                    categoryIcon2.setVisible(true);

                    // wait a second
                    player2WheelTimeStarted = true;
                }
            }
        })
        ));
    }   

    private Category determineSelectedCategory() {
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
    }
}
