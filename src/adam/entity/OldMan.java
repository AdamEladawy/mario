package adam.entity;

import main.GamePanel;

import java.util.Random;


public class OldMan extends Entity {

    public OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;


        getImage();
        setDialogue();

    }

    public void getImage() {

        up1 = setUp("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setUp("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("/npc/oldman_right_2", gp.tileSize, gp.tileSize);


    }

    public void setDialogue() {

        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you've come to this /nisland to find the /ntreasure?";
        dialogues[2] = "I used a great wizard but /nnow...I'm a bit too old /nfor taking an adventure";
        dialogues[3] = "well far well young /nlad , now   I must get /nwwgoing";

    }


    public void setAction() {

        actionLookCounter++;

        if (actionLookCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;// pick a number between 1-100
            if (i <= 25) {
                direction = "up";

            }
            if (i > 25 && i <= 50) {
                direction = "down";

            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLookCounter = 0;
        }


    }

    public void speak() {
        super.speak();


    }


}
//