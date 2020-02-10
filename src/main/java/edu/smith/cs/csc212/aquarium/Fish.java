package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Fish {
	// position of a fish
	double x;
	double y;

	// color and facing
	Color color;
	boolean facingLeft;
	boolean isLittle;

	// destination of fish
	int dest_x;
	int dest_y;
	
	double speedx;
	double speedy;
	
	// create a new random number generator
	Random rand = new Random();

	// the constructor method
	public Fish(Color color, boolean isLittle) {
		/**
		 * construct a fish object
		 */
		// randomly select an initial position
		int pos_x = rand.nextInt(400) + 50;
		int pos_y = rand.nextInt(400) + 50;
		this.x = pos_x;
		this.y = pos_y;

		// randomly select a destination
		int dest_x = rand.nextInt(500);
		int dest_y = rand.nextInt(500);
		this.dest_x = dest_x;
		this.dest_y = dest_y;

		this.isLittle = isLittle;
		this.color = color;
		
		// give the fish an initial speed
		this.calcSpeed();
	}

	public void swim() {
		// move the fish vertically to the destination
		if (this.y < this.dest_y) {
			this.y += this.speedy;
		} else if (this.y > this.dest_y) {
			this.y -= this.speedy;
		}

		// move the fish horizontally to the destination
		if (this.x < this.dest_x) {
			this.x += this.speedx;
		} else if (this.x > this.dest_x) {
			this.x -= this.speedx;
		}
	}
	
	public void calcSpeed() {
		/**
		 * fish swims at a constant speed toward the destination
		 */
		
		// calculate the distance between position and destination
		double distanceX = Math.abs(this.dest_x - this.x);
		double distanceY = Math.abs(this.dest_y - this.y);

		// how many times do you want the fish to swim to the destination
		int times = rand.nextInt(15) + 45;
		// how long does the fish swim each time
		double speedx = distanceX / times;
		double speedy = distanceY / times;
		
		this.speedx = speedx;
		this.speedy = speedy;
		
	}

	public void changeDest() {
		/**
		 * using the random number generator to choose another random destination for
		 * the fish
		 */
		int dest_x2 = rand.nextInt(500);
		int dest_y2 = rand.nextInt(500);
		this.dest_x = dest_x2;
		this.dest_y = dest_y2;
		
		// recalculate the speed of the fish
		this.calcSpeed();
	}

	public void draw(Graphics2D g) {
		// in case you forgot to make the fish swim
		this.swim();

		// to check if the fish is close enough to the destination
		// if true - choose another destination
		if (Math.abs(this.x - this.dest_x) < 7 && 
				Math.abs(this.y - this.dest_y) < 7) {
			this.changeDest();
		}

		// check the direction of the fish
		if (this.dest_x < this.x) {
			this.facingLeft = true;
		} else {
			this.facingLeft = false;
		}
		
//		System.out.println("position: "+this.x+", "+this.y);
//		System.out.println("speed: "+this.speedx+", "+this.speedy);
//		System.out.println("destination: "+this.dest_x+", "+this.dest_y);
//		System.out.println();

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
