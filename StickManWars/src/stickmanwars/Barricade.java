package stickmanwars;

import java.awt.Graphics;
import java.awt.Rectangle;

public final class Barricade extends GameObject{

    private int Armor;
    private boolean Activated = false; //stickMan picks it then it becomes true, uses it near castle when true increases castle health
    private Handler handler;

    public Barricade(int x, int y, ID id, Handler handler) {
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

    
    
    
}
