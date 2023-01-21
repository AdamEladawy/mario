package main;

import adam.entity.Entity;
import object.OBJ_Heart;
//import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    //BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: the first screen, 1: second screen
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    Font PixelColeco, AncientTales;
    BufferedImage heart_full, heart_half, heart_blank;
    int messageCounter = 0;

    //double playTime;
    //DecimalFormat dFormat = new DecimalFormat("#0.00");


    public UI(GamePanel gp) {
        this.gp = gp;


        try {
            InputStream is = getClass().getResourceAsStream("/font/PixelColeco-4vJW.ttf");
            PixelColeco = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/AncientModernTales-a7Po.ttf");
            AncientTales = Font.createFont(Font.TRUETYPE_FONT, is);


        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //arial_40 = new Font("Ancient Modern Tales", Font.PLAIN, 40);
        /// arial_80B = new Font("Arial", Font.BOLD, 80);
        // OBJ_Key key = new OBJ_Key(gp);
        ///  keyImage = key.image;
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(PixelColeco);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);


        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.playState) {
            drawPlayerLife();

        } // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            pauseScreen();

        }
// DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();

        }


    }

    public void drawPlayerLife() {
        // gp.player.life = 3;
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

// DRAW MAX LIFE
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        //  DRAW CURRENT LIFE

        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);

            }
            i++;
            x += gp.tileSize;

        }
    }

    public void drawTitleScreen() {


        if (titleScreenState == 0) {
            g2.setColor(new Color(0, 0, 0));


            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
            String text = "Blue Boy Adventure";

            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;

            //  SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);
            g2.setColor(Color.white);
            g2.drawString(text, x, y);


            /// BLUE BOY IMAGE
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            // MENU

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            text = "NEW GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize * 3.5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }



            text = "QUIT";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        } else if (titleScreenState == 1) {

            //  CLASS SELECTION
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your class!";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXForCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Thief";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);


            }
            text = "Sorcerer";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Back";
            x = getXForCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }

    }

    public void pauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - length / 2;


        g2.drawString(text, x, y);


    }

    public void drawDialogueScreen() {

        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;


        for (String line : currentDialogue.split("/n")) {
            g2.drawString(line, x, y);
            y += 40;
        }


    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

}
//if (gameFinished == true){
// g2.setFont(arial_40);
// g2.setColor(Color.white);


// String text;
// int textLength;
// int x;
//  int y;

//   text = "You found the TREASURE!";
//  textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
//  x = gp.screenWidth/2 - textLength/2;
//   y = gp.screenHeight/2 - (gp.tileSize*3);
//  g2.drawString(text,x,y);


// text = "Your time is :"+dFormat.format(playTime)+"!";
// textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
// x = gp.screenWidth/2 - textLength/2;
//y = gp.screenHeight/2 + (gp.tileSize*4);
// g2.drawString(text,x,y);


// g2.setFont(arial_80B);
//g2.setColor(Color.yellow);
// text = "CONGRATULATION!";
// textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
// x = gp.screenWidth/2 - textLength/2;
// y = gp.screenHeight/2 + (gp.tileSize*2);
// g2.drawString(text,x,y);

//gp.gameThread = null;


//} else {
// g2.setFont(arial_40);
// g2.setColor(Color.white);
//  g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
//  g2.drawString("x" + gp.player.hasKey, 75, 65);
//  String text;

//playTime+=(double)1/60;
//g2.drawString("Time:"+dFormat.format(playTime),gp.tileSize*11,65);

// if( messageOn == true){


// g2.setFont(g2.getFont().deriveFont(30F));
//g2.drawString(message,gp.tileSize/2,gp.tileSize*5);

// messageCounter++;
//if(messageCounter > 120){
//  messageCounter = 0;
// messageOn = false;
//  }
//}

