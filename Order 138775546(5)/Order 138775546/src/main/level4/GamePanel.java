package main.level4;

import main.level1.ActionPane;
import main.level1.CmdCenter;
import main.level2.ImageView;
import main.level1.GameObject;
import main.level3.Invader;
import main.level3.SpaceShip;
import main.level6.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

/*
* This is the heart of the application. It will include all the major
* controllers objects and Keyboard Listener*/
public class   GamePanel extends ActionPane implements KeyListener {


    private SpaceShip spaceShip;
//
    private static final long serialVersionUID = 1L;


    private CmdCenter keyPressedHandler;
    private CmdCenter keyReleasedHandler;

    public long usedTime;//time taken per controllers step
    public BufferStrategy strategy;	 //double buffering strategy

    private BufferedImage background, backgroundTile; //background cache
    private int backgroundY; //background cache position

    public GamePanel() {
        //init the UI
        setBounds(0,0, ActionPane.WIDTH, ActionPane.HEIGHT);
        setBackground(Color.BLACK);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(ActionPane.WIDTH, ActionPane.HEIGHT));
        panel.setLayout(null);

        panel.add(this);

        JFrame frame = new JFrame("Sergetech Games");
        frame.add(panel);

        frame.setBounds(0,0, ActionPane.WIDTH, ActionPane.HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);

