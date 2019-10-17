package asteroids;

import java.io.*;
import java.util.Comparator;
import java.util.Scanner;
import static asteroids.Constants.*;

/**
 * An object that holds names with corresponding
 * scores. 
 * @authors Greg Anderson and Umair Naveed
 */
public class Scores implements Serializable {

	private static final long serialVersionUID = -6542612592858405378L;
	
	// Holds the name of the user
	private String name;
	
	// Holds the user's score
	private int score;
	
	public Scores (String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public Scores () {
	}
	
	/**
	 * Returns the name from the score object
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the score from the score object 
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Adds a name and score to the file. No 
	 * duplicate names are allowed. The higher of
	 * two scores are added if they have the same
	 * name.
	 * @throws IOException 
	 */
	public void addScores(String name1, int score1) throws IOException {
		// Holds the value which determines if a new score will be added
		// 0 represents no change.  
		int state = 0;
		
		// Compares each name and score from the array to the one that
		// was just recently added via the current method.
		for(Scores s: ScoreList.fileToArray()) {
			String name2 = s.getName();
			int score2 = s.getScore();
			if (name1.equals(name2) && score1 < score2) {
				state = 1;
			}
			else if (name1.equals(name2) && score1 > score2) {
				state = 2;
			}
		}
		
		// 0 represents a new name and score, this it will be 
		// appended to the data file.
		if (state == 0) {
			writeTo(name1, score1);
		}
		
		// 2 represents an existing name that reached a higher score,
		// the new score will overrite the old score. 
		else if (state == 2) {
			
			// Represents whether the file has been overwritten or not.
			// Prevents file from being overwritten multiple times. 
			boolean override = false;
			Scanner s = new Scanner(new File(FILE_NAME));
			
			while(s.hasNextLine()){
				String a = s.nextLine();
				String[] t = a.split("\t");
				
				// Writes over the file for the first time
				if(!t[0].equals(name1) && !override) {
					overwrite(t[0], Integer.parseInt(t[1]));
					override = true;
				}
				
				// Writes over the file for the first time
				else if (t[0].equals(name1) && !override) {
					overwrite(name1, score1);
					override = true;
				}
				
				// Appends information to the file
				else if (!t[0].equals(name1) && override) {
					writeTo(t[0], Integer.parseInt(t[1]));
				}
				
				// Appends information to the file
				else if (t[0].equals(name1) && override){
					writeTo(name1, score1);
				}
			}
			s.close();
		}
		
	}
	
	/**
	 * Appends a new name and score to the data file.
	 * @param name, score
	 * @throws IOException
	 */
	private void writeTo (String name, int score) throws IOException {
		FileWriter out = new FileWriter(FILE_NAME, true);
		BufferedWriter writer = new BufferedWriter(out);
		writer.newLine();
		writer.write(name + "\t" + score);
		writer.close();
	}
	
	/**
	 * Writes over the file with new name and data information.
	 * @param name, score
	 * @throws IOException
	 */
	private void overwrite (String name, int score) throws IOException {
		FileWriter out = new FileWriter(FILE_NAME, false);
		BufferedWriter writer = new BufferedWriter(out);
		writer.write(name + "\t" + score);
		writer.close();
	}

	/**
	 * Sort method which organizes the score object by scores.
	 */
	public static Comparator<Scores> COMPARE_BY_SCORES = new Comparator<Scores>() { 
		public int compare(Scores score1, Scores score2) {
			int s1 = score1.getScore();
			int s2 = score2.getScore();
			if(s1 > s2) {
				return -1;
			}
			else if (s1 < s2) {
				return 1;
			}
			else {
				return 0;
			}
		} 
	};
}