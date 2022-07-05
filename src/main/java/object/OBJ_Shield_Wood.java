package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity {

    public OBJ_Shield_Wood(GamePanel gamePanel) {
        super(gamePanel);

        type = type_shield;
        name = "Wood Shield";
        down1 = setup("/objects/shield_wood", gamePanel.getTileSize(), gamePanel.getTileSize());

        defenseValue = 1;
        description = "[" + name + "]\nSolid wood";
        price = 35;
    }
}
