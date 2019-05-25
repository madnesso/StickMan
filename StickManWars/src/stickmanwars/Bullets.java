package stickmanwars;

import java.awt.*;

public class Bullets extends GameObject implements ICollide, IClamp {
    Handler handler;
    private Game game;

    public Bullets(int x, int y, ID id, int velX, Handler handler, Game game) {
        super(x, y, id);
        this.velX = velX;
        this.handler = handler;
        this.game = game;
    }

    public void Collision() {
        for (int i = 0; i < handler.objects.size(); i++) {

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
            if (tempObject.getId() == ID.Castle) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                    HUD.setHealth3(HUD.getHealth3() - 10);
                }
            }
            if (tempObject.getId() == ID.Castle2) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                    HUD.setHealth4(HUD.getHealth4() - 10);
                }
            }
        }
    }

    @Override
    public void tick() {

        if (game.getRange() != 0) {
            x += velX;
            Collision();
            game.setRange(game.getRange() - 1);
        } else {
            handler.removeObject(this);
            game.setRange(100);
        }
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
