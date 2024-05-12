package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Heart;
import object.OBJ_Syringe_FULL;
import object.OBJ_Vaccine;
import object.OBJ_Syringe;
import object.SuperObject;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_40;
	BufferedImage keyImage;
	BufferedImage heartFull, heartHalf, heartEmpty;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public String currentDialogue = "";
	public int commandNum = 0;
	int subState = 0;
	
	
	public UI(GamePanel gp) {
		
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		OBJ_Syringe_FULL sFull = new OBJ_Syringe_FULL(gp);
		keyImage = sFull.image;
	
		//CREAT HUD OBJECT
		SuperObject heart = new OBJ_Heart(gp);
		heartFull = heart.image;
		heartHalf = heart.image2;
		heartEmpty = heart.image3;
	
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		
		
		
		this.g2 = g2;
		
		//TITLESTATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		//PLAYSTATE
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
		}
		//PAUSESTATE
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		//DIALOGUESTATE
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		//OPTIONSTATE
		if(gp.gameState == gp.optionState) {
			drawOptionScreen();
		}
		//GAMEOVERSTATE
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		//GAMEFINISHSTATE
		if(gp.gameState == gp.gameFinishState) {
			drawGameFinishedScreen();
		}
	}
	public void drawGameOverScreen() {
		
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
		
		//GAMEOVER
		text = "GAME OVER";
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		//RETRY
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXforCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text,  x,  y);
		if(commandNum == 0) {
			g2.drawString(">", x-40, y);
		}
		
		//QUIT
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Quit";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text,  x,  y);
		if(commandNum == 1) {
			g2.drawString(">", x-40, y);
		}
	}
	public void drawPlayerLife() {
		
		//gp.player.life = 5;
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		//DRAW MAX LIFE
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heartEmpty, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		//RESET
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		//CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heartHalf, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heartFull, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		
		//NUMBER OF SYRINGE
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		g2.drawImage(keyImage,  gp.tileSize/2, gp.tileSize*2, gp.tileSize, gp.tileSize, null);
		g2.drawString("x " + gp.player.hasSyrFull, 74, gp.tileSize*3);
		
		//MESSAGE
		if(messageOn == true) {
			
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(message, gp.tileSize/2, gp.tileSize*4);
			
			messageCounter++;
			
			if(messageCounter > 120) {
				messageCounter = 0;
				messageOn = false;
			}
		}
	}
	
	public void drawTitleScreen() {
		
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,75F));
		String text = "Pandemic Survivor";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 4;
		
		//TEXT SHADOW
		g2.setColor(Color.blue);
		g2.drawString(text, x+5, y+5);
		//MAIN TEXT COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
		
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize*3;
		g2.setColor(Color.blue);
		g2.drawString(text, x+3, y+3);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString("=>", x-gp.tileSize, y);
		}
		
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize*1;
		g2.setColor(Color.blue);
		g2.drawString(text, x+3, y+3);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString("=>", x-gp.tileSize, y);
		}
	}
	
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
		
	}
	
	public void drawDialogueScreen() {
		
		//WINDOW
		int x = gp.tileSize*3;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
		x += gp.tileSize;
		y += gp.tileSize;

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawGameFinishedScreen() {
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,75F));
		String text = "Pandemic Survivor";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 4;
		
		//TEXT SHADOW
		g2.setColor(Color.blue);
		g2.drawString(text, x+5, y+5);
		//MAIN TEXT COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//THANK YOU MESSAGE
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
		
		text = "Now we can save the world...";
		x = getXforCenteredText(text);
		y += gp.tileSize*3;
		g2.setColor(Color.blue);
		g2.drawString(text, x+3, y+3);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		text = "THANK YOU FOR PLAYING!";
		x = getXforCenteredText(text);
		y += gp.tileSize*1;
		g2.setColor(Color.blue);
		g2.drawString(text, x+3, y+3);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
	}
	
	public void drawOptionScreen() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameX = gp.tileSize*6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0: options_top(frameX, frameY); break;
		//case 1: options_fullScreenNotification(frameX, frameY); break;
		case 1: options_control(frameX, frameY); break;
		case 2: options_endGameConformation(frameX, frameY); break;
		}
		
		gp.keyH.enterPressed = false;
	}
	public void options_top(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		//TITLE
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		//CHOOSE YOUR SETTING
		textX = frameX + gp.tileSize;
		textY += gp.tileSize*2;
		g2.drawString("Choose your Setting", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
		}
		
		//MUSIC
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
		}
		
		//CONTROL
		textY += gp.tileSize;
		g2.drawString("Control", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 1;
				commandNum = 0;
			}
		}
				
		//QUIT GAME
		textY += gp.tileSize;
		g2.drawString("Quit Game", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		
		//BACK TO GAME
		textY += gp.tileSize*2;
		g2.drawString("Back", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
		
		//MUSIC VOLUME
		textX = frameX + (int)(gp.tileSize*5);
		textY = frameY + gp.tileSize*2 + 24;
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
	
		Color c = new Color(0, 0, 0, 150);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	public void options_fullScreenNotification(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;
		int textY = frameY +  gp.tileSize*3;
		
		currentDialogue = "PLEASE RESTART /n       THE GAME.";
		
		for(String line: currentDialogue.split("/n")) {
			g2.drawString(line,  textX,  textY);
			textY += 40;
		}
		//BACK
		textY = frameY + gp.tileSize*9;
		g2.drawString("BACK", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}
	}
	public void options_control(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		String text = "CONTROL";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY); textY+=gp.tileSize;
		g2.drawString("Confirm", textX, textY); textY+=gp.tileSize;
		g2.drawString("Pause", textX, textY); textY+=gp.tileSize;
		g2.drawString("Options", textX, textY); textY+=gp.tileSize;
		g2.drawString("DrawTime", textX, textY); textY+=gp.tileSize;
		
		textX = frameX + gp.tileSize*5;
		textY = gp.tileSize*3;
		g2.drawString("W,S,A,D", textX, textY); textY+=gp.tileSize;
		g2.drawString("ENTER", textX, textY); textY+=gp.tileSize;
		g2.drawString("P", textX, textY); textY+=gp.tileSize;
		g2.drawString("ESC", textX, textY); textY+=gp.tileSize;
		g2.drawString("T", textX, textY); textY+=gp.tileSize;
		
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 2;
			}
		}
	}
	
	public void options_endGameConformation(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Quit now? :<";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		//YES
		String text = "Yes";
		textX = getXforCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString("Yes", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				gp.gameState = gp.titleState;
				gp.stopMusic();
			}
		}
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
