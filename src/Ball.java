import java.awt.*;

class Ball extends Player {

    private final String Imagefile = "puck.png";

    Ball(int x, int y, int r){
        super(x,y,r);
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Image img = toolkit.getImage(Imagefile);
//        Image scaleimg = img.getScaledInstance(2*Constants.BALL_RADIUS,2*Constants.BALL_RADIUS,Image.SCALE_DEFAULT);
        setImage(img);
    }
}
