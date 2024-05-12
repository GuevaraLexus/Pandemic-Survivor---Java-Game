package main;

import entity.NPC_doctor;
import entity.NPC_lover;
import monster.MON_zombie;
import object.OBJ_Syringe;
import object.OBJ_Vaccine;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		int mapNum = 0;
		int i = 0;
		gp.obj[mapNum][i] = new OBJ_Vaccine(gp);
		gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Vaccine(gp);
		gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 45 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Vaccine(gp);
		gp.obj[mapNum][i].worldX = 7 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Syringe(gp);
		gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 5 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Syringe(gp);
		gp.obj[mapNum][i].worldX = 40 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 40 * gp.tileSize;
		i++;
		
		mapNum = 1;
		gp.obj[mapNum][i] = new OBJ_Syringe(gp);
		gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 39 * gp.tileSize;
		i++;
	}
	
	public void setNPC() {
		
		int mapNum = 2;
		int i = 0;
		gp.npc[mapNum][i] = new NPC_doctor(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 13; 
		gp.npc[mapNum][i].worldY = gp.tileSize * 15;
		i++;
		gp.npc[mapNum][i] = new NPC_lover(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 16; 
		gp.npc[mapNum][i].worldY = gp.tileSize * 13;
		i++;
	}
	
	public void setMonster() {
		
		int mapNum = 0;
		int i = 0;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 26;
		gp.monster[mapNum][i].worldY = gp.tileSize * 9;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 26;
		gp.monster[mapNum][i].worldY = gp.tileSize * 13;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 26;
		gp.monster[mapNum][i].worldY = gp.tileSize * 18;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 26;
		gp.monster[mapNum][i].worldY = gp.tileSize * 27;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 26;
		gp.monster[mapNum][i].worldY = gp.tileSize * 35;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 8;
		gp.monster[mapNum][i].worldY = gp.tileSize * 29;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 10;
		gp.monster[mapNum][i].worldY = gp.tileSize * 29;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 9;
		gp.monster[mapNum][i].worldY = gp.tileSize * 33;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 3;
		gp.monster[mapNum][i].worldY = gp.tileSize * 35;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 35;
		gp.monster[mapNum][i].worldY = gp.tileSize * 18;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 33;
		gp.monster[mapNum][i].worldY = gp.tileSize * 20;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 40;
		gp.monster[mapNum][i].worldY = gp.tileSize * 29;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 45;
		gp.monster[mapNum][i].worldY = gp.tileSize * 33;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 34;
		gp.monster[mapNum][i].worldY = gp.tileSize * 15;
		i++;
		gp.monster[mapNum][i] = new MON_zombie(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 45;
		gp.monster[mapNum][i].worldY = gp.tileSize * 14;
		i++;
	}
}
