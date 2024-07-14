package mario.game.entities.object;

import mario.game.drawings.GamePanel;
import mario.game.entities.Entity;
import mario.game.entities.Projectile;

import java.awt.*;

public class OBJ_ROCK extends Projectile {


    public OBJ_ROCK(GamePanel gp) {
        super(gp);

        name = "Rock";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
        color = new Color(40, 50, 0);
        speed = 1;
        maxLife = 20;
        size = 10;
    }

    public void getImage() {
        up1 = setUp("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        up2 = setUp("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down1 = setUp("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left1 = setUp("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left2 = setUp("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right1 = setUp("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right2 = setUp("/projectile/rock_down_1", gp.tileSize, gp.tileSize);


    }

    public boolean haveResource(Entity user) {

        return user.ammo >= useCost;
    }

    public void subtractResource(Entity user) {
        user.ammo -= useCost;
    }




}