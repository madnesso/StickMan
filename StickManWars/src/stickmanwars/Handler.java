
package stickmanwars;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
    ArrayList<GameObject> objects = new ArrayList<>();
    
    public void tick(){
        for(int i=0; i<objects.size();i++ )
        {
            GameObject tempObject = objects.get(i);
            tempObject.tick();
        }
    }
    
    public void render(Graphics g){
        for(int i=0; i<objects.size();i++ )
        {
            GameObject tempObject = objects.get(i);
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object){
        this.objects.add(object);
    }
    
    public void removeObject(GameObject object){
        this.objects.remove(object);
    }
}
