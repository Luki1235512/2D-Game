package main;

public class EventHandler {

    private final GamePanel gamePanel;
    private final EventRect[][] eventRect;

    private int previousEventX;
    private int previousEventY;
    private boolean canTouchEvent = true;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        eventRect = new EventRect[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        int col = 0;
        int row = 0;
        while (col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gamePanel.getMaxWorldCol()) {
                col = 0;
                row++;
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
            if (hit(27, 16, "right")) {
                damagePit(gamePanel.getDialogueState());
            }
            if (hit(23, 12, "up")) {
                healingPool(gamePanel.getDialogueState());
            }
//        if (hit(27, 16, "right")) {
//            teleport(27, 16, gamePanel.getDialogueState());
//        }
        }


    }

    public boolean hit(int col, int row, String reqDirection) {

        boolean hit = false;

        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;
        eventRect[col][row].x = col * gamePanel.getTileSize() + eventRect[col][row].x;
        eventRect[col][row].y = row * gamePanel.getTileSize() + eventRect[col][row].y;

        if (gamePanel.getPlayer().getSolidArea().intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
            if (gamePanel.getPlayer().getDirection().contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gamePanel.getPlayer().getWorldX();
                previousEventY = gamePanel.getPlayer().getWorldY();
            }
        }
        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getSolidAreaDefaultX();
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getSolidAreaDefaultY();
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

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
            gamePanel.getUi().setCurrentDialogue("You drink the water.\nYour life has been recovered");
            gamePanel.getPlayer().setLife(gamePanel.getPlayer().getMaxLife());
        }
    }

}
