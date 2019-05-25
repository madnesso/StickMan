
package stickmanwars;

import java.awt.*;

public class StickMan extends GameObject implements ICollide{
    private int Width = 50, Height = 150 / 2;
    private Handler handler;
    private Barricade block;
    //private int meleeDamage;
    private float MaxSpeed = 10;
    private float gravity = 0.5f;
    private Game game;
    private boolean player2isdead = false;
    private boolean player1isdead;

    public StickMan(int x, int y, ID id, Handler handler, Game game) {
        super(x, y, id);
        this.setDimention(new Dimension(100, 100));
        this.handler = handler;
        this.game = game;

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

            }
            if(tempObject.getId() == ID.Weapon)
            {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (getId().equals(ID.StickMan1)) {
                        game.ammo += 10;
                        game.setSpeed(50);
                    } else if (getId().equals(ID.StickMan2)) {
                        game.ammo2 += 10;
                        game.setSpeed2(50);
                    }

                    handler.removeObject(tempObject);
                    game.numberofweps--;
                    AudioPlayer.getSoundMap("pickup").play();
                }
            }
            if(tempObject.getId() == ID.Barricade)
            {
                if (getBounds().intersects(tempObject.getBounds())) {
                    y += velY * -1;
                    falling = false;
                    jumping = false;
                } else {
                    falling = true;
                }
                if (getBoundsRight().intersects(tempObject.getBounds()) || getBoundsLeft().intersects(tempObject.getBounds())) {
                    x += velX * -1;
                }
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
            }
            if (tempObject.getId() == ID.HealthPack) {
                if (getId() == ID.StickMan1) {
                    if (HUD.getHealth() <= 50)
                        HUD.setHealth(HUD.getHealth() + 50);
                    else
                        HUD.setHealth(100);
                }
                if (getId() == ID.StickMan2) {
                    if (HUD.getHealth2() <= 50)
                        HUD.setHealth2(HUD.getHealth2() + 50);
                    else
                        HUD.setHealth2(100);
                }
                AudioPlayer.getSoundMap("pickup").play();
                handler.removeObject(this);
            }
            if (tempObject.getId() == ID.ArmorPack) {
                if (getId() == ID.StickMan1) {
                    if (HUD.getHealth3() <= 400)
                        HUD.setHealth3(HUD.getHealth3() + 100);
                    else
                        HUD.setHealth3(500);
                }
                if (getId() == ID.StickMan2) {
                    if (HUD.getHealth4() <= 400)
                        HUD.setHealth4(HUD.getHealth4() + 100);
                    else
                        HUD.setHealth4(500);
                }
                AudioPlayer.getSoundMap("pickup").play();
                handler.removeObject(this);
            }
            if (tempObject.getId() == ID.Bullets) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    if (getId() == ID.StickMan1) {
                        if (HUD.getHealth() > 0)
                            HUD.setHealth(HUD.getHealth() - 50);
                    }
                    if (getId() == ID.StickMan2) {
                        if (HUD.getHealth2() > 0)
                            HUD.setHealth2(HUD.getHealth2() - 50);
                    }
                    handler.removeObject(tempObject);
                }
            }

        }
    }
    
    @Override
    public void tick() {
        //here is the movement and collision 
        x += velX;
        y += velY;
        if (velX < 0) facing = -1;
        else if (velX > 0) facing = 1;
        setX(clamp(getX(), 0, Game.WIDTH - 100));
        setY(clamp(getY(), 0, Game.HEIGHT - 120));
        Collision();
        if (jumping || falling) {
            velY += gravity;
            if (velY > MaxSpeed)
                velY = MaxSpeed;
        }
        if (HUD.getHealth() == 0) {
            for (int i = 0; i < handler.objects.size(); i++) {
                GameObject tempObject = handler.objects.get(i);
                if (tempObject.getId() == ID.StickMan1) {
                    handler.removeObject(tempObject);
                    player1isdead = true;
                    AudioPlayer.getSoundMap("dead").play();

                }
            }
        }
        if (HUD.getHealth2() == 0) {
            for (int i = 0; i < handler.objects.size(); i++) {
                GameObject tempObject = handler.objects.get(i);
                if (tempObject.getId() == ID.StickMan2) {
                    handler.removeObject(tempObject);
                    player2isdead = true;
                    AudioPlayer.getSoundMap("dead").play();

                }
            }
        }
        if (player1isdead) {
            respawn(ID.StickMan1, 0, 1);
            player1isdead = false;
        }
        if (player2isdead) {
            respawn(ID.StickMan2, 2, 3);
            player2isdead = false;
        }
    }

    @Override
    public void render(Graphics g) {
        if (this.facing == 1)
            g.drawImage(tex.images[1], (int) x, (int) y, Width, Height, null);
        else
            g.drawImage(tex.images[3], (int) x, (int) y, Width, Height, null);
        g.setColor(Color.CYAN);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(getBounds());
        g2d.draw(getBoundsTop());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
    }

    public void respawn(ID test, int x, int y) {
        if (test == ID.StickMan1) {
            handler.addObject(new StickMan(game.getRespawnpoint(x), game.getRespawnpoint(y), test, handler, game));
            HUD.setHealth(100);
        } else {
            handler.addObject(new StickMan(game.getRespawnpoint(x), game.getRespawnpoint(y), test, handler, game));
            HUD.setHealth2(100);
        }
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

}
