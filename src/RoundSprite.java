import java.awt.Image;

public class RoundSprite {

    private Image image;
    private int x;
    private int y;
    private int xspeed;
    private int yspeed;
    private int radius;
    private int xCenter;
    private int yCenter;

    public RoundSprite(){
        this.x = 0;
        this.y = 0;
        xspeed = 0;
        yspeed = 0;
        radius = 0;
    }

    public RoundSprite(int x , int y, int r ){
        this();
        this.x = x ;
        this.y = y ;
        this.radius = r;
    }

    public void update(){
        x+= xspeed;
        y+= yspeed;
    }

    public int getXspeed() {
        return xspeed;
    }

    public void setXspeed(int xspeed) {
        this.xspeed = xspeed;
    }

    public int getYspeed() {
        return yspeed;
    }

    public void setYspeed(int yspeed) {
        this.yspeed = yspeed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x + xspeed;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y + yspeed;
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

    public int getxCenter() {
        return x + radius;
    }

    public int getyCenter() {
        return y + radius;
    }

}
