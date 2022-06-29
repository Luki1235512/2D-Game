package entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Player extends Entity {

    private final KeyHandler keyHandler;

    private final int screenX;
    private final int screenY;

    private int standCounter = 20;
    private boolean attackCanceled = false;
    private final ArrayList<Entity> inventory = new ArrayList<>();
    private final int maxInventorySize = 20;

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
        getPlayerAttackImage();
        setItems();
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
        maxMana = 4;
        mana = maxMana;
        strength = 1;
        toughness = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gamePanel);
        currentShield = new OBJ_Shield_Wood(gamePanel);
        projectile = new OBJ_Fireball(gamePanel);
        attack = getAttack();
        defense = getDefense();
    }

    public void setItems() {
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gamePanel));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
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

        if (currentWeapon.type == type_sword) {
            attackUp1 = setup("/player/player_attack_up_1", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
            attackUp2 = setup("/player/player_attack_up_2", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
            attackDown1 = setup("/player/player_attack_down_1", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
            attackDown2 = setup("/player/player_attack_down_2", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
            attackLeft1 = setup("/player/player_attack_left_1", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
            attackLeft2 = setup("/player/player_attack_left_2", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
            attackRight1 = setup("/player/player_attack_right_1", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
            attackRight2 = setup("/player/player_attack_right_2", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
        }
        if (currentWeapon.type == type_axe) {
            attackUp1 = setup("/player/player_axe_up_1", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
            attackUp2 = setup("/player/player_axe_up_2", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
            attackDown1 = setup("/player/player_axe_down_1", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
            attackDown2 = setup("/player/player_axe_down_2", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
            attackLeft1 = setup("/player/player_axe_left_1", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
            attackLeft2 = setup("/player/player_axe_left_2", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
            attackRight1 = setup("/player/player_axe_right_1", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
            attackRight2 = setup("/player/player_axe_right_2", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
        }

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

        if (gamePanel.getKeyHandler().isShotKeyPressed() && !projectile.alive && shotAvailableCounter == 30 && projectile.haveResource(this)) {
            projectile.set(worldX, worldY, direction, true, this);
            projectile.subtractResource(this);
            gamePanel.getProjectileList().add(projectile);
            shotAvailableCounter = 0;
//            TODO: sound for fireball
//            gamePanel.playSE();
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }

        if (life > maxLife) {
            life = maxLife;
        }

        if (mana > maxMana) {
            mana = maxMana;
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
            damageMonster(monsterIndex, attack);

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

            // PICKUP ONLY ITEMS
            if (gamePanel.getObj()[i].type == type_pickupOnly) {

                gamePanel.getObj()[i].use(this);
            }

            // INVENTORY ITEMS
            else {
                String text;

                if (inventory.size() != maxInventorySize) {
                    inventory.add(gamePanel.getObj()[i]);
                    gamePanel.playSE(1);
                    text = "Got a " + gamePanel.getObj()[i].name + "!";
                }
                else {
                    text = "You cannot carry anymore!";
                }
                gamePanel.getUi().addMessage(text);
            }
            gamePanel.getObj()[i] = null;
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
            if (!invincible && !gamePanel.getMonster()[i].dying) {
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

    public void damageMonster(int i, int attack) {
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
                    gamePanel.getUi().addMessage("Exp + " + gamePanel.getMonster()[i].exp);
                    exp += gamePanel.getMonster()[i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;
            strength++;
            toughness++;
            attack = getAttack();
            defense = getDefense();

            gamePanel.playSE(4);
            gamePanel.setGameState(gamePanel.getDialogueState());
            gamePanel.getUi().setCurrentDialogue("You are level " + level + " now!\n");
        }
    }

    public void selectItem() {

        int itemIndex = gamePanel.getUi().getItemIndexOnSlot();
        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_consumable) {
                selectedItem.use(this);
                inventory.remove(itemIndex);
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

    public ArrayList<Entity> getInventory() {
        return inventory;
    }

    public void setAttackCanceled(boolean attackCancel) {
        this.attackCanceled = attackCancel;
    }
}
