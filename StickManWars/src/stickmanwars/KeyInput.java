
package stickmanwars;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter {
    private boolean UP = false, DOWN = false, RIGHT = false, LEFT = false;
    private boolean[] isshooting = new boolean[2];
    private Handler handler;
    private Game game; //da

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
        isshooting[0] = false;
        isshooting[1] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.StickMan1) {
                if (key == KeyEvent.VK_W && !tempObject.isJumping()) {
                    {
                        tempObject.setVelY(-15);
                        UP = true;
                        tempObject.setJumping(true);
                    }
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(10);
                    DOWN = true;
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-10);
                    RIGHT = true;
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(10);
                    LEFT = true;
                }
                if (key == KeyEvent.VK_SPACE && game.ammo != 0 && !isshooting[0]) {
                    handler.addObject(new Bullets((int) tempObject.getX() + (tempObject.getFacing() * 50), (int) tempObject.getY() + 10, ID.Bullets, tempObject.getFacing() * game.getSpeed(), this.handler, game));
                    game.ammo--;
                    isshooting[0] = true;
                }
            }
            if (tempObject.getId() == ID.StickMan2) {
                if (key == KeyEvent.VK_UP && !tempObject.isJumping()) {
                    {
                        tempObject.setVelY(-15);
                        UP = true;
                        tempObject.setJumping(true);
                    }
                }
                if (key == KeyEvent.VK_DOWN) {
                    tempObject.setVelY(10);
                    DOWN = true;
                }
                if (key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(-10);
                    LEFT = true;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(10);
                    RIGHT = true;
                }
                if (key == KeyEvent.VK_CONTROL && game.ammo2 != 0 && !isshooting[1]) {
                    handler.addObject(new Bullets((int) tempObject.getX() + (tempObject.getFacing() * 50), (int) tempObject.getY() + 10, ID.Bullets, tempObject.getFacing() * game.getSpeed2(), this.handler, game));
                    game.ammo2--;
                    isshooting[1] = true;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.StickMan1) {
                if (key == KeyEvent.VK_W) {
                    UP = false;
                }
                if (key == KeyEvent.VK_S) DOWN = false;
                if (key == KeyEvent.VK_A) RIGHT = false;
                if (key == KeyEvent.VK_D) LEFT = false;
                //if (!UP && !DOWN) tempObject.setVelY(0);
                if (!RIGHT && !LEFT) tempObject.setVelX(0);
                if (key == KeyEvent.VK_SPACE) {
                    isshooting[0] = false;
                }
            }
            if (tempObject.getId() == ID.StickMan2) {
                if (key == KeyEvent.VK_UP) {
                    UP = false;
                }
                if (key == KeyEvent.VK_DOWN) DOWN = false;
                if (key == KeyEvent.VK_RIGHT) RIGHT = false;
                if (key == KeyEvent.VK_LEFT) LEFT = false;
                //if (!UP && !DOWN) tempObject.setVelY(0);
                if (!RIGHT && !LEFT) tempObject.setVelX(0);
                if (key == KeyEvent.VK_CONTROL) {
                    isshooting[1] = false;
                }
            }
        }
    }
}