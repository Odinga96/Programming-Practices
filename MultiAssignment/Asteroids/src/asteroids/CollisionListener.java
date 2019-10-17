package asteroids;

/**
 * Must be implemented by objects wishing to receive notifications of
 * collisions.
 * @author Joe Zachary
 */
public interface CollisionListener {
	
	/**
	 * Reports that the two participants have collided
	 */
	public void collidedWith (Polygon p1, Polygon p2);
	
}
