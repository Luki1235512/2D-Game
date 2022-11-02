package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_BigRock extends Entity {

    public static final String npcName = "Big Rock";

    public NPC_BigRock(GamePanel gamePanel) {
        super(gamePanel);

        name = npcName;
        direction = "down";
        speed = 4;

        solidArea = new Rectangle(2, 6, 44, 40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        dialogueSet = -1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/rock", gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup("/npc/rock", gamePanel.getTileSize(), gamePanel.getTileSize());
        down1 = setup("/npc/rock", gamePanel.getTileSize(), gamePanel.getTileSize());
        down2 = setup("/npc/rock", gamePanel.getTileSize(), gamePanel.getTileSize());
        left1 = setup("/npc/rock", gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup("/npc/rock", gamePanel.getTileSize(), gamePanel.getTileSize());
        right1 = setup("/npc/rock", gamePanel.getTileSize(), gamePanel.getTileSize());
        right2 = setup("/npc/rock", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public void setDialogue() {
        dialogues[0][0] = "Rock";
    }

    public void setAction() {

    }

    public void update() {

    }

    public void speak() {

        // DO THIS CHARACTER SPECIFIC STUFF
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;

        if (dialogues[dialogueSet][0] == null) {
            dialogueSet--;
        }
    }

}

