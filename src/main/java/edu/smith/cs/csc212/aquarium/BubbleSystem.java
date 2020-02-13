package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class BubbleSystem {

	// create a new random number generator
	Random rand = new Random();
	// set the color of the bubbles
	Color myColor = new Color(153, 204, 255, 120);

	int size;
	int speed;
	int x;
	int y;
	int times;
	int width;

	public BubbleSystem() {
		// randomly set the size the speed of the bubble
		int size = rand.nextInt(4) + 1;
		int speed = rand.nextInt(3) + 5;
		this.size = size;
		this.speed = speed;

		int pos = rand.nextInt(5);
		this.x = 80 + 15 * pos;
		this.y = 530 + 10 * pos;

		this.times = 0;
		this.width = (pos + 1) * 6;
	}

	public void move() {
		// if the bubble move out of the window,
		// bring it back to the bottom
		if (this.y < 0) {
			this.y = 520;
		}

		// move the bubble up at its own speed
		this.y -= this.speed;

		// the bubble also wiggles a little
		if (this.times % this.width < this.width / 2) {
			this.x -= 5;
		} else {
			this.x += 5;
		}
	}

	public void draw(Graphics2D g) {
		this.times += 1;
		this.move();
		DrawFish.Bubble(g, myColor, this.x, this.y, this.size);

	}
}
