package asteroids;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import static asteroids.Constants.*;

/**
 * Controls a game of asteroids
 * @author Joe Zachary, Greg Anderson and Umair Naveed
 */
public class Controller implements CollisionListener, ActionListener, KeyListener, CountdownTimerListener {
	
	// Number of ships to create (used in placeShip())
	private static final int SHIP_A = 1;
	private static final int SHIP_B = 2;
	private static final int BOTH_SHIPS = 3;
	
	// Shared random number generator
	private Random random;
	
	// The ships (if one is active) or null (otherwise)
	private ArrayList<Ship> ship;
	
	// When this timer goes off, it is time to refresh the animation
	private Timer refreshTimer; 
	
	// Count of how many transitions have been made.  This is used to keep two
	// conflicting transitions from being made at almost the same time.
	private int transitionCount;
	
	// Number of lives left
	private ArrayList<Integer> lives;
	
	// The Game and Screen objects being controlled
	private Game game;
	private Screen screen;
	
	// Keys being held down
	private HashSet<Integer> heldKeys;

	// Counts how many times the a life has been gained
	private int lifeIncrement; 
	
	// The number of points scored
	private int points;
	
	// Cooldown variable for shooting
	private ArrayList<Boolean> shoot;
	
	// Cooldown variable for teleporting
	private ArrayList<Boolean> teleport;
	
	// Count for asteroids destroyed
	private int asteroids_destroyed;
	
	// Says whether the game is 1 or 2 player
	private boolean two_player;
	private int num_players;
	
	
	
	/**
	 * Constructs a controller to coordinate the game and screen
	 */
	public Controller (Game game, Screen screen) {
		
		// Record the game and screen objects
		this.game = game;
		this.screen = screen;
		
		// Initialize the random number generator
		random = new Random();
		
		// Set up the refresh timer.
		refreshTimer = new Timer(FRAME_INTERVAL, this);
		transitionCount = 0;
		
		// Bring up the splash screen and start the refresh timer
		splashScreen();
		refreshTimer.start();
		
		// Set up the keys ready to be used in battle
		heldKeys = new HashSet<Integer>();
		
		// Get the guns ready to shoot
		shoot = new ArrayList<Boolean>();
		shoot.add(true);
		shoot.add(true);
		
		// Get the teleporting ability charged and ready
		teleport = new ArrayList<Boolean>();
		teleport.add(true);
		teleport.add(true);
		
		// Get the list ready to hold ships
		ship = new ArrayList<Ship>();
		
		// Get the round ready to start with no asteroids destroyed
		asteroids_destroyed = 0;
		
		// Get variable ready
		two_player = false;
		
		// Get lives set up and ready for action
		lives = new ArrayList<Integer>();
		lives.add(0);
		lives.add(0);
			
	}

	
	/**
	 * Configures the game screen to display the splash screen
	 */
	private void splashScreen () {
				
		// Clear the screen and display the legend
		screen.clear();
		screen.setLegend("Asteroids");
		
		// Displays the instructions for game play
		screen.setInstruction("Controls");
		screen.setBody("Player 1");
		screen.setBody("Up Arrow - Moves the ship forward");
		screen.setBody("Left Arrow - Rotates the ship to the left");
		screen.setBody("Right Arrow - Rotates the ship to the right");
		screen.setBody("Spacebar - Shoots");
		screen.setBody("M - Teleports the ship to a random location");
		
		// Displays the instructions for game play
		screen.setInstruction("Controls");
		// Fix spacing
		for (int i = 0; i < 8; i++){
			screen.setBody("");
		}
		screen.setBody("Player 2");
		screen.setBody("W - Moves the ship forward");
		screen.setBody("A - Rotates the ship to the left");
		screen.setBody("D - Rotates the ship to the right");
		screen.setBody("Q - Shoots");
		screen.setBody("T - Teleports the ship to a random location");
		
		// Place four asteroids near the corners of the screen.
		placeAsteroids(TOTAL_ASTEROIDS);
		
		// Make sure there's no ship
		ship = null;
		
	}
	
	
	/**
	 * Get the number of transitions that have occurred.
	 */
	public int getTransitionCount () {
		return transitionCount;
	}
	
	
	/**
	 * The game is over.  Displays a message to that effect and
	 * enables the start button to permit playing another game.
	 */
	private void finalScreen () {
		screen.setLegend(GAME_OVER);	
		new CountdownTimer(this, null, 2000);
		screen.removeCollisionListener(this);
		screen.removeKeyListener(this);
	}
	
	
	
