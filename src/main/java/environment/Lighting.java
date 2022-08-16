package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting {

    private GamePanel gamePanel;
    private BufferedImage darknessFilter;
    private int dayCounter;
    private float filterAlpha = 0f;

    // DAY STATE
    private final int day = 0;
    private final int dusk = 1;
    private final int night = 2;
    private final int dawn = 3;
    private int dayState = day;

    public Lighting(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        setLightSource();
    }

    public void setLightSource() {
        // Create a buffered image
        darknessFilter = new BufferedImage(gamePanel.getScreenWidth(), gamePanel.getScreenHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        if (gamePanel.getPlayer().getCurrentLight() == null) {
            g2.setColor(new Color(0, 0, 0, 0.98f));
        } else {
            int centerX = gamePanel.getPlayer().getScreenX() + (gamePanel.getTileSize()) / 2;
            int centerY = gamePanel.getPlayer().getScreenY() + (gamePanel.getTileSize()) / 2;


            Color[] color = new Color[12];
            float[] fraction = new float[12];

            color[0] = new Color(0, 0, 0, 0.1f);
            color[1] = new Color(0, 0, 0, 0.42f);
            color[2] = new Color(0, 0, 0, 0.52f);
            color[3] = new Color(0, 0, 0, 0.61f);
            color[4] = new Color(0, 0, 0, 0.69f);
            color[5] = new Color(0, 0, 0, 0.76f);
            color[6] = new Color(0, 0, 0, 0.82f);
            color[7] = new Color(0, 0, 0, 0.87f);
            color[8] = new Color(0, 0, 0, 0.91f);
            color[9] = new Color(0, 0, 0, 0.94f);
            color[10] = new Color(0, 0, 0, 0.96f);
            color[11] = new Color(0, 0, 0, 0.98f);

            fraction[0] = 0f;
            fraction[1] = 0.4f;
            fraction[2] = 0.5f;
            fraction[3] = 0.6f;
            fraction[4] = 0.65f;
            fraction[5] = 0.7f;
            fraction[6] = 0.75f;
            fraction[7] = 0.8f;
            fraction[8] = 0.85f;
            fraction[9] = 0.9f;
            fraction[10] = 0.95f;
            fraction[11] = 1f;

            RadialGradientPaint rgp = new RadialGradientPaint(centerX, centerY, gamePanel.getPlayer().getCurrentLight().getLightRadius(), fraction, color);

            g2.setPaint(rgp);
        }

        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        g2.dispose();
    }

    public void update() {
        if (gamePanel.getPlayer().isLightUpdated()) {
            setLightSource();
            gamePanel.getPlayer().setLightUpdated(false);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(darknessFilter, 0, 0, null);
    }
}
