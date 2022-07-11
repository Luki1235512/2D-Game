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

    public void update() {
        super.update();

        int xDistance = Math.abs(worldX - gamePanel.getPlayer().getWorldX());
        int yDistance = Math.abs(worldY - gamePanel.getPlayer().getWorldY());
        int tileDistance = (xDistance + yDistance) / gamePanel.getTileSize();

        if (!onPath && tileDistance < 5) {
            int i = new Random().nextInt(100) + 1;
            if (i > 50) {
                onPath = true;
            }
        }
        if (onPath && tileDistance > 20) {
            onPath = false;
        }
    }

    public void setAction() {

        if (onPath) {
            int goalCol = (gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x) / gamePanel.getTileSize();
            int goalRow = (gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y) / gamePanel.getTileSize();

            searchPath(goalCol, goalRow);

            int i = new Random().nextInt(200) + 1;
            if (i > 195 && !projectile.isAlive() && shotAvailableCounter == 30) {
                projectile.set(worldX, worldY, direction, true, this);
//                gamePanel.getProjectileList().add(projectile);

                for (int j = 0; j < gamePanel.getProjectile()[1].length; j++) {
                    if (gamePanel.getProjectile()[gamePanel.getCurrentMap()][j] == null) {
                        gamePanel.getProjectile()[gamePanel.getCurrentMap()][j] = projectile;
                        break;
                    }
                }

                shotAvailableCounter = 0;
            }

        }
        else {
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
