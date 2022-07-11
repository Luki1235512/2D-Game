package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {

    GamePanel gamePanel;

    public OBJ_Door(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_obstacle;
        name = "Door";
        down1 = setup("/objects/door", gamePanel.getTileSize(), gamePanel.getTileSize());
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact() {
        gamePanel.setGameState(gamePanel.getDialogueState());
        gamePanel.getUi().setCurrentDialogue("You need a key to open this");
    }

}
