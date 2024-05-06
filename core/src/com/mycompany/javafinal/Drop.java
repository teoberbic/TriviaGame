/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Game;

/**
 *
 * @author teoberbic
 */
public class Drop extends Game {
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 750;
    

    @Override
    public void create() {
        this.setScreen(new MainMenuScreen(this)); // Need to pass through the drop instance through the lifetime of game bc it needs the methods of game
    }
    

    @Override
    public void render() {
        super.render(); 
    }

    @Override
    public void dispose() {}

}
