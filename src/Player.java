import javax.swing.*;
import java.awt.*;

class Player extends RoundSprite {

    private final String Imagefile = "player.png";
    private boolean kicking;
    private int shotPower;

    Player(int x, int y, int r, double m){
        super(x,y,r,m);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage(Imagefile);
        setImage(img);
    }

    public void update(){
        x+= xspeed;
        y+= yspeed;
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
}
