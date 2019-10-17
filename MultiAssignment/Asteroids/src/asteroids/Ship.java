package asteroids;

import java.awt.Shape;
import java.awt.geom.*;


/**
 * Represents ship objects
 * @author Joe Zachary, Greg Anderson and Umair Naveed
 */
public class Ship extends Polygon {
	
	// Different kinds of ships
	public final static int REGULAR_SHIP = 0;
	public final static int DOUBLE_GUN_SHIP = 1;
	public final static int BORING_SHIP = 2;
	
	// The outline of the ship
	private Shape outline;
	
	// Type of ship
	private int variety;
	
	
	//  Constructs a ship
	public Ship (int variety) {
		this.variety = variety;
		
		Path2D.Double poly = new Path2D.Double();
		
		// Cool ship
		if (variety == REGULAR_SHIP){
			poly.moveTo(0, 0);
			poly.lineTo(-4, -2);
			poly.lineTo(-4, 6);
			poly.lineTo(0, 4);
			poly.lineTo(1, 6);
			poly.lineTo(-8, 16);
			poly.lineTo(2, 16);
			poly.lineTo(6, 12);
			poly.lineTo(16, 2);
			poly.lineTo(6, -8);
			poly.lineTo(2, -12);
			poly.lineTo(-12+4, -12);
			poly.lineTo(-3+4, -2);
			poly.closePath();
		}
		
		// Double gunned ship
		else if (variety == DOUBLE_GUN_SHIP){
			poly.moveTo(8, 0);
			poly.lineTo(0, -8);
			poly.lineTo(10, -8);
			poly.lineTo(10, -10);
			poly.lineTo(-2, -10);
			poly.lineTo(-6, -14);
			poly.lineTo(-16, -14);
			poly.lineTo(-7, -4);
			poly.lineTo(-8, -2);
			poly.lineTo(-12, -4);
			poly.lineTo(-12, 4);
			poly.lineTo(-8, 2);
			poly.lineTo(-7, 4);
			poly.lineTo(-16, 14);
			poly.lineTo(-6, 14);
			poly.lineTo(-2, 10);
			poly.lineTo(10, 10);
			poly.lineTo(10, 8);
			poly.lineTo(0, 8);
			poly.closePath();
		}
		
		// Boring ship
		else{
			poly.moveTo(20, 0);		
			poly.lineTo(-20, 12);
			poly.lineTo(-12, 0);
			poly.lineTo(-20, -12);
			poly.closePath();
		}
		
		outline = poly;
	}
	
	/**
	 * Returns the x-coordinate of the point on the screen where the 
	 * ship's nose is located.
	 */
	public double getXNose () {
		Point2D.Double point;
		if (variety == REGULAR_SHIP){
			point = new Point2D.Double(4, 1);
		}
		else if (variety == DOUBLE_GUN_SHIP){
			point = new Point2D.Double(0, 0);
		}
		else{
			point = new Point2D.Double(20, 0);			
		}
		transformPoint(point);
		return point.getX();
	}
	
	/**
	 * Returns the x-coordinate of the point on the screen where the
	 * ship's nose is located.
	 */
	public double getYNose () {
		Point2D.Double point;
		if (variety == REGULAR_SHIP){
			point = new Point2D.Double(4, 1);
		}
		else if (variety == DOUBLE_GUN_SHIP){
			point = new Point2D.Double(0, 0);
		}
		else{
			point = new Point2D.Double(20, 0);			
		}
		transformPoint(point);
		return point.getY();
	}
	
	
	/**
	 * Returns the outline of the ship.
	 */
	@Override
	protected Shape getOutline () {
		return outline;
	}
	
	/**
	 * Customizes the base move method by imposing friction
	 */
	@Override
	public void move () {
		super.move();
		friction();
	}
	
}