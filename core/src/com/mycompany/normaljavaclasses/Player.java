/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.normaljavaclasses;


/**
 *
 * @author teoberbic
 */
public class Player {
    private String name;
    private int score;
    private final Category category;
    
    public Player(String n, int s, Category c) {
        this.name = n;
        this.score = s;
        this.category = c;
    }
    
    public void updateScore() {
        this.score += 1;
    }
    
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
    
    public Category getCategory () {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }
}
