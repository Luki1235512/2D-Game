package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class IT_SickTree extends InteractiveTile {

    GamePanel gamePanel;

    public IT_SickTree(GamePanel gamePanel, int col, int row) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        this.worldX = gamePanel.getTileSize() * col;
        this.worldY = gamePanel.getTileSize() * row;

        down1 = setup("/tiles_interactive/sick_tree", gamePanel.getTileSize(), gamePanel.getTileSize());
        destructible = true;
        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {
        return entity.getCurrentWeapon().getType() == type_axe;
    }

    public void playSE() {
        gamePanel.playSE(9);
    }

    public InteractiveTile getDestroyedForm() {
        return new IT_Trunk(gamePanel, worldX / gamePanel.getTileSize(), worldY / gamePanel.getTileSize());
    }

    public Color getParticleColor() {
        return new Color(65, 50, 30);
    }

    public int getParticleSize() {
        return 6;
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 20;
    }

}
