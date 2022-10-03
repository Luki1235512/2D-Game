package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_SlimeBall;

import java.util.Random;

public class MON_Orc extends Entity {

    public MON_Orc(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Orc";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 8;
        defense = 2;
        exp = 10;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();
    }

//    TODO: Create Orc images
    public void getImage() {

        up1 = setup("/placeholders/placeholder", gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup("/placeholders/placeholder", gamePanel.getTileSize(), gamePanel.getTileSize());
        down1 = setup("/placeholders/placeholder", gamePanel.getTileSize(), gamePanel.getTileSize());
        down2 = setup("/placeholders/placeholder", gamePanel.getTileSize(), gamePanel.getTileSize());
        left1 = setup("/placeholders/placeholder", gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup("/placeholders/placeholder", gamePanel.getTileSize(), gamePanel.getTileSize());
        right1 = setup("/placeholders/placeholder", gamePanel.getTileSize(), gamePanel.getTileSize());
        right2 = setup("/placeholders/placeholder", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public void getAttackImage() {
        attackUp1 = setup("/placeholders/placeholder", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
        attackUp2 = setup("/placeholders/placeholder", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
        attackDown1 = setup("/placeholders/placeholder", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
        attackDown2 = setup("/placeholders/placeholder", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
        attackLeft1 = setup("/placeholders/placeholder", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
        attackLeft2 = setup("/placeholders/placeholder", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
        attackRight1 = setup("/placeholders/placeholder", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
        attackRight2 = setup("/placeholders/placeholder", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
    }

    public void setAction() {
        if (onPath) {

            // CHECK IF IT STOPS CHASING
            checkStopChasingOrNot(gamePanel.getPlayer(), 15, 100);

            // SEARCH THE DIRECTION TO GO
            searchPath(getGoalCol(gamePanel.getPlayer()), getGoalRow(gamePanel.getPlayer()));
        } else {
            // CHECK IF IT STARTS CHASING
            checkStartChasingOrNot(gamePanel.getPlayer(), 5, 100);

            // GET A RANDOM DIRECTION
            getRandomDirection();
        }

        // CHECK IF IT ATTACKS
        if (!attacking) {
            checkAttackOrNot(30, gamePanel.getTileSize() * 4, gamePanel.getTileSize());
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
