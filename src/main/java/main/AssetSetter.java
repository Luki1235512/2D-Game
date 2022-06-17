package main;

import entity.NPC_OldMan;
import monster.MON_BlueSlime;

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

    public void setMonster() {

        gamePanel.getMonster()[0] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[0].setWorldX(gamePanel.getTileSize() * 23);
        gamePanel.getMonster()[0].setWorldY(gamePanel.getTileSize() * 36);

        gamePanel.getMonster()[1] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[1].setWorldX(gamePanel.getTileSize() * 23);
        gamePanel.getMonster()[1].setWorldY(gamePanel.getTileSize() * 37);

    }


}
