package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {

    private final KeyHandler keyHandler;

    private final int screenX;
    private final int screenY;

    private int standCounter = 20;
    private boolean attackCanceled = false;
    private boolean lightUpdated = false;


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);

        this.keyHandler = keyHandler;

        screenX = gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize() / 2);
        screenY = gamePanel.getScreenHeight() / 2 - (gamePanel.getTileSize() / 2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
    }

    public void setDefaultValues() {

        // BASIC
//        worldX = gamePanel.getTileSize() * 23;
//        worldY = gamePanel.getTileSize() * 21;
        // MERCHANT
//        worldX = gamePanel.getTileSize() * 9;
//        worldY = gamePanel.getTileSize() * 40;
        // DUNGEON
        worldX = gamePanel.getTileSize() * 10;
        worldY = gamePanel.getTileSize() * 10;

        defaultSpeed = 4;
        speed = defaultSpeed;
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
        coin = 500;
        currentWeapon = new OBJ_Sword_Normal(gamePanel);
        currentShield = new OBJ_Shield_Wood(gamePanel);
        currentLight = null;
        projectile = new OBJ_Fireball(gamePanel);
        attack = getAttack();
        defense = getDefense();

        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
        setDialogue();
    }

    public void setDefaultPositions() {
        worldX = gamePanel.getTileSize() * 23;
        worldY = gamePanel.getTileSize() * 21;
        direction = "down";
    }

    public void setDialogue() {
        dialogues[0][0] = "You are level " + level + " now!\n";
    }

    public void restoreStatus() {
        life = maxLife;
        mana = maxMana;
        speed = defaultSpeed;
        invincible = false;
        transparent = false;
        attacking = false;
        guarding = false;
        knockBack = false;
        lightUpdated = true;
    }

    public void setItems() {

        inventory.clear();

        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gamePanel));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = toughness * currentShield.defenseValue;
    }

    public int getCurrentWeaponSlot() {
        int currentWeaponSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentWeapon) {
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }

    public int getCurrentShieldSlot() {
        int currentShieldSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentShield) {
                currentShieldSlot = i;
            }
        }
        return currentShieldSlot;
    }

    public void getImage() {

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

    public void getSleepingImage(BufferedImage image) {
        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;

        standUp = image;
        standLeft = image;
        standRight = image;
        standDown = image;
    }

    public void getAttackImage() {

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

    public void getGuardImage() {
        guardUp = setup("/player/player_block_up", gamePanel.getTileSize(), gamePanel.getTileSize());
        guardDown = setup("/player/player_block_down", gamePanel.getTileSize(), gamePanel.getTileSize());
        guardLeft = setup("/player/player_block_left", gamePanel.getTileSize(), gamePanel.getTileSize());
        guardRight = setup("/player/player_block_right", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public void update() {

        if (knockBack) {

            collisionOn = false;
            gamePanel.getCollisionChecker().checkTile(this);
            gamePanel.getCollisionChecker().checkObject(this, true);
            gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getNpc());
            gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getMonster());
            gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getITile());

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

        else if (keyHandler.isSpacePressed()) {
            guarding = true;
            guardCounter++;
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

            // CHECK INTERACTIVE TILE COLLISION
            gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getITile());

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
            guarding = false;
            guardCounter = 0;

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
            guarding = false;
            guardCounter = 0;
        }

        if (gamePanel.getKeyHandler().isShotKeyPressed() && !projectile.alive && shotAvailableCounter == 30 && projectile.haveResource(this)) {
            projectile.set(worldX, worldY, direction, true, this);
            projectile.subtractResource(this);

            for (int i = 0; i < gamePanel.getProjectile()[1].length; i++) {
                if (gamePanel.getProjectile()[gamePanel.getCurrentMap()][i] == null) {
                    gamePanel.getProjectile()[gamePanel.getCurrentMap()][i] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;
//            TODO: sound for fireball
//            gamePanel.playSE();
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                transparent = false;
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

        if (life <= 0) {
            gamePanel.setGameState(gamePanel.getGameOverState());
            gamePanel.getUi().setCommandNum(-1);
            gamePanel.stopMusic();
            // TODO: add game ove sound effect
//            gamePanel.playSE();
        }

    }

    public void pickUpObject(int i) {
        if (i != Integer.MAX_VALUE) {

            // PICKUP ONLY ITEMS
            if (gamePanel.getObj()[gamePanel.getCurrentMap()][i].type == type_pickupOnly) {
                gamePanel.getObj()[gamePanel.getCurrentMap()][i].use(this);
                gamePanel.getObj()[gamePanel.getCurrentMap()][i] = null;
            }

            // OBSTACLE
            else if (gamePanel.getObj()[gamePanel.getCurrentMap()][i].type == type_obstacle) {
                if (keyHandler.isEnterPressed()) {
                    attackCanceled = true;
                    gamePanel.getObj()[gamePanel.getCurrentMap()][i].interact();
                }
            }

            // INVENTORY ITEMS
            else {
                String text;

                if (canObtainItem(gamePanel.getObj()[gamePanel.getCurrentMap()][i])) {
                    gamePanel.playSE(1);
                    text = "Got a " + gamePanel.getObj()[gamePanel.getCurrentMap()][i].name + "!";
                }
                else {
                    text = "You cannot carry anymore!";
                }
                gamePanel.getUi().addMessage(text);
                gamePanel.getObj()[gamePanel.getCurrentMap()][i] = null;
            }

        }
    }

    public void interactNPC(int i) {

        if (i != Integer.MAX_VALUE) {
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                attackCanceled = true;
                gamePanel.getNpc()[gamePanel.getCurrentMap()][i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != Integer.MAX_VALUE) {
            if (!invincible && !gamePanel.getMonster()[gamePanel.getCurrentMap()][i].dying) {
                gamePanel.playSE(6);

                int damage = gamePanel.getMonster()[gamePanel.getCurrentMap()][i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }

                life -= damage;
                invincible = true;
                transparent = true;
            }

        }
    }

    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {
        if (i != Integer.MAX_VALUE) {
            if (!gamePanel.getMonster()[gamePanel.getCurrentMap()][i].invincible) {
                gamePanel.playSE(5);

                if (knockBackPower > 0) {
                    setKnockBack(gamePanel.getMonster()[gamePanel.getCurrentMap()][i], attacker, knockBackPower);
                }

                if (gamePanel.getMonster()[gamePanel.getCurrentMap()][i].offBalance) {
                    attack *= 5;
                }

                int damage = attack - gamePanel.getMonster()[gamePanel.getCurrentMap()][i].defense;
                if (damage < 0) {
                    damage = 0;
                }

                gamePanel.getMonster()[gamePanel.getCurrentMap()][i].life -= damage;
                gamePanel.getUi().addMessage(damage + " damage!");
                gamePanel.getMonster()[gamePanel.getCurrentMap()][i].invincible = true;
                gamePanel.getMonster()[gamePanel.getCurrentMap()][i].damageReaction();

                if (gamePanel.getMonster()[gamePanel.getCurrentMap()][i].life <= 0) {
                    gamePanel.getMonster()[gamePanel.getCurrentMap()][i].dying = true;
                    gamePanel.getUi().addMessage("Killed the " + gamePanel.getMonster()[gamePanel.getCurrentMap()][i].name + "!");
                    gamePanel.getUi().addMessage("Exp + " + gamePanel.getMonster()[gamePanel.getCurrentMap()][i].exp);
                    exp += gamePanel.getMonster()[gamePanel.getCurrentMap()][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void damageInteractiveTile(int i) {

        if (i != Integer.MAX_VALUE && gamePanel.getITile()[gamePanel.getCurrentMap()][i].isDestructible()
                && gamePanel.getITile()[gamePanel.getCurrentMap()][i].isCorrectItem(this) && !gamePanel.getITile()[gamePanel.getCurrentMap()][i].invincible) {
            gamePanel.getITile()[gamePanel.getCurrentMap()][i].playSE();
            gamePanel.getITile()[gamePanel.getCurrentMap()][i].life--;
            gamePanel.getITile()[gamePanel.getCurrentMap()][i].invincible = true;

            // GENERATE PARTICLE
            generateParticle(gamePanel.getITile()[gamePanel.getCurrentMap()][i], gamePanel.getITile()[gamePanel.getCurrentMap()][i]);

            if (gamePanel.getITile()[gamePanel.getCurrentMap()][i].life == 0) {
                gamePanel.getITile()[gamePanel.getCurrentMap()][i] = gamePanel.getITile()[gamePanel.getCurrentMap()][i].getDestroyedForm();
            }
        }
    }

    public void damageProjectile(int i) {
        if (i != Integer.MAX_VALUE) {
            Entity projectile = gamePanel.getProjectile()[gamePanel.getCurrentMap()][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp * 2;
            exp = 0;
            maxLife += 2;
            maxMana += 2;
            strength++;
            toughness++;
            attack = getAttack();
            defense = getDefense();

            gamePanel.playSE(4);
            gamePanel.setGameState(gamePanel.getDialogueState());
            setDialogue();
            startDialogue(this, 0);
        }
    }

    public void selectItem() {

        int itemIndex = gamePanel.getUi().getItemIndexOnSlot(gamePanel.getUi().getPlayerSlotCol(), gamePanel.getUi().getPlayerSlotRow());
        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getAttackImage();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_light) {
                if (currentLight == selectedItem) {
                    currentLight = null;
                }
                else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if (selectedItem.type == type_consumable) {

                if (selectedItem.use(this)) {
                    if (selectedItem.amount > 1) {
                        selectedItem.amount--;
                    } else {
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }

    public int searchItemInInventory(String itemName) {

        int itemIndex = Integer.MAX_VALUE;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item) {

        boolean canObtain = false;
        Entity newItem = gamePanel.getEntityGenerator().getObject(item.name);

        // CHECK IF STACKABLE
        if (newItem.stackable) {
            int index = searchItemInInventory(newItem.name);
            if (index != Integer.MAX_VALUE) {
                inventory.get(index).amount++;
                canObtain = true;
            }
            else {
                // NEW ITEM SO NEED TO CHECK VACANCY
                if (inventory.size() != maxInventorySize) {
                    inventory.add(newItem);
                    canObtain = true;
                }
            }
        }
        else {
            // NOT STACKABLE SO CHECK VACANCY
            if (inventory.size() != maxInventorySize) {
                inventory.add(newItem);
                canObtain = true;
            }
        }
        return canObtain;
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
                if (guarding) {
                    image = guardUp;
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
                if (guarding) {
                    image = guardDown;
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
                if (guarding) {
                    image = guardLeft;
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
                if (guarding) {
                    image = guardRight;
                }
                break;
        }

        if (transparent) {
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

    public boolean isLightUpdated() {
        return lightUpdated;
    }

    public void setLightUpdated(boolean lightUpdated) {
        this.lightUpdated = lightUpdated;
    }

    public void setAttackCanceled(boolean attackCancel) {
        this.attackCanceled = attackCancel;
    }


}
