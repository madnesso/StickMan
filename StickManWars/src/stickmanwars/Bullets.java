package stickmanwars;

import java.awt.*;

public class Bullets extends GameObject {
    Handler handler;

    public Bullets(int x, int y, ID id, int velX, Handler handler) {
        super(x, y, id);
        this.velX = velX;
        this.handler = handler;
    }

    public void Collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            // this code could be used at any class by the same way, just implement ICollide
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.StickMan1) {
                // collision code
            }
            if (tempObject.getId() == ID.Barricade) {

                //collision code
            }
            if (tempObject.getId() == ID.Terrain) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            }
        }
    }

    @Override
    public void tick() {
        x += velX;
        Collision();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }
}
