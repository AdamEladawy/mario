package mario.game.entities.object;

import mario.game.drawings.GamePanel;
import mario.game.entities.Entity;

public class OBJ_Coin_Bronze extends Entity {

    public OBJ_Coin_Bronze(GamePanel gp) {
        super(gp);

        type = type_pickupOnly;
        name = "Bronze Coin";
        value = 1;
        down1 = setUp("/objects/coin_bronze", gp.tileSize, gp.tileSize);

    }

    public void use(Entity entity) {

        gp.playSE(1);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;
    }


}