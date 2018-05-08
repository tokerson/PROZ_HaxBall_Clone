import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

class GameController
{

    private ArrayList<Player> players;
    private GameView  gameView;
    boolean[] keys;
    private Timer timer;

    GameController(ArrayList<Player> players, GameView gV){

        this.players = players;
        gameView  = gV;
        keys = new boolean[10];

        gameView.addKeyListener(new InputKeyEvents() {

        });

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                update();
                checkCollision();
                gameView.repaint();
            }
        };
        timer.schedule(timerTask,0,10);
    }

    private void update(){
        for(Player player : players){
            player.update();
        }
    }

    private boolean doPlayersCollide(){
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        double i = player1.getRadius() + player2.getRadius() ;
        double j = calcDistance(player1.getxCenter() , player2.getxCenter(), player1.getyCenter() , player2.getyCenter());
        if( i >= j )
        {
            System.out.println("i = " +i);
            System.out.println("j = " +j);
            System.out.println("player1 x and y " + player1.getxCenter()+" "+player1.getyCenter());
            System.out.println("player2 x and y " + player2.getxCenter()+" "+player2.getyCenter());
            return true;
        }
        return false;
    }

    private void checkCollision(){
        if(doPlayersCollide()){
            Player player1 = players.get(0);
            Player player2 = players.get(1);
            double distance = calcDistance(player1.getxCenter() , player2.getxCenter(), player1.getyCenter() , player2.getyCenter());
            double overlap =  0.5*(distance - player1.getRadius() - player2.getRadius());

            double player1x = player1.getX();
            double player2x = player2.getX();
            double player1y = player1.getY();
            double player2y = player2.getY();
            player1x -= overlap*(player1x - player2x) / distance;
            player1y -= overlap*(player1y - player2y) / distance;
            player2x += overlap*(player1x - player2x) / distance;
            player2y += overlap*(player1y - player2y) / distance;

            player1.setX(player1x);
            player1.setY(player1y);
            player2.setX(player2x);
            player2.setY(player2y);

            update();
        }
    }

    private double calcDistance(double x1, double x2 , double y1 , double y2){
        return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

    private class InputKeyEvents extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP :
                      keys[0] = true;
                    break;
                case KeyEvent.VK_DOWN :
                        keys[1] = true;
                    break;
                case KeyEvent.VK_RIGHT :
                        keys[2] = true;
                    break;
                case KeyEvent.VK_LEFT :
                        keys[3] = true;
                    break;
                case KeyEvent.VK_W :
                        keys[4] = true;
                    break;
                case KeyEvent.VK_S :
                        keys[5] = true;
                    break;
                case KeyEvent.VK_D :
                        keys[6] = true;
                    break;
                case KeyEvent.VK_A :
                        keys[7] = true;
                    break;
            }
            handleMoving();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP :
                    keys[0] = false;
                    players.get(0).setYspeed(0);
                    break;
                case KeyEvent.VK_DOWN :
                    keys[1] = false;
                    players.get(0).setYspeed(0);
                    break;
                case KeyEvent.VK_RIGHT :
                    keys[2] = false;
                    players.get(0).setXspeed(0);
                    break;
                case KeyEvent.VK_LEFT :
                    keys[3] = false;
                    players.get(0).setXspeed(0);
                    break;
                case KeyEvent.VK_W :
                    keys[4] = false;
                    players.get(1).setYspeed(0);
                    break;
                case KeyEvent.VK_S :
                    keys[5] = false;
                    players.get(1).setYspeed(0);
                    break;
                case KeyEvent.VK_D :
                    keys[6] = false;
                    players.get(1).setXspeed(0);
                    break;
                case KeyEvent.VK_A :
                    keys[7] = false;
                    players.get(1).setXspeed(0);
                    break;
            }
                update();
                gameView.repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        private void handleMoving(){
            if(keys[0]){
                players.get(0).setYspeed(-Constants.SPEED);
            }
            if(keys[1]){
                players.get(0).setYspeed(Constants.SPEED);
            }
            if(keys[2]){
                players.get(0).setXspeed(Constants.SPEED);
            }
            if(keys[3]){
                players.get(0).setXspeed(-Constants.SPEED);
            }
            if(keys[4]){
                players.get(1).setYspeed(-Constants.SPEED);
            }
            if(keys[5]){
                players.get(1).setYspeed(Constants.SPEED);
            }
            if(keys[6]){
                players.get(1).setXspeed(Constants.SPEED);
            }
            if(keys[7]){
                players.get(1).setXspeed(-Constants.SPEED);
            }
        }
    }


}
