package entity;

import java.awt.*;
import systems.Camera;

public class Car {
    public int x, y;

    public Car(int x, int y) { this.x = x; this.y = y; }

    public void update(boolean up, boolean down, boolean left, boolean right) {
        if (up) y -= 5;
        if (down) y += 5;
        if (left) x -= 5;
        if (right) x += 5;
    }

    public void draw(Graphics2D g2, Camera camera) {
        g2.setColor(Color.orange);
        g2.fillRect(x - camera.x, y - camera.y, 50, 30);
    }
}