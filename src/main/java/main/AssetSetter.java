package main;

import entity.NPC_BigRock;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.MON_Bat;
import monster.MON_BlueSlime;
import monster.MON_Orc;
import monster.MON_SkeletonLord;
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

        gamePanel.getObj()[mapNum][i] = new OBJ_Lantern(gamePanel);
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 23);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 36);

        i++;
        gamePanel.getObj()[mapNum][i] = new OBJ_Tent(gamePanel);
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 24);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 36);

        i++;
        gamePanel.getObj()[mapNum][i] = new OBJ_Axe(gamePanel);
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 25);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 36);

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
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 12);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 10);

//        i++;
//        gamePanel.getObj()[mapNum][i] = new OBJ_Door(gamePanel);
//        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 10);
//        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 12);

//        i++;
//        gamePanel.getObj()[mapNum][i] = new OBJ_Chest(gamePanel);
//        gamePanel.getObj()[mapNum][i].setLoot(new OBJ_Key(gamePanel));
//        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 30);
//        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 28);

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
        gamePanel.getObj()[mapNum][i].setWorldX(gamePanel.getTileSize() * 9);
        gamePanel.getObj()[mapNum][i].setWorldY(gamePanel.getTileSize() * 33);
    }

    public void setNPC() {

        // MAP 0
        int mapNum = 0;
        int i = 0;
        gamePanel.getNpc()[mapNum][i] = new NPC_OldMan(gamePanel);
        gamePanel.getNpc()[mapNum][i].setWorldX(gamePanel.getTileSize() * 32);
        gamePanel.getNpc()[mapNum][i].setWorldY(gamePanel.getTileSize() * 32);

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
        gamePanel.getNpc()[mapNum][i].setWorldX(gamePanel.getTileSize() * 8);
        gamePanel.getNpc()[mapNum][i].setWorldY(gamePanel.getTileSize() * 38);
        i++;

        gamePanel.getNpc()[mapNum][i] = new NPC_BigRock(gamePanel);
        gamePanel.getNpc()[mapNum][i].setWorldX(gamePanel.getTileSize() * 9);
        gamePanel.getNpc()[mapNum][i].setWorldY(gamePanel.getTileSize() * 38);
        i++;

        gamePanel.getNpc()[mapNum][i] = new NPC_BigRock(gamePanel);
        gamePanel.getNpc()[mapNum][i].setWorldX(gamePanel.getTileSize() * 10);
        gamePanel.getNpc()[mapNum][i].setWorldY(gamePanel.getTileSize() * 38);

    }

    public void setMonster() {

        int mapNum = 0;
        int i = 0;
        gamePanel.getMonster()[mapNum][i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 8);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 25);

        i++;
        gamePanel.getMonster()[mapNum][i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 8);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 20);

        i++;
        gamePanel.getMonster()[mapNum][i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 32);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 20);

        i++;
        gamePanel.getMonster()[mapNum][i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 32);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 28);

        i++;
        gamePanel.getMonster()[mapNum][i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 41);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 20);

        i++;
        gamePanel.getMonster()[mapNum][i] = new MON_BlueSlime(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 41);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 28);

        i++;
        gamePanel.getMonster()[mapNum][i] = new MON_Orc(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 36);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 12);

        // DUNGEON

        mapNum = 2;
        i++;
        gamePanel.getMonster()[mapNum][i] = new MON_Bat(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 34);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 39);
        i++;

        gamePanel.getMonster()[mapNum][i] = new MON_Bat(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 36);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 25);
        i++;

        gamePanel.getMonster()[mapNum][i] = new MON_Bat(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 39);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 26);
        i++;

        gamePanel.getMonster()[mapNum][i] = new MON_Bat(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 28);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 11);
        i++;

        gamePanel.getMonster()[mapNum][i] = new MON_Bat(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 10);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 19);
        i++;

        mapNum = 3;
        gamePanel.getMonster()[mapNum][i] = new MON_SkeletonLord(gamePanel);
        gamePanel.getMonster()[mapNum][i].setWorldX(gamePanel.getTileSize() * 23);
        gamePanel.getMonster()[mapNum][i].setWorldY(gamePanel.getTileSize() * 16);
    }

    public void setInteractiveTile() {

        int mapNum = 0;
        int i = 0;

        // SPAWN BLOCKING TREES
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 18, 36);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 30, 36);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 18, 24);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 30, 24);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 18, 12);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 18, 13);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 30, 12);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_SickTree(gamePanel, 30, 13);
        i++;

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

        gamePanel.getITile()[mapNum][i] = new IT_MetalPlate(gamePanel,8, 36);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_MetalPlate(gamePanel,9, 36);
        i++;
        gamePanel.getITile()[mapNum][i] = new IT_MetalPlate(gamePanel,10, 36);
        i++;


    }


}
