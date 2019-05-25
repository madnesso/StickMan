package stickmanwars;

import java.awt.*;

public final class Castle extends GameObject {

    private int castleHealth = HUD.getHealth();
    private Handler handler;

    public Castle(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        if (HUD.getHealth3() == 0) {
            for (int i = 0; i < handler.objects.size(); i++) {
                GameObject tempObject = handler.objects.get(i);
                if (tempObject.getId() == ID.Castle)
                    handler.removeObject(tempObject);
            }
        }
        if (HUD.getHealth4() == 0) {
            for (int i = 0; i < handler.objects.size(); i++) {
                GameObject tempObject = handler.objects.get(i);
                if (tempObject.getId() == ID.Castle2)
                    handler.removeObject(tempObject);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (getId().equals(ID.Castle))
            g.drawImage(tex.images[4], (int) x, (int) y, 96, 128, null);
        else
            g.drawImage(tex.images[5], (int) x - 20, (int) y, 96, 128, null);
        g.setColor(Color.CYAN);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        if (getId().equals(ID.Castle2))
            return new Rectangle((int) this.getX() + 20, (int) this.getY(), 55, 128);
        else
            return new Rectangle((int) this.getX(), (int) this.getY(), 55, 128);
    }

    public int getCastleHealth() {
        return castleHealth;
    }

    public void setCastleHealth(int castleHealth) {
        this.castleHealth = castleHealth;
    }


}
