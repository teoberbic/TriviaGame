/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafinal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author teoberbic
 */
public class Drop extends Game{
        public SpriteBatch batch;
	public BitmapFont font;
        public static final int WIDTH = 1500;
        public static final int HEIGHT = 750;

        @Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font
		this.setScreen(new MainMenuScreen(this));
	}

        @Override
	public void render() {
		super.render(); // important!
	}

        @Override
	public void dispose() {
		batch.dispose();
	}
        
        Test t = new Test();
}
