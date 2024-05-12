package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Syringe_FULL extends SuperObject{
	
GamePanel gp;
	
	public OBJ_Syringe_FULL(GamePanel gp) {
		
		name = "Syringe_Full";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/obj_syringe_full.png"));

			uTool.scaledImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
