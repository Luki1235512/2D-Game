package entity;

import main.GamePanel;

public class Projectile extends Entity {

    Entity user;

    public Projectile(GamePanel gamePanel) {
        super(gamePanel);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update() {

        if (user == gamePanel.getPlayer()) {
            int monsterIndex = gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getMonster());
            if (monsterIndex != Integer.MAX_VALUE) {
                gamePanel.getPlayer().damageMonster(monsterIndex, this, attack, knockBackPower);
                generateParticle(user.projectile, gamePanel.getMonster()[gamePanel.getCurrentMap()][monsterIndex]);
                alive = false;
            }
        }
        if (user != gamePanel.getPlayer()) {
            boolean contactPlayer = gamePanel.getCollisionChecker().checkPlayer(this);
            if (!gamePanel.getPlayer().invincible && contactPlayer) {
                damagePlayer(attack);
                generateParticle(user.projectile, gamePanel.getPlayer());
//                generateParticle(user.projectile, user.projectile);
                alive = false;
            }
        }

        switch (direction) {
            case "up":
                worldY -= speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
        }

        life--;
        if (life <= 0) {
            alive = false;
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }

    public boolean haveResource(Entity user) {
        return false;
    }

    public void subtractResource(Entity user) {
    }

}
