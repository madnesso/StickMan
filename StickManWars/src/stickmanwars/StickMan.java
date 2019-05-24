
package stickmanwars;

import java.awt.*;

public class StickMan extends GameObject implements ICollide{

    private Handler handler;
    private float Health;
    private Weapon weapon;
    private ArmorPack armor;
    private Barricade block;
    private int meleeDamage;
    private float MaxSpeed = 10;
    private float gravity = 0.5f;
    public float getHealth() {
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
            if (tempObject.getId() == ID.StickMan1)
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
                if (getBounds().intersects(tempObject.getBounds())) {
                    x += velX * -1;
                    y += velY * -1;
                }
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
        x += velX;
        y += velY;
      setX(clamp(getX(), 0, Game.WIDTH-100));
      setY(clamp(getY(), 0, Game.HEIGHT-120));
      Collision();
        if (jumping || falling) {
            velY += gravity;
            if (velY > MaxSpeed)
                velY = MaxSpeed;
        }
    }

    @Override
    public void render(Graphics g) {
        // here stickMan image should be done, remember to animate it
        g.setColor(Color.BLUE);
        g.fillRect((int) x, (int) y, getDimention().width, getDimention().height);
        g.setColor(Color.cyan);
        g.drawRect((int) x, (int) y, getBounds().width, getBounds().height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 100, 100);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    
}
