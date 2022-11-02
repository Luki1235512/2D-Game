package main;

import entity.NPC_BigRock;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.MON_BlueSlime;
import monster.MON_Orc;
import object.*;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_MetalPlate;
import tile_interactive.IT_SickTree;

public class AssetSetter {

    private final GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {

        int mapNum = 0;
        int i = 0;
//        gamePanel.getObj()[mapNum][i] = new OBJ_Potion_Red(gamePanel);
//        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 25);
//        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 23);
//
//        i++;
//        gamePanel.getObj()[mapNum][i] = new OBJ_Potion_Red(gamePanel);
//        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 21);
//        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 19);
//
//        i++;
//        gamePanel.getObj()[mapNum][i] = new OBJ_Potion_Red(gamePanel);
//        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 18);
//        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 20);

//        i++;
        gamePanel.getObj()[mapNum][i] = new OBJ_Lantern(gamePanel);
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 21);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 19);

        i++;
        gamePanel.getObj()[mapNum][i] = new OBJ_Tent(gamePanel);
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 19);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 20);

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
        gamePanel.getObj()[mapNum][i] = new OBJ_Chest(gamePanel);
        gamePanel.getObj()[mapNum][i].setLoot(new OBJ_Key(gamePanel));
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 30);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 28);

        // DUNGEON

        mapNum = 2;
        i = 0;

        gamePanel.getObj()[mapNum][i] = new OBJ_Chest(gamePanel);
        gamePanel.getObj()[mapNum][i].setLoot(new OBJ_Pickaxe(gamePanel));
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 40);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 41);
        i++;

        gamePanel.getObj()[mapNum][i] = new OBJ_Chest(gamePanel);
        gamePanel.getObj()[mapNum][i].setLoot(new OBJ_Potion_Red(gamePanel));
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 13);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 16);
        i++;

        gamePanel.getObj()[mapNum][i] = new OBJ_Chest(gamePanel);
        gamePanel.getObj()[mapNum][i].setLoot(new OBJ_Potion_Red(gamePanel));
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 26);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 34);
        i++;

        gamePanel.getObj()[mapNum][i] = new OBJ_Chest(gamePanel);
        gamePanel.getObj()[mapNum][i].setLoot(new OBJ_Potion_Red(gamePanel));
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 27);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 15);
        i++;

        gamePanel.getObj()[mapNum][i] = new OBJ_Iron_Door(gamePanel);
        gamePanel.getObj()[mapNum][i].setLoot(new OBJ_Potion_Red(gamePanel));
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 18);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 23);
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

        // MAP 2
        mapNum = 2;
        i = 0;
        gamePanel.getNpc()[mapNum][i] = new NPC_BigRock(gamePanel);
        gamePanel.getNpc()[mapNum][i].setWorldX(gamePanel.getTileSize() * 20);
        gamePanel.getNpc()[mapNum][i].setWorldY(gamePanel.getTileSize() * 25);
        i++;

        gamePanel.getNpc()[mapNum][i] = new NPC_BigRock(gamePanel);
        gamePanel.getNpc()[mapNum][i].setWorldX(gamePanel.getTileSize() * 11);
        gamePanel.getNpc()[mapNum][i].setWorldY(gamePanel.getTileSize() * 10);
        i++;

        gamePanel.getNpc()[mapNum][i] = new NPC_BigRock(gamePanel);
        gamePanel.getNpc()[mapNum][i].setWorldX(gamePanel.getTileSize() * 23);
        gamePanel.getNpc()[mapNum][i].setWorldY(gamePanel.getTileSize() * 14);

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

        i++;
        gamePanel.getMonster()[mapNum][i] = new MON_Orc(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 12);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 33);
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

        // DUNGEON

        mapNum = 2;
        i = 0;

        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,18, 30);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,17, 31);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,17, 32);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,17, 34);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,18, 34);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,18, 33);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,10, 22);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,10, 24);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,38, 18);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,38, 19);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,38, 20);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,38, 21);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,18, 13);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,18, 14);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,22, 28);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,30, 28);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_DestructibleWall(gamePanel,32, 28);
        i++;

        // METAL PLATE

        gamePanel.getITile()[mapNum][i] = new IT_MetalPlate(gamePanel,20, 22);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_MetalPlate(gamePanel,8, 17);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_MetalPlate(gamePanel,39, 31);
        i++;


    }


}
