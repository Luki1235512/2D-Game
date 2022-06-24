package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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

    protected BufferedImage image;
    protected BufferedImage image2;
    protected BufferedImage image3;

    protected Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    protected Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    protected int solidAreaDefaultX;
    protected int solidAreaDefaultY;
    protected boolean collisionOn = false;

    protected String[] dialogues = new String[20];

    // STATE
    protected int worldX;
    protected int worldY;
    protected String direction = "down";
    protected int spriteNum = 1;
    protected int dialogueIndex = 0;
    protected boolean collision = false;
    protected boolean invincible = false;
    protected boolean attacking = false;
    protected boolean alive = true;
    protected boolean dying = false;
    protected boolean hpBarOn = false;

    // COUNTER
    protected int spriteCounter = 0;
    protected int actionLockCounter = 0;
    protected int invincibleCounter = 0;
    protected int dyingCounter = 0;
    protected int hpBarCounter = 0;

    // CHARACTER ATTRIBUTES
    protected int type;
    protected String name;
    protected int speed;
    protected int maxLife;
    protected int life;
    protected int level;
    protected int strength;
    protected int toughness;
    protected int attack;
    protected int defense;
    protected int exp;
    protected int nextLevelExp;
    protected int coin;
    protected Entity currentWeapon;
    protected Entity currentShield;

    // ITEM ATTRIBUTES
    protected int attackValue;
    protected int defenseValue;

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

    public void setAction() {

    }

    public void damageReaction() {

    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gamePanel.getUi().setCurrentDialogue(dialogues[dialogueIndex]);
        dialogueIndex++;

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

    public void update() {

        setAction();
        collisionOn = false;
        gamePanel.getCollisionChecker().checkTile(this);
        gamePanel.getCollisionChecker().checkObject(this, false);
        gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getNpc());
        gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getMonster());
        boolean contactPlayer = gamePanel.getCollisionChecker().checkPlayer(this);

        if (this.type == 2 && contactPlayer) {
            if (!gamePanel.getPlayer().invincible) {
                gamePanel.playSE(6);
                gamePanel.getPlayer().life -= 1;
                gamePanel.getPlayer().invincible = true;
            }
        }

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
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gamePanel.getPlayer().worldX + gamePanel.getPlayer().getScreenX();
        int screenY = worldY - gamePanel.getPlayer().worldY + gamePanel.getPlayer().getScreenY();

        if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().worldX - gamePanel.getPlayer().getScreenX() &&
                worldX - gamePanel.getTileSize() < gamePanel.getPlayer().worldX + gamePanel.getPlayer().getScreenX() &&
                worldY + gamePanel.getTileSize() > gamePanel.getPlayer().worldY - gamePanel.getPlayer().getScreenY() &&
                worldY - gamePanel.getTileSize() < gamePanel.getPlayer().worldY + gamePanel.getPlayer().getScreenY()) {

            switch (direction) {
                case "up":
                    if (spriteNum == 0) {
                        image = standUp;
                    }
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 0) {
                        image = standDown;
                    }
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 0) {
                        image = standLeft;
                    }
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 0) {
                        image = standRight;
                    }
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }

            // MONSTER HEALTH BAR
            if (type == 2 && hpBarOn) {

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

            g2.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);

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
            dying = false;
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

    public void setLife(int life) {
        this.life = life;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }
}
