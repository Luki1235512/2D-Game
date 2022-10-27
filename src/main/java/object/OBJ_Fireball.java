package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Fireball extends Projectile {

    public static final String objName = "Fireball";
    GamePanel gamePanel;

    public OBJ_Fireball(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        name = objName;
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();

    }

    public void getImage() {
        up1 = setup("/projectile/fireball_up_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup("/projectile/fireball_up_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        down1 = setup("/projectile/fireball_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        down2 = setup("/projectile/fireball_down_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        left1 = setup("/projectile/fireball_left_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup("/projectile/fireball_left_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        right1 = setup("/projectile/fireball_right_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        right2 = setup("/projectile/fireball_right_2", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public boolean haveResource(Entity user) {

        return user.getMana() >= useCost;
    }

    public void subtractResource(Entity user) {
        user.decreaseMana(useCost);
    }

    public Color getParticleColor() {
        return new Color(240, 50, 0);
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
