
package stickmanwars;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 1920, HEIGHT = 1080;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Random rand;
    private HUD hud;
    
    public Game(){
        handler = new Handler();
        hud = new HUD();
        this.addKeyListener(new KeyInput(handler));
        new GameWindow(WIDTH, HEIGHT, "Stick Man Wars", this);
        handler.addObject(new StickMan(800, 500, ID.StickMan1, handler));
        //add objects here, but remember to set the dimension in the class's constructor using setter method
    }
    
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }
    
    private void tick(){
        handler.tick();
        hud.tick();
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ///////////////////////////////
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1920, 1080);
        handler.render(g);
        hud.render(g);

        //////////////////////////////
        g.dispose();
        bs.show();     
    }
    
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public synchronized void stop()
    {
        try{
        thread.join();
        running = false;}
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
