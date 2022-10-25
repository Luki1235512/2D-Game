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

        } catch (Exception e) {
            System.out.println("Load Exception");
        }
    }

}
