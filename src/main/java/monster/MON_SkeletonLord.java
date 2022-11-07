package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_SkeletonLord extends Entity {

    GamePanel gamePanel;
    public static final String monName = "Skeleton Lord";

    public MON_SkeletonLord(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_monster;
        name = monName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 50;
        life = maxLife;
        attack = 10;
        defense = 2;
        exp = 50;
        knockBackPower = 5;

        int size = gamePanel.getTileSize() * 5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48 * 2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;
        motion1_duration = 25;
        motion2_duration = 50;

        getImage();
        getAttackImage();
    }

    public void getImage() {

        int i = 5;

        up1 = setup("/monster/skeletonlord/skeletonlord_up_1", gamePanel.getTileSize() * i, gamePanel.getTileSize() * i);
        up2 = setup("/monster/skeletonlord/skeletonlord_up_2", gamePanel.getTileSize() * i, gamePanel.getTileSize() * i);
        down1 = setup("/monster/skeletonlord/skeletonlord_down_1", gamePanel.getTileSize() * i, gamePanel.getTileSize() * i);
        down2 = setup("/monster/skeletonlord/skeletonlord_down_2", gamePanel.getTileSize() * i, gamePanel.getTileSize() * i);
        left1 = setup("/monster/skeletonlord/skeletonlord_left_1", gamePanel.getTileSize() * i, gamePanel.getTileSize() * i);
        left2 = setup("/monster/skeletonlord/skeletonlord_left_2", gamePanel.getTileSize() * i, gamePanel.getTileSize() * i);
        right1 = setup("/monster/skeletonlord/skeletonlord_right_1", gamePanel.getTileSize() * i, gamePanel.getTileSize() * i);
        right2 = setup("/monster/skeletonlord/skeletonlord_right_2", gamePanel.getTileSize() * i, gamePanel.getTileSize() * i);
    }

    public void getAttackImage() {

        int i = 5;

        attackUp1 = setup("/monster/skeletonlord/skeletonlord_attack_up_1", gamePanel.getTileSize() * i, gamePanel.getTileSize() * i * 2);
        attackUp2 = setup("/monster/skeletonlord/skeletonlord_attack_up_2", gamePanel.getTileSize() * i, gamePanel.getTileSize() * i * 2);
        attackDown1 = setup("/monster/skeletonlord/skeletonlord_attack_down_1", gamePanel.getTileSize() * i, gamePanel.getTileSize() * i * 2);
        attackDown2 = setup("/monster/skeletonlord/skeletonlord_attack_down_2", gamePanel.getTileSize() * i, gamePanel.getTileSize() * i * 2);
        attackLeft1 = setup("/monster/skeletonlord/skeletonlord_attack_left_1", gamePanel.getTileSize() * i * 2, gamePanel.getTileSize() * i);
        attackLeft2 = setup("/monster/skeletonlord/skeletonlord_attack_left_2", gamePanel.getTileSize() * i * 2, gamePanel.getTileSize() * i);
        attackRight1 = setup("/monster/skeletonlord/skeletonlord_attack_right_1", gamePanel.getTileSize() * i * 2, gamePanel.getTileSize() * i);
        attackRight2 = setup("/monster/skeletonlord/skeletonlord_attack_right_2", gamePanel.getTileSize() * i * 2, gamePanel.getTileSize() * i);
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
            getRandomDirection(120);
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
