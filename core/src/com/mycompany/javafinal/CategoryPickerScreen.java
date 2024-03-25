package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;
import com.mycompany.normaljavaclasses.APIRequestHandler;
import com.mycompany.normaljavaclasses.Category;
import com.mycompany.normaljavaclasses.Player;
import com.mycompany.normaljavaclasses.Question;
import java.util.List;
import javax.swing.Timer;


public class CategoryPickerScreen extends UserInterface{
        
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
    //private Stage stage;
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
    
    CategoryPickerScreenAssets cpsa;

    // Constructor
    public CategoryPickerScreen (Drop game) {
        this.game = game;
    }

    @Override
    public void show() { //initializes everything when this screen is shown, used as the consructor of LibGDX

        //Put all assets in a new class for readibility and call a load method so they get loaded
        
        // Wheel
        wheel = new Image(new Texture(Gdx.files.internal("wheel3.png")));
        wheel.setOrigin(wheel.getWidth() / 2, wheel.getHeight() / 2); // sets the center of image as the origin so it spins from the center
        wheel.setPosition(Gdx.graphics.getWidth() / 2 - wheel.getWidth() / 2, Gdx.graphics.getHeight() / 2 - wheel.getHeight() / 2); // where the image is on the sreen
        stage.addActor(wheel);
        wheel.setVisible(false);


        //if we put this in render method it would keep prining out angles while spinning
        //input checker for space bar to spin wheel
        Gdx.input.setInputProcessor(stage);

        
        
        // Players Name Image Tag's
        player1NameTagImage = new Image(new Texture(Gdx.files.internal("playerName.png")));
        player1NameTagImage.setPosition(0,HEIGHT - 81);
        player2NameTagImage = new Image(new Texture(Gdx.files.internal("playerName.png")));
        player2NameTagImage.setPosition(WIDTH-400,HEIGHT -81);

        // Players Category Image Tag's
        player1CategoryTagImage = new Image(new Texture(Gdx.files.internal("categoryName.png")));
        player1CategoryTagImage.setPosition(0,HEIGHT - 81 -105);
        player2CategoryTagImage = new Image(new Texture(Gdx.files.internal("categoryName.png")));
        player2CategoryTagImage.setPosition(WIDTH-400,HEIGHT - 81 - 105);
        stage.addActor(player2CategoryTagImage);
        player2CategoryTagImage.setVisible(false);
        

        // Players Icon Image's
        player1IconImage = new Image(new Texture(Gdx.files.internal("player1Icon.png")));
        player1IconImage.setPosition(15,HEIGHT - player1IconImage.getHeight() - 5);
        categoryIcon1 = new Image(new Texture(Gdx.files.internal("categoryIcon1.png")));
        categoryIcon1.setPosition(15,HEIGHT - player1IconImage.getHeight() - 100);

        player2IconImage = new Image(new Texture(Gdx.files.internal("player2Icon.png")));
        player2IconImage.setPosition(WIDTH - player2IconImage.getWidth() - player2CategoryTagImage.getWidth() +60 ,HEIGHT - player1IconImage.getHeight() - 5);
        categoryIcon2 = new Image(new Texture(Gdx.files.internal("categoryIcon2.png")));
        categoryIcon2.setPosition(WIDTH - player2IconImage.getWidth() - player2CategoryTagImage.getWidth() +60 ,HEIGHT - player1IconImage.getHeight() - 100);


        // User Input Handling 
        // Player 1
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
        player1CategoryLabel = new Label("Player 1 Category: ", bigLabelStyle);
        player1CategoryLabel.setPosition(120, HEIGHT -145);

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
        player2CategoryLabel = new Label("Player 2 Category: ", bigLabelStyle);
        player2CategoryLabel.setPosition(WIDTH-280, HEIGHT -145);

        // Timer
        timerLabel = new Label("Countdown Until Game Start: " + countdownTime, bigLabelStyle);
        timerLabel.setPosition(WIDTH/2 - 200, HEIGHT/2);
        stage.addActor(timerLabel);
        timerLabel.setVisible(false);

        // Arrow Image
        cpsa = new CategoryPickerScreenAssets(stage);
//        arrowImage = new Image(new Texture(Gdx.files.internal("arrow.png")));
//        arrowImage.setPosition(WIDTH/2 - arrowImage.getWidth()/2, HEIGHT - 50 - arrowImage.getHeight()/2);
//        stage.addActor(arrowImage);

        // Create the Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 750);

        // Enter & Space Key pressed
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent e, int key) {
                if(key == Keys.ENTER & nameFieldUI.getText() != "" & !player1EnteredName) { //CONDITION PLAYER 1 ENTERED ENTER
                    wheel.setVisible(true);
                    player1NameLabel.setText(player1NameLabel.getText() + nameFieldUI.getText()); // Update player1Name with the latest text from the TextField
                    Gdx.input.setOnscreenKeyboardVisible(false); // Hide virtual keyboard 

                    //CONDITION PLAYER 1 TEXT FIELDS ARE HIDDEN
                    nameFieldUI.setVisible(false);
                    nameLabelUI.setVisible(false);


                    player1EnteredName = true;
                    stage.addActor(player1NameTagImage);
                    stage.addActor(player1NameLabel); 
                    stage.addActor(player1IconImage);

                    return true; // means the event has been handled by this listener and doesnt have to keep going down the chain of listeners

                } else if(key == Keys.ENTER & nameFieldUI2.getText() != "" & player2TextFieldAppeared & !player2EnteredName) { //CONDITION PLAYER 2 ENTERED ENTER
                    wheel.setVisible(true);
                    player2NameLabel.setText(player2NameLabel.getText() + nameFieldUI2.getText());
                    Gdx.input.setOnscreenKeyboardVisible(false);



                    //CONDITION PLAYER 2 TEXT FIELDS ARE HIDDEN
                    nameFieldUI2.setVisible(false);
                    nameLabelUI2.setVisible(false);


                    player2EnteredName = true;
                    stage.addActor(player2NameTagImage);
                    stage.addActor(player2NameLabel);
                    stage.addActor(player2IconImage);
                    return true;
                }

                //Enter Above//
                ///////////////
                //Space Below//

                if(key == Keys.SPACE & player1EnteredName & !player1GotCategoryBool & !player2GotCategoryBool & !player1SpunWheel) { //CONDITION SPACE PRESSED AND PLAYER 1 ENTERED NAME
                    player1SpunWheel = true;
                    spinWheel();
                    return true; // means the event has been handled by this listener and doesnt have to keep going down the chain of listeners
                }

                else if (key == Keys.SPACE & player2EnteredName & !player2GotCategoryBool & !player2SpunWheel) {
                    player2SpunWheel = true;
                    spinWheel();
                    return true; // means the event has been handled by this listener and doesnt have to keep going down the chain of listeners
                }
                return false;
            }
        });
    }

    private void spinWheel() {
        // Spin the wheel with a random duration and rotation
        float duration = MathUtils.random(2.0f, 6.0f); // Adjust the duration as needed
        float rotation = 360 * MathUtils.random(5.5f, 12.5f); // Adjust the rotation range as needed

        wheel.addAction(Actions.sequence(Actions.rotateBy(rotation, duration),Actions.run(new Runnable() {
            @Override
            public void run() {

                finalRotation = wheel.getRotation();
                System.out.println(finalRotation);

                // Handle category selection after the wheel stops spinning
                Category selectedCategory = determineSelectedCategory();

                //if player1 has not gotten a category assign it to player 1 else assign it to player 2
                if (player1SpunWheel & player1GotCategoryBool == false) { //CONDITION PLAYER 1 DOES NOT HAVE CATEGORY YET SO THIS SPIN IS TO BE ASSIGNED FOR PLAYER 1
                    player1 = new Player(nameFieldUI.getText(), 0, selectedCategory);
                    player1Questions = APIRequestHandler.makeRequest("10", player1.getCategory().getCategoryNumber(), "easy");

                    System.out.println("Player 1 (" + player1.getName() + " )Selected Category: " + player1.getCategory());
                    player1NameLabel.setVisible(true);
                    player1CategoryLabel.setText("Player 1 Category: \n" + player1.getCategory());
                    stage.addActor(player1CategoryTagImage);
                    stage.addActor(player1CategoryLabel); 
                    stage.addActor(categoryIcon1);
                    player1SpunWheel = true;
                    player1GotCategoryBool = true;


                    //waits 3 seconds and then set it to false
                    player1WheelTimeStarted = true;

                    //CONDITION PLAYER 1 GOT CATEGORY ASSIGNED SO MAKE PLAYER 2S TEXT BOX APPEAR

                    Gdx.input.setOnscreenKeyboardVisible(true);
                    player2TextFieldAppeared = true;

                } else if (player2SpunWheel & player2GotCategoryBool == false){ //CONDITION IF PLAYER 1 ALREADY HAS CATEGORY THEN THIS SPIN IS TO BE ASSIGNED TO PLAYER 2
                    player2 = new Player(nameFieldUI2.getText(), 0, selectedCategory);
                    player2GotCategoryBool = true;
                    player2CategoryLabel.setText("Player 2 Category: \n" + player2.getCategory());
                    System.out.println("Player 2 Selected Category: " + player2.getCategory());

                    player2CategoryTagImage.setVisible(true);
                    //stage.addActor(player2CategoryTagImage);
                    stage.addActor(player2CategoryLabel);
                    stage.addActor(categoryIcon2);

                    // wait a second
                    player2WheelTimeStarted = true;
                }
            }
        })
        ));
    }   

    private Category determineSelectedCategory() {
        //Implement logic to determine the selected category based on the wheel's final rotation
        //Divide the wheel into sectors, and each sector corresponds to a category
        //Return the selected category
        //Category.GEOGRAPHY.getCategoryNumber()
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
    public void render (float delta) {
        // clear the screen with black
        ScreenUtils.clear(0, 0, 0, 0);

        // tell the camera to update its matrices.
        camera.update();

        // Wheel
        cpsa.load();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        if (player1WheelTimeStarted && !player1WheelTimeHappendBefore) {
            player1WheelTime -= Gdx.graphics.getDeltaTime();
            //player1Questions = APIRequestHandler.makeRequest("10", player1.getCategory().getCategoryNumber(), "easy");

            if(player1WheelTime < 0) {
                //player2Questions = APIRequestHandler.makeRequest("10", player2.getCategory().getCategoryNumber(), "easy");
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
