package data;

import main.GamePanel;

import java.io.*;

public class SaveLoad {

    GamePanel gamePanel;

    public SaveLoad(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
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
                gamePanel.getPlayer().getInventory().add(gamePanel.getEntityGenerator().getObject(ds.itemNames.get(i)));
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

                     gamePanel.getObj()[mapNum][i] = gamePanel.getEntityGenerator().getObject(ds.mapObjectNames[mapNum][i]);
                     gamePanel.getObj()[mapNum][i].setWorldX(ds.mapObjectWorldX[mapNum][i]);
                     gamePanel.getObj()[mapNum][i].setWorldY(ds.mapObjectWorldY[mapNum][i]);
                     if (ds.mapObjectLootNames[mapNum][i] != null) {
                         gamePanel.getObj()[mapNum][i].setLoot(gamePanel.getEntityGenerator().getObject(ds.mapObjectLootNames[mapNum][i]));
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
