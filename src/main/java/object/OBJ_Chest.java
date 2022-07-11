package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {

    GamePanel gamePanel;
    Entity loot;
    boolean opened = false;

    public OBJ_Chest(GamePanel gamePanel, Entity loot) {
        super(gamePanel);

        this.gamePanel = gamePanel;
        this.loot = loot;

        type = type_obstacle;
        name = "Chest";

        image  = setup("/objects/chest", gamePanel.getTileSize(), gamePanel.getTileSize());
        image2 = setup("/objects/chest", gamePanel.getTileSize(), gamePanel.getTileSize());
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact() {
        gamePanel.setGameState(gamePanel.getDialogueState());
        if (!opened) {
//            gamePanel.playSE();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("You open the chest and find a ").append(loot.getName()).append("!");

            if (gamePanel.getPlayer().getInventory().size() == gamePanel.getPlayer().getMaxInventorySize()) {
                stringBuilder.append("\n... But you cannot carry any more");
            }
            else {
                stringBuilder.append("\nYou obtain the ").append(loot.getName()).append("!");
                gamePanel.getPlayer().getInventory().add(loot);
                down1 = image2;
                opened = true;
            }
            gamePanel.getUi().setCurrentDialogue(stringBuilder.toString());
        }
        else {
            gamePanel.getUi().setCurrentDialogue("It's empty");
        }
    }

}
