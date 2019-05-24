
package stickmanwars;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter {
    private boolean UP = false, DOWN = false, RIGHT = false, LEFT = false;
    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.StickMan1) {
                if (key == KeyEvent.VK_W && !tempObject.isJumping()) {
                    {
                        tempObject.setVelY(-20);
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
            }
        }
    }
}