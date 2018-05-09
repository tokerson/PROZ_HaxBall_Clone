import java.awt.Image;

public class RoundSprite {

    private Image image;
    protected double x;
    protected double y;
    protected double xspeed;
    protected double yspeed;
    private int radius;
    private double xCenter;
    private double yCenter;
    private double mass;

    public RoundSprite(){
        this.x = 0;
        this.y = 0;
        xspeed = 0;
        yspeed = 0;
        radius = 0;
        this.mass = 1;
    }

    public RoundSprite(int x , int y, int r,double mass ){
        this();
        this.x = x ;
        this.y = y ;
        this.radius = r;
        this.mass = mass;
    }

    public double getXspeed() {
        return xspeed;
    }

    public void setXspeed(double xspeed) {
        this.xspeed = xspeed;
    }

    public double getYspeed() {
        return yspeed;
    }

    public void setYspeed(double yspeed) {
        this.yspeed = yspeed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x ;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y =  y ;
    }

    public int getRadius() {
        return radius;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getxCenter() {
        return x + radius;
    }

    public double getyCenter() {
        return y + radius;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
}
