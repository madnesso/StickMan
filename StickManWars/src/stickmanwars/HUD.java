
package stickmanwars;

import java.awt.*;

//use this class for standard HUD like stickman health, castleHealth, etc.....

public class HUD {

    private static int health = 100;
    private static int health2 = 100;
    private static int health3 = 500;
    private static int health4 = 500;

    public static int getHealth() {
        return health;
    }

    public static void setHealth(int health) {
        HUD.health = health;
    }

    public static int getHealth2() {
        return health2;
    }

    public static void setHealth2(int health2) {
        HUD.health2 = health2;
    }

    public static int getHealth3() {
        return health3;
    }

    public static void setHealth3(int health3) {
        HUD.health3 = health3;
    }

    public static int getHealth4() {
        return health4;
    }

    public static void setHealth4(int health4) {
        HUD.health4 = health4;
    }

    public void tick(){
    }

    public void render(Graphics g, Game game) {
        //example
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 30);
        g.fillRect(1700, 15, 200, 30);
        g.fillRect(15, 50, 500, 30);
        g.fillRect(1700 - 300, 50, 500, 30);
        g.setColor(Color.green);
        g.fillRect(15, 15, health*2, 30);
        g.fillRect(1700, 15, health2 * 2, 30);
        g.fillRect(15, 50, health3, 30);
        g.fillRect(1700 - 300, 50, health4, 30);
        g.setColor(Color.red);
        g.drawRect(15, 15, 200, 30);
        g.drawRect(1700, 15, 200, 30);
        g.drawRect(15, 50, 500, 30);
        g.drawRect(1700 - 300, 50, 500, 30);
        g.setColor(Color.white);
        g.drawString("ammo:" + game.ammo, 15, 100);
        g.drawString("ammo:" + game.ammo2, 1835, 100);
    }
}
