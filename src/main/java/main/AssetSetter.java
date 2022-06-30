package main;

import entity.NPC_OldMan;
import monster.MON_BlueSlime;
import object.*;
import tile_interactive.IT_SickTree;

public class AssetSetter {

    private final GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {

        int i = 0;
        gamePanel.getObj()[i] = new OBJ_Coin(gamePanel);
        gamePanel.getObj()[i].setWorldX(gamePanel.getTileSize() * 25);
        gamePanel.getObj()[i].setWorldY(gamePanel.getTileSize() * 23);

        i++;
        gamePanel.getObj()[i] = new OBJ_Coin(gamePanel);
        gamePanel.getObj()[i].setWorldX(gamePanel.getTileSize() * 21);
        gamePanel.getObj()[i].setWorldY(gamePanel.getTileSize() * 19);

        i++;
        gamePanel.getObj()[i] = new OBJ_Coin(gamePanel);
        gamePanel.getObj()[i].setWorldX(gamePanel.getTileSize() * 26);
        gamePanel.getObj()[i].setWorldY(gamePanel.getTileSize() * 21);

        i++;
        gamePanel.getObj()[i] = new OBJ_Axe(gamePanel);
        gamePanel.getObj()[i].setWorldX(gamePanel.getTileSize() * 33);
        gamePanel.getObj()[i].setWorldY(gamePanel.getTileSize() * 21);

        i++;
        gamePanel.getObj()[i] = new OBJ_Shield2(gamePanel);
        gamePanel.getObj()[i].setWorldX(gamePanel.getTileSize() * 35);
        gamePanel.getObj()[i].setWorldY(gamePanel.getTileSize() * 21);

        i++;
        gamePanel.getObj()[i] = new OBJ_Potion_Red(gamePanel);
        gamePanel.getObj()[i].setWorldX(gamePanel.getTileSize() * 22);
        gamePanel.getObj()[i].setWorldY(gamePanel.getTileSize() * 27);

        i++;
        gamePanel.getObj()[i] = new OBJ_Heart(gamePanel);
        gamePanel.getObj()[i].setWorldX(gamePanel.getTileSize() * 22);
        gamePanel.getObj()[i].setWorldY(gamePanel.getTileSize() * 29);

        i++;
        gamePanel.getObj()[i] = new OBJ_ManaCrystal(gamePanel);
        gamePanel.getObj()[i].setWorldX(gamePanel.getTileSize() * 22);
        gamePanel.getObj()[i].setWorldY(gamePanel.getTileSize() * 31);
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

    public void setInteractiveTile() {

        int i = 0;
        gamePanel.getITile()[i] = new IT_SickTree(gamePanel, 27, 12);
        i++;
        gamePanel.getITile()[i] = new IT_SickTree(gamePanel, 28, 12);
        i++;
        gamePanel.getITile()[i] = new IT_SickTree(gamePanel, 29, 12);
        i++;
        gamePanel.getITile()[i] = new IT_SickTree(gamePanel, 30, 12);
        i++;
        gamePanel.getITile()[i] = new IT_SickTree(gamePanel, 31, 12);
        i++;
        gamePanel.getITile()[i] = new IT_SickTree(gamePanel, 32, 12);
        i++;
        gamePanel.getITile()[i] = new IT_SickTree(gamePanel, 33, 12);

        i++;
        gamePanel.getITile()[i] = new IT_SickTree(gamePanel, 20, 20);
        i++;
        gamePanel.getITile()[i] = new IT_SickTree(gamePanel, 20, 21);
        i++;
        gamePanel.getITile()[i] = new IT_SickTree(gamePanel, 20, 22);
        i++;
        gamePanel.getITile()[i] = new IT_SickTree(gamePanel, 22, 24);
        i++;
        gamePanel.getITile()[i] = new IT_SickTree(gamePanel, 23, 24);
        i++;
        gamePanel.getITile()[i] = new IT_SickTree(gamePanel, 24, 24);
    }


}
