import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

//handles user input
//The controller takes your actions and interprets them. If you click a key, it's the controller's job to figure out what that means and how the model should be manipulated based on that action.
//When the controller receives an action from the view, it may need to tell the view to change as a result. For example, the controller could enable or disable certain buttons or menu items in the interface.
public class GameController
{

    ArrayList<Player> players;
    GameView  gameView;
    boolean[] keys;

    public GameController(ArrayList<Player> players,GameView gV){

        this.players = players;
        gameView  = gV;

        gameView.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP :
                            System.out.println("UP");
                            players.get(0).setYspeed(-10);
                        break;
                    case KeyEvent.VK_DOWN :
                            System.out.println("DOWN");
                            players.get(0).setYspeed(10);
                        break;
                    case KeyEvent.VK_RIGHT :
                            System.out.println("RIGHT");
                            players.get(0).setXspeed(10);
                        break;
                    case KeyEvent.VK_LEFT :
                            System.out.println("LEFT");
                            players.get(0).setXspeed(-10);
                        break;
                }
                System.out.println("XSpeed: " + players.get(0).getXspeed());
                System.out.println("YSpeed: " + players.get(0).getYspeed());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP :
                        players.get(0).setYspeed(0);
                        break;
                    case KeyEvent.VK_DOWN :
                        players.get(0).setYspeed(0);
                        break;
                    case KeyEvent.VK_RIGHT :
                        players.get(0).setXspeed(0);
                        break;
                    case KeyEvent.VK_LEFT :
                        players.get(0).setXspeed(0);
                        break;
                }
//                update();
//                gameView.repaint();
                System.out.println("XSpeed: " + players.get(0).getXspeed());
                System.out.println("YSpeed: " + players.get(0).getYspeed());
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }
        });

        java.util.Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                update();
                gameView.repaint();
            }
        };
        timer.schedule(timerTask,0,20);
    }

    public void update(){
        for(Player player : players){
            player.update();
        }
    }

}
