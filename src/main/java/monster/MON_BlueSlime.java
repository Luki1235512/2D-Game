package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;


public class MON_BlueSlime extends Entity {

    public MON_BlueSlime(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = 2;
        name = "Blue Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {

        up1 = setup("/monster/blueslime_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup("/monster/blueslime_down_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        down1 = setup("/monster/blueslime_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        down2 = setup("/monster/blueslime_down_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        left1 = setup("/monster/blueslime_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup("/monster/blueslime_down_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        right1 = setup("/monster/blueslime_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        right2 = setup("/monster/blueslime_down_2", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gamePanel.getPlayer().getDirection();
    }

}
