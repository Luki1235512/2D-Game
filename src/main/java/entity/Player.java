package entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {

    private final KeyHandler keyHandler;

    private final int screenX;
    private final int screenY;

    private int standCounter = 20;
    private boolean attackCanceled = false;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);

        this.keyHandler = keyHandler;

        screenX = gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize() / 2);
        screenY = gamePanel.getScreenHeight() / 2 - (gamePanel.getTileSize() / 2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {

        worldX = gamePanel.getTileSize() * 23;
        worldY = gamePanel.getTileSize() * 21;
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1;
        toughness = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gamePanel);
        currentShield = new OBJ_Shield_Wood(gamePanel);
        attack = getAttack();
        defense = getDefense();
    }

    public int getAttack() {
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = toughness * currentShield.defenseValue;
    }

    public void getPlayerImage() {

        up1 = setup("/player/player_up_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup("/player/player_up_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        down1 = setup("/player/player_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        down2 = setup("/player/player_down_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        left1 = setup("/player/player_left_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup("/player/player_left_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        right1 = setup("/player/player_right_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        right2 = setup("/player/player_right_2", gamePanel.getTileSize(), gamePanel.getTileSize());

        standUp = setup("/player/player_stand_up", gamePanel.getTileSize(), gamePanel.getTileSize());
        standLeft = setup("/player/player_left_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        standRight = setup("/player/player_right_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        standDown = setup("/player/player_stand_down", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/player/player_attack_up_1", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
        attackUp2 = setup("/player/player_attack_up_2", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
        attackDown1 = setup("/player/player_attack_down_1", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
        attackDown2 = setup("/player/player_attack_down_2", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
        attackLeft1 = setup("/player/player_attack_left_1", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
        attackLeft2 = setup("/player/player_attack_left_2", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
        attackRight1 = setup("/player/player_attack_right_1", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
        attackRight2 = setup("/player/player_attack_right_2", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
    }

    public void update() {

        if (attacking) {
            attacking();
        }

        else if (keyHandler.isUpPressed() || keyHandler.isDownPressed() ||
                keyHandler.isLeftPressed() || keyHandler.isRightPressed() || keyHandler.isEnterPressed()) {
            if (keyHandler.isUpPressed()) {
                direction = "up";
            } else if (keyHandler.isDownPressed()) {
                direction = "down";
            } else if (keyHandler.isLeftPressed()) {
                direction = "left";
            } else if (keyHandler.isRightPressed()){
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

            if (!collisionOn && !keyHandler.isEnterPressed()) {
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

            if (keyHandler.isEnterPressed() && !attackCanceled) {
                gamePanel.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gamePanel.getKeyHandler().setEnterPressed(false);

            spriteCounter++;
            if (spriteCounter > 11) {
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

    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
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

            int monsterIndex = gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getMonster());
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {
        if (i != Integer.MAX_VALUE) {

        }
    }

    public void interactNPC(int i) {

        if (gamePanel.getKeyHandler().isEnterPressed()) {
            if (i != Integer.MAX_VALUE) {
                attackCanceled = true;
                gamePanel.setGameState(gamePanel.getDialogueState());
                gamePanel.getNpc()[i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != Integer.MAX_VALUE) {
            if (!invincible) {
                gamePanel.playSE(6);

                int damage = gamePanel.getMonster()[i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }

                life -= damage;
                invincible = true;
            }

        }
    }

    public void damageMonster(int i) {
        if (i != Integer.MAX_VALUE) {
            if (!gamePanel.getMonster()[i].invincible) {
                gamePanel.playSE(5);

                int damage = attack - gamePanel.getMonster()[i].defense;
                if (damage < 0) {
                    damage = 0;
                }

                gamePanel.getMonster()[i].life -= damage;
                gamePanel.getUi().addMessage(damage + " damage!");
                gamePanel.getMonster()[i].invincible = true;
                gamePanel.getMonster()[i].damageReaction();

                if (gamePanel.getMonster()[i].life <= 0) {
                    gamePanel.getMonster()[i].dying = true;
                    gamePanel.getUi().addMessage("Killed the " + gamePanel.getMonster()[i].name + "!");
                }
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
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
                    tempScreenY = screenY - gamePanel.getTileSize();
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
                    tempScreenX = screenX - gamePanel.getTileSize();
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

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY,null);

        // RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setAttackCanceled(boolean attackCancel) {
        this.attackCanceled = attackCancel;
    }
}
