/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
*/
import java.awt.*;
import java.awt.event.*;

class Asteroids extends Game {
  public Asteroids() {
    super("Asteroids!",800,600);
  }


	public void paint(Graphics brush) {
    brush.setColor(Color.black);
    brush.fillRect(0,0,width,height);
  }

	public static void main (String[] args) {
    new Asteroids();
  }
}