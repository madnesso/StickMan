
package stickmanwars;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Canvas implements Runnable, WindowListener, Serializable {
    public boolean[] isshooting = new boolean[2];
    static final int WIDTH = 1920, HEIGHT = 1080;
    public int ammo2 = 0;
    public int ammo = 0;
    public int numberofweps = 4;
    public int armorpacknumber = 2;
    public int healthpackumber = 3;
    private int speed = 5;
    private int speed2 = 5;
    private int range = 100;
    public State gameState;
    private Menu menu;
    private BufferedImageLoader loader = new BufferedImageLoader();
    private ArrayList<BufferedImage> maps = new ArrayList<BufferedImage>(5);
    private int[] respawnpoint = new int[4];
    private Random r = new Random();
    private Image background = null;

//    public void SaveData() throws FileNotFoundException, IOException
//    {
//        File myFile = new File("game.bin");
//        ObjectOutputStream Bin = new ObjectOutputStream(new FileOutputStream(myFile));
//        Bin.writeObject(this.menu.getGame());
//        Bin.close();
//    }


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

    public Game() {
        AudioPlayer.load();
        handler = new Handler();
        hud = new HUD();

        weapon = new Weapon(0, 0, ID.Weapon, handler);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        for (int i = 0; i < 5; i++) {
            BufferedImage map = loader.loadiamge("/pic/maps/maprp" + i + ".png");
            maps.add(map);
        }

        background = loader.loadiamge("/pic/2386168-_mg_7306.png");
        tex = new Texture();


        new GameWindow(WIDTH, HEIGHT, "Stick Man Wars", this);
        menu = new Menu(this, handler);
        loadingthemap(maps.get(r.nextInt(4)));
        AudioPlayer.getMusicMap("background").loop();
        //handler.addObject(new Sniper(50, 950, ID.Weapon, this.handler));
        //add objects here, but remember to set the dimension in the class's constructor using setter method
    }
    private Weapon weapon;

    public int getRespawnpoint(int i) {
        return respawnpoint[i];
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
        if (gameState == State.game) {
            handler.tick();
            hud.tick();
            if (numberofweps == 0) {
                respawn(maps.get(r.nextInt(4)), 0, 255, 255);
                numberofweps = 4;
            }
            if (armorpacknumber == 0) {
                respawn(maps.get(r.nextInt(4)), 255, 255, 0);
                armorpacknumber = 2;
            }
            if (healthpackumber == 0) {
                respawn(maps.get(r.nextInt(4)), 255, 0, 255);
                healthpackumber = 3;
            }
            if (isshooting[0] || isshooting[1]) {
                AudioPlayer.getSoundMap("shoot").play();
            }
        } else if (gameState == State.menu || gameState == State.end) {
            menu.tick();
        }

    }

    private void respawn(BufferedImage image, int r, int g, int b) {
        int w = image.getWidth(), h = image.getHeight();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int pixel = image.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                if (red == r && blue == b && green == g) {
                    if (r == 0 && b == 255 && g == 255)
                        handler.addObject(new Sniper(i * 32, j * 32, ID.Weapon, this.handler));
                    if (r == 255 && b == 0 && g == 255)
                        handler.addObject(new ArmorPack(i * 32, j * 32, ID.ArmorPack, this.handler));
                    if (r == 255 && b == 255 && g == 0)
                        handler.addObject(new HealthPack(i * 32, j * 32, ID.HealthPack, this.handler));
                }
            }
        }
    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.drawImage(background, 0, 0, 1920, 1080, this);
        handler.render(g);
        if (gameState == State.game)
            hud.render(g, this);
        else if (gameState == State.menu || gameState == State.end) {
            menu.render(g);
        }
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
        speed2 = i;
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
                } else if (red == 0 && blue == 255 && green == 255) {
                    handler.addObject(new Sniper(i * 32, j * 32, ID.Weapon, this.handler));

                } else if (red == 255 && blue == 0 && green == 255) {
                    handler.addObject(new ArmorPack(i * 32, j * 32, ID.ArmorPack, this.handler));

                } else if (red == 255 && blue == 255 && green == 0) {
                    handler.addObject(new HealthPack(i * 32, j * 32, ID.HealthPack, this.handler));
                }
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
