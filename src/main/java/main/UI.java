package main;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class UI {

    private final GamePanel gamePanel;
    Graphics2D g2;
    private final Font arial_40;
    private final Font arial_80B;
    private boolean messageOn = false;
    private String message = "";
    private int messageCounter = 0;
    private boolean gameFinished = false;

    private double playTime;
    private final DecimalFormat decimalFormat = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.US));

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            // TODO: Do playstate
        }
        if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXCenterText(text);
        int y = gamePanel.getScreenHeight() / 2;

        g2.drawString(text, x, y);
    }

    public int getXCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gamePanel.getScreenWidth() / 2 - length / 2;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}
