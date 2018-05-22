import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameView extends JPanel {

    private ArrayList<Player> players;
    private ArrayList<Ball> balls;
    private Stadium stadium;

    GameView(ArrayList<Player> players,ArrayList<Ball> balls,Stadium stadium){
        setFocusable(true);
        this.players = players;
        this.balls = balls;
        this.stadium = stadium;
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
        g.drawImage(stadium.getImage(),0,0,Constants.WIDTH,Constants.HEIGHT,this);
        drawPlayersAndBall(g);
        g.drawOval(70,210,10,10);
        g.drawOval(70,370,10,10);
    }




}
