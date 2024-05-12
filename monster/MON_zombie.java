package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_zombie extends Entity{

	public MON_zombie(GamePanel gp) {
		super(gp);
		
		type = 2;
		name = "zombie";
		direction = "down";
		speed = 1;
		
		getImage();
	}
	
	public void getImage() {
		
		back1 = setup("/monster/b1");
        back2 = setup("/monster/b2");
        front1 = setup("/monster/f1");
        front2 = setup("/monster/f2");
        left1 = setup("/monster/l1");
        left2 = setup("/monster/l2");
        right1 = setup("/monster/r1");
        right2 = setup("/monster/r2");
	}
	
	public void setAction() {
		
actionLockCounter ++;
		
		if(actionLockCounter == 120) {
			
			Random random  = new Random();
			int i = random.nextInt(100)+1;
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
	}

		
}
