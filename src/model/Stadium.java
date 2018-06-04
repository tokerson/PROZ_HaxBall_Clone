package model;

import java.awt.*;
import static model.Constants.*;

public class Stadium {

    private Image image;

    private double topBorder;
    private double downBorder;
    private double leftBorder;
    private double rightBorder;

    private double topPost;

    public Stadium(){
        String imagefile = "pitch.png";
        setImage(imagefile);
        topBorder = 50*(double)WIDTH/1200;
        downBorder = HEIGHT - 115*(double)WIDTH/1200;
        leftBorder = 70*(double)WIDTH/1200;
        rightBorder = WIDTH - leftBorder;

        topPost = 200*(double)HEIGHT/568;
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

    public double getDownBorder() {
        return downBorder;
    }

    public double getLeftBorder() {
        return leftBorder;
    }

    public double getRightBorder() {
        return rightBorder;
    }

    public double getTopPost() { return topPost; }

    public double getBottomPost() {
        return HEIGHT - topPost;
    }
}
