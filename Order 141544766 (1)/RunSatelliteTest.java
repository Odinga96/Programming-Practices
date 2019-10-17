import javax.swing.SwingUtilities;

public class RunSatelliteTest implements Runnable{
public void run() {
  SatelliteTest s = new SatelliteTest();
}
public static void main(String[] args) {
  SwingUtilities.invokeLater(new RunSatelliteTest());
}

}
