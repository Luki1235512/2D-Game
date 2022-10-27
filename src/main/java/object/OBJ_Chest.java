package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {

    public static final String objName = "Chest";
    GamePanel gamePanel;

    public OBJ_Chest(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_obstacle;
        name = objName;

        image  = setup("/objects/chest", gamePanel.getTileSize(), gamePanel.getTileSize());
        image2 = setup("/objects/chest_opened", gamePanel.getTileSize(), gamePanel.getTileSize());
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setLoot(Entity loot) {
        this.loot = loot;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You open the chest and find a " + loot.getName() + "!\n... But you cannot carry any more!";
        dialogues[1][0] = "You open the chest and find a " + loot.getName() + "!\nYou obtain the " + loot.getName() + "!";
        dialogues[2][0] = "It's empty!";
    }

    public void interact() {

        if (!opened) {
            // TODO: Add sound
//            gamePanel.playSE();

            if (!gamePanel.getPlayer().canObtainItem(loot)) {
                startDialogue(this, 0);
            }
            else {
                startDialogue(this, 1);
                down1 = image2;
                opened = true;
            }
        }
        else {
            startDialogue(this, 2);
        }
    }

}
