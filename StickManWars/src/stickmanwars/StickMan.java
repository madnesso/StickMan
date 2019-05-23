
package stickmanwars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

public class StickMan extends GameObject implements ICollide{

    private Handler handler;
    private int Health;
    private Weapon weapon;
    private ArmorPack armor;
    private Barricade block;
    private int meleeDamage;

    public int getHealth() {
        return Health;
    }

    public void setHealth(int Health) {
        //example on how to use clamp
        this.Health = clamp(Health,0,100);
    }
    
    public StickMan(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.setDimention(new Dimension(100, 100));
        this.handler = handler;
    }
    
    public void MeleeDamage(){
    //no idea how to do it yet
    }

    public void Collision()
    {
        for(int i=0; i < handler.objects.size(); i++ )
        {
            // this code could be used at any class by the same way, just implement ICollide 
            GameObject tempObject = handler.objects.get(i);
            if(tempObject.getId() == ID.StickMan)
            {
                // collision code 
            }
            if(tempObject.getId() == ID.Weapon)
            {
                //collision code
            }
            if(tempObject.getId() == ID.Barricade)
            {
                //collision code
            }
            if(tempObject.getId() == ID.Terrain)
            {
                //collision code
            }
            if(tempObject.getId() == ID.Pack)
            {
                //collision code
            }
            
        }
    }
    
    @Override
    public void tick() {
        //here is the movement and collision 
      setX(getX()+getVelX());
      setY(getY()+getVelY());
      setX(clamp(getX(), 0, Game.WIDTH-100));
      setY(clamp(getY(), 0, Game.HEIGHT-120));
      Collision();
    }

    @Override
    public void render(Graphics g) {
        // here stickMan image should be done, remember to animate it
        g.setColor(Color.BLUE);
        g.fillRect(getX(), getY(), getDimention().width, getDimention().height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getX(), this.getY(), this.getDimention().width, this.getDimention().height);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    
}
