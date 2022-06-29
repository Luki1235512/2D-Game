package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gamePanel;

    public OBJ_Potion_Red(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_consumable;
        name = "Red Potion";
        value = 5;
        down1 = setup("/objects/potion_red", gamePanel.getTileSize(), gamePanel.getTileSize());
        description = "[" + name + "]\nMay cause cancer if\nconsumed in excess";
    }

    public void use(Entity entity) {
        gamePanel.setGameState(gamePanel.getDialogueState());
        gamePanel.getUi().setCurrentDialogue("You drink the " + name + "!\n" +
                "Your life has been recovered by " + value + ".");
        entity.setLife(entity.getLife() + value);
        gamePanel.playSE(2);
    }

}