	/**
	 * Places four asteroids near the corners of the screen.
	 * Gives them random velocities and rotations.
	 */
	private void placeAsteroids (int num) {

		if (num == 4){
			Polygon a = new Asteroid(0, 2, EDGE_OFFSET, EDGE_OFFSET);
			a.setVelocity(3, random.nextDouble()*2*Math.PI);
			a.setRotation(2*Math.PI * random.nextDouble());
			screen.addParticipant(a);
			
			a = new Asteroid(1, 2, SIZE-EDGE_OFFSET, EDGE_OFFSET);
			a.setVelocity(3, random.nextDouble()*2*Math.PI);
			a.setRotation(2*Math.PI * random.nextDouble());
			screen.addParticipant(a);
			
			a = new Asteroid(2, 2, EDGE_OFFSET, SIZE-EDGE_OFFSET);
			a.setVelocity(3, random.nextDouble()*2*Math.PI);
			a.setRotation(2*Math.PI * random.nextDouble());
			screen.addParticipant(a);
			
			a = new Asteroid(3, 2, SIZE-EDGE_OFFSET, SIZE-EDGE_OFFSET);
			a.setVelocity(3, random.nextDouble()*2*Math.PI);
			a.setRotation(2*Math.PI * random.nextDouble());
			screen.addParticipant(a);
		}
		else {
			System.out.println("Need to create more asteroids");
		}
		
	}
	
