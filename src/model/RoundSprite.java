package model;
import java.awt.*;

public class RoundSprite implements Collidable{

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
        this(0,0,0,1);
        xspeed = 0;
        yspeed = 0;
    }

    public RoundSprite(int x , int y, int r,double mass ){
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

    public void setRadius(int radius){
        this.radius = radius;
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

    public void setImage(String image){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage(image);
        setImage(img);
    }

    public boolean isCollidingWith(Collidable collidingObject){
        double possibleDistance = getRadius() + collidingObject.getRadius() ;
        double actualDistance= calcDistance(getxCenter() , collidingObject.getxCenter(), getyCenter() , collidingObject.getyCenter());
        return possibleDistance >= actualDistance ;
    }

    private double calcDistance(double x1, double x2 , double y1 , double y2){
        return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

}
