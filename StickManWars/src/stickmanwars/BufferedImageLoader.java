package stickmanwars;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class BufferedImageLoader {
    private BufferedImage image;

    public BufferedImage loadiamge(String path) {
        InputStream is;
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            System.out.println("error hena");
            e.printStackTrace();
        }
        return image;
    }

}
