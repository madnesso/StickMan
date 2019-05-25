package stickmanwars;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.HashMap;
import java.util.Map;

public class AudioPlayer {
    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();

    public static void load() {
        try {
            soundMap.put("dead", new Sound("sounds/dead.ogg"));
            soundMap.put("jump", new Sound("sounds/jump.ogg"));
            soundMap.put("pickup", new Sound("sounds/pickup.ogg"));
            soundMap.put("shoot", new Sound("sounds/shoot.ogg"));
            musicMap.put("background", new Music("sounds/background.ogg"));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Sound getSoundMap(String key) {
        return soundMap.get(key);
    }

    public static Music getMusicMap(String key) {
        return musicMap.get(key);
    }
}