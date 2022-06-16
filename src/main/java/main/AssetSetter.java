package main;

import entity.NPC_OldMan;
import object.OBJ_Door;

public class AssetSetter {

    private final GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {

//        gamePanel.getObj()[0] = new OBJ_Door(gamePanel);
//        gamePanel.getObj()[0].setWorldX(gamePanel.getTileSize() * 21);
//        gamePanel.getObj()[0].setWorldY(gamePanel.getTileSize() * 22);

//        gamePanel.getObj()[1] = new OBJ_Door(gamePanel);
//        gamePanel.getObj()[1].setWorldX(gamePanel.getTileSize() * 23);
//        gamePanel.getObj()[1].setWorldY(gamePanel.getTileSize() * 25);
    }

    public void setNPC() {
        gamePanel.getNpc()[0] = new NPC_OldMan(gamePanel);
        gamePanel.getNpc()[0].setWorldX(gamePanel.getTileSize() * 21);
        gamePanel.getNpc()[0].setWorldY(gamePanel.getTileSize() * 21);

//        gamePanel.getNpc()[1] = new NPC_OldMan(gamePanel);
//        gamePanel.getNpc()[1].setWorldX(gamePanel.getTileSize() * 11);
//        gamePanel.getNpc()[1].setWorldY(gamePanel.getTileSize() * 21);

//        gamePanel.getNpc()[2] = new NPC_OldMan(gamePanel);
//        gamePanel.getNpc()[2].setWorldX(gamePanel.getTileSize() * 31);
//        gamePanel.getNpc()[2].setWorldY(gamePanel.getTileSize() * 21);

//        gamePanel.getNpc()[3] = new NPC_OldMan(gamePanel);
//        gamePanel.getNpc()[3].setWorldX(gamePanel.getTileSize() * 21);
//        gamePanel.getNpc()[3].setWorldY(gamePanel.getTileSize() * 11);

//        gamePanel.getNpc()[4] = new NPC_OldMan(gamePanel);
//        gamePanel.getNpc()[4].setWorldX(gamePanel.getTileSize() * 21);
//        gamePanel.getNpc()[4].setWorldY(gamePanel.getTileSize() * 31);
    }


}
