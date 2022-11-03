package entity;

import main.GamePanel;
import object.OBJ_Iron_Door;
import tile_interactive.IT_MetalPlate;
import tile_interactive.InteractiveTile;

import java.awt.*;
import java.util.ArrayList;
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

    public void move(String direction) {
        this.direction = direction;
        checkCollision();
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
        detectPlate();
    }

    public void detectPlate() {
        ArrayList<InteractiveTile> plateList = new ArrayList<>();
        ArrayList<Entity> rockList = new ArrayList<>();

        // CREATE A PLATE LIST
        for (int i = 0; i < gamePanel.getITile()[1].length; i++) {
            if (gamePanel.getITile()[gamePanel.getCurrentMap()][i] != null &&
                    gamePanel.getITile()[gamePanel.getCurrentMap()][i].name != null &&
                    gamePanel.getITile()[gamePanel.getCurrentMap()][i].name.equals(IT_MetalPlate.itName)) {
                plateList.add(gamePanel.getITile()[gamePanel.getCurrentMap()][i]);
            }
        }

        // CREATE A ROCK LIST
        for (int i = 0; i < gamePanel.getNpc()[1].length; i++) {
            if (gamePanel.getNpc()[gamePanel.getCurrentMap()][i] != null &&
                    gamePanel.getNpc()[gamePanel.getCurrentMap()][i].name.equals(NPC_BigRock.npcName)) {
                rockList.add(gamePanel.getNpc()[gamePanel.getCurrentMap()][i]);
            }
        }

        int count = 0;

        // SCAN THE PLATE LIST
        for (int i = 0; i < plateList.size(); i++) {
            int xDistance = Math.abs(worldX - plateList.get(i).worldX);
            int yDistance = Math.abs(worldY - plateList.get(i).worldY);
            int distance = Math.max(xDistance, yDistance);
            if (distance < 8) {
                if (linkedEntity == null) {
                    linkedEntity = plateList.get(i);
//                    TODO: Add SE
//                gamePanel.playSE();
                }
            } else {
                if (linkedEntity == plateList.get(i)) {
                    linkedEntity = null;
                }
            }
        }

        // SCAN THE ROCK LIST
        for (int i = 0; i < rockList.size(); i++) {
            // COUNT THE ROCK ON THE PLATE
            if (rockList.get(i).linkedEntity != null) {
                count++;
            }
        }

        // IF ALL THE ROCKS ARE ON THE PLATES, THE IRON DOOR OPENS
        if (count == rockList.size()) {
            for (int i = 0; i < gamePanel.getObj()[1].length; i++) {
                if (gamePanel.getObj()[gamePanel.getCurrentMap()][i] != null &&
                        gamePanel.getObj()[gamePanel.getCurrentMap()][i].name.equals(OBJ_Iron_Door.objName)) {
                    gamePanel.getObj()[gamePanel.getCurrentMap()][i] = null;
//                    TODO: Add SE
//                    gamePanel.playSE();
                }
            }
        }
    }

}

