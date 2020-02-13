package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	
	public static int WIDTH = 500;
	public static int HEIGHT = 500;

	/**
	 * Put a snail on the top of the tank.
	 */
	Snail algorithm = new Snail(177, Snail.HEIGHT, "top");

	/**
	 * This is a constructor, code that runs when we make a new Aquarium.
	 */
	public Aquarium() {
		// Here we ask GFX to make our window of size WIDTH and HEIGHT.
		// Don't change this here, edit the variables instead.
		super(WIDTH, HEIGHT);
	}

	// create a random number generator
	public static Random rand = new Random();

	// set a green component of the bgColor
	public static int green = 0;

	// create arrays colors and fish
	public static Color[] fishColor = { Color.LIGHT_GRAY, Color.YELLOW, Color.PINK, Color.GRAY, Color.CYAN,
			Color.WHITE, Color.ORANGE, Color.GREEN, Color.MAGENTA, Color.DARK_GRAY };
	public static Fish[] fish = new Fish[10];

	// create arrays of bubbles
	public static BubbleSystem[] bubbles = new BubbleSystem[10];

	// the treasure chest in the background
	public static BufferedImage img = null;

	// create the predator
	public static Predator Jaws;

	@Override
	public void draw(Graphics2D g) {
		// Draw the "ocean" background.
		Color bgColor = new Color(0, green, 200);
		g.setColor(bgColor);
		g.fillRect(0, 0, getWidth(), getHeight());

		// draw our bubbles here
		for (BubbleSystem b : bubbles) {
			b.draw(g);
		}

		// draw the treasure-chest
		// URL: https://docs.oracle.com/javase/tutorial/2d/images/drawimage.html
		g.drawImage(img, 55, 440, null);

		// draw our fish here
		for (Fish f : fish) {
			f.draw(g);
		}

		// Draw our snail!
		algorithm.draw(g);

		// draw the predator
		Jaws.drawPredator(g);

		// fuck debugging...
		System.out.println(Jaws.order);
		System.out.println("fish pos = " + fish[Jaws.order].x + ", " + fish[Jaws.order].y);
		System.out.println("dest = " + Jaws.dest_x + ", " + Jaws.dest_y);
		System.out.println("position = " + Jaws.x + ", " + Jaws.y);
		System.out.println("speed = " + Jaws.speedx + ", " + Jaws.speedy);
		

		// the water gets greener and greener
		if (green < 254 && Snail.eatTime == 0) {
			green++;
		} else if (Snail.eatTime > 200 && green > 0) {
			green--;
		}
	}

	public static void main(String[] args) {
		// Uncomment this to make it go slower!
		// GFX.FPS = 10;
		// This is potentially helpful for debugging movement if there are too many
		// print statements!

		// Note that we can store an Aquarium in a variable of type GFX because Aquarium
		// is a very specific GFX, much like 7 can be stored in a variable of type int!
		GFX app = new Aquarium();

		// load the treasure-chest image
		// URL: https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
		try {
			img = ImageIO.read(new File("src/main/java/edu/smith/cs/csc212/aquarium/treasureChest.png"));
		} catch (IOException e) {
		}

		for (int i = 0; i < 10; i++) {
			// determine the size of the fish
			boolean isLittle = rand.nextBoolean();

			// create the fish objects
			fish[i] = new Fish(fishColor[i], isLittle);
			// create the bubble objects
			bubbles[i] = new BubbleSystem();
		}

		Jaws = new Predator(fish);

		app.start();
	}

}
