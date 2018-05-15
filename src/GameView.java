import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.image.ImageObserver.WIDTH;

public class GameView extends JPanel {

    private ArrayList<Player> players;
    private ArrayList<Ball> balls;

    GameView(ArrayList<Player> players,ArrayList<Ball> balls){
        setFocusable(true);
        this.players = players;
        this.balls = balls;
        setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));

    }

    private void drawPlayersAndBall(Graphics g){
        for(Player player:players) {
            g.drawImage(player.getImage(), (int)player.getX(), (int)player.getY(), 2*player.getRadius(), 2*player.getRadius(), this);
        }
        for(Ball ball : balls) {
            g.drawImage(ball.getImage(), (int) ball.getX(), (int) ball.getY(), 2 * ball.getRadius(), 2 * ball.getRadius(), this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.fillRect(0,0,Constants.WIDTH,Constants.HEIGHT);
        drawPlayersAndBall(g);
    }



}
