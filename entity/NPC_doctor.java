package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_doctor extends Entity{

	public NPC_doctor(GamePanel gp) {
		
		super(gp);
		
		direction = "down";
		speed = 1;
		getImage();
		setDialogue();
	}
	
	public void getImage(){
        
        back1 = setup("/npc/db1");
        back2 = setup("/npc/db2");
        front1 = setup("/npc/df1");
        front2 = setup("/npc/df2");
        left1 = setup("/npc/dl1");
        left2 = setup("/npc/dl2");
        right1 = setup("/npc/dr1");
        right2 = setup("/npc/dr2");
    }
    
	public void setDialogue() {
		
		dialogue[0] = "Sir, I'm sorry to tell you that \nyour wife has Covid-19";
		dialogue[1] = "It's been a while since the government \ntalked about how the doctors are \n researching about the vaccine...";
		dialogue[2] = "So, to save your wife, Can you help me \nwith everything so that it can boost her recovery?";
		dialogue[3] = "Can you find me some vaccine? \nIf you can find 3 vaccines and syringe, \nI will combine it and help your wife...";
		dialogue[4] = "Thank you, I'll wait until you come back";
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
