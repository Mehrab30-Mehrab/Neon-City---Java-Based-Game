package world;

import java.awt.*;
import systems.Camera;

public class Building {
    public int x, y, width, height;
    public Color color;

    public Building(int x, int y, int width, int height, Color color) {
        this.x = x; this.y = y; this.width = width; this.height = height; this.color = color;
    }

    public void draw(Graphics2D g2, Camera camera) {
        g2.setColor(color);
        g2.fillRect(x - camera.x, y - camera.y, width, height);
    }
}