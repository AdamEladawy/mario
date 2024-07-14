package mario.game.play;

import mario.game.drawings.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
    private final GamePanel gp;
    private Graphics2D g2;

    //DEBUG
    private boolean showDebugText = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public boolean isShowDebugText() {
        return showDebugText;
    }

    public void setShowDebugText(boolean showDebugText) {
        this.showDebugText = showDebugText;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        int code = e.getKeyCode();

        //Title State
        if (gp.getGameState() == gp.titleState) {
            titleState(code);
        }
//PLAY STATE
        else if (gp.getGameState() == gp.playState) {
            playState(code);
        }
        // PAUSE STATE
        else if (gp.getGameState() == gp.pauseState) {
            pauseState(code);

        }


        // DIALOGUE STATE

        else if (gp.getGameState() == gp.dialogueState) {
            dialogueState(code);
        }
// CHARACTER STATE
        else if (gp.getGameState() == gp.characterState) {
            characterState(code);
        }
        // OPTIONS STATE
        else if (gp.getGameState() == gp.getOptionsState()) {
            optionsState(code);
        }
        // GAME OVER STATE
        else if (gp.getGameState() == gp.gameOverState) {
            gameOverState(code);
        }
        // TRADE STATE
        else if (gp.getGameState() == gp.getTradeState()) {
            tradeState(code);
        }
    }

    //DEBUG
    public void titleState(int code) {
        ///  if (gp.ui.titleScreenState == 0) {

        if (code == KeyEvent.VK_W) {
            gp.ui.setCommandNum(gp.ui.getCommandNum() - 1, 2);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.setCommandNum(gp.ui.getCommandNum() + 1, 2);

        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.getCommandNum() == 0) {
                gp.setGameState(gp.playState);/// gp.ui.titleScreenState
                gp.playMusic(0);


            }
            if (gp.ui.getCommandNum() == 1) {
                // add later
            }
            if (gp.ui.getCommandNum() == 2) {
                System.exit(0);
            }
        }


