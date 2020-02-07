package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;

public class Fish {
	// position of a fish
	int x;
    int y;
    
    // color and facing
    Color color;
    boolean facingLeft;
    boolean isLittle;
    
    // destination of fish
    int dest_x;
    int dest_y;
    
    public Fish(int pos_x, int pos_y, Color color, boolean isLeft, boolean isLittle) {
    	this.x = pos_x;
    	this.y = pos_y;
    	this.color = color;
    	
    	this.dest_x = 450;
    	this.dest_y = 450;
    	
    	this.isLittle = isLittle;
    	this.facingLeft = isLeft;
    }
    
    public void swim() {
    	// move the fish vertically to the destination
    	if (this.y < this.dest_y) {
    		this.y += 1;
    	} else {
    		this.y -= 1;
    	}
    	
    	// move the fish horizontally to the destination
    	if (this.x < this.dest_x) {
    		this.x += 2;
    	} else {
    		this.x -= 2;
    	}
    }
    
    public void draw(Graphics2D g) {
    	// in case you forgot to make the fish swim
    	this.swim();
    	
    	// fish small or not? fish facing left or not?
    	// choose the proper draw method
    	if (this.facingLeft && this.isLittle) {
    		DrawFish.smallFacingLeft(g, this.color, this.x, this.y);
    	} else if (this.facingLeft && !this.isLittle) {
    		DrawFish.facingLeft(g, this.color, this.x, this.y);
    	} else if (!this.facingLeft && this.isLittle) {
    		DrawFish.smallFacingRight(g, this.color, this.x, this.y);
    	} else {
    		DrawFish.facingRight(g, this.color, this.x, this.y);
    	}
    	
    }

}
