package main;

import java.awt.*;

public class EventHandler {

    private final GamePanel gamePanel;
    private final Rectangle eventRect;
    private final int eventRectDefaultX;
    private final int eventRectDefaultY;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {

        if (hit(27, 16, "right")) {
            damagePit(gamePanel.getDialogueState());
        }
        if (hit(23, 12, "up")) {
            healingPool(gamePanel.getDialogueState());
        }
//        if (hit(27, 16, "right")) {
//            teleport(gamePanel.getDialogueState());
//        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {

        boolean hit = false;

        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;
        eventRect.x = eventCol * gamePanel.getTileSize() + eventRect.x;
        eventRect.y = eventRow * gamePanel.getTileSize() + eventRect.y;

        if (gamePanel.getPlayer().getSolidArea().intersects(eventRect)) {
            if (gamePanel.getPlayer().getDirection().contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getSolidAreaDefaultX();
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getSolidAreaDefaultY();
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void teleport(int gameState) {
        gamePanel.setGameState(gameState);
        gamePanel.getUi().setCurrentDialogue("Teleport!");
        gamePanel.getPlayer().setWorldX(gamePanel.getTileSize() * 37);
        gamePanel.getPlayer().setWorldY(gamePanel.getTileSize() * 10);
    }

    public void damagePit(int gameState) {
        gamePanel.setGameState(gameState);
        gamePanel.getUi().setCurrentDialogue("You fall int a pit!");
        gamePanel.getPlayer().decreaseLife(1);
    }

    public void healingPool(int gameState) {
        if (gamePanel.getKeyHandler().isEnterPressed()) {
            gamePanel.setGameState(gameState);
            gamePanel.getUi().setCurrentDialogue("You drink the water.\nYour life has been recovered");
            gamePanel.getPlayer().setLife(gamePanel.getPlayer().getMaxLife());
        }
    }

}
