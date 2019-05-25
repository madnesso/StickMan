package stickmanwars;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public Game getGame() {
        return game;
    }

    public void mousePressed(MouseEvent e) {
        int px = e.getX();
        int py = e.getY();

        if (hover(px, py, 810, 250, 300, 150)) {
            game.gameState = State.game;
        }

        else if (hover(px, py, 810, 750, 300, 150)) {
            System.exit(1);
        }
    }



    public void mouseReleased() {

    }

    public void tick() {

    }

    private boolean hover(int px, int py, int x, int y, int width, int height) {

        if (px > x && px < x + width) {
            return py > y && py < y + height;
        } else
            return false;
    }

    public void render(Graphics g) {
        if (game.gameState == State.menu) {
            Font font = new Font("arial", 1, 50);
            Font font2 = new Font("bold", 2, 35);

            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("Welcome to war!", 760, 120);

            g.setFont(font2);
            g.setColor(Color.BLACK);
            g.fillRect(810, 250, 300, 150);
            g.setColor(Color.WHITE);
            g.drawString("New Game", 875, 340);

            /*g.setFont(font2);
            g.setColor(Color.BLACK);
            g.fillRect(810, 500, 300, 150);
            g.setColor(Color.WHITE);
            g.drawString("Load Saved Game", 820, 590);*/

            g.setFont(font2);
            g.setColor(Color.BLACK);
            g.fillRect(810, 750, 300, 150);
            g.setColor(Color.WHITE);
            g.drawString("Quit", 920, 840);
        } else if (game.gameState == State.end) {

        }
    }




}
