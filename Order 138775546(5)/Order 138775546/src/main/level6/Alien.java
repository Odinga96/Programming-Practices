package main.level6;


import main.level1.ActionPane;
import main.level1.GameObject;

public class Alien extends GameObject {

	private ActionPane actionPane;
	private static final int POINT_VALUE = 50;
	
	public Alien(ActionPane stage) {
		super(stage);		
		sprites = new String[]{"ufo0.gif","ufo1.gif","ufo2.gif","ufo3.gif","ufo4.gif"};
		frameSpeed = 100;
		width = 30;
		height = 17;
		posX = ActionPane.WIDTH/2;
		posY = ActionPane.HEIGHT/2;
		setVx(1);
		setVy(0);
	}
	
	public void move() {
		super.move();
		updateXSpeed();
		updateYSpeed();
	}
		
	private void updateXSpeed() {
		posX += getVx();
		if (posX > stage.getWidth()) setMarkedForRemoval(true);		
	}
	
	private void updateYSpeed() {
		
	}	
	
	public void collision(GameObject a) {
		if (a instanceof TheHord)
			setMarkedForRemoval(true);
	}
	
	public int getPointValue() {
		return Alien.POINT_VALUE;
	}
	
}
