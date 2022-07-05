package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gamePanel) {
        super(gamePanel);

        type = type_sword;
        name = "Normal Sword";
        down1 = setup("/objects/sword_normal", gamePanel.getTileSize(), gamePanel.getTileSize());

        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nNothing special";
        price = 20;

    }
}
