import java.awt.Image;

public class RoundSprite {

    private Image image;
    private double x;
    private double y;
    private double xspeed;
    private double yspeed;
    private int radius;
    private double xCenter;
    private double yCenter;
    private double angle;

    public RoundSprite(){
        this.x = 0;
        this.y = 0;
        xspeed = 0;
        yspeed = 0;
        radius = 0;
        angle = 0.0;
    }

    public RoundSprite(int x , int y, int r ){
        this();
        this.x = x ;
        this.y = y ;
        this.radius = r;
        angle = 0.0;
    }

    public void update(){
        x+= xspeed;
        y+= yspeed;
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


    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
