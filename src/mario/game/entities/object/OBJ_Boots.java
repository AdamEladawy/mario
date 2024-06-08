package mario.game.entities.object;

import mario.game.drawings.GamePanel;
import mario.game.entities.Entity;

public class OBJ_Boots extends Entity {


    public OBJ_Boots(GamePanel gp) {
        super(gp);

        name = "Boots";
        down1 = setUp("/objects/boots", gp.tileSize, gp.tileSize);

    }

}