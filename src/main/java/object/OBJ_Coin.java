package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity {

    GamePanel gamePanel;

    public OBJ_Coin(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        type = type_pickupOnly;
        name = "Coin";
        value = 1;
        down1 = setup("/objects/coin", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public void use(Entity entity) {
        gamePanel.playSE(1);
        gamePanel.getUi().addMessage("Coin +" + value);
        gamePanel.getPlayer().increaseCoin(value);
    }
}
