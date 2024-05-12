package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Vaccine extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Vaccine(GamePanel gp) {
		
		name = "Vaccine";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/obj_vaccine.png"));
			
			uTool.scaledImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}