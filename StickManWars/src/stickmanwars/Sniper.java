
package stickmanwars;

import java.awt.*;

public final class Sniper  extends Weapon {

    private Handler handler;
    
    public Sniper(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
        this.handler = handler;
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.getX(), (int) this.getY(), this.getDimention().width, this.getDimention().height);
    }
    
}
