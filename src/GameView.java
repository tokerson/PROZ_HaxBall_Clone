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
    private Ball ball;

    GameView(ArrayList<Player> players,Ball ball){
        setFocusable(true);
        this.players = players;
        this.ball = ball;
        setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));

    }

    private void drawPlayersAndBall(Graphics g){
        for(Player player:players) {
            g.drawImage(player.getImage(), (int)player.getX(), (int)player.getY(), 2*player.getRadius(), 2*player.getRadius(), this);
        }
        g.drawImage(ball.getImage(),(int) ball.getX(),(int) ball.getY(),2*ball.getRadius(),2*ball.getRadius(),this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.fillRect(0,0,Constants.WIDTH,Constants.HEIGHT);
        drawPlayersAndBall(g);
    }



}
