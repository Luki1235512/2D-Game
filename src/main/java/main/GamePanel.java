package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    private final int originalTileSize = 45;
    private final int scale = 1;

    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 11;

    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;

    // SYSTEM
    private final TileManager tileManager = new TileManager(this);
    private final KeyHandler keyHandler = new KeyHandler(this);
    private final Sound music = new Sound();
    private final Sound se = new Sound();
    private final CollisionChecker collisionChecker = new CollisionChecker(this);
    private final AssetSetter assetSetter = new AssetSetter(this);
    private final UI ui = new UI(this);
    private final EventHandler eventHandler = new EventHandler(this);
    private Thread gameThread;

    // ENTITY AND OBJECT
    private final Player player = new Player(this, keyHandler);
    private final Entity[] obj = new Entity[10];
    private final Entity[] npc = new Entity[10];
    private final Entity[] monster = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    private int gameState;
    private final int titleState = 0;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int dialogueState = 3;
    private final int characterState = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.setMonster();
//        playMusic(0);
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void update() {
        if (gameState == playState) {
            // PLAYER
            player.update();

            // NPC
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
            // MONSTER
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].isAlive() && !monster[i].isDying()) {
                        monster[i].update();
                    }
                    if (!monster[i].isAlive()) {
                        monster[i] = null;
                    }
                }
            }
        }
        if (gameState == pauseState) {
            // TODO: later
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // DEBUG
        long drawStart = 0;
        if (keyHandler.isCheckDrawTime()) {
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        }

        // OTHERS
        else {
            // TILE
            tileManager.draw(g2);

            // PLAYER
            entityList.add(player);

            // NPC
            for (Entity entity : npc) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            // OBJECT
            for (Entity entity : obj) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            // MONSTER
            for (Entity entity : monster) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            // SORT
            entityList.sort(Comparator.comparingInt(Entity::getWorldY));

            // DRAW ENTITIES
            for (Entity entity : entityList) {
                entity.draw(g2);
            }

            // EMPTY ENTITY LIST
            entityList.clear();

            // UI
            ui.draw(g2);
        }

        // DEBUG
        if (keyHandler.isCheckDrawTime()) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public Player getPlayer() {
        return player;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public int getGameState() {
        return gameState;
    }

    public int getPlayState() {
        return playState;
    }

    public int getPauseState() {
        return pauseState;
    }

    public Entity[] getNpc() {
        return npc;
    }

    public int getDialogueState() {
        return dialogueState;
    }

    public UI getUi() {
        return ui;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public int getTitleState() {
        return titleState;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public Entity[] getObj() {
        return obj;
    }

    public Entity[] getMonster() {
        return monster;
    }

    public int getCharacterState() {
        return characterState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }


    @Override
    public void run() {

        double FPS = 60;
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;

            }

            if (timer >= 1000000000) {
                timer = 0;
            }

        }
    }



}
