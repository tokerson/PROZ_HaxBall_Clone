package model;

public class Post implements Collidable {
    private double x;
    private double y;
    private int radius = 11 ;

    public Post(){
        this(0,0);
    }

    public Post(double x,double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public double getxCenter(){
        return x + radius;
    }

    public double getyCenter(){
        return y + radius;
    }
}
