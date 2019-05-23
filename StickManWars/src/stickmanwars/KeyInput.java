
package stickmanwars;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter{
    
    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(int i=0; i < handler.objects.size(); i++ )
        {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.StickMan1) {
                if(key == KeyEvent.VK_UP){
                tempObject.setY(tempObject.getY()-10);
                }
                if(key == KeyEvent.VK_DOWN){
                tempObject.setY(tempObject.getY()+10);
                }
                if(key == KeyEvent.VK_LEFT){
                tempObject.setX(tempObject.getX()-10);
                }
                if(key == KeyEvent.VK_RIGHT){
                tempObject.setX(tempObject.getX()+10);
                }
            }
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        //code example for keyEvent
        /*for(int i=0; i < handler.objects.size(); i++ )
        {
            GameObject tempObject = handler.objects.get(i);
            if(tempObject.getId() == ID.StickMan){}
        }*/
    }
    
    public void keyTyped(KeyEvent e) {
        int key = e.getKeyCode();
        //code example for keyEvent
        /*for(int i=0; i < handler.objects.size(); i++ )
        {
            GameObject tempObject = handler.objects.get(i);
            if(tempObject.getId() == ID.StickMan){}
        }*/
    }
}
