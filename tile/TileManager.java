package tile;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	
	public TileManager(GamePanel gp){
		
		this.gp = gp;
		
		tile = new Tile[150];
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];		
		getTileImage();
		loadMap("/maps/map01.txt", 0);
		loadMap("/maps/mapHouse.txt", 1);
		loadMap("/maps/mapHospital.txt", 2);
	}
	
	public void getTileImage() {
		
			//NO 0-9 for clearer map
			
			//GRASS
			setup(10, "grass", false);
			setup(11, "grass1", false);
			
			//TREE
			setup(15, "tree", true);
			setup(16, "tree1", true);
			setup(17, "tree2", true);
			setup(18, "tree3", true);
			setup(19, "tree4", true);
			
			//DIRT
			setup(20, "dirt", false);
			setup(21, "dirt1", false);
			setup(22, "dirt_grassBottom", false);
			setup(23, "dirt_grassTop", false);
			setup(24, "dirt_grassRight", false);
			setup(25, "dirt_grassLeft", false);
			setup(26, "dirt_grassBottomLeft", false);
			setup(27, "dirt_grassBottomRight", false);
			setup(28, "dirt_grassTopLeft", false);
			setup(29, "dirt_grassTopRight", false);
			setup(30, "dirt_grassCornerBottomLeft", false);
			setup(31, "dirt_grassCornerBottomRight", false);
			setup(32, "dirt_grassCornerTopLeft", false);
			setup(33, "dirt_grassCornerTopRight", false);
			
			
			setup(34, "wood", false); //INITIAL INTERIOR OF THE HOUSE
			setup(35, "stone", true); // INITIAL INTERIOR OF THE HOUSE(WALL)
			
			//SAND
			setup(36, "sand_cornerBottomLeft", true);
			setup(37, "sand_cornerBottomRight", true);
			setup(38, "sand_cornerTopLeft", true);
			setup(39, "sand_cornerTopRight", true);
			setup(40, "sand", false);
			setup(41, "sandBottomLeft", false);
			setup(42, "sandBottom", false);
			setup(43, "sandBottomRight", false);
			setup(44, "sandRight", false);
			setup(45, "sandTopRight", false);
			setup(46, "sandTop", false);
			setup(47, "sandTopLeft", false);
			setup(48, "sandLeft", false);
			setup(49, "sand_grassTop", false);
			setup(50, "sand_grassBottom", false);
			setup(51, "sandBottomLeft1", true);
			setup(52, "sandBottom1", true);
			setup(53, "sandBottomRight1", true);
			setup(54, "sandRight1", true);
			setup(55, "sandTopRight1", true);
			setup(56, "sandTop1", true);
			setup(57, "sandTopLeft1", true);
			setup(58, "sandLeft1", true);
			
			//WATER
			setup(60, "water", true);
			setup(61, "water1", true);
			setup(62, "water_grassBottomLeft", true);
			setup(63, "water_grassBottom", true);
			setup(64, "water_grassBottomRight", true);
			setup(65, "water_grassRight", true);
			setup(66, "water_grassTopRight", true);
			setup(67, "water_grassTop", true);
			setup(68, "water_grassTopLeft", true);
			setup(69, "water_grassLeft", true);
			setup(70, "water_grassCornerBottomLeft", true);
			setup(71, "water_grassCornerBottomRight", true);
			setup(72, "water_grassCornerTopLeft", true);
			setup(73, "water_grassCornerTopRight", true);
			
			//ASPHALT
			setup(80, "asphalt1", false);
			setup(81, "asphalt2", false);
			setup(82, "asphalt3", false);
			setup(83, "asphalt4", false);
			setup(84, "asphalt5", false);
			setup(85, "asphalt6", false);
			setup(86, "asphalt7", false);
			setup(87, "asphalt8", false);
			setup(88, "asphalt9", true);
			setup(89, "asphalt10", true);
			setup(90, "asphalt11", true);
			setup(91, "asphalt12", true);
			
			setup(92, "house1", true);
			setup(93, "house2", true);
			setup(94, "house3", true); //YOUR HOME
			setup(95, "house4", true);
			
			setup(100, "stone", true);
			
			setup(101, "houseBed", true);
			setup(102, "houseTable", true);
			setup(103, "houseWaterJug", true);
			setup(104, "houseWallTopSide", true);
			setup(105, "houseSideWall", true);
			setup(106, "houseWallWindow", true);
			
			setup(110, "hospital", true);
			setup(111, "hospitalFloor", false);
			setup(112, "hospitalFloor1", false);
			setup(113, "hospitalFloor2", false);
			setup(114, "hospitalFloor3", false);
			setup(115, "hospitalWall", true);
			setup(116, "hospitalWall1", true);
			setup(117, "hospitalWall2", true);
			setup(118, "hospitalWallWindow", true);
			setup(119, "hospitalSideWall", true);
	}
	public void setup(int index, String imageName, boolean collision) {
		
		UtilityTool utool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = utool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap(String filePath, int map){
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[map][col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
				
			}
			br.close();
			
		}catch(Exception e){
			
		}
	}
	public void draw(Graphics g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow <  gp.maxWorldRow) {
			
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;

			}
		}
	}
}
