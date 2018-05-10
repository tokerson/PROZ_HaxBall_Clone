import com.sun.tools.jconsole.JConsoleContext;

import javax.swing.*;
import java.awt.*;

class Player extends RoundSprite {

    private final String Imagefile = "player.png";
    private boolean kicking;
    private int shotPower;
    private double friction = 1;
    private int acceleration = 3;

    final double maxSpeed = Constants.SPEED;
    final int continuousMoveCycleMax = 50;

    double theCurrentSpeed = 0;
    int continuousMoveCycle = 0;

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

    public void move(boolean[] keys) {
        if (!(keys[0] || keys[1] || keys[2] || keys[3])) friction = 0.98;
        else {
            friction = 1.0;
            //I should find a better way to do this later on
            setXspeed(0);
            setYspeed(0);
            if (keys[0]) {
                if (keys[2] || keys[3]) {
                    setYspeed(-Constants.SPEED / Math.sqrt(2));
                } else
                    setYspeed(-Constants.SPEED);
            }
            if (keys[1]) {
                if (keys[2] || keys[3]) {
                    setYspeed(Constants.SPEED / Math.sqrt(2));
                } else setYspeed(Constants.SPEED);
            }
            if (keys[2]) {
                if (keys[0] || keys[1])
                    setXspeed(Constants.SPEED / Math.sqrt(2));
                else setXspeed(Constants.SPEED);
            }
            if (keys[3]) {
                if (keys[0] || keys[1])
                    setXspeed(-Constants.SPEED / Math.sqrt(2));
                else setXspeed(-Constants.SPEED);
            }
        }

    }
}
