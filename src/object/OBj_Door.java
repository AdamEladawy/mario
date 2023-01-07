package object;

import adam.entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBj_Door extends Entity {


    public OBj_Door(GamePanel gp) {


         super(gp);
        name = "Door";
        down1 = setUp("/objects/door");
        collisionON = true;


        solidArea.x = 10;
        solidArea.y = 16;
        solidArea.height = 48;
        solidArea.width = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
