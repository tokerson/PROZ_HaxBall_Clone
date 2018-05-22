import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Stadium {

    private Image image;
    private final String Imagefile = "pitch.png";
    private int height;
    private int width;
    private double leftGoalX;
    private double rightGoalX;

    private double topBorder;
    private double downBorder;
    private double leftBorder;
    private double rightBorder;

    private double leftTopPost;
    private double leftBottomPost;
    private double rightTopPost;
    private double rightBottomPost;


    public Stadium(){
        setImage(Imagefile);
        topBorder = 55;
        downBorder = Constants.HEIGHT - 115;
        leftBorder = 70;
        rightBorder = Constants.WIDTH - 130;

        leftTopPost = 210;
        leftBottomPost = 370;

        rightTopPost = 210;
        rightBottomPost = 370;

    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Image getImage(){
        return image;
    }

    public void setImage(String image){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        this.image  = toolkit.getImage(image);
    }

    public double getTopBorder() {
        return topBorder;
    }

    public void setTopBorder(double topBorder) {
        this.topBorder = topBorder;
    }

    public double getDownBorder() {
        return downBorder;
    }

    public void setDownBorder(double downBorder) {
        this.downBorder = downBorder;
    }

    public double getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(double leftBorder) {
        this.leftBorder = leftBorder;
    }

    public double getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(double rightBorder) {
        this.rightBorder = rightBorder;
    }

    public double getLeftTopPost() {
        return leftTopPost;
    }

    public double getLeftBottomPost() {
        return leftBottomPost;
    }

    public double getRightTopPost() {
        return rightTopPost;
    }

    public double getRightBottomPost() {
        return rightBottomPost;
    }
}
