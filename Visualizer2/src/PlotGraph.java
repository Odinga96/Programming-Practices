
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class PlotGraph extends JPanel {
    private final int height = 325;
    private final int width = 900;
   // private Graphics g;
    public PlotGraph(){init();}


    private void init(){

        setLayout(null);
        
        setSize(width+20,height+100);
        JLabel labelX = new JLabel("Time [s]");
        JLabel labelY = new JLabel("Volume [bytes]");
        labelY.setBounds(0, 0, 100, 100);
        labelX.setBounds(width/2, height-30, width, 30);

        setBackground(Color.WHITE);
        add(labelX);
        add(labelY);
        
        
    }
    @Override
    public void paintComponent(Graphics g){
      
//        setLayout(null);

        setLocation(0,100);
        setSize(width,height);

//        JLabel labelX = new JLabel("Time [s]");
////        JLabel labelY = new JLabel("Volume [bytes]");
//
//        labelY.setBounds(0, 0, 100, 100);
//
//       labelX.setBounds(width/2, 250, 100, 100);
//        setBackground(Color.CYAN);

//        add(labelX); add(labelY);

        g.drawLine(100, 10, 100, height);
        g.drawLine(90, height-10, width, height-10);
        
        int y1 = height-50, y2=y1+10,  x1=100, x2=x1;

        for(int i =0; i <= 600; i+=50){

            JLabel mark = new JLabel(""+i);
            mark.setBounds(x2, y2+10, 30, 30);
            add(mark);
            g.drawLine(x1, y1, x2, y2);
            x2+=50;x1=x2;
        }
        
        
    }
}
