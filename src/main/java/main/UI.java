package main;

import object.OBJ_Heart;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    private final GamePanel gamePanel;
    private Graphics2D g2;
    private Font webfontRegular;
    BufferedImage heart_full;
    BufferedImage heart_half;
    BufferedImage heart_blank;
    private boolean messageOn = false;
    private String message = "";
    private String currentDialogue = "";
    private int commandNum = 0;


    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        InputStream inputStream = getClass().getResourceAsStream("/font/000webfont Regular.ttf");
        try {
            assert inputStream != null;
            webfontRegular = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // CREATE HUD OBJECT
        SuperObject heart = new OBJ_Heart(gamePanel);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(webfontRegular);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        // TITLE STATE
        if (gamePanel.getGameState() == gamePanel.getTitleState()) {
            drawTitleScreen();
        }

        // PLAY STATE
        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            drawPlayerLife();
        }

        // PAUSE STATE
        if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            drawPlayerLife();
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if (gamePanel.getGameState() == gamePanel.getDialogueState()) {
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    public void drawPlayerLife() {
        int x = gamePanel.getTileSize() / 2;
        int y = gamePanel.getTileSize() / 2;
        int i = 0;

        // DRAW NAX LIFE
        while (i < gamePanel.getPlayer().getMaxLife() / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gamePanel.getTileSize();
        }

        // RESET
        x = gamePanel.getTileSize() / 2;
        y = gamePanel.getTileSize() / 2;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gamePanel.getPlayer().getLife()) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gamePanel.getPlayer().getLife()) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gamePanel.getTileSize();
        }
    }

    public void drawTitleScreen() {

        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "2D Game";
        int x = getXCenterText(text);
        int y = gamePanel.getTileSize() * 3;

        // SHADOW
        g2.setColor(Color.GRAY);
        g2.drawString(text, x + 5, y + 5);

        // MAIN COLOR
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // MAIN CHARACTER IMAGE
        x = gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize() * 2) / 2;
        y += gamePanel.getTileSize() * 2;
        g2.drawImage(gamePanel.getPlayer().getStandDown(), x, y, gamePanel.getTileSize() * 2, gamePanel.getTileSize() * 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = getXCenterText(text);
        y += gamePanel.getTileSize() * 3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gamePanel.getTileSize(), y);
        }

        text = "LOAD GAME";
        x = getXCenterText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gamePanel.getTileSize(), y);
        }

        text = "QUIT";
        x = getXCenterText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gamePanel.getTileSize(), y);
        }
    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXCenterText(text);
        int y = gamePanel.getScreenHeight() / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {

        // WINDOW
        int x = gamePanel.getTileSize() * 2;
        int y = gamePanel.getTileSize() / 2;
        int width = gamePanel.getScreenWidth() - (gamePanel.getTileSize() * 4);
        int height = gamePanel.getTileSize() * 3;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color color = new Color(0, 0, 0, 210);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gamePanel.getScreenWidth() / 2 - length / 2;
    }

    public void decreaseCommandNum() {
        this.commandNum--;
    }

    public void increaseCommandNum() {
        this.commandNum++;
    }

    public int getCommandNum() {
        return commandNum;
    }

    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }
}
