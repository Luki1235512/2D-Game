package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity {

    GamePanel gamePanel;
    protected boolean destructible = false;

    public InteractiveTile(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
    }

    public void update() {

    }

}
