
package stickmanwars;

import javax.swing.*;
import java.awt.*;


public class GameWindow extends Canvas {
    public GameWindow(int width, int height, String tittle, Game game)
    {
        JFrame frame = new JFrame(tittle);
        
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        //game.start();
        
    }
}
