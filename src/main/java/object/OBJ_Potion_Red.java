package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    public static final String objName = "Red Potion";
    GamePanel gamePanel;

    public OBJ_Potion_Red(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_consumable;
        name = objName;
        value = 5;
        down1 = setup("/objects/potion_red", gamePanel.getTileSize(), gamePanel.getTileSize());
        description = "[" + name + "]\nMay cause cancer if\nconsumed in excess";
        price = 25;
        stackable = true;
    }

    public void setDialogue() {
        dialogues[0][0] = "You drink the " + name + "!\n" +
                "Your life has been recovered by " + value + ".";
    }

    public boolean use(Entity entity) {
        startDialogue(this, 0);
        entity.increaseLife(value);
        gamePanel.playSE(2);

        return true;
    }

}
