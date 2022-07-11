package main;

import entity.NPC_Merchant;
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

        int mapNum = 0;
        int i = 0;
//        gamePanel.getObj()[mapNum][i] = new OBJ_Coin(gamePanel);
//        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 25);
//        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 23);

//        i++;
//        gamePanel.getObj()[mapNum][i] = new OBJ_Coin(gamePanel);
//        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 21);
//        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 19);

//        i++;
//        gamePanel.getObj()[mapNum][i] = new OBJ_Coin(gamePanel);
//        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 26);
//        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 21);

        i++;
        gamePanel.getObj()[mapNum][i] = new OBJ_Axe(gamePanel);
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 33);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 21);

//        i++;
//        gamePanel.getObj()[mapNum][i] = new OBJ_Shield2(gamePanel);
//        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 35);
//        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 21);

//        i++;
//        gamePanel.getObj()[mapNum][i] = new OBJ_Potion_Red(gamePanel);
//        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 22);
//        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 27);

//        i++;
//        gamePanel.getObj()[mapNum][i] = new OBJ_Heart(gamePanel);
//        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 22);
//        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 29);

//        i++;
//        gamePanel.getObj()[mapNum][i] = new OBJ_ManaCrystal(gamePanel);
//        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 22);
//        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 31);

        i++;
        gamePanel.getObj()[mapNum][i] = new OBJ_Door(gamePanel);
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 14);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 28);

        i++;
        gamePanel.getObj()[mapNum][i] = new OBJ_Door(gamePanel);
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 10);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 12);

        i++;
        gamePanel.getObj()[mapNum][i] = new OBJ_Chest(gamePanel, new OBJ_Key(gamePanel));
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 30);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 28);
    }

    public void setNPC() {

        // MAP 0
        int mapNum = 0;
        int i = 0;
        gamePanel.getNpc()[mapNum][i] = new NPC_OldMan(gamePanel);
        gamePanel.getNpc()[mapNum][i].setWorldX(gamePanel.getTileSize() * 21);
        gamePanel.getNpc()[mapNum][i].setWorldY(gamePanel.getTileSize() * 21);

        // MAP 1
        mapNum = 1;
        i = 0;
        gamePanel.getNpc()[mapNum][i] = new NPC_Merchant(gamePanel);
        gamePanel.getNpc()[mapNum][i].setWorldX(gamePanel.getTileSize() * 12);
        gamePanel.getNpc()[mapNum][i].setWorldY(gamePanel.getTileSize() * 7);
    }

    public void setMonster() {

        int mapNum = 0;
        int i = 0;
        gamePanel.getMonster()[mapNum][i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 23);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 36);

        i++;
        gamePanel.getMonster()[mapNum][i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 23);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 37);

        i++;
        gamePanel.getMonster()[mapNum][i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 24);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 37);

        i++;
        gamePanel.getMonster()[mapNum][i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 34);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 42);

        i++;
        gamePanel.getMonster()[mapNum][i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 38);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 42);

    }

    public void setInteractiveTile() {

        int mapNum = 0;
        int i = 0;

        // SPAWN BLOCKING TREES

        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 27, 12);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 28, 12);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 29, 12);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 30, 12);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 31, 12);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 32, 12);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 33, 12);

        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 20, 20);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 20, 21);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 20, 22);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 22, 24);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 23, 24);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 24, 24);

        // SHOP BLOCKING TREES
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 18, 40);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 17, 40);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 16, 40);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 15, 40);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 14, 40);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 13, 40);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 13, 41);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 12, 41);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 11, 41);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 10, 41);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 10, 40);

        // CHEST BLOCKING TREES
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 25, 27);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 26, 27);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 27, 27);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 28, 28);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 28, 29);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 30, 29);


    }


}
