package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    private final GamePanel gamePanel;
    private final KeyHandler keyHandler;

    private final int screenX;
    private final int screenY;

//    private int hasKey = 0;

    private int standCounter = 0;
    private boolean moving = false;
    private int pixelCounter = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize() / 2);
        screenY = gamePanel.getScreenHeight() / 2 - (gamePanel.getTileSize() / 2);

        solidArea = new Rectangle(1, 1, 46, 46);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gamePanel.getTileSize() * 23;
        worldY = gamePanel.getTileSize() * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        up1 = setup("player_up_1");
        up2 = setup("player_up_2");
        down1 = setup("player_down_1");
        down2 = setup("player_down_2");
        left1 = setup("player_left_1");
        left2 = setup("player_left_2");
        right1 = setup("player_right_1");
        right2 = setup("player_right_2");

        standUp = setup("player_stand_up");
        standLeft = setup("player_stand_left");
        standRight = setup("player_stand_right");
        standDown = setup("player_stand_down");
    }

    public BufferedImage setup(String imageName) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/" + imageName + ".png")));
            image = utilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        if (!moving) {

            if (keyHandler.isUpPressed() || keyHandler.isDownPressed() ||
                    keyHandler.isLeftPressed() || keyHandler.isRightPressed()) {
                if (keyHandler.isUpPressed()) {
                    direction = "up";
                } else if (keyHandler.isDownPressed()) {
                    direction = "down";
                } else if (keyHandler.isLeftPressed()) {
                    direction = "left";
                } else {
                    direction = "right";
                }

                moving = true;

                // CHECK TILE COLLISION
                collisionOn = false;
                gamePanel.getCollisionChecker().checkTile(this);

                // CHECK OBJECT COLLISION
                int objIndex = gamePanel.getCollisionChecker().checkObject(this, true);
                pickUpObject(objIndex);
            } else {
                standCounter++;

                if (standCounter == 20) {
                    spriteNum = 0;
                    standCounter = 0;
                }

            }
        }

        if (moving) {
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

            pixelCounter += speed;
            if (pixelCounter == 48) {
                moving = false;
                pixelCounter = 0;
            }
        }


    }

    public void pickUpObject(int i) {
        if (i != Integer.MAX_VALUE) {

        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
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
        g2.drawImage(image, screenX, screenY,null);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
}
