import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.image.ImageObserver.WIDTH;

//show all the things
//When you do something to the view (like click a Key) then the view tells the controller what you did. It's the controller's job to handle that.
public class GameView extends JPanel {

    private ArrayList<Player> players;

    GameView(ArrayList<Player> players){
        setFocusable(true);
        this.players = players;
        setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));

    }

    private void drawPlayers(Graphics g){
        for(Player player:players) {
            g.drawImage(player.getImage(), (int)player.getX(), (int)player.getY(), 2*player.getRadius(), 2*player.getRadius(), this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.fillRect(0,0,Constants.WIDTH,Constants.HEIGHT);
        drawPlayers(g);
    }



}
