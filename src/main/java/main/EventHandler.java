package main;

public class EventHandler {

    private final GamePanel gamePanel;
    private final EventRect[][][] eventRect;

    private int previousEventX;
    private int previousEventY;
    private boolean canTouchEvent = true;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

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
            if (hit(0, 23, 12, "up")) {
                healingPool(gamePanel.getDialogueState());
            }
//        if (hit(27, 16, "right")) {
//            teleport(27, 16, gamePanel.getDialogueState());
//        }
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

    public void teleport(int gameState) {
        gamePanel.setGameState(gameState);
        gamePanel.getUi().setCurrentDialogue("Teleport!");
        gamePanel.getPlayer().setWorldX(gamePanel.getTileSize() * 37);
        gamePanel.getPlayer().setWorldY(gamePanel.getTileSize() * 10);
    }

    public void damagePit(int gameState) {
        gamePanel.setGameState(gameState);
        gamePanel.playSE(6);
        gamePanel.getUi().setCurrentDialogue("You fall int a pit!");
        gamePanel.getPlayer().decreaseLife(1);
        canTouchEvent = false;
    }

    public void healingPool(int gameState) {
        if (gamePanel.getKeyHandler().isEnterPressed()) {
            gamePanel.getPlayer().setAttackCanceled(true);
            gamePanel.playSE(2);
            gamePanel.setGameState(gameState);
            gamePanel.getUi().setCurrentDialogue("You drink the water.\nYour life and mana have been recovered");
            gamePanel.getPlayer().setLife(gamePanel.getPlayer().getMaxLife());
            gamePanel.getPlayer().setMana(gamePanel.getPlayer().getMaxMana());
            gamePanel.getAssetSetter().setMonster();
        }
    }

}
