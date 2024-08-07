package mario.game.entities.object;

import mario.game.drawings.GamePanel;
import mario.game.entities.Entity;

public class OBJ_ManaCrystal extends Entity {


    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);

        type = type_pickupOnly;
        name = "Mana Crystal";
        value = 1;
        down1 = setUp("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image = setUp("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setUp("/objects/manacrystal_blank", gp.tileSize, gp.tileSize);

    }

    public void use(Entity entity) {

        gp.playSE(2);
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;

    }
}