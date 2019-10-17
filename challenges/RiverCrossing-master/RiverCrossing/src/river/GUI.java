package river;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI extends JPanel implements MouseListener {


    private GameEngine engine; // Model
    private boolean restart = false;

    private Rectangle item0Rectangle=new Rectangle();
    private Rectangle item1Rectangle=new Rectangle();
    private Rectangle item2Rectangle=new Rectangle();
    private Rectangle item3Rectangle=new Rectangle();
    private Rectangle boatRectangle=new Rectangle();

    // ==========================================================
    // Constructor
    // ==========================================================

    public GUI() {

        engine = new FarmerGameEngine();
        addMouseListener(this);
    }


    private static void createAndShowGUI() {

        // Create and set up the window
        JFrame frame = new JFrame("RiverCrossing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane
        GUI newContentPane = new GUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        // Display the window
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(GUI::createAndShowGUI);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        paintItem(g, Item.ITEM_0);
        paintItem(g, Item.ITEM_1);
        paintItem(g, Item.ITEM_2);
        paintItem(g, Item.ITEM_3);
        paintBoat(g);
    }

    private void paintItem(Graphics g, Item item) {
        if (item.equals(Item.ITEM_3)) g.setColor(Color.MAGENTA);
        else g.setColor(Color.CYAN);

    }
    private void paintBoat(Graphics g) {  }
    private Rectangle getItemRectangle(Item item) { ... }
    private Rectangle getBoatRectangle() { ... }

    private void paintRectangle(Graphics g, Color color, String label, Rectangle rect) {
        // similar to paintStringInRectangle but now it creates
        // the rectangle with the specified color first
        // use rect.x, rect.y, rect.width, rect.height to get those values if needed
    }
//    Notice that we have getters for the rectangles. Do we need setters?
//    For the controller, we should have something similar in that the code focuses on those 5 rectangles.
    @Override
    public void mouseClicked(MouseEvent e) {
        if (item0Rectangle.contains(e.getPoint())) {
            /* respond to click of Item.ITEM_0 */
            if      (engine.getItemLocation(Item.ITEM_0) == Location.START){ engine.loadBoat(Item.ITEM_0);}
            else if (engine.getItemLocation(Item.ITEM_0) == Location.FINISH){engine.loadBoat(Item.ITEM_0);}
            else if (engine.getItemLocation(Item.ITEM_0) == Location.BOAT){engine.unloadBoat(Item.ITEM_0);}
        } else if (item1Rectangle.contains(e.getPoint())) {
            /* respond to click of Item.ITEM_1 */
            /* respond to click of Item.ITEM_0 */
            if      (engine.getItemLocation(Item.ITEM_1) == Location.START){ engine.loadBoat(Item.ITEM_1);}
            else if (engine.getItemLocation(Item.ITEM_1) == Location.FINISH){engine.loadBoat(Item.ITEM_1);}
            else if (engine.getItemLocation(Item.ITEM_1) == Location.BOAT){engine.unloadBoat(Item.ITEM_1);}
        } else if (item2Rectangle.contains(e.getPoint())) {
            /* respond to click of Item.ITEM_2 */
            /* respond to click of Item.ITEM_0 */
            if      (engine.getItemLocation(Item.ITEM_2) == Location.START){ engine.loadBoat(Item.ITEM_2);}
            else if (engine.getItemLocation(Item.ITEM_2) == Location.FINISH){engine.loadBoat(Item.ITEM_2);}
            else if (engine.getItemLocation(Item.ITEM_2) == Location.BOAT){engine.unloadBoat(Item.ITEM_2);}
        } else if (item3Rectangle.contains(e.getPoint())) {
            /* respond to click of Item.ITEM_3 */
            /* respond to click of Item.ITEM_0 */
            if      (engine.getItemLocation(Item.ITEM_3) == Location.START){ engine.loadBoat(Item.ITEM_3);}
            else if (engine.getItemLocation(Item.ITEM_3) == Location.FINISH){engine.loadBoat(Item.ITEM_3);}
            else if (engine.getItemLocation(Item.ITEM_3) == Location.BOAT){engine.unloadBoat(Item.ITEM_3);}
        } else if (boatRectangle.contains(e.getPoint())) {
            /* respond to click of boat */
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
