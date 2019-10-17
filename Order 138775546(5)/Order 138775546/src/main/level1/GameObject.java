package main.level1;

import main.level2.ImageView;

import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class GameObject implements Movable {

	private static final int POINT_VALUE = 0;
	protected int vx; 
	protected int vy;
	protected int posX;
	protected int posY;
	protected int height;
	protected int width;
	protected int frame;
	protected int frameSpeed;
	protected int actorSpeed;
	protected int time;
	private boolean markedForRemoval = false;
	protected String[] sprites = null; 
	protected ActionPane stage = null;

	public GameObject(ActionPane canvas) {
		this.stage = canvas;
		frame = 0;
		frameSpeed = 1;
		actorSpeed = 10;
		time = 0;
	}
	
	public void act() {
		updateFrame();
	}
	
	protected void updateFrame() {
		time++;
		if (time % frameSpeed == 0) {
			time = 0;
			frame = (frame + 1) % sprites.length;
		}
	}
	
	public void playSound(final String name) {
		new Thread(new Runnable() {
			public void run() {
				ImageView.getInstance().getSound(name).play();
			}
		}).start();
	}

			
	public void paint(Graphics g) {		
		g.drawImage(ImageView.getInstance().getSprite(sprites[frame]), posX, posY, stage);
	}
	
	public void setX(int posX) {
		this.posX = posX;
	}
	
	public void setY(int posY) {
		this.posY = posY;
	}
	
	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}
	
	protected void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	protected void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}
	
	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVx() {
		return vx;
	}
	
	public void setVy(int vy) {
		this.vy = vy;
	}

	public int getVy() {
		return vy;
	}

	public Rectangle getBounds() {
		return new Rectangle(posX,posY,width, height);
	}
	
	public void collision(GameObject a) {
	}
	
	public void setMarkedForRemoval(boolean markedForRemoval) {
		this.markedForRemoval = markedForRemoval;
	}

	public boolean isMarkedForRemoval() {
		return markedForRemoval;
	}
	
	public int getPointValue() {
		return GameObject.POINT_VALUE;
	}


	@Override
	public void move() {

	}
}
