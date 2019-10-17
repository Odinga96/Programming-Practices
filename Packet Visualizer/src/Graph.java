import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class Graph extends JPanel {

    private final int height = 500;
    private final int width = 1100;


    public static ArrayList<Host> hosts_tocheck=null;


    public Graph() {
        removeAll();
        init();

    }

    private void init(){

        setLayout(null);

//        setSize(width+20,height+100);
        JLabel labelX = new JLabel("Time [s]");
        JLabel labelY = new JLabel("Volume [bytes]");
        labelY.setFont(new Font("",Font.PLAIN,10));
        labelY.setBounds(0, height/2, 90, 30);
        labelX.setBounds(width/2, height-25, width, 30);


        add(labelX); add(labelY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        setLocation(10,100);
        setSize(width,height);
        setBackground(Color.WHITE);


        g.setColor(Color.green);
        g.drawLine(120, 4, 120, height-60);
        g.drawLine(110,  height-70, width-100, height-70);

        Graphics2D draw=(Graphics2D)g;
        draw.setStroke(new BasicStroke(3));
        draw.drawLine(113, 14, 120, 4);
        draw.drawLine(120, 4, 127, 14);

        draw.drawLine(width-110, height-77, width-100, height-70);
        draw.drawLine(width-110, height-63, width-100, height-70);


        //Drawing the default x axis labels
        int y1 = height-75, y2=y1+10,  x1=120;

        for(int i =0; i <= 800; i+=50){

            JLabel mark = new JLabel(""+i);
            mark.setBounds(x1-10, y2+4, 30, 30);
            add(mark);

            g.setColor(Color.black);
            g.drawLine(x1, y1, x1, y2);
            x1+=50;
        }
//        Plotting  the y axis
         int yx1=112, Yy1=height-70;

        while(Yy1>0){
            JLabel mark = new JLabel(""+((height-70)-Yy1)*4);
            mark.setBounds(yx1-30, Yy1-10, 40, 25);
            add(mark);

            g.drawLine(yx1+3, Yy1, yx1+10, Yy1);
            Yy1-=50;
        }

        if (hosts_tocheck !=null){

            Graphics2D g2=(Graphics2D) g;


            g2.setStroke(new BasicStroke(1f));
//            g2.setStroke(new BasicStroke(2));
            Path2D path = new Path2D.Double();


              int count=0;
            for (Host host:hosts_tocheck) {

                double xValue=host.getTimeStamp();
                double yValue=(host.getPacket_size()/1500.0)*400;

                g2.setColor(((count++ %2)==0)?Color.ORANGE:Color.cyan);
                g.fillRect((int) (xValue + 120), (int) ((height-70) - yValue), 1, (int) Math.ceil(yValue));


//                }
            }
//





//            for (int i=0; i<hosts_tocheck.size(); i++){
//                path.moveTo();
//            }

        }


    }
}
