package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean enterPressed;

    // DEBUG
    private boolean checkDrawTime = false;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isCheckDrawTime() {
        return checkDrawTime;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // TITLE STATE
        if (gamePanel.getGameState() == gamePanel.getTitleState()) {
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gamePanel.getUi().increaseCommandNum();
                if (gamePanel.getUi().getCommandNum() > 2) {
                    gamePanel.getUi().decreaseCommandNum();
                }
            }
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gamePanel.getUi().decreaseCommandNum();
                if (gamePanel.getUi().getCommandNum() < 0) {
                    gamePanel.getUi().increaseCommandNum();
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.getUi().getCommandNum() == 0) {
                    gamePanel.setGameState(gamePanel.getPlayState());
                }
                if (gamePanel.getUi().getCommandNum() == 1) {
                    // TODO: add loading
                }
                if (gamePanel.getUi().getCommandNum() == 2) {
                    System.exit(0);
                }
            }
        }

        // PLAY STATE
        else if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P) {
                gamePanel.setGameState(gamePanel.getPauseState());
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

            // DEBUG
            if (code == KeyEvent.VK_T) {
                checkDrawTime = !checkDrawTime;
            }
        }

        // PAUSE STATE
        else if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            if (code == KeyEvent.VK_P) {
                gamePanel.setGameState(gamePanel.getPlayState());
            }
        }

        // DIALOGUE STATE
        else if (gamePanel.getGameState() == gamePanel.getDialogueState()) {
            if (code == KeyEvent.VK_ENTER) {
                gamePanel.setGameState(gamePanel.getPlayState());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
