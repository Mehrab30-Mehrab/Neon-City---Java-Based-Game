package entity;

import java.awt.*;
import systems.InputHandler;

public class Player {
    public int x, y, speed = 5;
    public int health = 100, money = 500;
    public boolean attack = false;

    public Player(int x, int y) { this.x = x; this.y = y; }

    public void update(InputHandler input) {
        if (input.up) y -= speed;
        if (input.down) y += speed;
        if (input.left) x -= speed;
        if (input.right) x += speed;

        if (input.attack) attack = true;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 40, 40);
    }

    public void draw(Graphics2D g2, systems.Camera camera) {
        g2.setColor(Color.cyan);
        g2.fillRect(x - camera.x, y - camera.y, 40, 40);
    }
}