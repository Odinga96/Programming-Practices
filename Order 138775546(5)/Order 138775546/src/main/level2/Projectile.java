package main.level2;


import main.level1.ActionPane;
import main.level1.GameObject;
import main.level3.Invader;
import main.level6.TheHord;

public class Projectile extends TheHord {

	public Projectile(ActionPane stage) {
		super(stage);
		super.up = false;
		bulletSpeed = 1;
	}

	public void collision(GameObject a) {
		if (a instanceof Invader)
			return;
		setMarkedForRemoval(true);
	}
	
}
