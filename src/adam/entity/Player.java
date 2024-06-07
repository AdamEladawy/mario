package adam.entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {

    public final int screenX;
    public final int screenY;
    public boolean attackCancel = false;

    KeyHandler keyH;
    /// /public int hasKey = 0;
    int standCounter = 0;


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

        //ATTACK AREA
//        attackArea.width = 36;
//        attackArea.height = 36;


        setDefaultValue();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();

    }

    public void setDefaultValue() {
     worldX = gp.tileSize * 23;
      worldY = gp.tileSize * 21;
//        worldY = gp.tileSize * 12;
//       worldX = gp.tileSize * 13;

        speed = 4;
        direction = "down";

        //PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        strength = 1;// The more strength he has, the more damage he gives
        dexterity = 1;// The more dexterity he has, the less damage he receives
        exp = 0;
        nextLevelExp = 5;
        coin = 0; //50
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);
        //  projectile = new OBJ_ROCK(gp);
        attack = getAttack();// The total attack Value is decided by strength and weapon
        defense = getDefense(); // The total event value is decided by dexterity and shield
    }
    public void  setDefaultPosition(){

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";

    }
    public void restoreLifeandMana(){

        life = maxLife;
        mana = maxMana;
        invincible = false;

    }

    public void setItems() {

        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));

    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {

        up1 = setUp("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setUp("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("/player/boy_right_2", gp.tileSize, gp.tileSize);


    }

    public void getPlayerAttackImage() {

        if (currentWeapon.type == type_sword) {
            attackUp1 = setUp("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setUp("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setUp("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setUp("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setUp("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setUp("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setUp("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setUp("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }
        if (currentWeapon.type == type_axe) {
            attackUp1 = setUp("/player/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setUp("/player/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setUp("/player/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setUp("/player/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setUp("/player/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setUp("/player/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setUp("/player/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setUp("/player/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);
        }


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

        if (attacking) {
            attacking();
        } else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
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
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // CHECK INTERACTIVE COLLISION
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);


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
            if (keyH.enterPressed && !attackCancel) {
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }


            attackCancel = false;
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            standCounter++;
            if (standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }
        if (gp.keyH.shotKeyPressed == true && projectile.alive == false
                && shotAvailableCounter == 30 && projectile.haveResource(this) == true) {

            // SET DEFAULT COORDINATES, DIRECTION AND USER
            projectile.set(worldX, worldY, direction, true, this);

            // SUBTRACT THE COST MANA,AMMO,ETC.
            projectile.subtractResource(this);

            // ADD IT TO THE LIST
            gp.projectileList.add(projectile);

            shotAvailableCounter = 0;

            gp.playSE(10);
        }

// This needs to be outside of key if statement
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if (life > maxLife) {
            life = maxLife;
        }
        if (mana > maxMana) {
            mana = maxMana;
        }
        if (life <= 0){
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSE(12);
        }
    }

    public void attacking() {

        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter >= 5 && spriteCounter <= 25) {
            spriteNum = 2;


            // Save the current worldX,worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;

            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            // After checking ,restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;


        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }

    }


    public void pickUpObject(int i) {
        if (i != 999) {

            // PICK ONLY ITEMS
            if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {

                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }

            // INVENTORY ITEMS
            else {
                String text;
                if (inventory.size() != maxInventorySize) {

                    inventory.add(gp.obj[gp.currentMap][i]);
                    gp.playSE(1);
                    text = "Got a " + gp.obj[gp.currentMap][i].name + "!";

                } else {
                    text = "You can not carry any more!`";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;

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


    }

    public void interactNPC(int i) {

        if (gp.keyH.enterPressed) {
            if (i != 999) {
                attackCancel = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }


        }


    }

    public void contactMonster(int i) {

        if (i != 999) {

            if (!invincible && !gp.monster[gp.currentMap][i].dying) {
                gp.playSE(6);
                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }
                life -= damage;
                invincible = true;

            }
        }
    }

    public void damageMonster(int i, int attack) {
        if (i != 999) {
            if (!gp.monster[gp.currentMap][i].invincible) {
                gp.playSE(5);

                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + "damage");

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp +" + gp.monster[gp.currentMap][i].exp);
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void damageInteractiveTile(int i) {

        if (i != 999 && gp.iTile[gp.currentMap][i].destructible == true
                && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false) {

            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            // Generate PArticle
            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);


            if (gp.iTile[gp.currentMap][i].life == 0) {
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }
        }
    }

    public void checkLevelUp() {

        if (exp >= nextLevelExp) {

            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + " now! /n" + "You fell stronger";


        }
    }

    public void selectItem() {

        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol,gp.ui.PlayerSlotRow);
        if (itemIndex < inventory.size()) {

            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {

                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();

            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_consumable) {

                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if (attacking) {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if (attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                }

                break;
            case "right":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if (attacking) {
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }

                }
                break;
        }

        if (invincible) {
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
