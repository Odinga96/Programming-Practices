package asteroids;

import java.util.Random;

import static asteroids.Constants.*;

/**
 * Represents the stars
 * @author Greg Anderson and Umair Naveed
 */
public class Star{
	
	// The x and y coordinates of the star
	private int pos_x;
	private int pos_y;
	
	// The random generator for finding positions
	private Random random;
	
	public Star(){
		random = new Random();
		
		// Pick a random position for the star to reside
		pos_x = random.nextInt(SIZE);
		pos_y = random.nextInt(SIZE);
	}
	
	/**
	 * Returns the x coordinate of the position
	 */
	public int getPosX(){
		return pos_x;
	}
	
	/**
	 * Returns the x coordinate of the position
	 */	
	public int getPosY(){
		return pos_y;
	}
	
}
