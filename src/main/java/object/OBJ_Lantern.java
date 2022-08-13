package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {

    public OBJ_Lantern(GamePanel gamePanel) {
        super(gamePanel);

        type = type_light;
        name = "Lantern";
        down1 = setup("/objects/lantern", gamePanel.getTileSize(), gamePanel.getTileSize());
        description = "[Lantern]\nIt glows";
        price = 200;
        lightRadius = 250;
    }

}
