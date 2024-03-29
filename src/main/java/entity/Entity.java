package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Entity {

    protected GamePanel gamePanel;

    protected BufferedImage up1;
    protected BufferedImage up2;
    protected BufferedImage down1;
    protected BufferedImage down2;
    protected BufferedImage left1;
    protected BufferedImage left2;
    protected BufferedImage right1;
    protected BufferedImage right2;

    protected BufferedImage attackUp1;
    protected BufferedImage attackUp2;
    protected BufferedImage attackDown1;
    protected BufferedImage attackDown2;
    protected BufferedImage attackLeft1;
    protected BufferedImage attackLeft2;
    protected BufferedImage attackRight1;
    protected BufferedImage attackRight2;

    protected BufferedImage standUp;
    protected BufferedImage standLeft;
    protected BufferedImage standRight;
    protected BufferedImage standDown;

    protected BufferedImage guardUp;
    protected BufferedImage guardDown;
    protected BufferedImage guardLeft;
    protected BufferedImage guardRight;

    protected BufferedImage image;
    protected BufferedImage image2;
    protected BufferedImage image3;

    protected Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    protected Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    protected int solidAreaDefaultX;
    protected int solidAreaDefaultY;
    protected boolean collisionOn = false;

    protected String[][] dialogues = new String[20][20];
    protected Entity attacker;
    protected Entity linkedEntity;

    // STATE
    protected int worldX;
    protected int worldY;
    protected String direction = "down";
    protected int spriteNum = 1;
    protected int dialogueSet = 0;
    protected int dialogueIndex = 0;
    protected boolean collision = false;
    protected boolean invincible = false;
    protected boolean attacking = false;
    protected boolean alive = true;
    protected boolean dying = false;
    protected boolean hpBarOn = false;
    protected boolean onPath = false;
    protected boolean knockBack = false;
    protected String knockBackDirection;
    protected boolean guarding = false;
    protected boolean transparent = false;
    protected boolean offBalance = false;
    protected Entity loot;
    protected boolean opened = false;
    protected boolean inRage = false;

    // COUNTER
    protected int spriteCounter = 0;
    protected int actionLockCounter = 0;
    protected int invincibleCounter = 0;
    protected int shotAvailableCounter = 0;
    protected int dyingCounter = 0;
    protected int hpBarCounter = 0;
    protected int knockBackCounter = 0;
    protected int guardCounter = 0;
    protected int offBalanceCounter = 0;

    // CHARACTER ATTRIBUTES
    protected String name;
    protected int defaultSpeed;
    protected int speed;
    protected int maxLife;
    protected int life;
    protected int maxMana;
    protected int mana;
    protected int level;
    protected int strength;
    protected int toughness;
    protected int attack;
    protected int defense;
    protected int exp;
    protected int nextLevelExp;
    protected int coin;
    protected int motion1_duration;
    protected int motion2_duration;
    protected Entity currentWeapon;
    protected Entity currentShield;
    protected Entity currentLight;
    protected Projectile projectile;

    // ITEM ATTRIBUTES
    protected final ArrayList<Entity> inventory = new ArrayList<>();
    protected final int maxInventorySize = 20;
    protected int value;
    protected int attackValue;
    protected int defenseValue;
    protected String description = "";
    protected int useCost;
    protected int price;
    protected int knockBackPower = 0;
    protected boolean stackable = false;
    protected int amount = 1;
    protected int lightRadius;

    // TYPE
    protected int type;
    protected final int type_player = 0;
    protected final int type_npc = 1;
    protected final int type_monster = 2;
    protected final int type_sword = 3;
    protected final int type_axe = 4;
    protected final int type_shield = 5;
    protected final int type_consumable = 6;
    protected final int type_pickupOnly = 7;
    protected final int type_obstacle = 8;
    protected final int type_light = 9;
    protected final int type_pickaxe = 10;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void searchPath(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x) / gamePanel.getTileSize();
        int startRow = (worldY + solidArea.y) / gamePanel.getTileSize();

        gamePanel.getPathFinder().setNodes(startCol, startRow, goalCol, goalRow);

        if (gamePanel.getPathFinder().search()) {

            int nextX = gamePanel.getPathFinder().getPathList().get(0).getCol() * gamePanel.getTileSize();
            int nextY = gamePanel.getPathFinder().getPathList().get(0).getRow() * gamePanel.getTileSize();

            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gamePanel.getTileSize()) {
                direction = "up";
            }
            else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gamePanel.getTileSize()) {
                direction = "down";
            }
            else if (enTopY >= nextY && enBottomY < nextY + gamePanel.getTileSize()) {
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX) {
                direction = "up";
                checkCollision();
                if (collisionOn) {
                    direction = "left";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX) {
                direction = "up";
                checkCollision();
                if (collisionOn) {
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                checkCollision();
                if (collisionOn) {
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX) {
                direction = "down";
                checkCollision();
                if (collisionOn) {
                    direction = "right";
                }
            }

            // If reaches the goal, stop the search
//            int nextCol = gamePanel.getPathFinder().getPathList().get(0).getCol();
//            int nextRow = gamePanel.getPathFinder().getPathList().get(0).getRow();
//
//            if (nextCol == goalCol && nextRow == goalRow) {
//                onPath = false;
//            }
        }
    }

    public int getDetected(Entity user, Entity[][] target, String targetName) {
        int index = Integer.MAX_VALUE;

        // Check the surrounding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction) {
            case "up":
                nextWorldY = user.getTopY() - gamePanel.getPlayer().speed;
                break;
            case "down":
                nextWorldY = user.getBottomY() + gamePanel.getPlayer().speed;
                break;
            case "left":
                nextWorldX = user.getLeftX() - gamePanel.getPlayer().speed;
                break;
            case "right":
                nextWorldX = user.getRightX() + gamePanel.getPlayer().speed;
                break;
        }

        int col = nextWorldX / gamePanel.getTileSize();
        int row = nextWorldY / gamePanel.getTileSize();

        for (int i = 0; i < target[1].length; i++) {
            if (target[gamePanel.getCurrentMap()][i] != null) {
                if (target[gamePanel.getCurrentMap()][i].getCol() == col &&
                    target[gamePanel.getCurrentMap()][i].getRow() == row &&
                    target[gamePanel.getCurrentMap()][i].name.equals(targetName)) {

                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    public int getXDistance(Entity target) {
        return Math.abs(getCenterX() - target.getCenterX());
    }

    public int getYDistance(Entity target) {
        return Math.abs(getCenterY() - target.getCenterY());
    }

    public int getTileDistance(Entity target) {
        return (getXDistance(target) + getYDistance(target)) / gamePanel.getTileSize();
    }

    public int getGoalCol(Entity target) {
        return (target.getWorldX() + target.getSolidArea().x) / gamePanel.getTileSize();
    }

    public int getGoalRow(Entity target) {
        return (target.getWorldY() + target.getSolidArea().y) / gamePanel.getTileSize();
    }

    public void resetCounter() {
        spriteCounter = 0;
        actionLockCounter = 0;
        invincibleCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }

    public void setLoot(Entity loot) {

    }

    public void setAction() {

    }

    public void move(String direction) {

    }

    public void damageReaction() {

    }

    public void speak() {

    }

    public void facePlayer() {
        // TODO
    }

    public void interact() {

        switch (gamePanel.getPlayer().direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void startDialogue(Entity entity, int setNum) {
        gamePanel.setGameState(gamePanel.getDialogueState());
        gamePanel.getUi().setNpc(entity);
        dialogueSet = setNum;
    }

    public boolean use(Entity entity) {
        return false;
    }
    public void checkDrop() {

    }

    public void dropItem(Entity droppedItem) {

        for (int i = 0; i < gamePanel.getObj()[1].length; i++) {
            if (gamePanel.getObj()[gamePanel.getCurrentMap()][i] == null) {
                gamePanel.getObj()[gamePanel.getCurrentMap()][i] = droppedItem;
                gamePanel.getObj()[gamePanel.getCurrentMap()][i].worldX = worldX;
                gamePanel.getObj()[gamePanel.getCurrentMap()][i].worldY = worldY;
                break;
            }
        }
    }

    public Color getParticleColor() {
        return null;
    }

    public int getParticleSize() {
        return 0;
    }

    public int getParticleSpeed() {
        return 0;
    }

    public int getParticleMaxLife() {
        return 0;
    }

    public void generateParticle(Entity generator, Entity target) {
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle particle1 = new Particle(gamePanel, target, color, size, speed, maxLife, -2, -1);
        Particle particle2 = new Particle(gamePanel, target, color, size, speed, maxLife, 2, -1);
        Particle particle3 = new Particle(gamePanel, target, color, size, speed, maxLife, -2, 1);
        Particle particle4 = new Particle(gamePanel, target, color, size, speed, maxLife, 2, 1);

        gamePanel.getParticleList().add(particle1);
        gamePanel.getParticleList().add(particle2);
        gamePanel.getParticleList().add(particle3);
        gamePanel.getParticleList().add(particle4);
    }

    public void checkCollision() {
        collisionOn = false;
        gamePanel.getCollisionChecker().checkTile(this);
        gamePanel.getCollisionChecker().checkObject(this, false);
        gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getNpc());
        gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getMonster());
        gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getITile());
        boolean contactPlayer = gamePanel.getCollisionChecker().checkPlayer(this);

        if (this.type == type_monster && contactPlayer) {
            damagePlayer(attack);
        }
    }

    public void update() {

        if (knockBack) {

            checkCollision();

            if (collisionOn) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else {
                switch (knockBackDirection) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            knockBackCounter++;
            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }

        else if (attacking) {
            attacking();
        }

        else {
            setAction();
            checkCollision();

            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 24) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if (offBalance) {
            offBalanceCounter++;
            if (offBalanceCounter > 60) {
                offBalance = false;
                offBalanceCounter = 0;
            }
        }

    }

    public void checkAttackOrNot(int rate, int straight, int horizontal) {
        boolean targetInRange = false;
        int xDis = getXDistance(gamePanel.getPlayer());
        int yDis = getYDistance(gamePanel.getPlayer());

        switch (direction) {
            case "up":
                if (gamePanel.getPlayer().getCenterY() < getCenterY() && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "down":
                if (gamePanel.getPlayer().getCenterY() > getCenterY() && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "left":
                if (gamePanel.getPlayer().getCenterX() < getCenterX() && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "right":
                if (gamePanel.getPlayer().getCenterX() > getCenterX() && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
        }

        if (targetInRange) {
            // CHECK IF IT INITIATES AN ATTACK
            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }

    public void checkShootOrNot(int rate, int shootInterval) {
        int i = new Random().nextInt(rate);
        if (i == 0 && !projectile.isAlive() && shotAvailableCounter == shootInterval) {
            projectile.set(worldX, worldY, direction, true, this);
//            gamePanel.getProjectileList().add(projectile);

            // CHECK VACANCY
            for (int j = 0; j < gamePanel.getProjectile()[1].length; j++) {
                if (gamePanel.getProjectile()[gamePanel.getCurrentMap()][j] == null) {
                    gamePanel.getProjectile()[gamePanel.getCurrentMap()][j] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }

    public void checkStartChasingOrNot(Entity target, int distance, int rate) {
        if (getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = true;
            }
        }
    }

    public void checkStopChasingOrNot(Entity target, int distance, int rate) {
        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }
        }
    }

    public void getRandomDirection(int interval) {
        actionLockCounter++;

        if (actionLockCounter > interval) {
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

    public void moveTowardPlayer(int interval) {
        actionLockCounter++;

        if (actionLockCounter > interval) {
            if (getXDistance(gamePanel.getPlayer()) > getYDistance(gamePanel.getPlayer())) {
                if (gamePanel.getPlayer().getCenterX() < getCenterX()) {
                    direction = "left";
                } else {
                    direction = "right";
                }
            } else if (getXDistance(gamePanel.getPlayer()) < getYDistance(gamePanel.getPlayer())) {
                if (gamePanel.getPlayer().getCenterY() < getCenterY()) {
                    direction = "up";
                } else {
                    direction = "down";
                }
            }
            actionLockCounter = 0;
        }
    }

    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= motion1_duration) {
            spriteNum = 1;
        }
        if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type == type_monster) {
                if (gamePanel.getCollisionChecker().checkPlayer(this)) {
                    damagePlayer(attack);
                }
            } else { // PLAYER
                int monsterIndex = gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getMonster());
                gamePanel.getPlayer().damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                int iTileIndex = gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getITile());
                gamePanel.getPlayer().damageInteractiveTile(iTileIndex);

                int projectileIndex = gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getProjectile());
                gamePanel.getPlayer().damageProjectile(projectileIndex);
            }


            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > motion2_duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void damagePlayer(int attack) {
        if (!gamePanel.getPlayer().invincible) {

            int damage = attack - gamePanel.getPlayer().defense;

            // GET AN OPPOSITE DIRECTION OF THIS ATTACKER
            String canGuardDirection = getOppositeDirection(direction);

//            TODO: Add sound effects
            if (gamePanel.getPlayer().guarding && gamePanel.getPlayer().direction.equals(canGuardDirection)) {
                // PARRY
                if (gamePanel.getPlayer().guardCounter < 10) {
                    damage = 0;
//                    gamePanel.playSE();
                    setKnockBack(this, gamePanel.getPlayer(), knockBackPower);
                    offBalance = true;
                    spriteCounter =- 60;
                } else {
                    // NORMAL GUARD
                    damage /= 3;
//                gamePanel.playSE();
                }
            } else {
                // NOT GUARDING
                gamePanel.playSE(6);
                if (damage < 0) {
                    damage = 0;
                }
            }

            if (damage != 0) {
                gamePanel.getPlayer().transparent = true;
                setKnockBack(gamePanel.getPlayer(), this, knockBackPower);
            }

            gamePanel.getPlayer().life -= damage;
            gamePanel.getPlayer().invincible = true;
        }
    }

    public String getOppositeDirection(String direction) {
        String oppositeDirection = "";
        switch (direction) {
            case "up":
                oppositeDirection = "down";
                break;
            case "down":
                oppositeDirection = "up";
                break;
            case "left":
                oppositeDirection = "right";
                break;
            case "right":
                oppositeDirection = "left";
                break;
        }
        return oppositeDirection;
    }

    public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gamePanel.getPlayer().worldX + gamePanel.getPlayer().getScreenX();
        int screenY = worldY - gamePanel.getPlayer().worldY + gamePanel.getPlayer().getScreenY();

        if (worldX + gamePanel.getTileSize() * 5 > gamePanel.getPlayer().worldX - gamePanel.getPlayer().getScreenX() &&
                worldX - gamePanel.getTileSize() < gamePanel.getPlayer().worldX + gamePanel.getPlayer().getScreenX() &&
                worldY + gamePanel.getTileSize() * 5 > gamePanel.getPlayer().worldY - gamePanel.getPlayer().getScreenY() &&
                worldY - gamePanel.getTileSize() < gamePanel.getPlayer().worldY + gamePanel.getPlayer().getScreenY()) {

            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch (direction) {
                case "up":
                    if (!attacking) {
                        if (spriteNum == 0) {
                            image = standUp;
                        }
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                    }
                    if (attacking) {
                        tempScreenY = screenY - up1.getHeight();
                        if (spriteNum == 1) {
                            image = attackUp1;
                        }
                        if (spriteNum == 2) {
                            image = attackUp2;
                        }
                    }
                    break;
                case "down":
                    if (!attacking) {
                        if (spriteNum == 0) {
                            image = standDown;
                        }
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                    }
                    if (attacking) {
                        if (spriteNum == 1) {
                            image = attackDown1;
                        }
                        if (spriteNum == 2) {
                            image = attackDown2;
                        }
                    }
                    break;
                case "left":
                    if (!attacking) {
                        if (spriteNum == 0) {
                            image = standLeft;
                        }
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                    }
                    if (attacking) {
                        tempScreenX = screenX - left1.getWidth();
                        if (spriteNum == 1) {
                            image = attackLeft1;
                        }
                        if (spriteNum == 2) {
                            image = attackLeft2;
                        }
                    }
                    break;
                case "right":
                    if (!attacking) {
                        if (spriteNum == 0) {
                            image = standRight;
                        }
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                    }
                    if (attacking) {
                        if (spriteNum == 1) {
                            image = attackRight1;
                        }
                        if (spriteNum == 2) {
                            image = attackRight2;
                        }
                    }
                    break;
            }

            // MONSTER HEALTH BAR
            if (type == type_monster && hpBarOn) {

                double oneScale = (double) gamePanel.getTileSize() / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY - 16, gamePanel.getTileSize() + 2, 12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);

                hpBarCounter++;
                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }

            if (dying) {
                dyingAnimation(g2);
            }

            g2.drawImage(image, tempScreenX, tempScreenY, null);
            changeAlpha(g2, 1f);
        }
    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 5;

        if (dyingCounter <= i) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i && dyingCounter <= i * 2) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 8) {
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDirection() {
        return direction;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public BufferedImage getStandDown() {
        return standDown;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getLife() {
        return life;
    }

    public BufferedImage getEntityImage() {
        return image;
    }

    public BufferedImage getEntityImage2() {
        return image2;
    }

    public BufferedImage getEntityImage3() {
        return image3;
    }

    public int getLevel() {
        return level;
    }

    public int getStrength() {
        return strength;
    }

    public int getToughness() {
        return toughness;
    }

    public int getExp() {
        return exp;
    }

    public int getNextLevelExp() {
        return nextLevelExp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getCoin() {
        return coin;
    }

    public Entity getCurrentWeapon() {
        return currentWeapon;
    }

    public Entity getCurrentShield() {
        return currentShield;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getMana() {
        return mana;
    }

    public int getType() {
        return type;
    }

    public ArrayList<Entity> getInventory() {
        return inventory;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getMaxInventorySize() {
        return maxInventorySize;
    }

    public int getLeftX() {
        return worldX + solidArea.x;
    }

    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }

    public int getTopY() {
        return worldY + solidArea.y;
    }

    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }

    public int getCol() {
        return (worldX + solidArea.x) / gamePanel.getTileSize();
    }

    public int getRow() {
        return (worldY + solidArea.y) / gamePanel.getTileSize();
    }

    public int getCenterX() {
        return worldX + left1.getWidth() / 2;
    }

    public int getCenterY() {
        return worldY + up1.getHeight() / 2;
    }

    public int getAmount() {
        return amount;
    }

    public Entity getCurrentLight() {
        return currentLight;
    }

    public int getLightRadius() {
        return lightRadius;
    }

    public String getKnockBackDirection() {
        return knockBackDirection;
    }

    public Entity getLoot() {
        return loot;
    }

    public BufferedImage getImage2() {
        return image2;
    }

    public int getDialogueSet() {
        return dialogueSet;
    }

    public int getDialogueIndex() {
        return dialogueIndex;
    }

    public String[][] getDialogues() {
        return dialogues;
    }

    public boolean isOpened() {
        return opened;
    }

    public boolean isKnockBack() {
        return knockBack;
    }

    public void decreaseAmount() {
        amount--;
    }

    public void decreaseMana(int mana) {
        this.mana -= mana;
    }

    public void increaseCoin(int value) {
        this.coin += value;
    }

    public void decreaseCoin(int value) {
        this.coin -= value;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDying() {
        return dying;
    }

    public boolean isCollision() {
        return collision;
    }

    public void decreaseLife(int life) {
        this.life -= life;
    }

    public void increaseLife(int life) {
        this.life += life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void increaseMana(int mana) {
        this.mana += mana;
    }

    public void increaseDialogueIndex() {
        this.dialogueIndex ++;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCurrentWeapon(Entity currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public void setCurrentShield(Entity currentShield) {
        this.currentShield = currentShield;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public void setDialogueIndex(int dialogueIndex) {
        this.dialogueIndex = dialogueIndex;
    }

    public void setDialogueSet(int dialogueSet) {
        this.dialogueSet = dialogueSet;
    }
}
