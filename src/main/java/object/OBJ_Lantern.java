package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {

    public static final String objName = "Lantern";

    public OBJ_Lantern(GamePanel gamePanel) {
        super(gamePanel);

        type = type_light;
        name = objName;
        down1 = setup("/objects/lantern", gamePanel.getTileSize(), gamePanel.getTileSize());
        description = "[Lantern]\nIt glows";
        price = 200;
        lightRadius = 350;
    }

}
