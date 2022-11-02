package tile_interactive;

import main.GamePanel;

public class IT_MetalPlate extends InteractiveTile {

    GamePanel gamePanel;
    public static final String itName = "Metal Plate";

    public IT_MetalPlate(GamePanel gamePanel, int col, int row) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        this.worldX = gamePanel.getTileSize() * col;
        this.worldY = gamePanel.getTileSize() * row;

        name = itName;
        down1 = setup("/tiles_interactive/metal_plate", gamePanel.getTileSize(), gamePanel.getTileSize());

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height =0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
