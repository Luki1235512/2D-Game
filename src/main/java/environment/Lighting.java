package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {

    GamePanel gamePanel;
    BufferedImage darknessFilter;

    public Lighting(GamePanel gamePanel, int circleSize) {

        // Create a buffered image
        darknessFilter = new BufferedImage(gamePanel.getScreenWidth(), gamePanel.getScreenHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        Area screenArea = new Area(new Rectangle2D.Double(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight()));

        int centerX = gamePanel.getPlayer().getScreenX() + (gamePanel.getTileSize()) / 2;
        int centerY = gamePanel.getPlayer().getScreenY() + (gamePanel.getTileSize()) / 2;

        double x = centerX - (circleSize / 2);
        double y = centerY - (circleSize / 2);

        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        Area lightArea = new Area(circleShape);

        screenArea.subtract(lightArea);

        g2.setColor(new Color(0, 0, 0, 0.95f));

        g2.fill(screenArea);
        g2.dispose();
    }
}
