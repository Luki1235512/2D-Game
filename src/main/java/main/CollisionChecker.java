package main;

import entity.Entity;

public class CollisionChecker {

    private final GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / gamePanel.getTileSize();
        int entityRightCol = entityRightWorldX / gamePanel.getTileSize();
        int entityTopRow = entityTopWorldY / gamePanel.getTileSize();
        int entityBottomRow = entityBottomWorldY / gamePanel.getTileSize();

        int tileNum1;
        int tileNum2;

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getCurrentMap()][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getCurrentMap()][entityRightCol][entityTopRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].collision || gamePanel.getTileManager().getTile()[tileNum2].collision) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getCurrentMap()][entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getCurrentMap()][entityRightCol][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].collision || gamePanel.getTileManager().getTile()[tileNum2].collision) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getCurrentMap()][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getCurrentMap()][entityLeftCol][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].collision || gamePanel.getTileManager().getTile()[tileNum2].collision) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getCurrentMap()][entityRightCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getCurrentMap()][entityRightCol][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].collision || gamePanel.getTileManager().getTile()[tileNum2].collision) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {

        int index = Integer.MAX_VALUE;

        for (int i = 0; i < gamePanel.getObj()[1].length; i++) {
            if (gamePanel.getObj()[gamePanel.getCurrentMap()][i] != null) {
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;

                gamePanel.getObj()[gamePanel.getCurrentMap()][i].getSolidArea().x =
                        gamePanel.getObj()[gamePanel.getCurrentMap()][i].getWorldX() +
                                gamePanel.getObj()[gamePanel.getCurrentMap()][i].getSolidArea().x;

                gamePanel.getObj()[gamePanel.getCurrentMap()][i].getSolidArea().y =
                        gamePanel.getObj()[gamePanel.getCurrentMap()][i].getWorldY() +
                                gamePanel.getObj()[gamePanel.getCurrentMap()][i].getSolidArea().y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        break;
                }

                if (entity.getSolidArea().intersects(gamePanel.getObj()[gamePanel.getCurrentMap()][i].getSolidArea())) {
                    if (gamePanel.getObj()[gamePanel.getCurrentMap()][i].isCollision()) {
                        entity.setCollisionOn(true);
                    }
                    if (player) {
                        index = i;
                    }
                }

                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                gamePanel.getObj()[gamePanel.getCurrentMap()][i].getSolidArea().x = gamePanel.getObj()[gamePanel.getCurrentMap()][i].getSolidAreaDefaultX();
                gamePanel.getObj()[gamePanel.getCurrentMap()][i].getSolidArea().y = gamePanel.getObj()[gamePanel.getCurrentMap()][i].getSolidAreaDefaultY();
            }
        }

        return index;
    }

    // NPC OR MONSTER
    public int checkEntity(Entity entity, Entity[][] target) {

        int index = Integer.MAX_VALUE;

        for (int i = 0; i < target[1].length; i++) {
            if (target[gamePanel.getCurrentMap()][i] != null) {
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;

                target[gamePanel.getCurrentMap()][i].getSolidArea().x = target[gamePanel.getCurrentMap()][i].getWorldX() + target[gamePanel.getCurrentMap()][i].getSolidArea().x;
                target[gamePanel.getCurrentMap()][i].getSolidArea().y = target[gamePanel.getCurrentMap()][i].getWorldY() + target[gamePanel.getCurrentMap()][i].getSolidArea().y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        break;
                }

                if (entity.getSolidArea().intersects(target[gamePanel.getCurrentMap()][i].getSolidArea())) {
                    if (target[gamePanel.getCurrentMap()][i] != entity) {
                        entity.setCollisionOn(true);
                        index = i;
                    }

                }

                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                target[gamePanel.getCurrentMap()][i].getSolidArea().x = target[gamePanel.getCurrentMap()][i].getSolidAreaDefaultX();
                target[gamePanel.getCurrentMap()][i].getSolidArea().y = target[gamePanel.getCurrentMap()][i].getSolidAreaDefaultY();
            }
        }

        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
        entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;

        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;

        switch (entity.getDirection()) {
            case "up":
                entity.getSolidArea().y -= entity.getSpeed();
                break;
            case "down":
                entity.getSolidArea().y += entity.getSpeed();
                break;
            case "left":
                entity.getSolidArea().x -= entity.getSpeed();
                break;
            case "right":
                entity.getSolidArea().x += entity.getSpeed();
                break;
        }

        if (entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {
            entity.setCollisionOn(true);
            contactPlayer = true;
        }

        entity.getSolidArea().x = entity.getSolidAreaDefaultX();
        entity.getSolidArea().y = entity.getSolidAreaDefaultY();
        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getSolidAreaDefaultX();
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getSolidAreaDefaultY();

        return contactPlayer;
    }

}
