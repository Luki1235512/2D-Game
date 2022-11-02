package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity {

    public static final String objName = "Pickaxe";

    public OBJ_Pickaxe(GamePanel gamePanel) {
        super(gamePanel);

        type = type_pickaxe;
        name = objName;
        down1 = setup("/objects/pickaxe", gamePanel.getTileSize(), gamePanel.getTileSize());
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;

        description = "[" + name + "]\nMiiinnneee";
        price = 75;
        knockBackPower = 10;
        motion1_duration = 30;
        motion2_duration = 50;
    }
}
