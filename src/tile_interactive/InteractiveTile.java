package tile_interactive;

import adam.entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity {

    public boolean destructible = false;
    GamePanel gp;

    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

    public void playSE() {

    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = null;
        return tile;
    }

    public void update() {

        if (invincible == true) {
            invincibleCounter++;
            if (invincible) {
                invincibleCounter++;
                if (invincibleCounter > 20) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
        }

    }

}
