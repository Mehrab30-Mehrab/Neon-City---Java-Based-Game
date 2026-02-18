package systems;

import entity.Player;

public class Camera {
    public int x, y;

    public void update(Player player, int screenWidth, int screenHeight) {
        x = player.x - screenWidth / 2;
        y = player.y - screenHeight / 2;
    }
}
