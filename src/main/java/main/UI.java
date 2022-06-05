package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class UI {

    private final GamePanel gamePanel;
    private final Font arial_40;
    private final Font arial_80B;
    private final BufferedImage keyImage;
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
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished) {

            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gamePanel.getScreenWidth() / 2 - textLength / 2;
            y = gamePanel.getScreenHeight() / 2 - (gamePanel.getTileSize() * 3);
            g2.drawString(text, x, y);

            text = "Your Time is: " + decimalFormat.format(playTime) + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gamePanel.getScreenWidth() / 2 - textLength / 2;
            y = gamePanel.getScreenHeight() / 2 + (gamePanel.getTileSize() * 4);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);

            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gamePanel.getScreenWidth() / 2 - textLength / 2;
            y = gamePanel.getScreenHeight() / 2 + (gamePanel.getTileSize() * 2);
            g2.drawString(text, x, y);

            gamePanel.setGameThread(null);

        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gamePanel.getTileSize() / 2, gamePanel.getTileSize() / 2, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            g2.drawString("x " + gamePanel.getPlayer().getHasKey(), 74, 65);

            // TIME
            playTime += (double) 1 / 60;
            g2.drawString("Time: " + decimalFormat.format(playTime), gamePanel.getTileSize() * 11, 65);

            // MESSAGE
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gamePanel.getTileSize() / 2, gamePanel.getTileSize() * 5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }


    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}
