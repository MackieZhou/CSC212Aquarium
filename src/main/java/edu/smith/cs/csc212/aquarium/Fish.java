package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;

public class Fish {
	
	int x; // position of a fish
    int y;
    Color color; // color of a fish
    boolean facingLeft;
    boolean isLittle;
    
    // destination of fish
    int dest_x;
    int dest_y;
    
    public Fish(int pos_x, int pos_y, Color color, 
    		boolean isLeft, boolean isLittle) {
    	this.x = pos_x;
    	this.y = pos_y;
    	this.color = color;
    	
    	this.dest_x = 450;
    	this.dest_y = 450;
    	
    	this.isLittle = isLittle;
    	this.facingLeft = isLeft;
    }
    
    public void swim() {
    	if (this.y < this.dest_y) {
    		this.y += 1;
    	}
    }
    
    public void draw(Graphics2D g) {
    	this.swim();
    	
    	// fish small or not?
    	// fish facing left or not?
    	DrawFish.smallFacingRight(g, this.color, this.x, this.y);
    }

}
