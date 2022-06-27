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

        int i = 0;
        gamePanel.getMonster()[i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[i].setWorldX(gamePanel.getTileSize() * 23);
        gamePanel.getMonster()[i].setWorldY(gamePanel.getTileSize() * 36);

        i++;
        gamePanel.getMonster()[i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[i].setWorldX(gamePanel.getTileSize() * 23);
        gamePanel.getMonster()[i].setWorldY(gamePanel.getTileSize() * 37);

        i++;
        gamePanel.getMonster()[i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[i].setWorldX(gamePanel.getTileSize() * 24);
        gamePanel.getMonster()[i].setWorldY(gamePanel.getTileSize() * 37);

        i++;
        gamePanel.getMonster()[i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[i].setWorldX(gamePanel.getTileSize() * 34);
        gamePanel.getMonster()[i].setWorldY(gamePanel.getTileSize() * 42);

        i++;
        gamePanel.getMonster()[i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[i].setWorldX(gamePanel.getTileSize() * 38);
        gamePanel.getMonster()[i].setWorldY(gamePanel.getTileSize() * 42);


    }


}
