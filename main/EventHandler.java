package main;

import java.awt.Rectangle;

public class EventHandler {

	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.width = 2;
		eventRect.height = 2;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
	}
	
	public void checkEvent() {
		
		if(hit(0,8,43,"up") == true) {
			teleportHome(1,40,37,gp.dialogueState);
		}
		else if(hit(1,40,39,"down") == true) {
			teleport(0,8,44,gp.dialogueState);
		}
		else if(hit(1,41,39,"down") == true) {
			teleport(0,8,44,gp.dialogueState);
		}
		else if(hit(0,44,5,"up") == true) {
			teleport(2,13,18,gp.dialogueState);
		}
		else if(hit(2,13,18,"down") == true) {
			teleport(0,44,5,gp.dialogueState);
		}
		else if(hit(1,30,32,"up") == true) {
			drinkWater(gp.dialogueState);
		}
		else if(hit(0,44,5,"up") == true && gp.player.hasSyrFull == 3) {
			gp.gameState = gp.gameFinishState;
		}
	}
	
	public boolean hit(int map, int eventCol, int eventRow, String reqDirection) {
		
		boolean hit  = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect.x = eventCol*gp.tileSize + eventRect.x;
		eventRect.y = eventRow*gp.tileSize + eventRect.y;
		
		if(gp.player.solidArea.intersects(eventRect)) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;
	}
	
	public void teleport(int map, int col, int row, int gameState) {
		
		
		
		gp.currentMap = map;
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
	}
	
	public void teleportHome(int map, int col, int row, int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Home...";
		
		gp.currentMap = map;
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
	}
	
	public void drinkWater(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "so refreshing...";
		
		gp.player.life = gp.player.maxLife;
	}
}
