package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gamePanel) {
        super(gamePanel);

        direction = "down";
        speed = 1;
        solidArea = new Rectangle(8, 16, 30, 30);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        dialogueSet = -1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/oldman_up_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup("/npc/oldman_up_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        down1 = setup("/npc/oldman_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        down2 = setup("/npc/oldman_down_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        left1 = setup("/npc/oldman_left_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup("/npc/oldman_left_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        right1 = setup("/npc/oldman_right_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        right2 = setup("/npc/oldman_right_2", gamePanel.getTileSize(), gamePanel.getTileSize());

        // TODO: add idle?
//        standUp = setup("player_stand_up");
//        standLeft = setup("player_stand_left");
//        standRight = setup("player_stand_right");
//        standDown = setup("player_stand_down");
    }

    public void setDialogue() {
        dialogues[0][0] = "Hello!";
        dialogues[0][1] = "Where am I now?";
        dialogues[0][2] = "Sometimes I remember.";
        dialogues[0][3] = "What do you need?";

        dialogues[1][0] = "You can rest by the water.";
        dialogues[1][1] = "But watch out for monsters!";
        dialogues[1][2] = "Good luck!";

        dialogues[2][0] = "Hm?";
    }

    public void setAction() {

        if (onPath) {
            int goalCol = (gamePanel.getPlayer().worldX + gamePanel.getPlayer().solidArea.x) / gamePanel.getTileSize();
            int goalRow = (gamePanel.getPlayer().worldY + gamePanel.getPlayer().solidArea.y) / gamePanel.getTileSize();


            searchPath(goalCol, goalRow);
        }
        else {
            actionLockCounter++;

            if (actionLockCounter == 120) {
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
