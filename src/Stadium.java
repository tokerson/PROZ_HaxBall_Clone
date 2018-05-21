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


    public Stadium(){
        setImage(Imagefile);
        topBorder = 100;
        downBorder = Constants.HEIGHT - 100;
        leftBorder = 100;
        rightBorder = Constants.WIDTH - 100;
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
}
