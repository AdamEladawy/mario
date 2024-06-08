package mario.game.entities.object;

import mario.game.drawings.GamePanel;
import mario.game.entities.Entity;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {
        super(gp);

        type = type_pickupOnly;
        name = "Heart";
        value = 2;
        down1 = setUp("/objects/heart_full", gp.tileSize, gp.tileSize);
        image = setUp("/objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setUp("/objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setUp("/objects/heart_blank", gp.tileSize, gp.tileSize);

    }

    public void use(Entity entity) {

        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;

    }
}