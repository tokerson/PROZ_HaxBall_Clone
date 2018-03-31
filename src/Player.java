import javax.swing.*;
import java.awt.*;

public class Player extends RoundSprite {

    private final String Imagefile = "player.png";

    public Player(int x , int y, int r ){
        super(x,y,r);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage(Imagefile);
        setImage(img);
    }
}
