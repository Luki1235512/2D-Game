package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class IT_DestructibleWall extends InteractiveTile {

    GamePanel gamePanel;

    public IT_DestructibleWall(GamePanel gamePanel, int col, int row) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        this.worldX = gamePanel.getTileSize() * col;
        this.worldY = gamePanel.getTileSize() * row;

        down1 = setup("/tiles_interactive/odd_wall", gamePanel.getTileSize(), gamePanel.getTileSize());
        destructible = true;
        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {
        return entity.getCurrentWeapon().getType() == type_pickaxe;
    }

    public void playSE() {
        gamePanel.playSE(9);
    }

    public InteractiveTile getDestroyedForm() {
        return null;
    }

    public Color getParticleColor() {
        return new Color(65, 65, 65);
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
