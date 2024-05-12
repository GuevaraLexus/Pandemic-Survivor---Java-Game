package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    public int hasVax = 0;
    public int hasSyrFull = 0;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
    	super(gp);
    	
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        
        worldX = gp.tileSize * 44 ;
        worldY = gp.tileSize * 7 ;
        speed = 5;
        direction = "down";
        
        //PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }
    
    public void setDefaultPositions() {
    	
    	//MAP 0 POSITION:
    	worldX = gp.tileSize * 13 ;
        worldY = gp.tileSize * 16 ;
        direction = "down";
    }
    
    public void restoreLife() {
    	
    	life = maxLife;
    	invincible = false;
    }
    
    public void getPlayerImage(){
        
        back1 = setup("/player/back1");
        back2 = setup("/player/back2");
        front1 = setup("/player/front1");
        front2 = setup("/player/front2");
        left1 = setup("/player/left1");
        left2 = setup("/player/left2");
        right1 = setup("/player/right1");
        right2 = setup("/player/right2");
    }
    
    public void update(){
        
    	if(keyH.upPressed == true || keyH.downPressed == true || 
    			keyH.leftPressed == true || keyH.rightPressed == true) {
        if(keyH.upPressed == true){
            direction = "up";
        }
        else if(keyH.downPressed == true){
            direction = "down";  
        }
        else if(keyH.leftPressed == true){
            direction = "left";
        }
        else if(keyH.rightPressed == true){
            direction = "right";
        }
        
        //CHECK TILE COLLISION
        collisionOn = false;
        gp.cChecker.checkTile(this);
        
        //CHECK OBJECT COLLISION
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);
        
        //CHECK NPC COLLISION
        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);
        
        //CHECK EVENT
        gp.eHandler.checkEvent();
        gp.keyH.enterPressed = false;
        
        //CHECK MONSTER COLLISION
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        contactMonster(monsterIndex);
        
        //IF COLLISION IS FALSE, PLAYER CAN MOVE
        if(collisionOn == false) {
        
        	switch(direction) {
        	case "up": worldY -= speed; break;
        	case "down": worldY += speed; break;
        	case "left": worldX -= speed; break;
        	case "right": worldX += speed; break;
        	
        	}
        }
        spriteCounter++;
        if(spriteCounter > 10) {
        	if(spriteNum == 1) {
        		spriteNum = 2;
        	}
        	else if(spriteNum == 2) {
        		spriteNum = 1;
        		
        	}
        	spriteCounter = 0;
        	}
    	}
    	
    	if(invincible == true) {
    		invincibleCounter++;
    		if(invincibleCounter > 120) {
    			invincible = false;
    			invincibleCounter = 0;
    		}
    	}
    	if(life <= 0) {
    		gp.gameState = gp.gameOverState;
    	}
    	
    	if(hasSyrFull == 3) {
    		gp.gameState = gp.gameFinishState;
    	}
    }
    
    public void pickUpObject(int i) {
    	
    	if (i != 999) {
    		
    		String objectName = gp.obj[gp.currentMap][i].name;
    		
    		switch(objectName) {
    		case "Vaccine":
    			hasVax++;
    			gp.obj[gp.currentMap][i] = null;
    			gp.ui.showMessage("You got a vaccine!");
    			break;
    		case "Syringe":
    			if(hasVax > 0) {
    				gp.obj[gp.currentMap][i] = null;
    				hasVax--;
    				hasSyrFull++;
    				gp.ui.showMessage("You got a syringe!");
    			}
    			else {
    				gp.ui.showMessage("You need a vaccine!");
    			}
    			break;
    		}
    	}
    }
    
    public void interactNPC(int i) {
    	
    	if (i != 999) {
    		
    		if(gp.keyH.enterPressed == true) {
        		gp.npc[gp.currentMap][i].speak();
        		gp.gameState = gp.dialogueState;
    		}
    		gp.keyH.enterPressed = false;
    	}
    	
    }
    
    public void contactMonster(int i) {
    	
    	if(i != 999) {
    		
    		if(invincible == false) {
    			life -= 1;
    			invincible = true;
    		}
    		
    	}
    }
    
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        
        BufferedImage image = null;
        
        switch(direction){
            case "up":
            	if(spriteNum == 1) {
                image = back1;
            	}
            	if(spriteNum == 2) {
                image = back2;
                }
                break;
            case "down":
            	if(spriteNum == 1) {
                image = front1;
                }
                if(spriteNum == 2) {
                image = front2;
                }
                break;
            case "left":
            	if(spriteNum == 1) {
                image = left1;
                }
                if(spriteNum == 2) {
                image = left2;
                }
                break;
            case "right":
            	if(spriteNum == 1) {
                image = right1;
                }
                if(spriteNum == 2) {
                image = right2;
                }
                break;
        }
        
        if(invincible == true) {
        	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        
        g2.drawImage(image, screenX, screenY, null);
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
    }
}
