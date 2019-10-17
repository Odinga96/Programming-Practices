package asteroids;

import java.awt.Shape;
import java.awt.geom.*;

/**
 * Represents bullets
 * @author Greg Anderson and Umair Naveed
 */

public class Bullet extends Polygon {
	
	private Shape outline;
	
	/**
	 * Creates a bullet starting at the tip of the ship
	 */
	public Bullet(double tipX, double tipY){
		Path2D.Double poly = new Path2D.Double();

		poly.moveTo(tipX, tipY);
		poly.lineTo(tipX+1, tipY);
		poly.lineTo(tipX+1, tipY+1);
		poly.lineTo(tipX, tipY+1);
		poly.closePath();
		outline = poly;
	}

	/**
	 * Returns the outline of the ship.
	 */
	@Override
	Shape getOutline() {
		return outline;
	}

}
