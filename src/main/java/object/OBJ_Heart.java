package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {

    public static final String objName = "Heart";
    GamePanel gamePanel;

    public OBJ_Heart(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_pickupOnly;
        name = objName;
        value = 2;
        down1 = setup("/objects/heart_full", gamePanel.getTileSize(), gamePanel.getTileSize());
        image = setup("/objects/heart_full", gamePanel.getTileSize(), gamePanel.getTileSize());
        image2 = setup("/objects/heart_half", gamePanel.getTileSize(), gamePanel.getTileSize());
        image3 = setup("/objects/heart_blank", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public boolean use(Entity entity) {
        gamePanel.playSE(2);
        gamePanel.getUi().addMessage("Life +" + value);
        entity.increaseLife(value);

        return true;
    }

}
