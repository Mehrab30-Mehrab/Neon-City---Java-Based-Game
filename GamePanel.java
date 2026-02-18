package engine;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import entity.*;
import world.*;
import systems.*;
import ui.*;

public class GamePanel extends JPanel implements Runnable {

    public final int WIDTH = 800, HEIGHT = 600;
    Thread gameThread;

    Player player;
    ArrayList<Police> polices;
    ArrayList<Car> cars;
    ArrayList<Building> buildings;

    Camera camera;
    InputHandler input;
    HUD hud;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        setDoubleBuffered(true);

        input = new InputHandler();
        addKeyListener(input);
        setFocusable(true);

        camera = new Camera();
        hud = new HUD();

        player = new Player(400, 300);

        // Police
        polices = new ArrayList<>();
        polices.add(new Police(600, 400));
        polices.add(new Police(200, 500));

        // Cars
        cars = new ArrayList<>();
        cars.add(new Car(300, 300));

        // Buildings
        buildings = new ArrayList<>();
        buildings.add(new Building(100, 100, 150, 200, Color.gray));
        buildings.add(new Building(500, 400, 200, 250, Color.darkGray));
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update(input);

        // Combat: attack police
        if (player.attack) {
            for (Police p : polices) {
                if (player.getBounds().intersects(p.getBounds())) {
                    p.health -= 10;
                }
            }
            player.attack = false; // reset
        }

        // Remove dead police
        polices.removeIf(p -> p.health <= 0);

        // Police AI chase
        for (Police p : polices) {
            p.update(player);
        }

        // Update camera
        camera.update(player, WIDTH, HEIGHT);

        // Update cars
        for (Car c : cars) {
            c.update(input.up, input.down, input.left, input.right);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw buildings
        for (Building b : buildings) b.draw(g2, camera);

        // Draw cars
        for (Car c : cars) c.draw(g2, camera);

        // Draw player
        player.draw(g2, camera);

        // Draw police
        for (Police p : polices) p.draw(g2, camera);

        // Draw HUD
        hud.draw(g2, player);
    }
}