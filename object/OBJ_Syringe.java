package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Syringe extends SuperObject{

	GamePanel gp;
	
	public OBJ_Syringe(GamePanel gp) {
		
		name = "Syringe";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/obj_syringe_empty.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
