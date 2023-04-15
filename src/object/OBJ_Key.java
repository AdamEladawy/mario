package object;

import adam.entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {//SuperObject


    public OBJ_Key(GamePanel gp) {
        super(gp);
        name = "Key";
        down1 = setUp("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]/nit opens a door.";

    }
}