//        / } else if (gp.ui.titleScreenState == 1) {
//
///                if (code == KeyEvent.VK_W) {
///                    gp.ui.commandNum--;
///                    if (gp.ui.commandNum < 0) {
///                        gp.ui.commandNum = 3;
///                    }
///                }
///                if (code == KeyEvent.VK_S) {
///                    gp.ui.commandNum++;
///                    if (gp.ui.commandNum > 2) {
///                        gp.ui.commandNum = 3;
///                    }
///
///                }
///                if (code == KeyEvent.VK_ENTER) {
///                    if (gp.ui.commandNum == 0) {
///                        System.out.println("Do some fighting specific stuff!");
///                        gp.gameState = gp.playState;
///                        gp.playMusic(0);
///                    }
///                    if (gp.ui.commandNum == 1) {
///                        System.out.println("Do some thief specific stuff!");
///                        gp.gameState = gp.playState;
///                        gp.playMusic(0);
///                    }
///                    if (gp.ui.commandNum == 2) {
///                        System.out.println("Do some  specific stuff!");
///                        gp.gameState = gp.playState;
///                        gp.playMusic(0);
///                    }
///                   if (gp.ui.commandNum == 3) {
///                        gp.ui.titleScreenState = 0;
///                    }
///                }
//
//
//        /}

    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;

        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;

        }
        if (code == KeyEvent.VK_D) {

            rightPressed = true;
        }

        if (code == KeyEvent.VK_P) {
            gp.setGameState(gp.pauseState);

        }
        if (code == KeyEvent.VK_C) {
            gp.setGameState(gp.characterState);
        }
        if (code == KeyEvent.VK_SPACE) { // maybe enter
            enterPressed = true;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.setGameState(gp.getOptionsState());
        }


        if (code == KeyEvent.VK_T) {
            showDebugText=!showDebugText;

        }
        if (code == KeyEvent.VK_R) {

            switch (gp.currentMap) {
                case 0:
                    gp.getTileM().loadMap("/maps/worldV3.txt", 0);
                    break;
                case 1:
                    gp.getTileM().loadMap("/maps/interior01.txt", 1);
                    break;
            }


        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.setGameState(gp.playState);

        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_SPACE) { /// maybe enter
            gp.setGameState(gp.playState);
        }

    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.setGameState(gp.playState);
        }
        if (code == KeyEvent.VK_SPACE) {// maybe eneter
            gp.player.selectItem();
        }
        playerInventory(code);

    }

    public void optionsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.setGameState(gp.playState);
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNum = switch (gp.ui.getSubState()) {
            case 0 -> 5;
            case 3 -> 1;
            default -> 0;
        };

        if (code == KeyEvent.VK_W) {
            gp.ui.setCommandNum(gp.ui.getCommandNum() - 1, maxCommandNum);
            gp.playSE(Sound.CURSOR);

        }
        if (code == KeyEvent.VK_S) {
            gp.ui.setCommandNum(gp.ui.getCommandNum() + 1, maxCommandNum);
            gp.playSE(Sound.CURSOR);

        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.getSubState() == 0) {
                if (gp.ui.getCommandNum() == 1 && gp.getMusic().getVolumeScale() > 0) {
                    gp.getMusic().setVolumeScale(gp.getMusic().getVolumeScale() - 1);
                    gp.getMusic().checkVolume();
                    gp.playSE(Sound.CURSOR);
                }
                if (gp.ui.getCommandNum() == 2 && gp.getSe().getVolumeScale() > 0) {
                    gp.getSe().setVolumeScale(gp.getSe().getVolumeScale() - 1);
                    gp.playSE(Sound.CURSOR);
                }
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.getSubState() == 0) {
                if (gp.ui.getCommandNum() == 1 && gp.getMusic().getVolumeScale() < 5) {
                    gp.getMusic().setVolumeScale(gp.getMusic().getVolumeScale() + 1);
                    gp.getMusic().checkVolume();
                    gp.playSE(Sound.CURSOR);
                }
                if (gp.ui.getCommandNum() == 2 && gp.getSe().getVolumeScale() < 5) {
                    gp.getSe().setVolumeScale(gp.getSe().getVolumeScale() + 1);
                    gp.playSE(Sound.CURSOR);
                }
            }
        }

    }

    public void gameOverState(int code) {

        if (code == KeyEvent.VK_W) {

            gp.ui.setCommandNum(gp.ui.getCommandNum() - 1, 1);
            gp.playSE(Sound.CURSOR);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.setCommandNum(gp.ui.getCommandNum() + 1, 1);
            gp.playSE(Sound.CURSOR);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.getCommandNum() == 0) {
                gp.setGameState(gp.playState);
                gp.retry();
                gp.playMusic(0);
            } else if (gp.ui.getCommandNum() == 1) {
                gp.setGameState(gp.titleState);
                gp.restart();
            }
        }
    }

    public void tradeState(int code) {

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (gp.ui.getSubState() == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.setCommandNum(gp.ui.getCommandNum() - 1, 2);

                gp.playSE(Sound.CURSOR);
            }
            if (code == KeyEvent.VK_S) {

                gp.ui.setCommandNum(gp.ui.getCommandNum() + 1, 2);
                gp.playSE(Sound.CURSOR);
            }
        }
        if (gp.ui.getSubState() == 1) {
            npcInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.setSubState(0);
            }
        }
        if (gp.ui.getSubState() == 2) {
            playerInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.setSubState(0);
            }
        }
    }

    public void npcInventory(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.setNpcSlotRow(gp.ui.getNpcSlotRow() - 1);
            gp.playSE(Sound.CURSOR);
        }
        if (code == KeyEvent.VK_A) {
            gp.ui.setNpcSlotCol(gp.ui.getNpcSlotCol() - 1);
            gp.playSE(Sound.CURSOR);

        }
        if (code == KeyEvent.VK_S) {
            gp.ui.setNpcSlotRow(gp.ui.getNpcSlotRow() + 1);
            gp.playSE(Sound.CURSOR);

        }
        if (code == KeyEvent.VK_D) {
            gp.ui.setNpcSlotCol(gp.ui.getNpcSlotCol() + 1);
            gp.playSE(Sound.CURSOR);


        }
    }

    public void playerInventory(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.setPlayerSlotRow(gp.ui.getPlayerSlotRow() - 1);

            gp.playSE(Sound.CURSOR);
        }
        if (code == KeyEvent.VK_A) {
            gp.ui.setPlayerSlotCol(gp.ui.getPlayerSlotCol() - 1);
            gp.playSE(Sound.CURSOR);

        }
        if (code == KeyEvent.VK_S) {

            gp.ui.setPlayerSlotRow(gp.ui.getPlayerSlotRow() + 1);
            gp.playSE(Sound.CURSOR);

        }
        if (code == KeyEvent.VK_D) {

            gp.ui.setPlayerSlotCol(gp.ui.getPlayerSlotCol() + 1);
            gp.playSE(Sound.CURSOR);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;

        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;

        }
        if (code == KeyEvent.VK_D) {

            rightPressed = false;

        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }

    }
}