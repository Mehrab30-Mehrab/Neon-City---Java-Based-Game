package entity;

import java.awt.*;
import systems.Camera;

public class Police {
    public int x, y, speed = 3;
    public int health = 50;

    public Police(int x, int y) { this.x = x; this.y = y; }

    public void update(Player player) {
        if (player.x > x) x += speed;
        if (player.x < x) x -= speed;
        if (player.y > y) y += speed;
        if (player.y < y) y -= speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 40, 40);
    }

    public void draw(Graphics2D g2, Camera camera) {
        g2.setColor(Color.red);
        g2.fillRect(x - camera.x, y - camera.y, 40, 40);
    }
}