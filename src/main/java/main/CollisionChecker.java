package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.getTileSize();
        int entityRightCol = entityRightWorldX / gamePanel.getTileSize();
        int entityTopRow = entityTopWorldY / gamePanel.getTileSize();
        int entityBottomRow = entityBottomWorldY / gamePanel.getTileSize();

        int tileNum1;
        int tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].collision || gamePanel.getTileManager().getTile()[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].collision || gamePanel.getTileManager().getTile()[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].collision || gamePanel.getTileManager().getTile()[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].collision || gamePanel.getTileManager().getTile()[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

}
