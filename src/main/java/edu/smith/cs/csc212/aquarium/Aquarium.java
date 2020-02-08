package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;

import me.jjfoley.gfx.GFX;

/**
 * Aquarium is a graphical "application" that uses some code I built and have
 * shared with you that takes care of opening a window and communicating with
 * the user in a simple way.
 * 
 * The method draw is called 50 times per second, so we make an animation by
 * drawing our fish in one place, and moving that place slightly. The next time
 * draw gets called, our fish looks like it moved!
 * 
 * @author jfoley
 *
 */
public class Aquarium extends GFX {
	/**
	 * This is a static variable that tells us how wide the aquarium is.
	 */
	public static int WIDTH = 500;
	/**
	 * This is a static variable that tells us how tall the aquarium is.
	 */
	public static int HEIGHT = 500;

	/**
	 * Put a snail on the top of the tank.
	 */
	Snail algorithm = new Snail(177, Snail.HEIGHT + 1, "top");

	/**
	 * This is a constructor, code that runs when we make a new Aquarium.
	 */
	public Aquarium() {
		// Here we ask GFX to make our window of size WIDTH and HEIGHT.
		// Don't change this here, edit the variables instead.
		super(WIDTH, HEIGHT);
	}

	// create our fish here
	Fish henry = new Fish(Color.LIGHT_GRAY, true);
	Fish lucy = new Fish(Color.YELLOW, false);
	Fish bob = new Fish(Color.PINK, true);
	Fish adam = new Fish(Color.CYAN, false);
	
	// create bubbles
	BubbleSystem b1 = new BubbleSystem();
	BubbleSystem b2 = new BubbleSystem();
	BubbleSystem b3 = new BubbleSystem();
	BubbleSystem b4 = new BubbleSystem();
	BubbleSystem b5 = new BubbleSystem();
	BubbleSystem b6 = new BubbleSystem();
	BubbleSystem b7 = new BubbleSystem();
	BubbleSystem b8 = new BubbleSystem();
	BubbleSystem b9 = new BubbleSystem();
	BubbleSystem b10 = new BubbleSystem();

	@Override
	public void draw(Graphics2D g) {
		// Draw the "ocean" background.
		g.setColor(Color.blue);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// draw bubbles here
		b1.Draw(g);
		b2.Draw(g);
		b3.Draw(g);
		b4.Draw(g);
		b5.Draw(g);
		b6.Draw(g);
		b7.Draw(g);
		b8.Draw(g);
		b9.Draw(g);
		b10.Draw(g);

		// draw our fish here
		henry.draw(g);
		lucy.draw(g);
		bob.draw(g);
		adam.draw(g);

		// Draw our snail!
		algorithm.draw(g);

	}

	public static void main(String[] args) {
		// Uncomment this to make it go slower!
		// GFX.FPS = 10;
		// This is potentially helpful for debugging movement if there are too many
		// print statements!

		// Note that we can store an Aquarium in a variable of type GFX because Aquarium
		// is a very specific GFX, much like 7 can be stored in a variable of type int!
		GFX app = new Aquarium();
		app.start();
	}

}
