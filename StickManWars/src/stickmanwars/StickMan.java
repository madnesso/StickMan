
package stickmanwars;

import java.awt.*;

public class StickMan extends GameObject implements ICollide{
    private int Width = 100, Height = 100;
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
                    y += velY * -1;
                    falling = false;
                    jumping = false;
                } else {
                    falling = true;
                }
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y += velY * -1;
                }
                if (getBoundsRight().intersects(tempObject.getBounds()) || getBoundsLeft().intersects(tempObject.getBounds())) {
                    x += velX * -1;
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
        g.setColor(Color.CYAN);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(getBounds());
        g2d.draw(getBoundsTop());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + Width / 4, (int) y + Height / 2, Width / 2, Height / 2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) x, (int) y + 10, 10, Height - 20);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x + Width - 10, (int) y + 10, 10, Height - 20);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) x + Width / 4, (int) y, Width / 2, Height / 2);
    }
    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    
}
