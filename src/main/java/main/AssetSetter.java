package main;

import entity.NPC_OldMan;
import monster.MON_BlueSlime;
import object.OBJ_Axe;
import object.OBJ_Key;

public class AssetSetter {

    private final GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {

        int i = 0;
        gamePanel.getObj()[i] = new OBJ_Key(gamePanel);
        gamePanel.getObj()[i].setWorldX(gamePanel.getTileSize() * 25);
        gamePanel.getObj()[i].setWorldY(gamePanel.getTileSize() * 23);

        i++;
        gamePanel.getObj()[i] = new OBJ_Key(gamePanel);
        gamePanel.getObj()[i].setWorldX(gamePanel.getTileSize() * 21);
        gamePanel.getObj()[i].setWorldY(gamePanel.getTileSize() * 19);

        i++;
        gamePanel.getObj()[i] = new OBJ_Key(gamePanel);
        gamePanel.getObj()[i].setWorldX(gamePanel.getTileSize() * 26);
        gamePanel.getObj()[i].setWorldY(gamePanel.getTileSize() * 21);

        i++;
        gamePanel.getObj()[i] = new OBJ_Axe(gamePanel);
        gamePanel.getObj()[i].setWorldX(gamePanel.getTileSize() * 33);
        gamePanel.getObj()[i].setWorldY(gamePanel.getTileSize() * 21);
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
