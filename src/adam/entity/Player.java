package adam.entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    public final int screenX;
    public final int screenY;
    KeyHandler keyH;
    /// /public int hasKey = 0;
    int standCounter = 0;
    public boolean attackCancel = false;


    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);


        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);

        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(0, 0, 48, 48);
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValue();
        getPlayerImage();
        getPlayerAttackImage();

    }

    public void setDefaultValue() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;

        speed = 4;
        direction = "down";

        //PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

        up1 = setUp("/player/boy_up_1",gp.tileSize,gp.tileSize);
        up2 = setUp("/player/boy_up_2",gp.tileSize,gp.tileSize);
        down1 = setUp("/player/boy_down_1",gp.tileSize,gp.tileSize);
        down2 = setUp("/player/boy_down_2",gp.tileSize,gp.tileSize);
        left1 = setUp("/player/boy_left_1",gp.tileSize,gp.tileSize);
        left2 = setUp("/player/boy_left_2",gp.tileSize,gp.tileSize);
        right1 = setUp("/player/boy_right_1",gp.tileSize,gp.tileSize);
        right2 = setUp("/player/boy_right_2",gp.tileSize,gp.tileSize);


    }
    public void getPlayerAttackImage(){

        attackUp1 = setUp("/player/boy_attack_up_1",gp.tileSize,gp.tileSize*2);
        attackUp2 = setUp("/player/boy_attack_up_2",gp.tileSize,gp.tileSize*2);
        attackDown1 = setUp("/player/boy_attack_down_1",gp.tileSize,gp.tileSize*2);
        attackDown2 = setUp("/player/boy_attack_down_2",gp.tileSize,gp.tileSize*2);
        attackLeft1 = setUp("/player/boy_attack_left_1",gp.tileSize*2,gp.tileSize);
        attackLeft2 = setUp("/player/boy_attack_left_2",gp.tileSize*2,gp.tileSize);
        attackRight1= setUp("/player/boy_attack_right_1",gp.tileSize*2,gp.tileSize);
        attackRight2 = setUp("/player/boy_attack_right_2",gp.tileSize*2,gp.tileSize);



    }
    // public BufferedImage setUp(String imageName) {
    //  UtilityTool uTool = new UtilityTool();
    //   BufferedImage image = null;

    // try {
    //  image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
    //  image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    //} catch (IOException e) {
    //  e.printStackTrace();

    // }
    // return image;
    // }

    public void update() {

        if (attacking){
            attacking();
        }

       else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed ) {
            if (keyH.upPressed) {
                direction = "up";


            } else if (keyH.downPressed) {
                direction = "down";

            } else if (keyH.leftPressed) {
                direction = "left";

            } else if (keyH.rightPressed) {
                direction = "right";

            }
            // CHECK TILE COLLISION
            collisionON = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
// CHECK  NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
            contactMonster(monsterIndex);



            //  CHECK EVENT COLLISION
            gp.eHandler.checkEvent();


// IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionON && !keyH.enterPressed) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            if (keyH.enterPressed == true && attackCancel == false){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }


            attackCancel = false;
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
// This needs to be outside of key if statement
        if (invincible == true){
            invincibleCounter ++;
            if (invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }
public void attacking(){

        spriteCounter++;

        if (spriteCounter <= 5){
            spriteNum = 1;
        }
        if (spriteCounter >= 5 && spriteCounter <= 25){
            spriteNum = 2;


            // Save the current worldX,worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea
            switch (direction){
                case"up": worldY -= attackArea.height; break;
                case"down": worldY += attackArea.height; break;
                case"left": worldX -= attackArea.width; break;
                case"right": worldX += attackArea.width; break;

            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            // After checking ,restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;



        }
        if (spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }

}
    public void pickUpObject(int i) {
        if (i != 999) {

            //String objectName = gp.obj[i].name;
//switch (objectName){
            //  case "Key":
            //  gp.playSE(1);
            // hasKey++;
            ///gp.obj[i] = null;
            //gp.ui.showMessage("You got a key!");

            // break;
            // case "Door":
            //   if (hasKey > 0) {
            //  gp.playSE(3);
            //  gp.obj[i] = null;
            //  hasKey--;
            //  gp.ui.showMessage("You opened a Door!");

            // } else {
            //  gp.ui.showMessage("You need a key");
            // }
            // break;
            // case "Boots":
            //gp.playSE(2);
            // speed += 1;
            // gp.obj[i] = null;
            // gp.ui.showMessage("speed up!");
            // break;
            // case "Chest":
            ///  gp.ui.gameFinished = true;
            //  gp.stopMusic();
            //  gp.playSE(4);
            //   break;

        }


    }

    public void interactNPC(int i) {

        if(gp.keyH.enterPressed){
            if (i != 999) {
                attackCancel  = true;
                    gp.gameState = gp.dialogueState;
                    gp.npc[i].speak();
            }


        }


    }
    public void contactMonster(int i){

        if(i != 999){

            if (invincible == false){
                gp.playSE(6);
                life -= 1;
                invincible = true;

            }
        }
    }

    public void damageMonster(int i){
       if (i != 999){
           if(!gp.monster[i].invincible){
               gp.playSE(5);
               gp.monster[i].life -=1;
               gp.monster[i].invincible =true;
               gp.monster[i].damageReaction();

               if (gp.monster[i].life <= 0){
                  gp.monster[i].dying = true;
               }
           }
       }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (!attacking){
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
               if (attacking){
                   tempScreenY = screenY - gp.tileSize;
                   if (spriteNum == 1) {image = attackUp1;}
                   if (spriteNum == 2) {image = attackUp2;}
               }
                break;
            case "down":
                if (!attacking){
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
              if (attacking){
                  if (spriteNum == 1) {image =  attackDown1;}
                  if (spriteNum == 2) {image = attackDown2;}
              }
                break;
            case "left":
                if (!attacking){
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                }
                if(attacking){
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }

                break;
            case "right":
                if (!attacking){
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }
                if (attacking){
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}

                }
                break;
        }

       if (invincible  == true){
           g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
       }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

       // Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


        // DEBUG

//        g2.setFont(new Font("Arial",Font.PLAIN,26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible"+invincibleCounter,10,400);
    }
}
