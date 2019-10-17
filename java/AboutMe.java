
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AboutMe extends JFrame{

    public static void main(String[] args) {
        AboutMe am = new AboutMe();
        am.setUp();
    }
    
    public void paint(Graphics g){
        super.paint(g);
    }
    
    public void setUp(){
        this.setSize(700, 700);
        this.setLayout(null);
        JPanel base = new JPanel();
        base.setLayout(null);
        base.setBackground(Color.BLACK);
        Font titleFont = new Font("Times Roman", Font.PLAIN, 22);
        Font font1 = new Font("Times Roman", Font.PLAIN, 18);
        Font font2 = new Font("Times Roman", Font.PLAIN, 18);
        base.setBounds(0, 0, 700, 700);
        this.add(base);
        JPanel left = new JPanel();
        left.setLayout(null);
        left.setBounds(5, 5, 390, 650);
        left.setBackground(Color.DARK_GRAY);
        ImageIcon image = new ImageIcon("examples.png");
        JLabel img = new JLabel(image);
        img.setBounds(5, 5, 380, 640);
        left.add(img);
        base.add(left);
        JPanel right = new JPanel();
        right.setBackground(Color.CYAN);
        right.setLayout(null);
        right.setBounds(400, 5, 280, 650);
        JLabel nameTitle = new JLabel("My Full Name");
        nameTitle.setForeground(Color.magenta);
        nameTitle.setFont(titleFont);
        nameTitle.setBounds(10, 10, 200, 30);
        right.add(nameTitle);
        
        JLabel name = new JLabel("Insert name here");
        name.setFont(font1);
        name.setForeground(Color.blue);
        name.setBounds(10, 50, 200, 30);
        right.add(name);
        
        JLabel prefTitle = new JLabel("My Prefered Name");
        prefTitle.setForeground(Color.magenta);
        prefTitle.setFont(titleFont);
        prefTitle.setBounds(10, 90, 200, 30);
        right.add(prefTitle);
        
        JLabel pref = new JLabel("Insert prefered name");
        pref.setFont(font1);
        pref.setForeground(Color.blue);
        pref.setBounds(10, 130, 200, 30);
        right.add(pref);
        
        JLabel favTitle = new JLabel("My Favourite Food is");
        favTitle.setForeground(Color.magenta);
        favTitle.setFont(titleFont);
        favTitle.setBounds(10, 170, 250, 30);
        right.add(favTitle);
        
        JLabel fav = new JLabel("Buffalo Wings");
        fav.setFont(font1);
        fav.setForeground(Color.blue);
        fav.setBounds(10, 210, 200, 30);
        right.add(fav);
        
        JPanel rightBottom = new JPanel();
        rightBottom.setLayout(null);
        rightBottom.setBackground(Color.ORANGE);
        rightBottom.setBounds(5, 300, 270, 345);
        JLabel funFactsTitle = new JLabel("Fun Facts");
        funFactsTitle.setForeground(Color.magenta);
        funFactsTitle.setFont(titleFont);
        funFactsTitle.setBounds(10, 10, 200, 30);
        rightBottom.add(funFactsTitle);
        
        JLabel ff1 = new JLabel("I am a triplet");
        ff1.setFont(font2);
        ff1.setForeground(Color.red);
        ff1.setBounds(10, 40, 200, 30);
        rightBottom.add(ff1);
        
        JLabel ff2 = new JLabel("I was born in Montana");
        ff2.setFont(font2);
        ff2.setForeground(Color.red);
        ff2.setBounds(10, 70, 200, 30);
        rightBottom.add(ff2);
        
        JLabel ff3 = new JLabel("I love to snowboard");
        ff3.setFont(font2);
        ff3.setForeground(Color.red);
        ff3.setBounds(10, 100, 200, 30);
        rightBottom.add(ff3);
        
        JLabel ff4 = new JLabel("I ride a motorcycle");
        ff4.setFont(font2);
        ff4.setForeground(Color.red);
        ff4.setBounds(10, 130, 200, 30);
        rightBottom.add(ff4);
        base.add(right);
        right.add(rightBottom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        

    }
}
