import com.sun.tools.jconsole.JConsoleContext;

import javax.swing.*;
import java.awt.*;

class Player extends RoundSprite {

    private final String Imagefile = "player.png";
    private boolean kicking;
    private int shotPower;
    private double friction = 1;

    Player(int x, int y, int r, double m) {
        super(x, y, r, m);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage(Imagefile);
        setImage(img);
    }

    public void update() {
        xspeed = xspeed * friction;
        yspeed = yspeed * friction;
        x += xspeed;
        y += yspeed;
    }

    public boolean isKicking() {
        return kicking;
    }

    public void setKicking(boolean kicking) {
        this.kicking = kicking;
    }

    public int getShotPower() {
        return shotPower;
    }

    public void setShotPower(int shotPower) {
        this.shotPower = shotPower;
    }

    public void move(int[] keys) {

        if(isAnyKeyPressed(keys)){
            friction = 1.0;

            if(isMovingInTwoDirections(keys)){
                setYspeed((double)keys[0]*Constants.SPEED / Math.sqrt(2));
                setXspeed((double)keys[1]*Constants.SPEED / Math.sqrt(2));
            }
            else {
                setYspeed((double)keys[0]*Constants.SPEED);
                setXspeed((double)keys[1]*Constants.SPEED);
            }
        }
        else friction = 0.96;


    }

    private boolean isAnyKeyPressed(int[]keys){
        for(int i = 0 ; i < 2 ; ++i){
            if(keys[i] != 0) return true;
        }
        return false;
    }

    private boolean isMovingInTwoDirections(int[] keys){
        for(int i = 0 ; i < 2 ; ++i){
            if(keys[i] == 0) return false;
        }
        return true;
    }
}
