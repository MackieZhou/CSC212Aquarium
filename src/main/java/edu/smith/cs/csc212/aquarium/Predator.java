package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Predator {
	
	Random rand = new Random();
	
	// position, destination, and speed
	double x;
	double y;
	double dest_x;
	double dest_y;
	double speedx;
	double speedy;

	// color and facing
	Color color;
	boolean facingLeft;

	// prey of the predator
	Fish[] prey;
	// the index of the fish(that Jaws is hunting) in the array
	int order;

	// the constructor method
	public Predator(Fish[] fish) {
		
		// randomly select an initial position
		int pos_x = rand.nextInt(400) + 50;
		int pos_y = rand.nextInt(400) + 50;
		this.x = pos_x;
		this.y = pos_y;

		// what are the prey?
		this.prey = fish;

		// Jaws always starts hunting from the 0th fish
		this.order = 0;
		this.getDest();
	}

	public void swim() {
		// move the predator vertically to the destination
		this.y += this.speedy;
		// move the predator horizontally to the destination
		this.x += this.speedx;
	}

	public void calcSpeed() {
		/**
		 * predator swims at a constant speed toward the destination
		 */

		// calculate the distance between position and destination
		double distanceX = (this.dest_x - this.x);
		double distanceY = (this.dest_y - this.y);

		// how many times do you want the predator to swim to the destination
		int times = rand.nextInt(5) + 10;
		// how fast does the predator swim each time
		double speedx = distanceX / times;
		double speedy = distanceY / times;

		this.speedx = speedx;
		this.speedy = speedy;
	}

	public void getDest() {
		/**
		 * using the random number generator to choose another random destination for
		 * the predator
		 */
		this.dest_x = this.prey[this.order].x;
		this.dest_y = this.prey[this.order].y;

		// recalculate the speed of the predator
		this.calcSpeed();
	}

	public void changeTarget() {
		if (this.order < this.prey.length - 1) {
			this.order++;
		}
	}

	public void drawPredator(Graphics2D g) {
		// in case you forgot to make the predator swim
		this.swim();

		// to check if the predator is close enough to a fish
		// if true - choose another target
		if (Math.abs(this.x - this.dest_x) < 10 && Math.abs(this.y - this.dest_y) < 10) {
			this.prey[this.order].eaten = true;
			this.changeTarget();
		}

		// check the facing of the predator
		if (this.dest_x < this.x) {
			this.facingLeft = true;
		} else {
			this.facingLeft = false;
		}

		// predator facing left or not?
		// choose the proper draw method
		if (this.facingLeft) {
			DrawFish.predatorLeft(g, Color.RED, this.x, this.y);
		} else {
			DrawFish.predatorRight(g, Color.RED, this.x, this.y);
		}

		this.getDest();
	}
}
