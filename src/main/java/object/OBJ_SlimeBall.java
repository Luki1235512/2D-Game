package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_SlimeBall extends Projectile {

    public OBJ_SlimeBall(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "Slime ball";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        up1 = setup("/projectile/slime_ball_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup("/projectile/slime_ball_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        down1 = setup("/projectile/slime_ball_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        down2 = setup("/projectile/slime_ball_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        left1 = setup("/projectile/slime_ball_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup("/projectile/slime_ball_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        right1 = setup("/projectile/slime_ball_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        right2 = setup("/projectile/slime_ball_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
    }



}
