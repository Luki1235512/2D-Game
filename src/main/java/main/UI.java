package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    private final GamePanel gamePanel;
    private Graphics2D g2;
    private Font webfontRegular;
    private boolean messageOn = false;
    private String message = "";
    private final int messageCounter = 0;
    private final boolean gameFinished = false;
    private String currentDialogue = "";


    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        InputStream inputStream = getClass().getResourceAsStream("/font/000webfont Regular.ttf");
        try {
            assert inputStream != null;
            webfontRegular = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

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

        // PLAY STATE
        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            // TODO: Do playstate
        }

        // PAUSE STATE
        if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if (gamePanel.getGameState() == gamePanel.getDialogueState()) {
            drawDialogueScreen();
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

    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }
}
