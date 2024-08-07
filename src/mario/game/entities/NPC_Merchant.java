package mario.game.entities;

import mario.game.drawings.GamePanel;
import mario.game.entities.object.*;

public class NPC_Merchant extends Entity {

    public NPC_Merchant(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;


        getImage();
        setDialogue();
        setItems();

    }

    public void getImage() {

        up1 = setUp("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = setUp("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        down1 = setUp("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setUp("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = setUp("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setUp("/npc/merchant_down_2", gp.tileSize, gp.tileSize);


    }

    public void setDialogue() {

        dialogues[0] = "He he, so you found /nme.I have good /nstuff.Do you want /nto trade with me"; ///<Mistake

    }

    public void setItems() {

        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
    }

    public void speak() {

        super.speak();
        gp.setGameState(gp.getTradeState());
        gp.ui.setNpc(this);
    }

}