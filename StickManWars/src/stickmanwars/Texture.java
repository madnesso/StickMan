package stickmanwars;

import java.awt.image.BufferedImage;

public class Texture {

    public BufferedImage[] images = new BufferedImage[6];
    Spritesheet bs, ps;
    BufferedImage blocksheet = null;
    BufferedImage playersheet = null;

    public Texture() {
        BufferedImageLoader loader = new BufferedImageLoader();
        //blocksheet = loader.loadiamge("/blabla");
        //playersheet = loader.loadiamge("/blablatany");
        bs = new Spritesheet(blocksheet);
        ps = new Spritesheet(playersheet);
        gettexture();
    }

    private void gettexture() {
        BufferedImageLoader loader = new BufferedImageLoader();
        images[0] = loader.loadiamge("/pic/block.png");
        images[1] = loader.loadiamge("/pic/Layer 1.png");
        images[2] = loader.loadiamge("/pic/Sniper.png");
        images[3] = loader.loadiamge("/pic/Layer 2.png");
        images[4] = loader.loadiamge("/pic/dirty.png");
        images[5] = loader.loadiamge("/pic/dirty2.png");
    }
}
