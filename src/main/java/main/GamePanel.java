package main;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    private final int originalTileSize = 16;
    private final int scale = 3;

    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 20;
    private final int maxScreenRow = 12;

    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;
    private final int maxMap = 10;
    private int currentMap = 0;

    // FULL SCREEN
    private int screenWidthFull = screenWidth;
    private int screenHeightFull = screenHeight;
    private BufferedImage tempScreen;
    private Graphics2D g2;
    private boolean fullScreenStatus = false;


    // SYSTEM
    private final TileManager tileManager = new TileManager(this);
    private final KeyHandler keyHandler = new KeyHandler(this);
    private final Sound music = new Sound();
    private final Sound se = new Sound();
    private final CollisionChecker collisionChecker = new CollisionChecker(this);
    private final AssetSetter assetSetter = new AssetSetter(this);
    private final UI ui = new UI(this);
    private final EventHandler eventHandler = new EventHandler(this);
    private final Config config = new Config(this);
    private PathFinder pathFinder = new PathFinder(this);
    private EnvironmentManager environmentManager = new EnvironmentManager(this);
    private Thread gameThread;

    // ENTITY AND OBJECT
    private final Player player = new Player(this, keyHandler);
    private final Entity[][] obj = new Entity[maxMap][20];
    private final Entity[][] npc = new Entity[maxMap][10];
    private final Entity[][] monster = new Entity[maxMap][20];
    private final InteractiveTile[][] iTile = new InteractiveTile[maxMap][50];
    private final Entity projectile[][] = new Entity[maxMap][20];
//    private final ArrayList<Entity> projectileList = new ArrayList<>();
    private final ArrayList<Entity> particleList = new ArrayList<>();
    private final ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    private int gameState;
    private final int titleState = 0;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int dialogueState = 3;
    private final int characterState = 4;
    private final int optionState = 5;
    private final int gameOverState = 6;
    private final int transitionState = 7;
    private final int tradeState = 8;

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
        assetSetter.setInteractiveTile();
        environmentManager.setup();

        playMusic(0);
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        if (fullScreenStatus) {
            setFullScreen();
        }

    }

    public void retry() {

        player.setDefaultPositions();
        player.restoreLifeAndMana();
        assetSetter.setNPC();
        assetSetter.setMonster();
    }

    public void restart() {

        player.setDefaultValues();
        player.setItems();
        assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.setMonster();
        assetSetter.setInteractiveTile();
    }

    public void setFullScreen() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Main.window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        screenWidthFull = (int) width;
        screenHeightFull = (int) height;
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
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            // MONSTER
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if (monster[currentMap][i].isAlive() && !monster[currentMap][i].isDying()) {
                        monster[currentMap][i].update();
                    }
                    if (!monster[currentMap][i].isAlive()) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }

            // PROJECTILE
            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    if (projectile[currentMap][i].isAlive()) {
                        projectile[currentMap][i].update();
                    }
                    if (!projectile[currentMap][i].isAlive()) {
                        projectile[currentMap][i] = null;
                    }
                }
            }

            // PARTICLES
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    if (particleList.get(i).isAlive()) {
                        particleList.get(i).update();
                    }
                    if (!particleList.get(i).isAlive()) {
                        particleList.remove(i);
                    }
                }
            }

            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].update();
                }
            }
            environmentManager.update();
        }
        if (gameState == pauseState) {
            // TODO: later
        }
    }

    public void drawToTempScreen() {

        // DEBUG
        long drawStart = 0;
        if (keyHandler.isShowDebugText()) {
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

            // INTERACTIVE TILE
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }

            // PLAYER
            entityList.add(player);

            // NPC
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            // OBJECT
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }

            // MONSTER
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }

            // PROJECTILE
            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    entityList.add(projectile[currentMap][i]);
                }
            }

            // PARTICLE
            for (Entity entity : particleList) {
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

            // ENVIRONMENT
            environmentManager.draw(g2);

            // UI
            ui.draw(g2);
        }

        // DEBUG
        if (keyHandler.isShowDebugText()) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.WHITE);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX: " + player.getWorldX(), x, y);
            g2.drawString("WorldY: " + player.getWorldY(), x, y += lineHeight);
            g2.drawString("Col: " + (player.getWorldX() + player.getSolidArea().x) / tileSize, x, y += lineHeight);
            g2.drawString("Row: " + (player.getWorldY() + player.getSolidArea().y) / tileSize, x, y += lineHeight);
            g2.drawString("Draw Time: " + passed, x, y + lineHeight);
        }
    }

    public void drawToScreen() {
        Graphics graphics = getGraphics();
        graphics.drawImage(tempScreen, 0, 0, screenWidthFull, screenHeightFull, null);
        graphics.dispose();
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

    public Entity[][] getNpc() {
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

    public Entity[][] getObj() {
        return obj;
    }

    public Entity[][] getMonster() {
        return monster;
    }

    public int getCharacterState() {
        return characterState;
    }

    public AssetSetter getAssetSetter() {
        return assetSetter;
    }

    public Entity[][] getProjectile() {
        return projectile;
    }

    public InteractiveTile[][] getITile() {
        return iTile;
    }

    public ArrayList<Entity> getParticleList() {
        return particleList;
    }

    public int getOptionState() {
        return optionState;
    }

    public boolean isFullScreenStatus() {
        return fullScreenStatus;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public Sound getMusic() {
        return music;
    }

    public Sound getSe() {
        return se;
    }

    public Config getConfig() {
        return config;
    }

    public int getGameOverState() {
        return gameOverState;
    }

    public int getMaxMap() {
        return maxMap;
    }

    public int getCurrentMap() {
        return currentMap;
    }

    public void setFullScreenStatus(boolean fullScreenStatus) {
        this.fullScreenStatus = fullScreenStatus;
    }

    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }

    public int getTransitionState() {
        return transitionState;
    }

    public int getTradeState() {
        return tradeState;
    }

    public PathFinder getPathFinder() {
        return pathFinder;
    }

    public void setPathFinder(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
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
                drawToTempScreen();
                drawToScreen();
                delta--;

            }

            if (timer >= 1000000000) {
                timer = 0;
            }

        }
    }



}
