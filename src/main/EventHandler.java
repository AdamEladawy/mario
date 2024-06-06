package main;


public class EventHandler {
    GamePanel gp;
    EventRect eventRect [][][] ;
    //Rectangle eventRect;
    // int eventRectDefaultX,eventRectDefaultY;
    int PreviousEventX, PreviousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;


    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];


        int map = 0;
        int col = 0;
        int row = 0;

        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }


    }

    public void checkEvent() {
        // Check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - PreviousEventX);
        int yDistance = Math.abs(gp.player.worldY - PreviousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;

        }
        if (canTouchEvent) {
            if (hit(0,27, 16, "right")) {damagePit( gp.dialogueState);}
            //if (hit(27, 16, "right") == true) {teleport(gp.dialogueState);}
            else if (hit(0,23, 12, "up")) {healingPool( gp.dialogueState);}
            else if (hit(0,10,39,"any") == true){ teleport(1,12,13);}
           else if (hit(1,12,13,"any") == true){ teleport(0,10,39);}
        }


    }

    public boolean hit(int map,int col, int row, String reqDirection) {
        boolean hit = false;


        if (map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;


                    PreviousEventX = gp.player.worldX;
                    PreviousEventY = gp.player.worldY;


                }
            }


            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }


        return hit;
    }

    public void teleport(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "teleport";
        gp.player.worldX = gp.tileSize * 37;


        gp.player.worldY = gp.tileSize * 10;


    }

    public void damagePit( int gameState) {


        gp.gameState = gameState;
        gp.playSE(6);
        gp.ui.currentDialogue = "you fall into a pit";
        gp.player.life -= 1;
        // eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healingPool( int gameState) {

        if (gp.keyH.enterPressed) {

            gp.player.attackCancel = true;
            gp.gameState = gameState;
            gp.playSE(2);
            gp.ui.currentDialogue = "You drank the water./nYour life and mana have /nbeen recovered";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setMonster();

        }

    }
    public void teleport(int map, int col, int row) {

        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;

//        gp.currentMap = map;
//        gp.player.worldX = gp.tileSize*col;
//        gp.player.worldY = gp.tileSize*row;
//        PreviousEventX = gp.player.worldX;
//        PreviousEventY = gp.player.worldY;
        canTouchEvent = false;
        gp.playSE(13);
    }

}
