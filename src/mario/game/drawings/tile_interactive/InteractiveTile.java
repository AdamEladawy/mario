package mario.game.drawings.tile_interactive;

import mario.game.drawings.GamePanel;
import mario.game.entities.Entity;

public class InteractiveTile extends Entity {

    public boolean destructible = false;


    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);

    }

    public boolean isCorrectItem(Entity entity) {
        return false;
    }

    public void playSE() {

    }

    public void update() {


        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }


    }
    public InteractiveTile getDestroyedForm() {
        return tile;
    }

}