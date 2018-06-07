package model;

public class Ball extends RoundSprite {

    public Ball(){
        this(0,0,Constants.BALL_RADIUS,Constants.BALL_MASS);
    }

    public Ball(int x, int y, int r,double m){
        super(x,y,r,m);
        setFriction(0.98);
        String imagefile = "puck.png";
        setImage(imagefile);
    }

}
