package asteroids;

import java.awt.Shape;
import java.awt.geom.*;
import java.util.Random;

/**
 * Represents debris.
 * @author Greg Anderson and Umair Naveed
 */

public class Debris extends Polygon {
	
	// Sizes of debris
	public final static int SMALL = 0;
	public final static int MEDIUM = 1;
	public final static int LARGE = 2;
	
	// Outline of the debris
	private Shape outline;
	
	// Represents the size of the debris, either SMALL, MEDIUM, or LARGE
	private int size;
	
	public Debris(double tipX, double tipY, int size){

		setPosition(tipX, tipY);
		
		this.size = size;
		
		// The outline of the debris
		outline = createDebris(size);

	}
	
	/**
	 * Returns the debris of given size, size being the type.
	 */
	private Shape createDebris(int size){
		
		// This will contain the outline
		Path2D.Double poly = new Path2D.Double();
		
		// Random rotation factor
		Random random = new Random();
		int factor;
		if (random.nextBoolean()){
			factor = 1;
		}
		else{
			factor = -1;
		}

		// Create debris according to size
		if (size == SMALL){
			poly.moveTo(0, 0);
			poly.lineTo(3, random.nextInt(3)*factor);
			poly.closePath();
		}
		else if (size == MEDIUM){
			poly.moveTo(0, 0);
			poly.lineTo(5,random.nextInt(5)*factor);
			poly.closePath();
		}
		else{
			poly.moveTo(0, 0);
			poly.lineTo(15, random.nextInt(25)*factor);
			poly.closePath();
		}
		
		// Return the outline
		return poly;
	}
	
	/**
	 * Returns the size of the debris
	 */
	@Override
	public int getSize(){
		return size;
	}

	/**
	 * Returns the outline of the debris
	 */
	@Override
	Shape getOutline() {
		return outline;
	}

}
