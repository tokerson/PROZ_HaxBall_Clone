import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game {

    public static GameView getGameView(){

        Player player1 = new Player(0,0,Constants.PLAYER_RADIUS,2);
        Player player2 = new Player(100,100,Constants.PLAYER_RADIUS,2);

        Ball ball = new Ball(500,500,Constants.BALL_RADIUS,1.5);

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        ArrayList<Ball> balls = new ArrayList<>();
        balls.add(ball);

        GameView gameView = new GameView(players,balls);
        GameController gameController = new GameController(players,balls,gameView);

        return gameView;
    }


}
