package asteroids;

import java.awt.Color;

/**
 * Provides constants governing the game
 * @author Joe Zachary, Greg Anderson and Umair Naveed
 */
public class Constants {

	/**
	 * The height and width of the game area.
	 */
	public final static int SIZE = 750;
	
	/**
	 * Game title
	 */
	public final static String TITLE = "Asteroids";
	
	/**
	 * Label on start game button
	 */
	public final static String START_LABEL = "Start Game";
	
	/**
	 * Name and location of the text file containing scores and names
	 */
	public final static String FILE_NAME = "src/scores.txt";
	
	/**
	 * Speed beyond which participants may not accelerate
	 */
	public final static double SPEED_LIMIT = 15;
	
	/**
	 * Amount of "friction" that can be applied to ships so that
	 * they eventually stop.  Should be negative.
	 */
	public final static double FRICTION = -0.05;
	
	/**
	 * The number of milliseconds between the beginnings of
	 * frame refreshes
	 */
	public final static int FRAME_INTERVAL = 33;
	
	/**
	 * The number of milliseconds between the end of a life and
	 * the display of the next screen.
	 */
	public final static int END_DELAY = 2500;
	
	/**
	 * The number of milliseconds to wait to check for end of level
	 */
	public final static int END_OF_ROUND = 4000;
	
	/**
	 * The offset in pixels from the edges of the screen of 
	 * newly-placed asteroids.
	 */
	public final static int EDGE_OFFSET = 100;
	
	/**
	 * The game over message
	 */
	public final static String GAME_OVER = "Game Over";
	
	/**
	 * Total asteroids in one big asteroid (including those after it breaks)
	 */
	private final static int BIG_ASTEROID = 7;
	
	/**
	 * Number of big asteroids in the game
	 */
	public final static int TOTAL_ASTEROIDS = 4;
	
	/**
	 * Number of asteroids that must be destroyed to complete a level.
	 */
	public final static int ASTEROID_COUNT = BIG_ASTEROID * TOTAL_ASTEROIDS;
	
	/**
	 * Duration in milliseconds of a bullet before it disappears.
	 */
	public final static int BULLET_DURATION = 1000;
	
	/**
	 * Speed, in pixels per frame, of a bullet.
	 */
	public final static int BULLET_SPEED = 15;
	
	/**
	 * Scaling factors used for asteroids of size 0, 1, and 2.
	 */
	public final static double[] ASTEROID_SCALE = {0.5, 1.0, 2.0};
	
	/**
	 * Rate of acceleration for ship
	 */
	public final static double SHIP_ACCELERATION = 0.5;
	
	/**
	 * Point levels that lead to extra life gains.
	 */
	public final static int[] LIFE_GAINS = {900, 5000, 12000, 50000};
	
	/**
	 * Total number of stars
	 */
	public final static int STAR_COUNT = 100;
	
	/**
	 * Cooldown time for the gun
	 */
	public final static int GUN_COOLDOWN = 125;
	
	/**
	 * Cooldown time for teleporting
	 */
	public final static int TELEPORT_COOLDOWN = 500;
	
	/**
	 * Color of ship and ship debris
	 */
	public final static Color SHIP_COLOR = Color.cyan;
	
	/**
	 * Starting number of lives
	 */
	public final static int STARTING_LIVES = 3;

	/**
     * Name and location of laser sound file for player 1
     */
    public final static String LASER = "src/laser1.wav";
    
    
    /**
     * Name and location of laser sound file for player 2
     */
    public final static String LASER2 = "src/laser2.wav";

    /**
     * Name an location of explosion sound file
     */
    public final static String EXPLOSION = "src/explosion.wav";
}
