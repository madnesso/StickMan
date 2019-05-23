
package stickmanwars;

import java.awt.*;

public abstract class GameObject implements IClamp{
    protected float x, y;
    protected float velX, velY;
    private ID id;
    private Dimension dimention;
    protected boolean falling = true;
    protected boolean jumping = false;

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
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

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = clamp(x,0,Game.WIDTH);
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = clamp(y,0,Game.HEIGHT);
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = clamp(velX,-50,50);
    }

    public float getVelY() {
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

    public float clamp(float var, int min, int max) {
    if(var >= max)
        return var = max;
    else if(var <= min)
        return var = min;
    else 
        return var;
    }
}
