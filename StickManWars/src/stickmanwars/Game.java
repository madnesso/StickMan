
package stickmanwars;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 1920, HEIGHT = 1080;
    public int ammo2 = 10;
    private int speed = 5;
    private int speed2 = 5;
    private int range = 100;
    float timer = 0;
    private int[] respawnpoint = new int[4];

    public Game(){
        handler = new Handler();
        hud = new HUD();
        weapon = new Weapon(0, 0, ID.Weapon, handler);
        this.addKeyListener(new KeyInput(handler, this));
        BufferedImageLoader loader = new BufferedImageLoader();
        ArrayList<BufferedImage> maps = new ArrayList<BufferedImage>(5);
        for (int i = 0; i < 5; i++) {
            BufferedImage map = loader.loadiamge("/pic/maps/maprp" + i + ".png");
            maps.add(map);
        }
        Random r = new Random();
        background = loader.loadiamge("/pic/2386168-_mg_7306.png");
        tex = new Texture();


        new GameWindow(WIDTH, HEIGHT, "Stick Man Wars", this);
        loadingthemap(maps.get(r.nextInt(4)));
        //handler.addObject(new Sniper(50, 950, ID.Weapon, this.handler));
        //add objects here, but remember to set the dimension in the class's constructor using setter method
    }

    public int getSpeed2() {
        return speed2;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRange() {
        return range;
    }

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Random rand;
    private HUD hud;
    static Texture tex;
    Image background = null;
    public int ammo = 10;  //da
    private Weapon weapon;

    public int getRespawnpoint(int i) {
        return respawnpoint[i];
    }

    public float getTimer() {
        return timer;
    }

    public void setRange(int range) {
        this.range = range;
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
        timer++;
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
        g.drawImage(background, 0, 0, 1920, 1080, this);
        handler.render(g);
        hud.render(g, this);

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

    public void setSpeed2(int i) {
    }

    public static Texture getinstance() {
        return tex;
    }

    private void loadingthemap(BufferedImage image) {
        int w = image.getWidth(), h = image.getHeight();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int pixel = image.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                if (blue == 255 && red == 0 && green == 0) {
                    handler.addObject(new Castle(i * 32, j * 32, ID.Castle, this.handler));
                    respawnpoint[0] = i * 32;
                    respawnpoint[1] = j * 32;
                    handler.addObject(new StickMan(i * 32, j * 32, ID.StickMan1, handler, this));
                } else if (red == 255 && blue == 0 && green == 0)
                    handler.addObject(new Terrain(i * 32, j * 32, ID.Terrain));
                else if (red == 0 && blue == 0 && green == 255) {
                    handler.addObject(new Castle(i * 32, j * 32, ID.Castle2, this.handler));
                    respawnpoint[2] = i * 32;
                    respawnpoint[3] = j * 32;
                    handler.addObject(new StickMan(i * 32, j * 32, ID.StickMan2, handler, this));

                }
            }
        }
    }
}
