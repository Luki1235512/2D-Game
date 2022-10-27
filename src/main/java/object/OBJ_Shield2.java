package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield2 extends Entity {

    public static final String objName = "Magic Shield";

    public OBJ_Shield2(GamePanel gamePanel) {
        super(gamePanel);

        type = type_shield;
        name = objName;
        down1 = setup("/objects/shield2", gamePanel.getTileSize(), gamePanel.getTileSize());

        defenseValue = 2;
        description = "[" + name + "]\nMight be magic";
        price = 250;
    }
}
