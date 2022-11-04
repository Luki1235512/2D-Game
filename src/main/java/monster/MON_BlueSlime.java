package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_SlimeBall;

import java.util.Random;


public class MON_BlueSlime extends Entity {

    public MON_BlueSlime(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Blue Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
        projectile = new OBJ_SlimeBall(gamePanel);

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
        if (onPath) {

            // CHECK IF IT STOPS CHASING
            checkStopChasingOrNot(gamePanel.getPlayer(), 15, 100);

            // SEARCH THE DIRECTION TO GO
            searchPath(getGoalCol(gamePanel.getPlayer()), getGoalRow(gamePanel.getPlayer()));

            // CHECK IF IT SHOOTS THE PROJECTILE
            checkShootOrNot(200, 30);
        } else {
            // CHECK IF IT STARTS CHASING
            checkStartChasingOrNot(gamePanel.getPlayer(), 5, 100);

            // GET A RANDOM DIRECTION
            getRandomDirection(120);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
//        direction = gamePanel.getPlayer().getDirection();
        onPath = true;
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
