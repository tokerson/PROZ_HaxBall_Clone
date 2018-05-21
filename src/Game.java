import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends JFrame {

    public Game(){
        add(getGameView());
//        setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public static GameView getGameView(){

        Stadium stadium = new Stadium();
        Player player1 = new Player(Constants.WIDTH/4 - Constants.PLAYER_RADIUS,Constants.HEIGHT/2 - Constants.PLAYER_RADIUS,Constants.PLAYER_RADIUS,2);
        Player player2 = new Player(Constants.WIDTH*3/4 - Constants.PLAYER_RADIUS,Constants.HEIGHT/2 - Constants.PLAYER_RADIUS, Constants.PLAYER_RADIUS,2);

        Ball ball = new Ball(Constants.WIDTH/2 - Constants.BALL_RADIUS,Constants.HEIGHT/2 - Constants.BALL_RADIUS,Constants.BALL_RADIUS,2);

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        ArrayList<Ball> balls = new ArrayList<>();
        balls.add(ball);

        GameView gameView = new GameView(players,balls,stadium);
        GameController gameController = new GameController(players,balls,gameView,stadium);

        return gameView;
    }


}
