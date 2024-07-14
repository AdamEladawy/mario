package mario.game.entities.object;

import mario.game.drawings.GamePanel;
import mario.game.entities.Entity;

public class OBJ_Chest extends Entity {


    public OBJ_Chest(GamePanel gp) {
        super(gp);
        name = "Chest";
        down1 = setUp("/objects/chest", gp.tileSize, gp.tileSize);

    }

}