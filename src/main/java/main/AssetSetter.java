package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.getObj()[0] = new OBJ_Key();
        gamePanel.getObj()[0].worldX = 23 * gamePanel.getTileSize();
        gamePanel.getObj()[0].worldY = 7 * gamePanel.getTileSize();

//        gamePanel.getObj()[1] = new OBJ_Key();
//        gamePanel.getObj()[1].worldX = 23 * gamePanel.getTileSize();
//        gamePanel.getObj()[1].worldY = 40 * gamePanel.getTileSize();

        gamePanel.getObj()[2] = new OBJ_Key();
        gamePanel.getObj()[2].worldX = 38 * gamePanel.getTileSize();
        gamePanel.getObj()[2].worldY = 8 * gamePanel.getTileSize();

        gamePanel.getObj()[3] = new OBJ_Door();
        gamePanel.getObj()[3].worldX = 10 * gamePanel.getTileSize();
        gamePanel.getObj()[3].worldY = 11 * gamePanel.getTileSize();

//        gamePanel.getObj()[4] = new OBJ_Door();
//        gamePanel.getObj()[4].worldX = 8 * gamePanel.getTileSize();
//        gamePanel.getObj()[4].worldY = 28 * gamePanel.getTileSize();

//        gamePanel.getObj()[5] = new OBJ_Door();
//        gamePanel.getObj()[5].worldX = 12 * gamePanel.getTileSize();
//        gamePanel.getObj()[5].worldY = 22 * gamePanel.getTileSize();

        gamePanel.getObj()[6] = new OBJ_Chest();
        gamePanel.getObj()[6].worldX = 10 * gamePanel.getTileSize();
        gamePanel.getObj()[6].worldY = 7 * gamePanel.getTileSize();
    }



}
