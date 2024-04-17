package com.mycompany.javafinal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 750;
    
    public static void main (String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Drop");
        config.setWindowedMode(WIDTH, HEIGHT);
        config.useVsync(true);
        config.setForegroundFPS(60);
        config.setTitle("Teo's Trivia Game");
        new Lwjgl3Application(new Drop(), config);
        
    }

}