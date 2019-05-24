package stickmanwars;

import java.awt.image.BufferedImage;

public class Texture {

    public BufferedImage[] images = new BufferedImage[2];
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
    }
}
