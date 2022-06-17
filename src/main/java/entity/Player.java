package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {

    private final KeyHandler keyHandler;

    private final int screenX;
    private final int screenY;

    private int standCounter = 20;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);

        this.keyHandler = keyHandler;

        screenX = gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize() / 2);
        screenY = gamePanel.getScreenHeight() / 2 - (gamePanel.getTileSize() / 2);

        solidArea = new Rectangle(8, 16, 32, 32);
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

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

        up1 = setup("/player/player_up_1");
        up2 = setup("/player/player_up_2");
        down1 = setup("/player/player_down_1");
        down2 = setup("/player/player_down_2");
        left1 = setup("/player/player_left_1");
        left2 = setup("/player/player_left_2");
        right1 = setup("/player/player_right_1");
        right2 = setup("/player/player_right_2");

        standUp = setup("/player/player_stand_up");
        standLeft = setup("/player/player_stand_left");
        standRight = setup("/player/player_stand_right");
        standDown = setup("/player/player_stand_down");
    }

    public void update() {

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

                // CHECK TILE COLLISION
                collisionOn = false;
                gamePanel.getCollisionChecker().checkTile(this);

                // CHECK OBJECT COLLISION
                int objIndex = gamePanel.getCollisionChecker().checkObject(this, true);
                pickUpObject(objIndex);

                // CHECK NPC COLLISION
                int npcIndex = gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getNpc());
                interactNPC(npcIndex);

                // CHECK MONSTER COLLISION
                int monsterIndex = gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getMonster());
                contactMonster(monsterIndex);

                // CHECK EVENT
                gamePanel.getEventHandler().checkEvent();
                gamePanel.getKeyHandler().setEnterPressed(false);

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

            } else {
                standCounter++;

                if (standCounter == 21) {
                    spriteNum = 0;
                    standCounter = 0;
                }
            }

            if (invincible) {
                invincibleCounter++;
                if (invincibleCounter > 60) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
    }

    public void pickUpObject(int i) {
        if (i != Integer.MAX_VALUE) {

        }
    }

    public void interactNPC(int i) {
        if (i != Integer.MAX_VALUE) {
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                gamePanel.setGameState(gamePanel.getDialogueState());
                gamePanel.getNpc()[i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != Integer.MAX_VALUE) {
            if (!invincible) {
                life -= 1;
                invincible = true;
            }

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

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, screenX, screenY,null);

        // RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
}
