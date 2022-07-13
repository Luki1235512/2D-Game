package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting {

    GamePanel gamePanel;
    BufferedImage darknessFilter;

    public Lighting(GamePanel gamePanel, int circleSize) {

        // Create a buffered image
        darknessFilter = new BufferedImage(gamePanel.getScreenWidth(), gamePanel.getScreenHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

    }
}
