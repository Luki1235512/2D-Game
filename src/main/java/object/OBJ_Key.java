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

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You use the " + name + " and open the door";
        dialogues[1][0] = "There is no door here";
    }

    public boolean use(Entity entity) {

        int objIndex = getDetected(entity, gamePanel.getObj(), "Door");

        if (objIndex != Integer.MAX_VALUE) {
            startDialogue(this, 0);
            // TODO: Add sound
//            gamePanel.playSE();
            gamePanel.getObj()[gamePanel.getCurrentMap()][objIndex] = null;
            return true;
        }
        else {
            startDialogue(this, 1);
            return false;
        }
    }

}
