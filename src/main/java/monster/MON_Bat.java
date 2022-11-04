package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_SlimeBall;

import java.util.Random;

public class MON_Bat extends Entity {

    public MON_Bat(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Bat";
        defaultSpeed = 4;
        speed = defaultSpeed;
        maxLife = 7;
        life = maxLife;
        attack = 7;
        defense = 0;
        exp = 7;

        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {

        up1 = setup("/monster/bat_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup("/monster/bat_down_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        down1 = setup("/monster/bat_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        down2 = setup("/monster/bat_down_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        left1 = setup("/monster/bat_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup("/monster/bat_down_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        right1 = setup("/monster/bat_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        right2 = setup("/monster/bat_down_2", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public void setAction() {
        if (onPath) {

        } else {

            // GET A RANDOM DIRECTION
            getRandomDirection(10);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
    }

    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;

        // SET THE MONSTER DROP
        if (i >= 45 && i < 60) {
            dropItem(new OBJ_Heart(gamePanel));
        }
        if (i >= 60 && i < 75) {
            dropItem(new OBJ_ManaCrystal(gamePanel));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_Coin(gamePanel));
        }
    }

}
