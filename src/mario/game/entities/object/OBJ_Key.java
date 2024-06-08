package mario.game.entities.object;

import mario.game.drawings.GamePanel;
import mario.game.entities.Entity;

public class OBJ_Key extends Entity {//SuperObject


    public OBJ_Key(GamePanel gp) {
        super(gp);
        name = "Key";
        down1 = setUp("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]/nit opens a door.";
        price = 4;

    }
}