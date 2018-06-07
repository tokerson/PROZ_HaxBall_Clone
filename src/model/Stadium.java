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
    // 0 - left top post, 1 - left bottom post , 2 - right top post , 3 - right bottom post
    public Post[] posts = new Post[4];

    public Stadium(){
        String imagefile = "pitch.png";
        setImage(imagefile);

        topBorder = 50*(double)WIDTH/1200;
        downBorder = HEIGHT - 115*(double)WIDTH/1200;
        leftBorder = 70*(double)WIDTH/1200;
        rightBorder = WIDTH - leftBorder;

        topPost = 183*(double)HEIGHT/568;

        for(int i = 0 ; i < posts.length ; i++){
            posts[i] = new Post();
        }

        posts[0].setX(leftBorder - posts[0].getRadius());
        posts[1].setX(leftBorder - posts[1].getRadius());
        posts[2].setX(rightBorder - posts[2].getRadius());
        posts[3].setX(rightBorder - posts[3].getRadius());

        posts[0].setY(topPost);
        posts[1].setY(getBottomPost() - 2*posts[1].getRadius());
        posts[2].setY(topPost);
        posts[3].setY(getBottomPost() - 2*posts[3].getRadius());

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
