package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity {

    private GamePanel gamePanel;

    public OBJ_Tent(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_consumable;
        name = "Tent";
        down1 = setup("/objects/tent", gamePanel.getTileSize(), gamePanel.getTileSize());
        description = "[Tent]\nYou can sleep through the night";
        price = 300;
        stackable = true;
    }

    public boolean use(Entity entity) {
        gamePanel.setGameState(gamePanel.getSleepState());
        gamePanel.getPlayer().setLife(gamePanel.getPlayer().getMaxLife());
        gamePanel.getPlayer().setMana(gamePanel.getPlayer().getMaxMana());
        gamePanel.getPlayer().getSleepingImage(down1);
        return true;
    }

}
