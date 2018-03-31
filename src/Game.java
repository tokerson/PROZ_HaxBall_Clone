import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends JFrame{

    public Game(){
        Player player = new Player(0,0,100);
        Player player1 = new Player(100,100,100);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player1);
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
