
package stickmanwars;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject implements IClamp{
    private int x,y;
    private int velX,velY;
    private ID id;
    private Dimension dimention;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public Dimension getDimention() {
        return dimention;
    }

    public void setDimention(Dimension dimention) {
        this.dimention = dimention;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = clamp(x,0,Game.WIDTH);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = clamp(y,0,Game.HEIGHT);
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = clamp(velX,-50,50);
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = clamp(velY,-50,50);
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public abstract Rectangle getBounds();
    
    public int clamp(int var, int min, int max) {
    if(var >= max)
        return var = max;
    else if(var <= min)
        return var = min;
    else 
        return var;
    }
}
