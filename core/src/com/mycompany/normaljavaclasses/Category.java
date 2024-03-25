/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.normaljavaclasses;

/**
 *
 * @author teoberbic
 */
public enum Category {
    GENERAL_KNOWLEDGE ("9"), 
    FILM ("11"), 
    MUSIC ("12"), 
    VIDEO_GAMES ("15"), 
    SCIENCE ("17"), 
    COMPUTERS ("18"), 
    MYTHOLOGY ("20"),
    SPORTS ("21"), 
    GEOGRAPHY ("22"), 
    HISTORY ("23"), 
    CELEBERTIES ("26"), 
    VEHICLES ("28");
    
    private final String categoryNumber;
    private Category (String num) {
        this.categoryNumber = num;
    }
    
    public String getCategoryNumber () {
        return this.categoryNumber;
    }
}
