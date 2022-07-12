package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    GamePanel gamePanel;

    public OBJ_Key(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_consumable;
        name = "Key";
        down1 = setup("/objects/key", gamePanel.getTileSize(), gamePanel.getTileSize());
        description = "[" + name + "]\nWhat does this open?";

        price = 100;
        stackable = true;
    }

    public boolean use(Entity entity) {
        gamePanel.setGameState(gamePanel.getDialogueState());
        int objIndex = getDetected(entity, gamePanel.getObj(), "Door");

        if (objIndex != Integer.MAX_VALUE) {
            gamePanel.getUi().setCurrentDialogue("You use the " + name + " and open the door");
//            gamePanel.playSE();
            gamePanel.getObj()[gamePanel.getCurrentMap()][objIndex] = null;
            return true;
        }
        else {
            gamePanel.getUi().setCurrentDialogue("huh, there is no door here");
            return false;
        }
    }

}
