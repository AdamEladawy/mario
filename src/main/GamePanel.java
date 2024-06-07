package main;

import adam.entity.Entity;
import adam.entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 10;
    public  int currentMap = 0;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    final int orginalTileSize = 16; //16x16 tile
    final int scale = 3;
    public final int tileSize = orginalTileSize * scale;// 48x48 title
    public final int screenWidth = tileSize * maxScreenCol;// 768 pixels
    //FOR FULL SCREEN
    int screenWidth2 = screenWidth;
    public final int screenHeight = tileSize * maxScreenRow;//576 pixels
    int screenHeight2 = screenHeight;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public KeyHandler keyH = new KeyHandler(this);
    public EventHandler eHandler = new EventHandler(this);
    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity[][] obj = new Entity [maxMap][20];
    public Entity[] [] npc = new Entity[maxMap][10];
    public Entity[] [] monster = new Entity[maxMap][20];
    public InteractiveTile iTile[] [] = new InteractiveTile[maxMap][50];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    public final int gameOverState = 6;
    //GAME STATE
    public int gameState;
    public final int optionsState = 5;
    public final int transitionState = 7;
    public final int tradeState = 8;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;
    ArrayList<Entity> entityList = new ArrayList<>();
    Config config = new Config(this);
    Thread gameThread;

    //fps
    int Fps = 60;
    TileManager tileM = new TileManager(this);
    Sound music = new Sound();
    // ENTITY AND OBJECT
    Sound Se = new Sound();


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
        aSetter.setInteractiveTiles();
        // playMusic(0);

        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

       if (fullScreenOn == true) {
           setFullScreen();
       }

    }
    public void retry(){

        player.setDefaultPosition();
        player.restoreLifeandMana();
        aSetter.setNpc();
        aSetter.setMonster();

    }
    public void restart(){

        player.setDefaultValue();
        player.setDefaultPosition();
        player.restoreLifeandMana();
        player.setItems();
        aSetter.setObject();
        aSetter.setNpc();
        aSetter.setMonster();
        aSetter.setInteractiveTiles();
    }

    public void setFullScreen(){
        // GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // GET SCREEN HEIGHT AND WIDTH
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
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
        double drawInterval = 1000000000f / Fps;
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
                // repaint();
                drawToTempScreen();// draw everything to the buffered image
                drawToScreen();// draw the buffered image to the screen
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                //System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }

            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive && !monster[currentMap][i].dying) {
                        monster[currentMap][i].update();
                    }
                    if (!monster[currentMap][i].alive) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }

                }
            }

            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive) {
                        projectileList.get(i).update();
                    }
                    if (!projectileList.get(i).alive) {
                        projectileList.remove(i);
                    }

                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    if (particleList.get(i).alive) {
                        particleList.get(i).update();
                    }
                    if (!particleList.get(i).alive) {
                        particleList.remove(i);
                    }

                }
            }
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].update();
                }
            }

        }
        if (gameState == pauseState) {
            //nothing

        }

    }

    public void drawToTempScreen() {

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


            // INTERACTIVE TILE
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }

            // ADD ENTITIES TO THE LIST
            entityList.add(player);

            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);

                }
            }
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }

            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }

            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }
            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);

                    return result;
                }
            });


            // DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
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
        if (keyH.showDebugText) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX" + player.worldX, x, y);
            y += lineHeight;
            g2.drawString("WorldY" + player.worldY, x, y);
            y += lineHeight;
            g2.drawString("Col" + (player.worldX + player.solidArea.x) / tileSize, x, y);
            y += lineHeight;
            g2.drawString("Col" + (player.worldY + player.solidArea.y) / tileSize, x, y);
            y += lineHeight;

            g2.drawString("Draw Time" + passed, x, y);

        }

    }
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//
//        //DEBUG
//
//        long drawStart = 0;
//        if (keyH.showDebugText) {
//            drawStart = System.nanoTime();
//        }
//
//
//        // TITLE SCREEN
//        if (gameState == titleState) {
//            ui.draw(g2);
//        } else {
//
//
//            //TILE
//            tileM.draw(g2);
//
//
//            // INTERACTIVE TILE
//            for (int i = 0; i < iTile.length; i++){
//                if (iTile[i] != null){
//                    iTile[i].draw(g2);
//                }
//            }
//
//            // ADD ENTITIES TO THE LIST
//            entityList.add(player);
//
//            for (int i = 0; i < npc.length; i++) {
//                if (npc[i] != null) {
//                    entityList.add(npc[i]);
//
//                }
//            }
//            for (int i = 0; i < obj.length; i++) {
//                if (obj[i] != null) {
//                    entityList.add(obj[i]);
//                }
//            }
//            for (int i = 0; i < monster.length; i++) {
//                if (monster[i] != null) {
//                    entityList.add(monster[i]);
//                }
//            }
//
//            for (int i = 0; i < projectileList.size(); i++) {
//                if (projectileList.get(i) != null) {
//                    entityList.add(projectileList.get(i));
//                }
//            }
//
//            for (int i = 0; i < particleList.size(); i++) {
//                if (particleList.get(i) != null) {
//                    entityList.add(particleList.get(i));
//                }
//            }
//            // SORT
//            Collections.sort(entityList, new Comparator<Entity>() {
//                @Override
//                public int compare(Entity e1, Entity e2) {
//                    int result = Integer.compare(e1.worldY, e2.worldY);
//
//                    return result;
//                }
//            });
//
//
//            // DRAW ENTITIES
//            for (int i = 0; i < entityList.size(); i++) {
//                entityList.get(i).draw(g2);
//
//            }
//            // EMPTY ENTITY LIST
//            entityList.clear();
//
//            //UI
//            ui.draw(g2);
//
//
//            //  player.draw(g2);
//            // OBJECT
//            // for (int i = 0; i < obj.length; i++) {
//            //  if (obj[i] != null) {
//            //    obj[i].draw(g2, this);
//            //  }
//            //   }
//
//            // NPC
//            // for (int i = 0; i < npc.length; i++) {
//            //  if (npc[i] != null) {
//            //      npc[i].draw(g2);
//            //   }
//            // }
//
//        }
//        //DEBUG
//        if (keyH.showDebugText) {
//            long drawEnd = System.nanoTime();
//            long passed = drawEnd - drawStart;
//
//            g2.setFont(new Font("Arial", Font.PLAIN, 20));
//            g2.setColor(Color.white);
//            int x = 10;
//            int y = 400;
//            int lineHeight = 20;
//
//            g2.drawString("WorldX" + player.worldX, x, y);
//            y += lineHeight;
//            g2.drawString("WorldY" + player.worldY, x, y);
//            y += lineHeight;
//            g2.drawString("Col" + (player.worldX + player.solidArea.x) / tileSize, x, y);
//            y += lineHeight;
//            g2.drawString("Col" + (player.worldY + player.solidArea.y) / tileSize, x, y);
//            y += lineHeight;
//
//            g2.drawString("Draw Time" + passed, x, y);
//
//        }
//
//        g2.dispose();
//
//    }

    public void drawToScreen() {

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
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
