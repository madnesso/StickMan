package stickmanwars;

import java.awt.*;

public final class ArmorPack extends GameObject{

    private Handler handler;

    public ArmorPack(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.images[6], (int) x, (int) y, 96, 96, null);
        g.setColor(Color.cyan);
        g.drawRect((int) x, (int) y + 20, getBounds().width, getBounds().height);
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.getX(), (int) this.getY() + 20, 128, 64);
    }

    
}
