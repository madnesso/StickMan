
package stickmanwars;

import java.awt.*;

public final class Sniper  extends Weapon {


    private Handler handler;

    //private int ammo = 5;
    public Sniper(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
        this.handler = handler;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.images[2], (int) x, (int) y, 128, 128, null);
        g.setColor(Color.cyan);
        g.drawRect((int) x, (int) y + 20, getBounds().width, getBounds().height);
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.getX(), (int) this.getY() + 20, 128, 64);
    }
    
}
