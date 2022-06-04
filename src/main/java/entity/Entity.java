package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX;
    public int worldY;
    public int speed;

    protected BufferedImage up1;
    protected BufferedImage up2;
    protected BufferedImage down1;
    protected BufferedImage down2;
    protected BufferedImage left1;
    protected BufferedImage left2;
    protected BufferedImage right1;
    protected BufferedImage right2;

    public String direction;

    protected int spriteCounter = 0;
    protected int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false;
}
