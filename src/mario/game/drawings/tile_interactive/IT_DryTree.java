package mario.game.drawings.tile_interactive;

import mario.game.drawings.GamePanel;
import mario.game.entities.Entity;

import java.awt.*;

public class IT_DryTree extends InteractiveTile {


    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);


        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setUp("/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
        speed=1;
        size = 6;
        color = new Color(65, 50, 30);
        tile = new IT_Trunk(gp, worldX / gp.tileSize, worldY / gp.tileSize);
        maxLife = 20;
    }

    public boolean isCorrectItem(Entity entity) {

        return entity.currentWeapon.type == type_axe;
    }

    public void playSE() {
        gp.playSE(11);
    }





}