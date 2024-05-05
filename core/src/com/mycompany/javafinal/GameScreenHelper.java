/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mycompany.normaljavaclasses.Player;
import com.mycompany.normaljavaclasses.Question;
import java.util.Collections;
import java.util.List;
import org.apache.commons.text.StringEscapeUtils;

/**
 *
 * @author teoberbic
 */
public class GameScreenHelper {

    private final GameScreen gameScreen;
    private final GameScreenAssets gsa;
    private Stage stage;
    
    private Sound correctAnswerSFX;
    private Player currentPlayer;
    private Label currentPlayerScoreLabel;
    private Player player1;
    private Player player2;
    private List<Question> player1Questions;
    private List<Question> player2Questions;
    private final Label player1GainedPointLabel;
    private final Label player2GainedPointLabel;
    private final Music gameBackgroundMusic;
    private float timer = 15;
    private float showAnswerImageTime = 1;
    private final Image correctAnswerImage;
    private final Image incorrectAnswerImage;
    private Label questionLabel;
    private String currentCorrectAnswer;
    private List<Label> answerLabels;
    private final Sound incorrectAnswerSFX;
    private final Label player1CurrentIndexLabel;
    private float endQTimer = 3;
    
    public GameScreenHelper (GameScreenAssets gsa, GameScreen gameScreen, Player player1, Player player2, List<Question> player1Questions, List<Question> player2Questions) {
        this.gsa = gsa;
        this.gameScreen = gameScreen;
        this.stage = GameScreenAssets.stage;
        
        this.correctAnswerSFX = GameScreenAssets.correctAnswerSFX;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Questions = player1Questions;
        this.player2Questions = player2Questions;
        currentPlayer = player1;
        
        this.currentPlayerScoreLabel = GameScreenAssets.currentPlayerScoreLabel;
        this.player1GainedPointLabel = GameScreenAssets.player1GainedPointLabel;
        this.player2GainedPointLabel = GameScreenAssets.player2GainedPointLabel;
        this.correctAnswerImage = GameScreenAssets.correctAnswerImage;
        this.incorrectAnswerImage = GameScreenAssets.incorrectAnswerImage;
        this.questionLabel = GameScreenAssets.questionLabel;
        this.answerLabels = GameScreenAssets.answerLabels;
        this.gameBackgroundMusic = GameScreenAssets.gameBackgroundMusic;
        this.incorrectAnswerSFX = GameScreenAssets.incorrectAnswerSFX;
        this.player1CurrentIndexLabel = GameScreenAssets.playerCurrentIndexLabel;
    }
    
    // Methods
    public void clickEqualsCorrectAnswer(int index) {
        // Update player's score and UI
        correctAnswerSFX.play(1.0f);
        currentPlayer.updateScore();
        currentPlayerScoreLabel.setText("Player Score: " + currentPlayer.getScore());
        System.out.println(currentPlayer.getScore());

        if (currentPlayer == player1) {
            player1GainedPointLabel.setVisible(true);
        } else {
            player2GainedPointLabel.setVisible(true);
        }
    }

    public Player updateCurrentTurn(Label playerScoreLabel, Player player) {
        currentPlayerScoreLabel = playerScoreLabel;
        currentPlayer = player;
        return currentPlayer;
    }

    public void outOfQuestions(Drop game) {
        stage.dispose();
        game.setScreen(new EndGameScreen(game, player1, player2, player1Questions, player2Questions));
        gameBackgroundMusic.stop();
    }

    public boolean showAnswerFeedBack(float deltaTime, int answer) {
        timer = 15;
        showAnswerImageTime -= deltaTime;
        if (answer == 1) {
            correctAnswerImage.setVisible(true);

            if (showAnswerImageTime <= 0) {
                correctAnswerImage.setVisible(false);
                showAnswerImageTime = 1;
                player1GainedPointLabel.setVisible(false);
                player2GainedPointLabel.setVisible(false);
                return false;
            }
            return true;
            
        } else {
            incorrectAnswerImage.setVisible(true);

            if (showAnswerImageTime <= 0) {
                incorrectAnswerImage.setVisible(false);
                showAnswerImageTime = 1;
                return false;
            }
            return true;
        }
    }
    
    public void timerRanOut() {
        incorrectAnswerSFX.play(1.0f);
    }

    public void switchTurnLabels(Label trueLabel, Label falseLabel) {
         trueLabel.setVisible(true);
         falseLabel.setVisible(false);
    }

    public String displayQuestion(int playerCurrentQuestionIndex, List<Question> questions) {
        String question = StringEscapeUtils.unescapeHtml4(questions.get(playerCurrentQuestionIndex).getQuestion());
        
        if (question.length() > 35) 
            question = adjustLabelWidth(question, 35);
        
        questionLabel.setText(StringEscapeUtils.unescapeHtml4(question));
        
        // Get all answers and shuffle their order
        List<String> answers = questions.get(playerCurrentQuestionIndex).getIncorrectAnswers();
        currentCorrectAnswer = StringEscapeUtils.unescapeHtml4(questions.get(playerCurrentQuestionIndex).getCorrectAnswer());
        answers.add(questions.get(playerCurrentQuestionIndex).getCorrectAnswer());
        Collections.shuffle(answers);
        
        // Sets an answer on each label
        for (int i = 0; i <= 3; i++) {
            System.out.println("Multiple Choice: " + i + "==" + answers.get(i));
            String currentAnswer = StringEscapeUtils.unescapeHtml4(answers.get(i));
            
            // For debugging
            if (currentAnswer.length() > 80) {
                System.out.println("ANSWER LONG");
            }
            if (currentAnswer.length() > 23) {
                currentAnswer = this.adjustLabelWidth(currentAnswer, 23);
            }
            
            answerLabels.get(i).setText(StringEscapeUtils.unescapeHtml4(currentAnswer));
        }
        
        System.out.println("CORRECT ANSWER:" + currentCorrectAnswer);
        return currentCorrectAnswer;
    }

    private String adjustLabelWidth(String text, int lengthCap) {
        int lastIDXofSpace = -1;
        StringBuilder stringbuilder = new StringBuilder();
        
        while (text.length() > lengthCap) {
            String currentSubString = text.substring(0, lengthCap); // substring == "Hi my name "

            lastIDXofSpace = currentSubString.lastIndexOf(" ");
            System.out.println("LAST INDEX OF SPACE:" + lastIDXofSpace);

            if (lastIDXofSpace == -1) {
                lastIDXofSpace = currentSubString.length() -1;
            }
            currentSubString = currentSubString.substring(0, lastIDXofSpace) + "\n"; // code breaks here sometimes, caught it at length 36 & and 35
                

            stringbuilder.append(currentSubString);

            text = text.substring(lastIDXofSpace + 1);

            if (text.length() < lengthCap ) {
                stringbuilder.append(text);
            }
        }
        
        text = stringbuilder.toString();
           
        return text;
    }
}
