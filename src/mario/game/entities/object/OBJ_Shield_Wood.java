package mario.game.entities.object;

import mario.game.drawings.GamePanel;
import mario.game.entities.Entity;

public class OBJ_Shield_Wood extends Entity {

    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = "Wood Shield";
        down1 = setUp("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]/nMade by wood.";
        price = 5;

    }
}