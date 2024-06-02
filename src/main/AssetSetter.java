package main;

import adam.entity.OldMan;
import monster.MON_GreenSlime;
import object.*;
import tile_interactive.IT_DryTree;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int i = 0;

        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = gp.tileSize * 25;
        gp.obj[i].worldY = gp.tileSize * 23;
        i++;

        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = gp.tileSize * 21;
        gp.obj[i].worldY = gp.tileSize * 19;
        i++;

        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = gp.tileSize * 26;
        gp.obj[i].worldY = gp.tileSize * 21;
        i++;

        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = gp.tileSize * 33;
        gp.obj[i].worldY = gp.tileSize * 21;
        i++;

        gp.obj[i] = new OBJ_Shield_Blue(gp);
        gp.obj[i].worldX = gp.tileSize * 35;
        gp.obj[i].worldY = gp.tileSize * 21;
        i++;
        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = gp.tileSize * 22;
        gp.obj[i].worldY = gp.tileSize * 27;
        i++;
        gp.obj[i] = new OBJ_Heart(gp);
        gp.obj[i].worldX = gp.tileSize * 22;
        gp.obj[i].worldY = gp.tileSize * 29;
        i++;
        gp.obj[i] = new OBJ_ManaCrystal(gp);
        gp.obj[i].worldX = gp.tileSize * 22;
        gp.obj[i].worldY = gp.tileSize * 31;
        i++;
    }

    public void setNpc() {
        gp.npc[0] = new OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;


    }

    public void setMonster() {

        int i = 0;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 21;
        gp.monster[i].worldY = gp.tileSize * 38;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 23;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 24;
        gp.monster[i].worldY = gp.tileSize * 37;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 34;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 38;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;

    }

    public void setInteractiveTiles() {

        int i = 0;
        gp.iTile[i] = new IT_DryTree(gp, 27, 12);
        i++;
        gp.iTile[i] = new IT_DryTree(gp, 28, 12);
        i++;
        gp.iTile[i] = new IT_DryTree(gp, 29, 12);
        i++;
        gp.iTile[i] = new IT_DryTree(gp, 30, 12);
        i++;
        gp.iTile[i] = new IT_DryTree(gp, 31, 12);
        i++;
        gp.iTile[i] = new IT_DryTree(gp, 32, 12);
        i++;
//        gp.iTile[i] = new IT_DryTree(gp,33,12);i++;
//        gp.iTile[i] = new IT_DryTree(gp,21,19);i++;
//        gp.iTile[i] = new IT_DryTree(gp,22,19);i++;
//        gp.iTile[i] = new IT_DryTree(gp,23,19);i++;//30,20
//        gp.iTile[i] = new IT_DryTree(gp,24,19);i++;//30,21
//        gp.iTile[i] = new IT_DryTree(gp,25,19);i++;//30,22
//        gp.iTile[i] = new IT_DryTree(gp,20,20);i++;
//        gp.iTile[i] = new IT_DryTree(gp,20,21);i++;
//        gp.iTile[i] = new IT_DryTree(gp,20,22);i++;
//        gp.iTile[i] = new IT_DryTree(gp,22,24);i++;
//        gp.iTile[i] = new IT_DryTree(gp,23,24);i++;
//        gp.iTile[i] = new IT_DryTree(gp,24,24);i++;

    }
}
//gp.obj[0] = new OBJ_Key(gp);
//   gp.obj[0].worldX = 23 * gp.tileSize;
// gp.obj[0].worldY = 7 * gp.tileSize;

// gp.obj[1] = new OBJ_Key(gp);
// gp.obj[1].worldX = 23 * gp.tileSize;
//  gp.obj[1].worldY = 40 * gp.tileSize;

// gp.obj[2] = new OBJ_Key(gp);
// gp.obj[2].worldX = 38 * gp.tileSize;
// gp.obj[2].worldY = 8 * gp.tileSize;

// gp.obj[3] = new OBj_Door(gp);
//gp.obj[3].worldX = 10 * gp.tileSize;
// gp.obj[3].worldY = 12 * gp.tileSize;

//gp.obj[4] = new OBj_Door(gp);
// gp.obj[4].worldX = 8 * gp.tileSize;
//gp.obj[4].worldY = 27 * gp.tileSize;

//gp.obj[5] = new OBj_Door(gp);
// gp.obj[5].worldX = 12 * gp.tileSize;
//gp.obj[5].worldY = 24 * gp.tileSize;

// gp.obj[6] = new OBJ_Chest(gp);
// gp.obj[6].worldX = 10 * gp.tileSize;
//  gp.obj[6].worldY = 7 * gp.tileSize;

//  gp.obj[7] = new OBJ_Boots(gp);
//  gp.obj[7].worldX = 37 * gp.tileSize;
//  gp.obj[7].worldY = 42 * gp.tileSize;