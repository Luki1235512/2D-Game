package entity;

import main.GamePanel;
import object.*;

import java.awt.*;

public class NPC_Merchant extends Entity {

    public NPC_Merchant(GamePanel gamePanel) {
        super(gamePanel);

        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getImage();
        setDialogue();
        setItems();
    }

    public void getImage() {
        up1 = setup("/npc/merchant_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup("/npc/merchant_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        down1 = setup("/npc/merchant_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        down2 = setup("/npc/merchant_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        left1 = setup("/npc/merchant_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup("/npc/merchant_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        right1 = setup("/npc/merchant_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        right2 = setup("/npc/merchant_2", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public void setDialogue() {
        dialogues[0][0] = "Uhhh, so you found me.\nWanna trade?";
        dialogues[1][0] = "See you soon!";
        dialogues[2][0] = "You don't have enough money for that!";
        dialogues[3][0] = "Your inventory is full";
        dialogues[4][0] = "You cannot sell an equipped item!";
    }

    public void setItems() {

        inventory.add(new OBJ_Potion_Red(gamePanel));
        inventory.add(new OBJ_Key(gamePanel));
        inventory.add(new OBJ_Sword_Normal(gamePanel));
        inventory.add(new OBJ_Axe(gamePanel));
        inventory.add(new OBJ_Shield2(gamePanel));
    }

    public void speak() {

        facePlayer();
        gamePanel.setGameState(gamePanel.getTradeState());
        gamePanel.getUi().setNpc(this);
    }

}
