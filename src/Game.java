import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends JFrame{

    Game(){
//        Ball ball = new Ball(200,150,Constants.BALL_RADIUS);
        Player player = new Player(0,0,Constants.PLAYER_RADIUS);
        Player player1 = new Player(100,100,Constants.PLAYER_RADIUS);
        Ball ball = new Ball(500,500,Constants.BALL_RADIUS);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player1);
        players.add(ball);
        GameView gameView = new GameView(players);
        GameController gameController = new GameController(players,gameView);
        init(gameView);
    }
    private void init(GameView gameView){
        add(gameView);
        setTitle(Constants.TITLE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game();
            }
        });
    }


}
