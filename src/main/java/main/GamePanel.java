package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    private final int originalTileSize = 16;
    private final int scale = 3;

    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;

    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;

    // SYSTEM
    private final TileManager tileManager = new TileManager(this);
    private final KeyHandler keyHandler = new KeyHandler();
    private final Sound sound = new Sound();
    private final CollisionChecker collisionChecker = new CollisionChecker(this);
    private final AssetSetter assetSetter = new AssetSetter(this);
    private Thread gameThread;

    // ENTITY AND OBJECT
    private final Player player = new Player(this, keyHandler);
    private final SuperObject[] obj = new SuperObject[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
//        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // TILE
        tileManager.draw(g2);
        // OBJECT
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2, this);
            }
        }
        // PLAYER
        player.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
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

    public SuperObject[] getObj() {
        return obj;
    }

    @Override
    public void run() {


        double FPS = 60;
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;

                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }



}
