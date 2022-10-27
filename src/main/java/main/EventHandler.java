package main;

import entity.Entity;

public class EventHandler {

    private final GamePanel gamePanel;
    private final EventRect[][][] eventRect;
    Entity eventMaster;

    private int previousEventX;
    private int previousEventY;
    private boolean canTouchEvent = true;
    private int tempMap;
    private int tempCol;
    private int tempRow;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        eventMaster = new Entity(gamePanel);

        eventRect = new EventRect[gamePanel.getMaxMap()][gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        int map = 0;
        int col = 0;
        int row = 0;
        while (col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow() && map < gamePanel.getMaxMap()) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gamePanel.getMaxWorldCol()) {
                col = 0;
                row++;
                if (row == gamePanel.getMaxWorldRow()) {
                    row = 0;
                    map++;
                }
            }
        }
        setDialogue();
    }

    public void setDialogue() {
        eventMaster.getDialogues()[0][0] = "You fall int a pit!";
        eventMaster.getDialogues()[1][0] = "You drink the water.\nYour life and mana have been recovered.\n" +
                "(The progress has been saved)";
    }

    public void checkEvent() {

        int distanceX = Math.abs(gamePanel.getPlayer().getWorldX() - previousEventX);
        int distanceY = Math.abs(gamePanel.getPlayer().getWorldY() - previousEventY);
        int distance = Math.max(distanceX, distanceY);
        if (distance > gamePanel.getTileSize()) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {
            if (hit(0, 27, 16, "right")) {
                damagePit(gamePanel.getDialogueState());
            }
            else if (hit(0, 23, 12, "up")) {
                healingPool(gamePanel.getDialogueState());
            }
            else if (hit(0, 10, 39, "any")) {
                teleport(1, 12, 13);
            }
            else if (hit(1, 12, 13, "any")) {
                teleport(0, 10, 39);
            }
            else if (hit(1, 12, 9, "up")) {
                speak(gamePanel.getNpc()[1][0]);
            }
        }


    }

    public boolean hit(int map, int col, int row, String reqDirection) {

        boolean hit = false;

        if (map == gamePanel.getCurrentMap()) {
            gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
            gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;
            eventRect[map][col][row].x = col * gamePanel.getTileSize() + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gamePanel.getTileSize() + eventRect[map][col][row].y;

            if (gamePanel.getPlayer().getSolidArea().intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if (gamePanel.getPlayer().getDirection().contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gamePanel.getPlayer().getWorldX();
                    previousEventY = gamePanel.getPlayer().getWorldY();
                }
            }
            gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getSolidAreaDefaultX();
            gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getSolidAreaDefaultY();
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void teleport(int map, int col, int row) {

        gamePanel.setGameState(gamePanel.getTransitionState());
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        // TODO: transition se
//        gamePanel.playSE();
    }

    public void damagePit(int gameState) {
        gamePanel.setGameState(gameState);
        gamePanel.playSE(6);
        eventMaster.startDialogue(eventMaster, 0);
        gamePanel.getPlayer().decreaseLife(1);
        canTouchEvent = false;
    }

    public void healingPool(int gameState) {
        if (gamePanel.getKeyHandler().isEnterPressed()) {

            gamePanel.setGameState(gameState);
            gamePanel.getPlayer().setAttackCanceled(true);
            gamePanel.playSE(2);
            eventMaster.startDialogue(eventMaster, 1);
            gamePanel.getPlayer().setLife(gamePanel.getPlayer().getMaxLife());
            gamePanel.getPlayer().setMana(gamePanel.getPlayer().getMaxMana());
            gamePanel.getAssetSetter().setMonster();
            gamePanel.getSaveLoad().save();
        }
    }

    public void speak(Entity entity) {

        if (gamePanel.getKeyHandler().isEnterPressed()) {
            gamePanel.setGameState(gamePanel.getDialogueState());
            gamePanel.getPlayer().setAttackCanceled(true);
            entity.speak();
        }
    }

    public int getTempMap() {
        return tempMap;
    }

    public int getTempCol() {
        return tempCol;
    }

    public int getTempRow() {
        return tempRow;
    }

    public void setPreviousEventX(int previousEventX) {
        this.previousEventX = previousEventX;
    }

    public void setPreviousEventY(int previousEventY) {
        this.previousEventY = previousEventY;
    }
}
