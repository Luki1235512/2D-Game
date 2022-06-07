package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    private final GamePanel gamePanel;
    private final Tile[] tile;
    private final int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        tile = new Tile[50];
        mapTileNum = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        getTileImage();
        loadMap("/maps/worldV2.txt");
    }

    public void getTileImage() {

        setup(10, "grass00", false);

        setup(11, "water00", true);
        setup(12, "water01", true);
        setup(13, "water02", true);
        setup(14, "water03", true);
        setup(15, "water04", true);
        setup(16, "water05", true);
        setup(17, "water06", true);
        setup(18, "water07", true);
        setup(19, "water08", true);
        setup(20, "water09", true);
        setup(21, "water10", true);
        setup(22, "water11", true);
        setup(23, "water12", true);
        setup(24, "water13", true);

        setup(25, "road00", false);
        setup(26, "road01", false);
        setup(27, "road02", false);
        setup(28, "road03", false);
        setup(29, "road04", false);
        setup(30, "road05", false);
        setup(31, "road06", false);
        setup(32, "road07", false);
        setup(33, "road08", false);
        setup(34, "road09", false);
        setup(35, "road10", false);
        setup(36, "road11", false);
        setup(37, "road12", false);

        setup(38, "earth", false);
        setup(39, "wall", true);
        setup(40, "tree", true);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool utilityTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gamePanel.getTileSize(), gamePanel.getTileSize());
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);

            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()) {

                String line = br.readLine();

                while (col < gamePanel.getMaxWorldCol()) {

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gamePanel.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception ignored) {

        }
    }
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gamePanel.getMaxWorldCol() && worldRow < gamePanel.getMaxWorldRow()) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.getTileSize();
            int worldY = worldRow * gamePanel.getTileSize();
            int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
            int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

            if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()) {

                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }
            worldCol++;

            if (worldCol == gamePanel.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }

    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    public Tile[] getTile() {
        return tile;
    }
}
