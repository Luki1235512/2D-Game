package main;

import entity.Entity;
import object.*;

public class EntityGenerator {

    GamePanel gamePanel;

    public EntityGenerator(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Entity getObject(String itemName) {
        Entity obj = null;
        switch (itemName) {
            case OBJ_Axe.objName:
                obj = new OBJ_Axe(gamePanel);
                break;
            case OBJ_Boots.objName:
                obj = new OBJ_Boots(gamePanel);
                break;
            case OBJ_Key.objName:
                obj = new OBJ_Key(gamePanel);
                break;
            case OBJ_Lantern.objName:
                obj = new OBJ_Lantern(gamePanel);
                break;
            case OBJ_Potion_Red.objName:
                obj = new OBJ_Potion_Red(gamePanel);
                break;
            case OBJ_Shield2.objName:
                obj = new OBJ_Shield2(gamePanel);
                break;
            case OBJ_Shield_Wood.objName:
                obj = new OBJ_Shield_Wood(gamePanel);
                break;
            case OBJ_Sword_Normal.objName:
                obj = new OBJ_Sword_Normal(gamePanel);
                break;
            case OBJ_Tent.objName:
                obj = new OBJ_Tent(gamePanel);
                break;
            case OBJ_Door.objName:
                obj = new OBJ_Door(gamePanel);
                break;
            case OBJ_Chest.objName:
                obj = new OBJ_Chest(gamePanel);
                break;
            case OBJ_Heart.objName:
                obj = new OBJ_Heart(gamePanel);
                break;
            case OBJ_ManaCrystal.objName:
                obj = new OBJ_ManaCrystal(gamePanel);
                break;
            case OBJ_Coin.objName:
                obj = new OBJ_Coin(gamePanel);
                break;
            case OBJ_Fireball.objName:
                obj = new OBJ_Fireball(gamePanel);
                break;
            case OBJ_SlimeBall.objName:
                obj = new OBJ_SlimeBall(gamePanel);
                break;
            case OBJ_Pickaxe.objName:
                obj = new OBJ_Pickaxe(gamePanel);
                break;
        }
        return obj;
    }

}
