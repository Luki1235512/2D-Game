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

    protected int worldX;
    protected int worldY;
    protected int speed;

    protected BufferedImage up1;
    protected BufferedImage up2;
    protected BufferedImage down1;
    protected BufferedImage down2;
    protected BufferedImage left1;
    protected BufferedImage left2;
    protected BufferedImage right1;
    protected BufferedImage right2;

    protected BufferedImage standUp;
    protected BufferedImage standLeft;
    protected BufferedImage standRight;
    protected BufferedImage standDown;

    protected String direction = "down";

    protected int spriteCounter = 0;
    protected int spriteNum = 1;

    protected Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    protected int solidAreaDefaultX;
    protected int solidAreaDefaultY;
    protected boolean collisionOn = false;
    protected int actionLockCounter = 0;
    protected String[] dialogues = new String[20];
    protected int dialogueIndex = 0;

    protected BufferedImage image;
    protected BufferedImage image2;
    protected BufferedImage image3;
    protected String name;
    protected boolean collision = false;

    // CHARACTER STATUS
    protected int maxLife;
    protected int life;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void setAction() {

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
        gamePanel.getCollisionChecker().checkPlayer(this);

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

            g2.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
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
