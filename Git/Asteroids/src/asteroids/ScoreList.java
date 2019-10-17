package asteroids;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import static asteroids.Constants.*;

/**
 * An ArrayList that contains score objects.
 * Has the ability to create an ArrayList by
 * reading from a text file.  
 * @authors Greg Anderson and Umair Naveed
 */

public class ScoreList {

	// Array that will hold each score element
	private static ArrayList<Scores> scoreList;

	public ScoreList() {
	}
	
	/**
	 * A scanner reads through a file and adds a name with a 
	 * corresponding score to a Scores object. Each Scores 
	 * object is added to an array.  The array is sorted by
	 * scores in descending order.
	 * @return - Returns an array with Scores objects inside
	 * @throws IOException 
	 */
	public static ArrayList<Scores> fileToArray() throws IOException {
		
			// Create a new file 
			File f = new File(FILE_NAME);
			
			// Creates the file if it does not exist
			if(f.exists()) {
				f.createNewFile();
			}

		scoreList = new ArrayList<Scores>();
		Scanner s = new Scanner(f);

		// Scans the data file and adds names and scores to the array
		while(s.hasNext()) {
			String line = s.nextLine();
			Scanner sLine = new Scanner(line);
			while(sLine.hasNext()) {
				String name = sLine.next();
				sLine.useDelimiter("\t");
				int score = sLine.nextInt();
				Scores scr = new Scores(name, score);
				scoreList.add(scr);
			}
			sLine.close();
		}
		s.close();
		
		// Sorts the array list by score in descending order.
		Collections.sort(scoreList, Scores.COMPARE_BY_SCORES);
		return scoreList;
	}
	
	/**
	 * Adds a score object to the array.
	 * @param score
	 */
	public void addTo (Scores score) {
		scoreList.add(score);
	}
}