package asteroids;

import javax.swing.*;
import java.awt.*;
import static asteroids.Constants.*;
import java.io.IOException;


/**
 * Implements an asteroid game.
 * @author Joe Zachary
 *
 */
public class Game extends JFrame {
	
	/**
	 * Launches the game
	 * @throws IOException 
	 */
	public static void main (String[] args) throws IOException {
		Game a = new Game();
		a.setVisible(true);
	}

	// Labels that will hold information about life count
	public static JLabel lifeCount;
	public static JLabel lifeCount2;
	
	// Label that will hold information about points scored
	public static JLabel score;
	
	
	/**
	 * Lays out the game and creates the controller
	 * @throws IOException 
	 */
	public Game () throws IOException {
		
		// Title at the top
		setTitle(TITLE);
		
		// Default behavior on closing
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// The main playing area and the controller
		Screen screen = new Screen();
		Controller controller = new Controller(this, screen);

		// This panel contains the screen to prevent the screen from being resized
		JPanel screenPanel = new JPanel();
		screenPanel.setLayout(new GridBagLayout());
		screenPanel.add(screen);
		
		// This panel contains buttons and labels
		JPanel controls = new JPanel();
		
		// The button that starts the game
		JButton startGame = new JButton(START_LABEL);
		controls.add(startGame);

		// Labels that show the lives and points scored
		lifeCount = new JLabel();
		lifeCount2 = new JLabel();
		score = new JLabel();

		// Sets invisible border around Lives label
		lifeCount.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
		lifeCount2.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

		// Adds lifeCount and score to the panel
		controls.add(lifeCount);
		controls.add(lifeCount2);
		controls.add(score);
		
		// Organize everything
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(screenPanel, "Center");
		mainPanel.add(controls, "North");
		setContentPane(mainPanel);
		pack();
		
		// Connect the controller to the start button
		startGame.addActionListener(controller);
		
	}	

}