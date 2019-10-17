import javax.swing.*;
import java.awt.*;
//import org.math.plot.*;

public class A2HelpQ2 extends JFrame {
	double[] y ={2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0, 22.0, 24.0, 26.0, 28.0, 30.0, 32.0, 34.0, 36.0, 38.0, 40.0, 42.0};
	double[] x = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 60.0, 1500.0, 643.0, 643.0, 0.0};
    /** main method for A2
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new A2HelpQ2();
            }
        });
    }
    public A2HelpQ2() {
		super("A2");
		//complete this
		//create a new Plot2DPanel

		//Create a plot

		//Add the Plot2DPanel to the center of the contentPane of the JFrame


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setVisible(true);
	}
}