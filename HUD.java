package ui;

import java.awt.*;
import entity.Player;

public class HUD {
    public void draw(Graphics2D g2, Player player) {
        g2.setColor(Color.white);
        g2.drawString("Health: " + player.health, 20, 20);
        g2.drawString("Money: $" + player.money, 20, 40);
    }
}