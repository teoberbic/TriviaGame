/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import static com.mycompany.javafinal.Drop.HEIGHT;
import static com.mycompany.javafinal.Drop.WIDTH;

/**
 *
 * @author teoberbic
 */
public class CategoryPickerScreenAssets {
    Stage stage;
    private Image arrowImage;
    
    public CategoryPickerScreenAssets(Stage stage) {
        this.stage = stage;
    }
    
    public void load() {
        arrowImage = new Image(new Texture(Gdx.files.internal("arrow.png")));
        arrowImage.setPosition(WIDTH/2 - arrowImage.getWidth()/2, HEIGHT - 50 - arrowImage.getHeight()/2);
        stage.addActor(arrowImage);
    }
}
