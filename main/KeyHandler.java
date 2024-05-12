package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.GenericSignatureFormatError;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	
	//DEBUG
	boolean checkDrawTime = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

    @Override
    public void keyTyped(KeyEvent e) {
 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        if(gp.gameState == gp.titleState) {
        	gp.restart();
        	titleState(code);
        }
        else if (gp.gameState == gp.playState) {
        	playState(code);
        }
        else if (gp.gameState == gp.pauseState) {
        	pauseState(code);
        }
        else if (gp.gameState == gp.dialogueState) {
        	dialogueState(code);
        }
        else if (gp.gameState == gp.optionState) {
        	optionState(code);
        }
        else if (gp.gameState == gp.gameOverState) {
        	gameOverState(code);
        }
    }
    
    public void titleState(int code) {
    	if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
            	gp.ui.commandNum = 1;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1) {
            	gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER) {
        	if(gp.ui.commandNum == 0) {
        		gp.gameState = gp.playState;
        		gp.playMusic(0);
        	}
        	if(gp.ui.commandNum == 1) {
        		System.exit(0);
        	}
        }
    }
    
    public void playState(int code) {
    	if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
        	gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_ENTER){
        	enterPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
        	gp.gameState = gp.optionState;
        }
        
        //DEBUG
        if(code == KeyEvent.VK_T){
            if(checkDrawTime == false) {
            	checkDrawTime = true;
            }
            else if(checkDrawTime == true) {
            	checkDrawTime = false;
            }
        }
        if(code == KeyEvent.VK_R) {
        	switch(gp.currentMap) {
        	case 0: gp.tileM.loadMap("/maps/map01.txt", 0); break;
        	case 1: gp.tileM.loadMap("/maps/mapHouse.txt", 1); break;
        	}
        	
        }
    }

    public void pauseState(int code) {
    	if(code == KeyEvent.VK_P){
        	gp.gameState = gp.playState;
            
        }
    }

    public void dialogueState(int code) {
    	if(code == KeyEvent.VK_ENTER) {
    		gp.gameState = gp.playState;
    	}
    }
    
    public void optionState(int code) {
    	if(code == KeyEvent.VK_ESCAPE) {
    		gp.gameState = gp.playState;	
    	}
    	if(code == KeyEvent.VK_ENTER) {
    		enterPressed = true;
    	}
    	
    	int maxCommandNum = 0;
    	switch(gp.ui.subState) {
    	case 0: maxCommandNum = 4; break;
    	case 3: maxCommandNum = 1; break;
    	}
    	
    	if(code == KeyEvent.VK_W) {
    		gp.ui.commandNum--;
    		if(gp.ui.commandNum < 0) {
    			gp.ui.commandNum = maxCommandNum;
    		}
    	}
    	if(code == KeyEvent.VK_S) {
    		gp.ui.commandNum++;
    		if(gp.ui.commandNum > maxCommandNum) {
    			gp.ui.commandNum = 0;
    		}
    	}
    	if(code == KeyEvent.VK_A) {
    		if(gp.ui.subState == 0) {
    			if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
    				gp.music.volumeScale--;
    				gp.music.checkVolume();
    			}
    		}
    	}
    	if(code == KeyEvent.VK_D) {
    		if(gp.ui.subState == 0) {
    			if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
    				gp.music.volumeScale++;
    				gp.music.checkVolume();
    			}
    		}
    	}
    	
    }
    
    public void gameOverState(int code) {
    	
    	if(code == KeyEvent.VK_W) {
    		gp.ui.commandNum--;
    		if(gp.ui.commandNum < 0) {
    			gp.ui.commandNum = 1;
    		}
    	}
    	if(code == KeyEvent.VK_S) {
    		gp.ui.commandNum++;
    		if(gp.ui.commandNum > 1) {
    			gp.ui.commandNum = 0;
    		}
    	}
    	if(code == KeyEvent.VK_ENTER) {
    		if(gp.ui.commandNum == 0) {
    			gp.gameState = gp.playState;
    			gp.retry();
    		}
    		else if(gp.ui.commandNum == 1) {
    			gp.gameState = gp.titleState;
    			gp.restart();
    		}
    	}
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        
    }
    
}