        //cleanup resources on exit
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ImageView.getInstance().cleanup();
                System.exit(0);
            }
        });


        addKeyListener(this);

        //create a double buffer
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        requestFocus();
        initWorld();

        keyPressedHandler = new CmdCenter(this, spaceShip);
        keyPressedHandler.action = CmdCenter.Action.PRESS;
        keyReleasedHandler = new CmdCenter(this, spaceShip);
        keyReleasedHandler.action = CmdCenter.Action.RELESEASE;
    }

    public void addInvaders() {
       Invader invader = new Invader(this);
        //padding between units/rows
        int xPad = invader.getWidth() + 15;
        int yPad = invader.getHeight() + 20;
        //number of units per row
        int unitsPerRow = ActionPane.WIDTH/(xPad) - 1;
        int rows = (ActionPane.HEIGHT/yPad) - 3; //number of invader rows

        //create and add invaders for each row
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < unitsPerRow -1; j++) {
                Invader inv = new Invader(this);
                inv.setX((j + 1)*xPad);
                inv.setY((i + 1)*yPad);
                inv.setVx(10);
                //set movement boundaries for each invader
                inv.setLeftWall((j + 1)*xPad - 20);
                inv.setRightWall((j + 1)*xPad + 20);
                gameObjects.add(inv);
            }
        }
    }

    public void initWorld() {
        gameObjects = new ArrayList<GameObject>();
        gameOver = false;
        gameWon = false;
        //add a spaceShip
        spaceShip = new SpaceShip(this);
        spaceShip.setX(ActionPane.WIDTH/2 - spaceShip.getWidth()/2);
        spaceShip.setY(ActionPane.HEIGHT - 50);
        spaceShip.setVx(10);

        //load cached background
        backgroundTile = ImageView.getInstance().getSprite("space.gif");
        background = ImageView.createCompatible(
                WIDTH, HEIGHT+ backgroundTile.getHeight(),
                Transparency.OPAQUE);
        Graphics2D g = (Graphics2D)background.getGraphics();
        g.setPaint( new TexturePaint( backgroundTile,new Rectangle(0,0,backgroundTile.getWidth(),backgroundTile.getHeight())));
        g.fillRect(0,0,background.getWidth(),background.getHeight());
        backgroundY = backgroundTile.getHeight();

        addInvaders();
    }

    public void paintWorld() {

        //get the graphics from the buffer
        Graphics g = strategy.getDrawGraphics();
        //init image to background
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        //load subimage from the background
        g.drawImage( background,0,0, ActionPane.WIDTH, ActionPane.HEIGHT,0,backgroundY, ActionPane.WIDTH,backgroundY+ ActionPane.HEIGHT,this);

        //paint the gameObjects
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject gameObject = gameObjects.get(i);
            gameObject.paint(g);
        }

        spaceShip.paint(g);
        paintScore(g);
        paintFPS(g);
        //swap buffer
        strategy.show();
    }

    public void paintGameOver() {
        Graphics g = strategy.getDrawGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        paintScore(g);

        //about 310 pixels wide
        g.setFont(new Font("Arial",Font.BOLD,50));
        g.setColor(Color.RED);
        int xPos = getWidth()/2 - 155;
        g.drawString("GAME OVER",(xPos < 0 ? 0 : xPos),getHeight()/2);

        xPos += 30;
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("ENTER: try again",(xPos < 0 ? 0 : xPos),getHeight()/2 + 50);

        strategy.show();
    }

    public void paintGameWon() {
        Graphics g = strategy.getDrawGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        paintScore(g);

        //about 300 pixels wide
        g.setFont(new Font("Arial",Font.BOLD,50));
        g.setColor(Color.RED);
        int xPos = getWidth()/2 - 145;
        g.drawString("GAME WON",(xPos < 0 ? 0 : xPos),getHeight()/2);

        xPos += 20;
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("ENTER: try again",(xPos < 0 ? 0 : xPos),getHeight()/2 + 50);

        strategy.show();
    }

    public void paintFPS(Graphics g) {
        g.setColor(Color.RED);
        if (usedTime > 0)
            g.drawString(String.valueOf(1000/usedTime)+" fps",0, ActionPane.HEIGHT-50);
        else
            g.drawString("--- fps",0, ActionPane.HEIGHT-50);
    }

    public void paintScore(Graphics g) {
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.setColor(Color.green);
        g.drawString("Score: ",20,20);
        g.setColor(Color.red);
        g.drawString("" + spaceShip.getScore(), 100, 20);
    }

    public void paint(Graphics g) {}

    public void updateWorld() {

        int i = 0;
        int numInvaders = 0;
        while (i < gameObjects.size()) {
            GameObject gameObject = gameObjects.get(i);
            if (gameObject instanceof TheHord)
                checkCollision(gameObject);

            if (gameObject.isMarkedForRemoval()) {
                spaceShip.updateScore(gameObject.getPointValue());
                gameObjects.remove(i);
            }
            else {
                //check how many invaders are remaining
                //0 means spaceShip won the match
                if(gameObject instanceof Invader)
                    numInvaders++;
                gameObject.act();
                i++;
            }
        }
        if (numInvaders == 0)
            super.gameWon = true;

        checkCollision(spaceShip);
        spaceShip.act();
    }

    private void checkCollision(GameObject gameObject) {

        Rectangle actorBounds = gameObject.getBounds();
        for (int i = 0; i < gameObjects.size(); i ++) {
            GameObject otherGameObject = gameObjects.get(i);
            if (null == otherGameObject || gameObject.equals(otherGameObject)) continue;
            if (actorBounds.intersects(otherGameObject.getBounds())) {
                gameObject.collision(otherGameObject);
                otherGameObject.collision(gameObject);
            }
        }
    }

    public void loopSound(final String name) {
        new Thread(new Runnable() {
            public void run() {
                ImageView.getInstance().getSound(name).loop();
            }
        }).start();
    }


    public void game() {
        loopSound("music.wav");
        usedTime= 0;
        while(isVisible()) {
            long startTime = System.currentTimeMillis();

            backgroundY--;
            if (backgroundY < 0)
                backgroundY = backgroundTile.getHeight();

            if (super.gameOver) {
                paintGameOver();
                break;
            }
            else if (super.gameWon) {
                paintGameWon();
                break;
            }

            int random = (int)(Math.random()*1000);
            if (random == 700) {
                GameObject ufo = new Alien(this);
                ufo.setX(0);
                ufo.setY(20);
                ufo.setVx(1);
                gameObjects.add(ufo);
            }

            updateWorld();
            paintWorld();

            usedTime = System.currentTimeMillis() - startTime;

            //calculate sleep time
            if (usedTime == 0) usedTime = 1;
            int timeDiff = (int) ((1000/usedTime) - DESIRED_FPS);
            if (timeDiff > 0) {
                try {
                    Thread.sleep(timeDiff/100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        keyPressedHandler.handleInput(e);
    }

    public void keyReleased(KeyEvent e) {
        keyReleasedHandler.handleInput(e);
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        GamePanel inv = new GamePanel();
        inv.game();
    }

}
