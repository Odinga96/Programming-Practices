package main.level6;


import main.level1.ActionPane;
import main.level1.GameObject;

public class TheHord extends GameObject {

	protected int bulletSpeed = 2;  
	protected boolean up = true;
	
	public TheHord(ActionPane stage) {
		super(stage);
		width = 10;
		height = 15;
		sprites = new String[]{"shot1.gif","shot2.gif"};
	}

	public void act() {
		super.move();
		if (up)
			posY -= bulletSpeed;
		else
			posY += bulletSpeed;
		
		if (posY < 0)
			setMarkedForRemoval(true);
	}
	
	public void collision(GameObject a) {
		setMarkedForRemoval(true);
	}
}
