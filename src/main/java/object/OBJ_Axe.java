package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

    public static final String objName = "Woodcutter's Axe";

    public OBJ_Axe(GamePanel gamePanel) {
        super(gamePanel);

        type = type_axe;
        name = objName;
        down1 = setup("/objects/axe", gamePanel.getTileSize(), gamePanel.getTileSize());
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;

        description = "[" + name + "]\nCan cut trees";
        price = 75;
        knockBackPower = 10;
        motion1_duration = 20;
        motion2_duration = 40;
    }
}
