package stickmanwars;

import java.awt.Graphics;
import java.awt.Rectangle;

public final class Castle extends GameObject{

    private int castleHealth;
    private Handler handler;

    public Castle(int x, int y, ID id, Handler handler) {
        super(x, y, id);
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
        return new Rectangle(this.getX(), this.getY(), this.getDimention().width, this.getDimention().height);
    }

    public int getCastleHealth() {
        return castleHealth;
    }

    public void setCastleHealth(int castleHealth) {
        this.castleHealth = castleHealth;
    }

    
    
}
