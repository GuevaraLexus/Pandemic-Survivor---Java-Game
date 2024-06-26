package main;

	import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

	public class GamePanel extends JPanel implements Runnable{
	    
	    //SCREEN SETTINGS
	    final int originalTileSize = 16; // 16x16 tile
	    final int scale = 3;
	    
	    public final int tileSize = originalTileSize * scale; //16*3 = 48x48 tile
	    public final int maxScreenCol = 20;
	    public final int maxScreenRow = 12;
	    public final int screenWidth = tileSize * maxScreenCol; //768px
	    public final int screenHeight = tileSize * maxScreenRow; // 576px
	    
	    //WORLD SETTINGS
	    public final int maxWorldCol = 50;
	    public final int maxWorldRow = 50;
	    public final int maxMap = 5;
	    public int currentMap = 2;
	    //FULLSCREEN
	    int screenWidth2 = screenWidth;
	    int screenHeight2 = screenHeight;
	    BufferedImage tempScreen;
	    Graphics2D g2;
	    public boolean fullScreenOn = false;
	    
	    //FPS
	    int FPS = 60;
	    
	    //SYSTEM
	    TileManager tileM = new TileManager(this);
	    public KeyHandler keyH = new KeyHandler(this);
	    Sound music = new Sound();
	    public CollisionChecker cChecker = new CollisionChecker(this);
	    public AssetSetter aSetter = new AssetSetter(this);
	    public UI ui = new UI(this);
	    public EventHandler eHandler = new EventHandler(this);
	    Thread gameThread;
	    
	    //ENTITY AND OBJECT
	    public Player player = new Player(this,keyH);
	    public SuperObject obj[][] = new SuperObject[maxMap][10];
	    public Entity npc[][] = new Entity[maxMap][10];
	    public Entity monster[][] = new Entity[maxMap][15];
	    
	    //GAME STATE
	    public int gameState;
	    public final int titleState = 0;
	    public final int playState = 1;
	    public final int pauseState = 2;
	    public final int dialogueState = 3;
	    public final int optionState = 4;
	    public final int gameOverState = 5;
	    public final int gameFinishState = 6;
	    
	    public GamePanel() {
	        
	        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	        this.setBackground(Color.black);
	        this.setDoubleBuffered(true);
	        this.addKeyListener(keyH);
	        this.setFocusable(true);
	    }
	    
	    public void setupGame() {
	    	
	    	aSetter.setObject();
	    	aSetter.setNPC();
	    	aSetter.setMonster();
	    	//PLEASE DON'T FORGET TO CHANGE IT BACK TO TITLE STATE
	    	gameState = titleState;
	    	
	    	tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
	    	g2 = (Graphics2D)tempScreen.getGraphics();
	    }
	    
	    public void retry() {
	    	
	    	player.setDefaultPositions();
	    	player.restoreLife();
	    	aSetter.setNPC();
	    	aSetter.setMonster();
	    }
	    
	    public void restart() {
	    	
	    	player.setDefaultValues();
	    	player.setDefaultPositions();
	    	player.restoreLife();
	    	aSetter.setObject();
	    	aSetter.setNPC();
	    	aSetter.setMonster();
	    }
	    
	    public void startGameThread(){
	        
	        gameThread = new Thread(this);
	        gameThread.start();
	    }
	    
	    @Override
	    public void run() {
	        
	        double drawInterval = 1000000000/FPS;
	        double delta = 0;
	        long lastTime = System.nanoTime();
	        long currentTime;
	        long timer = 0;
	        long drawCount = 0;
	        
	        while(gameThread != null){
	            
	            currentTime = System.nanoTime();
	            
	            delta += (currentTime - lastTime) / drawInterval;
	            timer += (currentTime - lastTime);
	            lastTime = currentTime;
	            
	            if(delta >= 1){
	            update();
	            drawToTempScreen();
	            drawToScreen();
	            delta--;
	            drawCount++;
	            }
	            
	            if(timer >= 1000000000){
	                
	                drawCount = 0;
	                timer = 0;
	            }
	            
	        }
	        
	        
	        
	    }
	    public void update(){
	        
	    	
	    	if(gameState == playState) {
	    		//player
	    		player.update();
	    		//npc
	    		for(int i = 0;i <npc[1].length; i++) {
	    			if(npc[currentMap][i] != null) {
	    				npc[currentMap][i].update(); 
	    			}
	    		}
	    		for(int i = 0;i <monster[1].length; i++) {
	    			if(monster[currentMap][i] != null) {
	    				monster[currentMap][i].update(); 
	    			}
	    		}
	    	}
	    	if(gameState == pauseState) {
	    		
	    	}
	    }
	    
	    public void drawToTempScreen() {
	    	
	    	//DEBUG
	        
	        long drawStart = 0;
	        if(keyH.checkDrawTime == true) {
	        	drawStart = System.nanoTime();
	        }
	        
	        //TITLE SCREEN
	        if(gameState == titleState) {
	        	ui.draw(g2);
	        }
	        
	        //GAMEFINISHED SCREEN
	        if(gameState == gameFinishState) {
	        	ui.draw(g2);
	        }
	        
	        //OTHERS
	        else {
	        	//TILE
		        tileM.draw(g2);
		        
		        //OBJECT
		        for(int i = 0; i < obj[1].length; i++){
		        	if(obj[currentMap][i] != null) {
		        		obj[currentMap][i].draw(g2, this);
		        	}
		        }
		        
		        //NPC
		        for(int i = 0; i < npc[1].length; i++) {
		        	if(npc[currentMap][i] != null) {
		        		npc[currentMap][i].draw(g2);
		        	}
		        }
		        
		        //MONSTER
		        for(int i = 0; i < monster[1].length; i++) {
		        	if(monster[currentMap][i] != null) {
		        		monster[currentMap][i].draw(g2);
		        	}
		        }
		        
		        //PLAYER
		        player.draw(g2);
		        
		        //UI
		        ui.draw(g2);
		        
		        //DEBUG
		        if(keyH.checkDrawTime == true) {
		        	long drawEnd = System.nanoTime();
			        long passed = drawEnd - drawStart;
			        g2.setColor(Color.white);
			        g2.drawString("Draw Time : " + passed, 10, 400);
		        }
	        }
	    }
	    
	    public void drawToScreen() {
	    	
	    	Graphics g = getGraphics();
	    	g.drawImage(tempScreen,  0,  0, screenWidth2, screenHeight2, null);
	    	g.dispose();
	    }
	        
	    public void playMusic(int i) {
	    	
	    	music.setFile(i);
	    	music.play();
	    	music.loop();
	    }
	    
	    public void stopMusic() {
	    	
	    	music.stop();
	    }
	}

