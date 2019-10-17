package main.level3;


import main.level1.ActionPane;
import main.level1.GameObject;
import main.level2.Projectile;
import main.level6.TheHord;

public class Invader extends GameObject {
	
	private static final int POINT_VALUE = 10;
	protected static final double FIRING_FREQUENCY = 0.01;
	 
	private int leftWall = 0;
	private int rightWall = 0;
	private int step = 0;
	private int advanceTime = 1000;
	
	public Invader(ActionPane stage) {
		super(stage);
		
		if (((int)(Math.random()*10))%2 == 0) 
			sprites = new String[]{"invader1.gif", "invader2.gif"};
		else 
			sprites = new String[]{"invader3.gif", "invader4.gif"};
		
		frame = 0;
		frameSpeed = 50;
		actorSpeed = 100;
		width = 20;
		height = 20;
		posX = ActionPane.WIDTH/2;
		posY = ActionPane.HEIGHT/2;
	}




	public void fire() {
		Projectile shot = new Projectile(stage);
		shot.setX(posX + width/2);
		shot.setY(posY + shot.getHeight());
		stage.gameObjects.add(shot);
	}
	
	public void act() {

		super.act();
		if (Math.random() < FIRING_FREQUENCY) {
			if (Math.random() < FIRING_FREQUENCY)
				fire();
		}
		
		updateXSpeed();
		updateYSpeed();
	}
		
	public void setLeftWall(int leftWall) {
		this.leftWall = leftWall;
	}
	
	public void setRightWall(int rightWall) {
		this.rightWall = rightWall;
	}
	
	private void updateXSpeed() {
		if (time % actorSpeed == 0) {
			posX += getVx();
			if (posX < leftWall || posX > rightWall) setVx(-getVx());
		}
	}
	
	private void updateYSpeed() {
		step++;
		if (step == advanceTime) {
			posY += height;
			step = 0;
		}	

		if (posY == stage.getHeight()) 
			stage.endGame();
	}

	public void collision(GameObject a) {
		if (a instanceof Projectile)
			return;
		
		playSound("explosion.wav");
		if (a instanceof TheHord)
			setMarkedForRemoval(true);
	}
	
	public int getPointValue() {
		return Invader.POINT_VALUE;
	}	
}
