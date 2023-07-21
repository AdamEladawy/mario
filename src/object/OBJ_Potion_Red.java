package object;

import adam.entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {
    GamePanel gp;


    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        this.gp = gp;
        type = type_consumable;
        name = "Red Potion";
        value = 5;
        down1 = setUp("/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[Red Potion]/nHeals your life /nby" + value + ".";

    }

    public void use(Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!/n"
                + "You life has been /nrecovered by " + value + ".";
        entity.life += value;
        gp.playSE(2);
    }
}
