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
    private boolean shotKeyPressed;

    // DEBUG
    private boolean showDebugText = false;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void titleState(int code) {
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

    public void playState(int code) {
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
        if (code == KeyEvent.VK_C) {
            gamePanel.setGameState(gamePanel.getCharacterState());
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getOptionState());
        }

        // DEBUG
        if (code == KeyEvent.VK_T) {
            showDebugText = !showDebugText;
        }

        if (code == KeyEvent.VK_R) {
            gamePanel.getTileManager().loadMap("/maps/worldV2.txt");
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gamePanel.setGameState(gamePanel.getPlayState());
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.setGameState(gamePanel.getPlayState());
        }
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_C || code == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
        }
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if (gamePanel.getUi().getSlotRow() != 0) {
                gamePanel.getUi().decreaseSlotRow();
                gamePanel.playSE(8);
            }
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (gamePanel.getUi().getSlotCol() != 0) {
                gamePanel.getUi().decreaseSlotCol();
                gamePanel.playSE(8);
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if (gamePanel.getUi().getSlotRow() != 3) {
                gamePanel.getUi().increaseSlotRow();
                gamePanel.playSE(8);
            }
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gamePanel.getUi().getSlotCol() != 4) {
                gamePanel.getUi().increaseSlotCol();
                gamePanel.playSE(8);
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.getPlayer().selectItem();
        }
    }

    public void optionState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gamePanel.getUi().getSubState()) {
            case 0:
                maxCommandNum = 5;
                break;
            case 3:
                maxCommandNum = 1;
                break;

        }

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gamePanel.getUi().decreaseCommandNum();
            gamePanel.playSE(8);
            if (gamePanel.getUi().getCommandNum() < 0) {
                gamePanel.getUi().setCommandNum(maxCommandNum);
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gamePanel.getUi().increaseCommandNum();
            gamePanel.playSE(8);
            if (gamePanel.getUi().getCommandNum() > maxCommandNum) {
                gamePanel.getUi().setCommandNum(0);
            }
        }

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (gamePanel.getUi().getSubState() == 0) {
                if (gamePanel.getUi().getCommandNum() == 1 && gamePanel.getMusic().getVolumeScale() > 0) {
                    gamePanel.getMusic().decreaseVolumeScale();
                    gamePanel.getMusic().checkVolume();
                    gamePanel.playSE(8);
                }
                if (gamePanel.getUi().getCommandNum() == 2 && gamePanel.getSe().getVolumeScale() > 0) {
                    gamePanel.getSe().decreaseVolumeScale();
                    gamePanel.playSE(8);
                }
            }
        }

        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gamePanel.getUi().getSubState() == 0) {
                if (gamePanel.getUi().getCommandNum() == 1 && gamePanel.getMusic().getVolumeScale() < 5) {
                    gamePanel.getMusic().increaseVolumeScale();
                    gamePanel.getMusic().checkVolume();
                    gamePanel.playSE(8);
                }
                if (gamePanel.getUi().getCommandNum() == 2 && gamePanel.getSe().getVolumeScale() < 5) {
                    gamePanel.getSe().increaseVolumeScale();
                    gamePanel.playSE(8);
                }
            }
        }
    }

    public void gameOverState(int code) {

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gamePanel.getUi().decreaseCommandNum();
            if (gamePanel.getUi().getCommandNum() < 0) {
                gamePanel.getUi().setCommandNum(1);
            }
            gamePanel.playSE(8);
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gamePanel.getUi().increaseCommandNum();
            if (gamePanel.getUi().getCommandNum() > 1) {
                gamePanel.getUi().setCommandNum(0);
            }
            gamePanel.playSE(8);
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gamePanel.getUi().getCommandNum() == 0) {
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.retry();
                gamePanel.playMusic(0);
            }
            else if (gamePanel.getUi().getCommandNum() == 1) {
                gamePanel.setGameState(gamePanel.getTitleState());
                gamePanel.restart();
            }
        }
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

    public boolean isShowDebugText() {
        return showDebugText;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    public boolean isShotKeyPressed() {
        return shotKeyPressed;
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
            titleState(code);
        }

        // PLAY STATE
        else if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            playState(code);
        }

        // PAUSE STATE
        else if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            pauseState(code);
        }

        // DIALOGUE STATE
        else if (gamePanel.getGameState() == gamePanel.getDialogueState()) {
            dialogueState(code);
        }

        // CHARACTER STATE
        else if (gamePanel.getGameState() == gamePanel.getCharacterState()) {
            characterState(code);
        }

        // OPTION STATE
        else if (gamePanel.getGameState() == gamePanel.getOptionState()) {
            optionState(code);
        }

        // GAME OVER STATE
        else if (gamePanel.getGameState() == gamePanel.getGameOverState()) {
            gameOverState(code);
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
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
    }
}
