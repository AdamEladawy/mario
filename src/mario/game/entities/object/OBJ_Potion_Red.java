package mario.game.entities.object;

import mario.game.drawings.GamePanel;
import mario.game.entities.Entity;

public class OBJ_Potion_Red extends Entity {



    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        type = type_consumable;
        name = "Red Potion";
        value = 5;
        down1 = setUp("/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[Red Potion]/nHeals your life /nby" + value + ".";
        price = 12;

    }

    public void use(Entity entity) {

        gp.setGameState(gp.dialogueState);
        gp.ui.setCurrentDialogue("You drink the " + name + "!/n"
                + "You life has been /nrecovered by " + value + ".");
        entity.life += value;
        gp.playSE(2);

    }
}