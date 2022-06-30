package object;

import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_SlimeBall extends Projectile {

    public OBJ_SlimeBall(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "Slime ball";
        speed = 4;
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

    public Color getParticleColor() {
        return new Color(10, 30, 80);
    }

    public int getParticleSize() {
        return 10;
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 20;
    }

}
