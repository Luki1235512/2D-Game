package tile;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map extends TileManager {

    GamePanel gamePanel;
    BufferedImage[] worldMap;
    private boolean miniMapOn = false;

    public Map(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        createWorldMap();
    }

    public void createWorldMap() {
        worldMap = new BufferedImage[gamePanel.getMaxMap()];
        int worldMapWidth = gamePanel.getTileSize() * gamePanel.getMaxWorldCol();
        int worldMapHeight = gamePanel.getTileSize() * gamePanel.getMaxWorldRow();

        for (int i = 0; i < gamePanel.getMaxMap(); i++) {
            worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D) worldMap[i].createGraphics();

            int col = 0;
            int row = 0;

            while (col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()) {
                int tileNum = getMapTileNum()[i][col][row];
                int x = gamePanel.getTileSize() * col;
                int y = gamePanel.getTileSize() * row;

                // TODO: Find out why tileNum get filled with 0
                if (tileNum != 0) {
                    g2.drawImage(getTile()[tileNum].image, x, y, null);
                }

                col++;
                if (col == gamePanel.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            g2.dispose();
        }
    }
    public void drawFullMapScreen(Graphics2D g2) {

        // BACKGROUND COLOR
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());

        // DRAW MAP
        int width = 500;
        int height = 500;
        int x = gamePanel.getScreenWidth() / 2 - width / 2;
        int y = gamePanel.getScreenHeight() / 2 - height / 2;
        g2.drawImage(worldMap[gamePanel.getCurrentMap()], x, y, width, height, null);

        // DRAW PLAYER
        double scale = (double) (gamePanel.getTileSize() * gamePanel.getMaxWorldCol()) / width;
        int playerX = (int) (x + gamePanel.getPlayer().getWorldX() / scale);
        int playerY = (int) (y + gamePanel.getPlayer().getWorldY() / scale);
        int playerSize = (int) (gamePanel.getTileSize() / scale);
        g2.drawImage(gamePanel.getPlayer().getStandDown(), playerX, playerY, playerSize, playerSize, null);

        // HINT
        g2.setFont(gamePanel.getUi().getWebfontRegular().deriveFont(32f));
        g2.setColor(Color.WHITE);
        g2.drawString("Press M to close", 750, 550);
    }

    public void drawMiniMap(Graphics2D g2) {
        if (miniMapOn) {

            // DRAW MAP
            int width = 200;
            int height = 200;
            int x = gamePanel.getScreenWidth() - width - 50;
            int y = 50;

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.drawImage(worldMap[gamePanel.getCurrentMap()], x, y, width, height, null);

            // DRAW PLAYER
            double scale = (double) (gamePanel.getTileSize() * gamePanel.getMaxWorldCol()) / width;
            int playerX = (int) (x + gamePanel.getPlayer().getWorldX() / scale);
            int playerY = (int) (y + gamePanel.getPlayer().getWorldY() / scale);
            int playerSize = (int) (gamePanel.getTileSize() / 3);

            g2.drawImage(gamePanel.getPlayer().getStandDown(), playerX - 6, playerY, playerSize, playerSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public boolean isMiniMapOn() {
        return miniMapOn;
    }

    public void setMiniMapOn(boolean miniMapOn) {
        this.miniMapOn = miniMapOn;
    }
}