	/**
	 * Set things up and begin a new game.
	 */
	private void initialScreen (boolean start_over) {
				
		// Clear the screen
		screen.clear();
		
		if (start_over){
			// Get user input for 1 or 2 player
			Object[] options = {"1 Player", "2 Player"};
			num_players = JOptionPane.showOptionDialog(null, "Choose the number of players:",
												"Greg", JOptionPane.YES_NO_OPTION,
												JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (num_players == 0){
				two_player = false;
				num_players = SHIP_A;
			}
			else {
				two_player = true;
				num_players = BOTH_SHIPS;
			}
		}
		
		// Place four asteroids
		placeAsteroids(Constants.TOTAL_ASTEROIDS);
		if (start_over){
			// Reset statistics
			lives.set(0, STARTING_LIVES);
			if (two_player){
				lives.set(1, STARTING_LIVES);
			}
			points = 0;
			lifeIncrement = 0;
		}
		ship.clear();
		
		// Reset movements and actions
		shoot.set(0, true);
		teleport.set(0, true);
		if (two_player){
			shoot.set(1, true);
			teleport.set(1, true);
		}
		heldKeys.clear();
		asteroids_destroyed = 0;
		
		// Place the ship
		placeShip(num_players);
		
		// Start listening to events.  In case we're already listening, take
		// care to avoid listening twice.
		screen.removeCollisionListener(this);
		screen.removeKeyListener(this);
		screen.addCollisionListener(this);
		screen.addKeyListener(this);
		
		// Give focus to the game screen
		screen.requestFocusInWindow();
		
	}
	
	
	/**
	 * Place a ship in the center of the screen.
	 */
	private void placeShip (int num) {
		if (num == SHIP_A) {
			if (ship.size() == 0){
				ship.add(new Ship(Ship.REGULAR_SHIP));
			}
			else {
				ship.set(0, new Ship(Ship.DOUBLE_GUN_SHIP));
				shoot.set(0, true);		
				teleport.set(0, true);
			}
			ship.get(0).setPosition(SIZE/2+20, SIZE/2);
			ship.get(0).setRotation(-Math.PI/2);
			screen.addParticipant(ship.get(0));			
		}
		else if (num == SHIP_B){
			// Add player 2
			if (ship.size() == 1){
				ship.add(new Ship(Ship.REGULAR_SHIP));
			}
			else {
				ship.set(1, new Ship(Ship.REGULAR_SHIP));
				shoot.set(1, true);
				teleport.set(1, true);
			}
			ship.get(1).setPosition(SIZE/2-20, SIZE/2);
			ship.get(1).setRotation(-Math.PI/2);
			screen.addParticipant(ship.get(1));
		}
		else {
			// Add player 1
			ship.add(new Ship(Ship.DOUBLE_GUN_SHIP));
			ship.get(0).setPosition(SIZE/2+20, SIZE/2);
			ship.get(0).setRotation(-Math.PI/2);
			screen.addParticipant(ship.get(0));
			// Add player 2
			ship.add(new Ship(Ship.REGULAR_SHIP));
			ship.get(1).setPosition(SIZE/2-20, SIZE/2);
			ship.get(1).setRotation(-Math.PI/2);
			screen.addParticipant(ship.get(1));
		}

	}

	
	/**
	 * Deal with collisions between participants.
	 */
	@Override
	public void collidedWith(Polygon p1, Polygon p2) {
		
		// Collisions between asteroids and ship
		if (p1 instanceof Asteroid && p2 instanceof Ship) {
			asteroidCollision((Asteroid)p1);
			scoring((Asteroid)p1);
			shipCollision((Ship)p2);
		}
		else if (p1 instanceof Ship && p2 instanceof Asteroid) {
			asteroidCollision((Asteroid)p2);
			scoring((Asteroid)p2);
			shipCollision((Ship)p1);
		}
		
		//  Collisions between asteroids and bullets
		else if (p1 instanceof Asteroid && p2 instanceof Bullet){
			asteroidCollision((Asteroid)p1);
			scoring((Asteroid)p1);
			try {
				bulletCollision((Bullet)p2);
			}
			catch (IOException e){
			}
		}
		else if (p1 instanceof Bullet && p2 instanceof Asteroid){
			asteroidCollision((Asteroid)p2);
			scoring((Asteroid)p2);
			try{
				bulletCollision((Bullet)p1);
			}
			catch (IOException e){
			}
		}
	}
	
	
	/**
	 * The ship has collided with something
	 */
	private void shipCollision (Ship s) {

		// Make ship debris
		makeShipDebris(s);
		
		// Remove the ship from the screen, null it out, and decrement lives
		screen.removeParticipant(s);
		if (s == ship.get(0)){
			lives.set(0, getLives(s)-1);
			ship.set(0, null);
		}
		else {
			lives.set(1, getLives(s)-1);
			ship.set(1, null);
		}
		
		// Start the timer that will cause the next round to begin.
		new TransitionTimer(END_DELAY, transitionCount, this);
		
		// Plays the sound for an explosion
//		try {
////	   		 playSound(EXPLOSION);
//	   	 } catch (IOException e) {
//	   		 e.printStackTrace();
//	   	 }
		
	}
	
	/**
	 * Returns the number of lives of the ship
	 */
	private int getLives(Ship s){
		if (s == ship.get(0)){
			return lives.get(0);
		}
		return lives.get(1);
	}
	
	/**
	 * Makes the debris of the ship when it collides with an asteroid.
	 */
	private void makeShipDebris(Ship s){
		
		Polygon debris = new Debris(s.getX(), s.getY(), Debris.MEDIUM);
		debris.setVelocity(random.nextDouble()/2, s.getRotation()+random.nextInt(10));
		new CountdownTimer(this, debris, 1300+random.nextInt(500));
		screen.addParticipant(debris);
		
		debris = new Debris(s.getX(), s.getY(), Debris.LARGE);
		debris.setVelocity(random.nextDouble()/2, s.getRotation()+random.nextInt(10));
		new CountdownTimer(this, debris, 1300+random.nextInt(500));
		screen.addParticipant(debris);
		
		debris = new Debris(s.getX(), s.getY(), Debris.LARGE);
		debris.setVelocity(random.nextDouble()/2, s.getRotation()+random.nextInt(10));
		new CountdownTimer(this, debris, 1300+random.nextInt(500));
		screen.addParticipant(debris);
	}
	
	/**
	 * Something has hit an asteroid
	 */
	private void asteroidCollision (Asteroid a) {
		
		asteroids_destroyed++;
		
		// Starts a timer to see if the level has ended
		if (asteroids_destroyed == ASTEROID_COUNT){
			new TransitionTimer(END_OF_ROUND, transitionCount, this);
		}
		// Make asteroid debris or dust
		makeAsteroidDebris(a);
		
		// The asteroid disappears
		screen.removeParticipant(a);
		
		
		// Create two smaller asteroids.  Put them at the same position
		// as the one that was just destroyed and give them a random
		// direction.
		int size = a.getSize();
		size = size - 1;
		if (size >= 0) {
			int speed = 5 - size;
			Asteroid a1 = new Asteroid(random.nextInt(4), size, a.getX(), a.getY());
			Asteroid a2 = new Asteroid(random.nextInt(4), size, a.getX(), a.getY());
			a1.setVelocity(speed, random.nextDouble()*2*Math.PI);
			a2.setVelocity(speed, random.nextDouble()*2*Math.PI);
			a1.setRotation(2*Math.PI*random.nextDouble());
			a2.setRotation(2*Math.PI*random.nextDouble());
			screen.addParticipant(a1);
			screen.addParticipant(a2);
		}
	}
	
	/**
	 * Makes the dust debris of the asteroid when the ship collides with it.
	 */
	private void makeAsteroidDebris(Asteroid a){
		for (int i = 0; i < 8; i++){
			Polygon dust = new Debris(a.getX(), a.getY(), Debris.SMALL);
			dust.setVelocity(random.nextDouble(), a.getRotation()+random.nextInt(10));
			new CountdownTimer(this, dust, 1300+random.nextInt(500));
			screen.addParticipant(dust);
		}
	}
	
	/**
	 * The bullet hit an asteroid
	 * @throws IOException 
	 */
	public void bulletCollision(Bullet b) throws IOException{
//		playSound(EXPLOSION);
		screen.removeParticipant(b);
	}

	/**
	 * Shoots the bullet
	 */
	public void shootBullet(Ship s) {
		
		// Create the bullet at the nose of the ship
		Polygon bullet = new Bullet(s.getXNose(), s.getYNose());
		bullet.setVelocity(BULLET_SPEED, s.getRotation());
		screen.addParticipant(bullet);
		new CountdownTimer(this, bullet, BULLET_DURATION);
	}
	
	/**
	 * This method will be invoked because of button presses and timer events.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// The start button has been pressed.  Stop whatever we're doing
		// and bring up the initial screen
		if (e.getSource() instanceof JButton) {
			transitionCount++;
			initialScreen(true);	
		}
		
		
		// Time to refresh the screen
		else if (e.getSource() == refreshTimer) {
			for (Integer a: heldKeys){
				// Player 1
				if (a == KeyEvent.VK_LEFT){
					if (ship.get(0) != null) ship.get(0).rotate(-Math.PI/16);
				}
				else if (a == KeyEvent.VK_RIGHT){
					if (ship.get(0) != null) ship.get(0).rotate(Math.PI/16);
				}
				else if (a == KeyEvent.VK_UP){
					if (ship.get(0) != null) ship.get(0).accelerate(SHIP_ACCELERATION);	
				}
				else if (a == KeyEvent.VK_SPACE){
					if (ship.get(0) != null && shoot.get(0)){
						shootBullet(ship.get(0));
						new CountdownTimer(this, ship.get(0), GUN_COOLDOWN);
						shoot.set(0, false);
//						try {
////							playSound(LASER);
//						} catch (IOException e1) {
//							e1.printStackTrace();
//						}
					}
				}
				else if (a == KeyEvent.VK_M){
					if (ship.get(0) != null && teleport.get(0)){
						ship.get(0).setPosition(random.nextInt(SIZE), random.nextInt(SIZE));
						ship.get(0).setVelocity(0, ship.get(0).getRotation());
						new CountdownTimer(this, ship.get(0), TELEPORT_COOLDOWN);
						teleport.set(0, false);	
						ship.get(0).move();
					}
				}
				// Player 2
				if (two_player){
					if (a == KeyEvent.VK_A){
						if (ship.get(1) != null) ship.get(1).rotate(-Math.PI/16);
					}
					else if (a == KeyEvent.VK_D){
						if (ship.get(1) != null) ship.get(1).rotate(Math.PI/16);
					}
					else if (a == KeyEvent.VK_W){
						if (ship.get(1) != null) ship.get(1).accelerate(SHIP_ACCELERATION);	
					}
					else if (a == KeyEvent.VK_Q){
						if (ship.get(1) != null && shoot.get(1)){
							shootBullet(ship.get(1));
							new CountdownTimer(this, ship.get(1), GUN_COOLDOWN);
							shoot.set(1, false);
//							try {
//								playSound(LASER2);
//							} catch (IOException e1) {
//								e1.printStackTrace();
//							}
						}
					}
					else if (a == KeyEvent.VK_T){
						if (ship.get(1) != null && teleport.get(1)){
							ship.get(1).setPosition(random.nextInt(SIZE), random.nextInt(SIZE));
							ship.get(1).setVelocity(0, ship.get(1).getRotation());
							new CountdownTimer(this, ship.get(1), TELEPORT_COOLDOWN);
							teleport.set(1, false);	
							ship.get(1).move();
						}
					}
				}
			}
					
			// Display lives and points
			Game.lifeCount.setText("Player 1:  " + lives.get(0));
			if (two_player){
				Game.lifeCount2.setText("Player 2:  " + lives.get(1));
			}
			Game.score.setText("Points: " + points);
			increaseLives(points);
			
			// Refresh screen
			screen.refresh();
		}
	}
	
	/**
	 * Increase the players lives
	 */
	public void increaseLives (int p) {
		if(p >= LIFE_GAINS[0] && lifeIncrement == 0) {
			lives.set(0, getLives(ship.get(0))+1);
			if (two_player){
				lives.set(1, getLives(ship.get(1))+1);
			}
			lifeIncrement++;
		}
		else if (p >= LIFE_GAINS[1] && lifeIncrement == 1) {
			lives.set(0, getLives(ship.get(0))+1);
			if (two_player){
				lives.set(1, getLives(ship.get(1))+1);
			}			lifeIncrement++;
		}
		else if (p >= LIFE_GAINS[2] && lifeIncrement == 2) {
			lives.set(0, getLives(ship.get(0))+1);
			if (two_player){
				lives.set(1, getLives(ship.get(1))+1);
			}			lifeIncrement++;
		}
		else if (p >= LIFE_GAINS[3] && lifeIncrement == 3) {
			lives.set(0, getLives(ship.get(0))+1);
			if (two_player){
				lives.set(1, getLives(ship.get(1))+1);
			}			lifeIncrement++;
		}
	}
	
	/**
	 * Scores the players Asteroid hits with bullets
	 * by the size of the asteroid. 
	 */
	private void scoring (Asteroid a) {
		int size = a.getSize();
		if(size == 0) {
			points = points + 100;
		}
		else if (size == 1) {
			points = points + 50;
		}
		else if (size == 2) {
			points = points + 20;
		}
	}
	
	/**
	 * Based on the state of the controller, transition to the next state.
	 */
	public void performTransition () {
				
		// Record that a transition was made.  That way, any other pending
		// transitions will be ignored.
		transitionCount++;

		// If there are no lives left, the game is over.  Show
		// the final screen.
		if (lives.get(0) == 0 && lives.get(1) == 0) {
			finalScreen();
		}
		// If there are still lives with either player, start the next round
		// with both ships, even if one previously had 0 lives (everyone deserves
		// a second chance)
		else if (asteroids_destroyed == ASTEROID_COUNT && (!two_player && ship.size() == 1
														|| two_player && ship.size() == 2)){
			initialScreen(false);
		}
		
		// A ship must have been destroyed.  Place a new one and
		// continue on the current level
		else {
			if (ship.get(0) == null && lives.get(0) > 0){
				placeShip(SHIP_A);
			}
			if (two_player){
				if (ship.get(1) == null && lives.get(1) > 0){
					placeShip(SHIP_B);
				}
			}
		}		
	}


	/**
	 * Deals with certain key presses
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// Player 1
		if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
			heldKeys.add(KeyEvent.VK_LEFT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			heldKeys.add(KeyEvent.VK_RIGHT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP){
			heldKeys.add(KeyEvent.VK_UP);
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE){
			if (shoot.get(0)){
				heldKeys.add(KeyEvent.VK_SPACE);
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_M){
			heldKeys.add(KeyEvent.VK_M);
		}
		// Player 2
		if (two_player){
			if (e.getKeyCode() == KeyEvent.VK_A){
				heldKeys.add(KeyEvent.VK_A);
			}
			else if (e.getKeyCode() == KeyEvent.VK_D) {
				heldKeys.add(KeyEvent.VK_D);
			}
			else if (e.getKeyCode() == KeyEvent.VK_W){
				heldKeys.add(KeyEvent.VK_W);
			}
			else if (e.getKeyCode() == KeyEvent.VK_Q){
				if (shoot.get(1)){
					heldKeys.add(KeyEvent.VK_Q);
				}
			}
			else if (e.getKeyCode() == KeyEvent.VK_T){
				heldKeys.add(KeyEvent.VK_T);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Player 1
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			heldKeys.remove(KeyEvent.VK_LEFT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			heldKeys.remove(KeyEvent.VK_RIGHT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP){
			heldKeys.remove(KeyEvent.VK_UP);
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE){
			heldKeys.remove(KeyEvent.VK_SPACE);
		}
		if (e.getKeyCode() == KeyEvent.VK_M) {
			heldKeys.remove(KeyEvent.VK_M);
		}
		// Player 2
		if (two_player){
			if (e.getKeyCode() == KeyEvent.VK_A){
				heldKeys.remove(KeyEvent.VK_A);
			}
			else if (e.getKeyCode() == KeyEvent.VK_D){
				heldKeys.remove(KeyEvent.VK_D);
			}
			else if (e.getKeyCode() == KeyEvent.VK_W){
				heldKeys.remove(KeyEvent.VK_W);
			}
			else if (e.getKeyCode() == KeyEvent.VK_Q){
				heldKeys.remove(KeyEvent.VK_Q);
			}
			if (e.getKeyCode() == KeyEvent.VK_T) {
				heldKeys.remove(KeyEvent.VK_T);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Plays sound file
	 * @throws IOException
	 */
//	public void playSound (String source) throws IOException {
//		InputStream in = new FileInputStream(source);
//		// Errors will be here, but the game works just fine.  Ignore the errors.
//		AudioInputStream as = new AudioInputStream(in);
//		AudioSystem.start(as);
//	}
//	// TODO: Sound
	
	/**
	 * Callback for countdown timer.  Used to create transient effects.
	 */
	@Override
	public void timeExpired(Polygon p) {

		// Removes bullet after set amount of time
		if (p instanceof Bullet){
			screen.removeParticipant(p);
		}
		// Removes debris after set amount of time
		else if (p instanceof Debris){
			screen.removeParticipant(p);
		}
		// Gun has cooled down and is ready to shoot again
		else if (p instanceof Ship){
			if (p == ship.get(0)){
				shoot.set(0, true);				
				teleport.set(0, true);
			}
			if (two_player){
				if (p == ship.get(1)){
					shoot.set(1, true);
					teleport.set(1, true);
				}
			}
		}
		// Game must be over, so display high score stuff
		else if (p == null) {
			String name = JOptionPane.showInputDialog(null, "Enter your name");
			if (name != null && !name.equals("")) {
				try {
					Scores s = new Scores();
					s.addScores(name, points);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			screen.setHSTitle("High Scores");
			screen.setHSName("Name");
			screen.setHSScore("Score");
			try {
				screen.showList();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		screen.setLegend("");
	}

}
