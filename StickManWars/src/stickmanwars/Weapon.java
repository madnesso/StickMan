
package stickmanwars;

import java.awt.*;


public class Weapon extends GameObject{

    private Handler handler;
    private int damage;
    private int range;
    public int fireRate = 5;
    
    public Weapon(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        //weapon image
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.getX(), (int) this.getY(), this.getDimention().width, this.getDimention().height);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getFireRate() {
        return fireRate;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }


}
