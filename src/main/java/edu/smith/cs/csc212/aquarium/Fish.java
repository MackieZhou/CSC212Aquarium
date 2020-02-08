package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

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
    
    // create a new random number generator
    Random rand = new Random();
    
    public Fish(int pos_x, int pos_y, Color color,
    		boolean isLeft, boolean isLittle) {
    	/**
    	 * construct a fish object
    	 */
    	this.x = pos_x;
    	this.y = pos_y;
    	this.color = color;
    	
    	int dest_x = rand.nextInt(500);
    	int dest_y = rand.nextInt(500);
    	this.dest_x = dest_x;
    	this.dest_y = dest_y;
    	
    	this.isLittle = isLittle;
    	this.facingLeft = isLeft;
    }
    
    public void swim() {
    	// move the fish vertically to the destination
    	if (this.y < this.dest_y) {
    		this.y += 1;}
//    	} else {
//    		this.y -= 1;
//    	}
    	
    	// move the fish horizontally to the destination
    	if (this.x < this.dest_x) {
    		this.x += 1;}
//    	} else {
//    		this.x -= 2;
//    	}
    }
    
    public void changeDest() {
    	/**
    	 * using the random number generator to choose
    	 * another random destination for the fish
    	 */
    	int dest_x2 = rand.nextInt(500);
    	int dest_y2 = rand.nextInt(500);
    	this.dest_x = dest_x2;
    	this.dest_y = dest_y2;
    }
    
    public void draw(Graphics2D g) {
    	// in case you forgot to make the fish swim
    	this.swim();
    	
    	// to check if the fish has reached destination
    	// if true - choose another destination
    	if (this.x==this.dest_x && this.y==this.dest_y) {
    		this.changeDest();
    	}
    	
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
