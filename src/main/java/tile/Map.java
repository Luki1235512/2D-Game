package tile;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map extends TileManager {

    GamePanel gamePanel;
    BufferedImage worldMap[];
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

                // TODO: Find out why tile num get filled with 0
                if (tileNum != 0) {
                    g2.drawImage(getTile()[tileNum].image, x, y, null);
                }

                col++;
                if (col == gamePanel.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
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
    }
}
