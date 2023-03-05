package main;

import adam.entity.Entity;
import adam.entity.Player;
//import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    final int orginalTileSize = 16; //16x16 tile
    final int scale = 3;
    public final int tileSize = orginalTileSize * scale;// 48x48 title
    public final int screenWidth = tileSize * maxScreenCol;// 768 pixels
    public final int screenHeight = tileSize * maxScreenRow;//576 pixels
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public KeyHandler keyH = new KeyHandler(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity[] obj = new Entity[10];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();


    //GAME STATE
    public int gameState;
    //fps
    int Fps = 60;
    TileManager tileM = new TileManager(this);
    Sound music = new Sound();
    // ENTITY AND OBJECT
    Sound Se = new Sound();
    public final int characterState = 4;



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);


    }

    public void setUpGame() {
        aSetter.setObject();
        aSetter.setNpc();
        aSetter.setMonster();
        // playMusic(0);

        gameState = titleState;


    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
// Sleeping method for game developing
    // @Override
    //public void run() {
    //double drawInverval = 1000000000/Fps; // o.o1666666 seconds
    // double nextDrawTime = System.nanoTime() + drawInverval;

    // while (gameThread != null){

    //update();

    // repaint();


    // try {
    // double remainingTime = nextDrawTime - System.nanoTime();
    // remainingTime = remainingTime/1000000;

    //  if (remainingTime <0){
    //       remainingTime = 0;
    //  }

    // Thread.sleep((long)remainingTime);

    //nextDrawTime += drawInverval;
    //  } catch (InterruptedException e) {
    //  e.printStackTrace();
    //  }
    //  }
    // }
    // delta method for game developing
    public void run() {
        double drawInterval = 1000000000 / Fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        for (int  i = 0; i < monster.length;i ++ ){
            if (monster[i] != null){
                if (monster[i].alive == true && monster[i].dying == false){
                    monster[i].update();
                }
                if (monster[i].alive == false){
                    monster[i] = null;
                }

            }
        }

        if (gameState == pauseState) {
            //nothing

        }


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g ;

        //DEBUG

        long drawStart = 0;
        if (keyH.showDebugText) {
            drawStart = System.nanoTime();
        }


        // TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        } else {


            //TILE
            tileM.draw(g2);

            // ADD ENTITIES TO THE LIST
            entityList.add(player);

            for (int i = 0; i < npc.length; i ++){
                if (npc[i] != null){
                    entityList.add(npc[i]);

                }
            }
            for (int i= 0;i < monster.length;i++ ){
                if (monster[i] != null){
                    entityList.add(monster[i]);
                }
            }
            for (int i= 0;i < obj.length;i++ ){
                if (obj[i] != null){
                    entityList.add(obj[i]);
                }
            }

            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY,e2.worldY);

                    return result;
                }
            });


            // DRAW ENTITIES
            for(int i = 0; i < entityList.size();i++){
                entityList.get(i).draw(g2);

            }
             // EMPTY ENTITY LIST
           entityList.clear();

         //UI
            ui.draw(g2);


            //  player.draw(g2);
            // OBJECT
            // for (int i = 0; i < obj.length; i++) {
            //  if (obj[i] != null) {
            //    obj[i].draw(g2, this);
            //  }
            //   }

            // NPC
            // for (int i = 0; i < npc.length; i++) {
            //  if (npc[i] != null) {
            //      npc[i].draw(g2);
            //   }
            // }

        }
        //DEBUG
        if (keyH.showDebugText == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial",Font.PLAIN,20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX"+player.worldX,x,y); y+= lineHeight;
            g2.drawString("WorldY"+player.worldY,x,y);y+= lineHeight;
            g2.drawString("Col"+(player.worldX + player.solidArea.x)/tileSize,x,y);y+= lineHeight;
            g2.drawString("Col"+(player.worldY + player.solidArea.y)/tileSize,x,y);y+= lineHeight;

            g2.drawString("Draw Time" + passed, x, y);

        }



    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {

        Se.setFile(i);
        Se.play();
    }
}
