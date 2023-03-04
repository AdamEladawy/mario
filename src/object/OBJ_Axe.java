package object;

import adam.entity.Entity;
import main.GamePanel;

import java.awt.*;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePanel gp) {
        super(gp);

        type = type_axe;
        name = "Woodcutter's axe";
        down1 = setUp("/objects/axe",gp.tileSize,gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;

        description = "[Woodcutter's/naxe]Can cut some /ntrees";
    }
}
