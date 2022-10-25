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

        } catch (Exception e) {
            System.out.println("Load Exception");
        }
    }

}
