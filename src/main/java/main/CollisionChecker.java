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
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].collision || gamePanel.getTileManager().getTile()[tileNum2].collision) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].collision || gamePanel.getTileManager().getTile()[tileNum2].collision) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].collision || gamePanel.getTileManager().getTile()[tileNum2].collision) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].collision || gamePanel.getTileManager().getTile()[tileNum2].collision) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {

        int index = Integer.MAX_VALUE;

        for (int i = 0; i < gamePanel.getObj().length; i++) {
            if (gamePanel.getObj()[i] != null) {
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;

                gamePanel.getObj()[i].solidArea.x = gamePanel.getObj()[i].worldX + gamePanel.getObj()[i].solidArea.x;
                gamePanel.getObj()[i].solidArea.y = gamePanel.getObj()[i].worldY + gamePanel.getObj()[i].solidArea.y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(gamePanel.getObj()[i].solidArea)) {
                            if (gamePanel.getObj()[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if (entity.getSolidArea().intersects(gamePanel.getObj()[i].solidArea)) {
                            if (gamePanel.getObj()[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(gamePanel.getObj()[i].solidArea)) {
                            if (gamePanel.getObj()[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if (entity.getSolidArea().intersects(gamePanel.getObj()[i].solidArea)) {
                            if (gamePanel.getObj()[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                gamePanel.getObj()[i].solidArea.x = gamePanel.getObj()[i].solidAreaDefaultX;
                gamePanel.getObj()[i].solidArea.y = gamePanel.getObj()[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    // NPC OR MONSTER
    public int checkEntity(Entity entity, Entity[] target) {

        int index = Integer.MAX_VALUE;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;

                target[i].getSolidArea().x = target[i].getWorldX() + target[i].getSolidArea().x;
                target[i].getSolidArea().y = target[i].getWorldY() + target[i].getSolidArea().y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                target[i].getSolidArea().x = target[i].getSolidAreaDefaultX();
                target[i].getSolidArea().y = target[i].getSolidAreaDefaultY();
            }
        }

        return index;
    }

    public void checkPlayer(Entity entity) {

        entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
        entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;

        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;

        switch (entity.getDirection()) {
            case "up":
                entity.getSolidArea().y -= entity.getSpeed();
                if (entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entity.getSolidArea().y += entity.getSpeed();
                if (entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entity.getSolidArea().x -= entity.getSpeed();
                if (entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entity.getSolidArea().x += entity.getSpeed();
                if (entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
        }
        entity.getSolidArea().x = entity.getSolidAreaDefaultX();
        entity.getSolidArea().y = entity.getSolidAreaDefaultY();
        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getSolidAreaDefaultX();
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getSolidAreaDefaultY();
    }

}
