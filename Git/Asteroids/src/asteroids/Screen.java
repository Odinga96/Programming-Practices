package asteroids;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import static asteroids.Constants.*;
import java.io.IOException;

/**
 * The area in which the game takes place.
 * @author Joe Zachary, Greg Anderson and Umair Naveed
 */
public class Screen extends JPanel {

	// The participants (asteroids, bullets, ships, etc.) that are
	// involved in the game.
	private LinkedList<Polygon> participants;

	// Objects interested in learning about collisions between 
	// pairs of participants
	private Set<CollisionListener> listeners;

	// Participants that will be added to/removed from the game at the next refresh
	private Set<Polygon> pendingAdds;
	private Set<Polygon> pendingRemoves;

	// Legend that is displayed across the screen
	private String legend;

	// Title for instructions and high score, respectively
	private String instructions, highScore;

	// Title for name and scores under high score title
	private String titleScore, titleName;

	// Holds information about the name and score
	private ArrayList<Scores> scoreList;

	// Text that displays actual instructions
	private ArrayList<String> body;

	// Stores the stars
	private HashSet<Star> stars;


	/**
	 * Creates an empty screen
	 * @throws IOException 
	 */
	public Screen () throws IOException {
		participants = new LinkedList<Polygon>();
		listeners = new HashSet<CollisionListener>();
		pendingAdds = new HashSet<Polygon>();
		pendingRemoves = new HashSet<Polygon>();
		legend = "";
		instructions = "";
		highScore = "";
		titleScore = "";
		titleName = "";
		scoreList = new ArrayList<Scores>();
		body = new ArrayList<String>();
		setPreferredSize(new Dimension(SIZE, SIZE));
		setMinimumSize(new Dimension(SIZE, SIZE));
		setBackground(Color.black);
		setForeground(Color.white);
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 120));
		setFocusable(true);

		// Create the stars
		stars = new HashSet<Star>();
		createStars();
	}


	/**
	 * Add a participant to the game
	 */
	public void addParticipant (Polygon p) {
		pendingAdds.add(p);
	}


	/**
	 * Remove a participant from the game
	 */
	public void removeParticipant(Polygon p) {
		pendingRemoves.add(p);	
	}


	/**
	 * Set the legend
	 */
	public void setLegend (String legend) {
		this.legend = legend;
	}

	/**
	 * Sets the title for instructions
	 * @param ins
	 */
	public void setInstruction (String ins) {
		instructions = ins;
	}

	/**
	 * Sets the high score title
	 * @param s
	 */
	public void setHSTitle (String s) {
		highScore = s;
	}

	/**
	 * Set the name title under high score
	 * @param s
	 */
	public void setHSName (String s) {
		titleName = s;
	}

	/**
	 * Set the score title under high score
	 * @param s
	 */
	public void setHSScore (String s) {
		titleScore = s;
	}

	/**
	 * Adds text to the body of the 
	 * instructions
	 * @param text
	 */
	public void setBody (String text) {
		body.add(text);
	}

	/**
	 * Adds the scores to the list that will be displayed
	 * @throws IOException
	 */
	public void showList () throws IOException {
		int size = ScoreList.fileToArray().size();
		if (size > 0 && size <= 10) {
			for(Scores s: ScoreList.fileToArray()){
				String name = s.getName();
				int num = s.getScore();
				Scores scores = new Scores(name, num);
				scoreList.add(scores);
			}
		}
		// Only show first 10 high scores
		else if (size > 10){
			for(int i = 0; i < 10; i++) {
				ArrayList<Scores> temp = ScoreList.fileToArray();
				scoreList.add(temp.get(i));
			}
		}
	}

	/**
	 * Paint the participants onto this panel
	 */
	@Override
	public void paintComponent (Graphics g) {

		// Do the default painting 
		super.paintComponent(g);

		// Draw each participant in its proper place, each with it's own color
		for (Polygon e: participants) {
			if (e instanceof Ship){
				g.setColor(SHIP_COLOR);
			}
			else if (e instanceof Asteroid){
				g.setColor(Color.red);
			}
			else if (e instanceof Bullet){
				g.setColor(Color.yellow);
			}
			else if (e instanceof Debris){
				if (e.getSize() == Debris.SMALL){
					g.setColor(Color.yellow);
				}
				else{
					g.setColor(SHIP_COLOR);
				}
			}
			e.draw((Graphics2D) g);
		}

		// Draws the legend across the middle of the panel
		g.setColor(Color.white);
		int size = g.getFontMetrics().stringWidth(legend);
		g.drawString(legend, (SIZE - size)/2, SIZE/2);

		// Draws the high score title across the top center of screen
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 65));
		size = g.getFontMetrics().stringWidth(highScore);
		g.drawString(highScore, (SIZE - size)/2, SIZE/8);

		// Represents the y-position of the text
		int yPos = (SIZE/5);	

		// Draws the name and score titles under high scores
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		size = g.getFontMetrics().stringWidth(legend);
		g.drawString(titleName, (SIZE - size)/3, yPos);
		g.drawString(titleScore, SIZE/2, yPos);

		yPos = (SIZE/5) + 18;					
		for (Scores s: scoreList) {
			// Draws the name and score of each player in the high score list
			g.drawString(s.getName(), (SIZE - size)/3, yPos);
			g.drawString((""+s.getScore()), SIZE/2, yPos);
			yPos += 18;
		}

		// Draws the title of instructions in the top right corner
		g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 35));
		size = g.getFontMetrics().stringWidth(instructions);
		g.drawString(instructions, (SIZE-size)/16, SIZE/16);

		// Draws the actual instructions below the instructions title
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		size = g.getFontMetrics().stringWidth(instructions);

		yPos = SIZE/10;

		// Loop to create a new line for each instruction
		for(int i = 0; i <  body.size(); i++) {
			g.drawString(body.get(i), (SIZE-size)/12, yPos);
			yPos += 25;
		}

		// Draws the stars in the heavens
		for (Star s: stars){
			g.drawOval(s.getPosX(), s.getPosY(), 1, 1);
		}

	}

	/**
	 * Creates the stars
	 */
	private void createStars(){
		for (int i = 0; i < STAR_COUNT; i++){
			stars.add(new Star());
		}
	}

	/**
	 * Clear the screen so that nothing is displayed
	 */
	public void clear () {
		pendingRemoves.clear();
		pendingAdds.clear();
		participants.clear();
		legend = "";
		instructions = "";
		highScore = "";
		titleScore = "";
		titleName = "";
		scoreList.clear();
		body.clear();
	}


	/**
	 * Records a new listener
	 */
	public void addCollisionListener (CollisionListener listener) {
		listeners.add(listener);
	}


	/**
	 * Removes an existing listener.
	 */
	public void removeCollisionListener (CollisionListener listener) {
		listeners.remove(listener);
	}


	/**
	 * Compares each pair of elements to detect collisions, then notifies
	 * all listeners of any found. 
	 */
	private void checkForCollisions () {
		for (Polygon p1: participants) {
			Iterator<Polygon> iter = participants.descendingIterator();
			while (iter.hasNext()) {
				Polygon p2 = iter.next();
				if (p2 == p1) break;
				if (pendingRemoves.contains(p1)) break;
				if (pendingRemoves.contains(p2)) break;
				if (p1.overlaps(p2)) {
					for (CollisionListener listener: listeners) {
						listener.collidedWith(p1, p2);
					}
				}
			}
		}
	}


	/**
	 * Completes any adds and removes that have been requested.  
	 */
	private void completeAddsAndRemoves () {

		// Note: These updates are saved up and done later to avoid modifying
		// the participants list while it is being iterated over
		for (Polygon p: pendingAdds) {
			participants.add(p);
		}
		pendingAdds.clear();
		for (Polygon p: pendingRemoves) {
			participants.remove(p);
		}
		pendingRemoves.clear();
	}


	/**
	 * Called when it is time to update the screen display.  This is what
	 * drives the animation.
	 */
	public void refresh () {
		completeAddsAndRemoves();
		for (Polygon p: participants) {
			p.move();
		}
		checkForCollisions();
		repaint();
	}

}
