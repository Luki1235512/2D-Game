package data;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.io.*;

public class SaveLoad {

    GamePanel gamePanel;

    public SaveLoad(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Entity getObject(String itemName) {
        Entity obj = null;
        switch (itemName) {
            case "Woodcutter's Axe":
                obj = new OBJ_Axe(gamePanel);
                break;
            case "Boots":
                obj = new OBJ_Boots(gamePanel);
                break;
            case "Key":
                obj = new OBJ_Key(gamePanel);
                break;
            case "Lantern":
                obj = new OBJ_Lantern(gamePanel);
                break;
            case "Red Potion":
                obj = new OBJ_Potion_Red(gamePanel);
                break;
            case "Magic Shield":
                obj = new OBJ_Shield2(gamePanel);
                break;
            case "Wood Shield":
                obj = new OBJ_Shield_Wood(gamePanel);
                break;
            case "Normal Sword":
                obj = new OBJ_Sword_Normal(gamePanel);
                break;
            case "Tent":
                obj = new OBJ_Tent(gamePanel);
                break;
            case "Door":
                obj = new OBJ_Door(gamePanel);
                break;
            case "Chest":
                obj = new OBJ_Chest(gamePanel);
                break;
            case "Heart":
                obj = new OBJ_Heart(gamePanel);
                break;
            case "Mana Crystals":
                obj = new OBJ_ManaCrystal(gamePanel);
                break;
        }
        return obj;
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            DataStorage ds = new DataStorage();

            // PLAYER STATS
            ds.level = gamePanel.getPlayer().getLevel();
            ds.maxLife = gamePanel.getPlayer().getMaxLife();
            ds.life = gamePanel.getPlayer().getLife();
            ds.maxMana = gamePanel.getPlayer().getMaxMana();
            ds.mana = gamePanel.getPlayer().getMana();
            ds.strength = gamePanel.getPlayer().getStrength();
            ds.toughness = gamePanel.getPlayer().getToughness();
            ds.exp = gamePanel.getPlayer().getExp();
            ds.nextLevelExp = gamePanel.getPlayer().getNextLevelExp();
            ds.coin = gamePanel.getPlayer().getCoin();

            // PLAYER INVENTORY
            for (int i = 0; i < gamePanel.getPlayer().getInventory().size(); i++) {
                ds.itemNames.add(gamePanel.getPlayer().getInventory().get(i).getName());
                ds.itemAmounts.add(gamePanel.getPlayer().getInventory().get(i).getAmount());
            }

            // PLAYER EQUIPMENT
            ds.currentWeaponSlot = gamePanel.getPlayer().getCurrentWeaponSlot();
            ds.currentShieldSlot = gamePanel.getPlayer().getCurrentShieldSlot();

            // OBJECTS ON MAP
            ds.mapObjectNames = new String[gamePanel.getMaxMap()][gamePanel.getObj()[1].length];
            ds.mapObjectWorldX = new int[gamePanel.getMaxMap()][gamePanel.getObj()[1].length];
            ds.mapObjectWorldY = new int[gamePanel.getMaxMap()][gamePanel.getObj()[1].length];
            ds.mapObjectLootNames = new String[gamePanel.getMaxMap()][gamePanel.getObj()[1].length];
            ds.mapObjectOpened = new boolean[gamePanel.getMaxMap()][gamePanel.getObj()[1].length];

            for (int mapNum = 0; mapNum < gamePanel.getMaxMap(); mapNum++) {
                for (int i = 0; i < gamePanel.getObj()[1].length; i++) {
                    if  (gamePanel.getObj()[mapNum][i] == null) {
                        ds.mapObjectNames[mapNum][i] = "NA";
                    } else {
                        ds.mapObjectNames[mapNum][i] = gamePanel.getObj()[mapNum][i].getName();
                        ds.mapObjectWorldX[mapNum][i] = gamePanel.getObj()[mapNum][i].getWorldX();
                        ds.mapObjectWorldY[mapNum][i] = gamePanel.getObj()[mapNum][i].getWorldY();
                        if (gamePanel.getObj()[mapNum][i].getLoot() != null) {
                            ds.mapObjectLootNames[mapNum][i] = gamePanel.getObj()[mapNum][i].getLoot().getName();
                        }
                        ds.mapObjectOpened[mapNum][i] = gamePanel.getObj()[mapNum][i].isOpened();
                    }
                }
            }

            // WRITE THE DATA STORAGE OBJECT
            oos.writeObject(ds);

        } catch (Exception e) {
            System.out.println("Save Exception");
        }

    }

    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // READ THE DATA STORAGE OBJECT
            DataStorage ds = (DataStorage) ois.readObject();

            gamePanel.getPlayer().setLevel(ds.level);
            gamePanel.getPlayer().setLife(ds.life);
            gamePanel.getPlayer().setMaxLife(ds.maxLife);
            gamePanel.getPlayer().setMaxMana(ds.maxMana);
            gamePanel.getPlayer().setMana(ds.mana);
            gamePanel.getPlayer().setStrength(ds.strength);
            gamePanel.getPlayer().setToughness(ds.toughness);
            gamePanel.getPlayer().setExp(ds.exp);
            gamePanel.getPlayer().setNextLevelExp(ds.nextLevelExp);
            gamePanel.getPlayer().setCoin(ds.coin);

            // PLAYER INVENTORY
            gamePanel.getPlayer().getInventory().clear();
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gamePanel.getPlayer().getInventory().add(getObject(ds.itemNames.get(i)));
                gamePanel.getPlayer().getInventory().get(i).setAmount(ds.itemAmounts.get(i));
            }

            // PLAYER EQUIPMENT
            gamePanel.getPlayer().setCurrentWeapon(gamePanel.getPlayer().getInventory().get(ds.currentWeaponSlot));
            gamePanel.getPlayer().setCurrentShield(gamePanel.getPlayer().getInventory().get(ds.currentShieldSlot));
            gamePanel.getPlayer().getAttack();
            gamePanel.getPlayer().getDefense();
            gamePanel.getPlayer().getAttackImage();

            // OBJECTS ON MAP
            for (int mapNum = 0; mapNum < gamePanel.getMaxMap(); mapNum++) {
                for (int i = 0; i < gamePanel.getObj()[1].length; i++) {
                    if (ds.mapObjectNames[mapNum][i].equals("NA")) {
                        gamePanel.getObj()[mapNum][i] = null;
                    } else {

                     gamePanel.getObj()[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                     gamePanel.getObj()[mapNum][i].setWorldX(ds.mapObjectWorldX[mapNum][i]);
                     gamePanel.getObj()[mapNum][i].setWorldY(ds.mapObjectWorldY[mapNum][i]);
                     if (ds.mapObjectLootNames[mapNum][i] != null) {
                         gamePanel.getObj()[mapNum][i].setLoot(getObject(ds.mapObjectLootNames[mapNum][i]));
                     }
                     gamePanel.getObj()[mapNum][i].setOpened(ds.mapObjectOpened[mapNum][i]);
                     if (gamePanel.getObj()[mapNum][i].isOpened()) {
                         gamePanel.getObj()[mapNum][i].setDown1(gamePanel.getObj()[mapNum][i].getImage2());
                     }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Load Exception");
        }
    }

}
