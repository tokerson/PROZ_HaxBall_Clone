package model;
import java.awt.*;
public class Ball extends RoundSprite {

    private final String Imagefile = "puck.png";
    private double friction = 0.99;

    public Ball(){
        setImage(Imagefile);
    }

    public Ball(int x, int y, int r,double m){
        super(x,y,r,m);
        setImage(Imagefile);
    }

    public void update(){
        xspeed = xspeed*friction;
        yspeed  = yspeed*friction;
        x+= xspeed;
        y+= yspeed;
    }

    public double getFriction() {
        return friction;
    }

    public void setFriction(double friction) {
        this.friction = friction;
    }
}
