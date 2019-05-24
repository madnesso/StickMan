package stickmanwars;

import java.awt.*;

public class Terrain extends GameObject {
    public Terrain(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int) x, (int) y, 32, 32);
        g.setColor(Color.yellow);
        g.drawRect((int) x, (int) y, getBounds().width, getBounds().height);
    }

    @Override
    public Rectangle getBounds() {
        Graphics g;
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
