package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	
	GamePanel gp;
	
	public int worldX, worldY;
    public int speed;
    public String name;
    
    public BufferedImage front1, front2, back1, back2, left1, left2, right1, right2;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public Rectangle solidArea = new Rectangle (0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public int type;
    
    String dialogue[] = new String[20];
    int dialogueIndex = 0;
    
    //CHARACTER STATUS
    public int maxLife;
    public int life;
    
    public Entity(GamePanel gp) {
    	this.gp = gp;
    }
    
    public void setAction() {}
    public void speak() {
    	if(dialogue[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogue[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
    }
    public void update() {
    	
    	setAction();
    	
    	collisionOn = false;
    	gp.cChecker.checkTile(this);
    	gp.cChecker.checkObject(this, false);
    	boolean contactPlayer =  gp.cChecker.checkPlayer(this);
    	gp.cChecker.checkEntity(this, gp.monster);
    	gp.cChecker.checkEntity(this, gp.npc);
    	
    	if(this.type == 2 && contactPlayer == true) {
    		if(gp.player.invincible == false) {
    			gp.player.life -= 1;
    			gp.player.invincible = true;
    		}
    	}
    	
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
    public void draw(Graphics2D g2) {
    	
    	BufferedImage image = null;
    	
    		int screenX = worldX - gp.player.worldX + gp.player.screenX;
    		int screenY = worldY - gp.player.worldY + gp.player.screenY;
    		
    		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
    				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
    				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
    				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
    			
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
    			
    			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    		}
    		
    		
    }
    public BufferedImage setup(String imagePath) {
    	
    	UtilityTool uTool = new UtilityTool();
    	BufferedImage  Image = null;
    	
    	try {
    		Image = ImageIO.read(getClass().getResourceAsStream( imagePath + ".png"));
    		Image = uTool.scaledImage(Image, gp.tileSize, gp.tileSize);
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	return Image;
    }
}
