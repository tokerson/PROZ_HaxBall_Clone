package model;
import java.awt.*;

public class RoundSprite implements Collidable{

    private Image image;
    double x;
    double y;
    double xSpeed;
    double ySpeed;
    private int radius;
    private double mass;
    double friction;

    public RoundSprite(){
        this(0,0,0,1);
        xSpeed = 0;
        ySpeed = 0;
    }

    public RoundSprite(int x , int y, int r,double mass ){
        this.x = x ;
        this.y = y ;
        this.radius = r;
        this.mass = mass;
    }

    public double getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
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

    public double getXCenter() {
        return x + radius;
    }

    public double getYCenter() {
        return y + radius;
    }

    public double getMass() {
        return mass;
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    public void setImage(String image){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage(image);
        setImage(img);
    }

    public double getFriction() {
        return friction;
    }

    public void setFriction(double friction) {
        this.friction = friction;
    }

    public boolean isCollidingWith(Collidable collidingObject){
        double possibleDistance = getRadius() + collidingObject.getRadius() ;
        double actualDistance = calcDistance(getXCenter() , collidingObject.getXCenter(), getYCenter() , collidingObject.getYCenter());
        return possibleDistance >= actualDistance ;
    }

    private double calcDistance(double x1, double x2 , double y1 , double y2){
        return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

    public void update(){
        xSpeed = xSpeed *friction;
        ySpeed = ySpeed *friction;
        x+= xSpeed;
        y+= ySpeed;
    }

}
