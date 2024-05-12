package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_lover extends Entity{
	
	public NPC_lover(GamePanel gp) {
		
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
	}
	
	public void getImage(){
        
        back1 = setup("/npc/lb1");
        back2 = setup("/npc/lb2");
        front1 = setup("/npc/lf1");
        front2 = setup("/npc/lf2");
        left1 = setup("/npc/ll1");
        left2 = setup("/npc/ll2");
        right1 = setup("/npc/lr1");
        right2 = setup("/npc/lr2");
    }
	
	public void setAction() {
		
		actionLockCounter++;
		
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
	public void speak() {
		
		super.speak();
	}
    
}
