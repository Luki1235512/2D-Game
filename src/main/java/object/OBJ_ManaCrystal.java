package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity {

    public static final String objName = "Mana Crystals";
    GamePanel gamePanel;

    public OBJ_ManaCrystal(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_pickupOnly;
        name = objName;
        value = 1;
        down1 = setup("/objects/mana_crystal_full", gamePanel.getTileSize(), gamePanel.getTileSize());
        image = setup("/objects/mana_crystal_full", gamePanel.getTileSize(), gamePanel.getTileSize());
        image2 = setup("/objects/mana_crystal_half", gamePanel.getTileSize(), gamePanel.getTileSize());
        image3 = setup("/objects/mana_crystal_blank", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public boolean use(Entity entity) {
        gamePanel.playSE(2);
        gamePanel.getUi().addMessage("Mana +" + value);
        entity.increaseMana(value);

        return true;
    }

}
