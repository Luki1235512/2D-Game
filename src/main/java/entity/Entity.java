package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int worldX;
    public int worldY;
    protected int speed;

    protected BufferedImage up1;
    protected BufferedImage up2;
    protected BufferedImage down1;
    protected BufferedImage down2;
    protected BufferedImage left1;
    protected BufferedImage left2;
    protected BufferedImage right1;
    protected BufferedImage right2;

    protected String direction;

    protected int spriteCounter = 0;
    protected int spriteNum = 1;
}
