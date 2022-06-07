package main;

import entity.NPC_OldMan;

public class AssetSetter {

    private final GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {

    }

    public void setNPC() {
        gamePanel.getNpc()[0] = new NPC_OldMan(gamePanel);
        gamePanel.getNpc()[0].setWorldX(gamePanel.getTileSize() * 21);
        gamePanel.getNpc()[0].setWorldY(gamePanel.getTileSize() * 21);
    }


}
