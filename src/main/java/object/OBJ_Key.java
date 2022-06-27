package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gamePanel) {
        super(gamePanel);

        name = "Key";
        down1 = setup("/objects/key", gamePanel.getTileSize(), gamePanel.getTileSize());
        description = "[" + name + "]\nWhat does this open?";
    }

}
