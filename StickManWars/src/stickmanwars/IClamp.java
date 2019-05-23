
package stickmanwars;


public interface IClamp {
    //Use this to set limits for any varibale whatever it is, the implementation is in Gameobject
    float clamp(float var, int min, int max);
}
