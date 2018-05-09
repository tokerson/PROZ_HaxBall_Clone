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
    private Ball ball;
    private ArrayList<RoundSprite> gameObjects = new ArrayList<>();
    private GameView  gameView;
    boolean[] keys;
    private Timer timer;

    GameController(ArrayList<Player> players,Ball ball, GameView gV){

        this.players = players;
        this.ball = ball;

        for(int i = 0 ; i < this.players.size();++i){
            gameObjects.add(this.players.get(i));
        }
        gameObjects.add(ball);
        gameView  = gV;
        keys = new boolean[10];

        gameView.addKeyListener(new InputKeyEvents() {

        });

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                update();
                handleCollision();
                gameView.repaint();
            }
        };
        timer.schedule(timerTask,0,10);
    }

    private void update(){
        for(Player player : players){
            player.update();
        }
        ball.update();
    }

    private boolean doObjectsCollide(RoundSprite sprite1,RoundSprite sprite2){
        double possibleDistance = sprite1.getRadius() + sprite2.getRadius() ;
        double actualDistance= calcDistance(sprite1.getxCenter() , sprite2.getxCenter(), sprite1.getyCenter() , sprite2.getyCenter());
        return possibleDistance >= actualDistance ;
    }

    private void handleCollision(){
        for(int i = 0 ; i <  gameObjects.size() - 1 ; ++i){
            for(int j = 1 ; j <  gameObjects.size(); ++j) {
                if (gameObjects.get(i) !=  gameObjects.get(j) && doObjectsCollide(gameObjects.get(i), gameObjects.get(j))) {
                    performStaticCollision(gameObjects.get(i), gameObjects.get(j));
                }
            }
        }
    }

    private void performStaticCollision(RoundSprite sprite1, RoundSprite sprite2){
        double distance = calcDistance(sprite1.getxCenter() , sprite2.getxCenter(), sprite1.getyCenter() , sprite2.getyCenter());
        double overlap =  0.5*(distance - sprite1.getRadius() - sprite2.getRadius());

        double sprite1x = sprite1.getX();
        double sprite2x = sprite2.getX();
        double sprite1y = sprite1.getY();
        double sprite2y = sprite2.getY();

        sprite1x -= overlap*(sprite1x - sprite2x) / distance;
        sprite1y -= overlap*(sprite1y - sprite2y) / distance;
        sprite2x += overlap*(sprite1x - sprite2x) / distance;
        sprite2y += overlap*(sprite1y - sprite2y) / distance;

        sprite1.setX(sprite1x);
        sprite1.setY(sprite1y);
        sprite2.setX(sprite2x);
        sprite2.setY(sprite2y);

        double nx = (sprite2.getX()-sprite1.getX()) / distance;
        double ny = (sprite2.getY()-sprite1.getY()) / distance;
        double kx = sprite1.getXspeed() - sprite2.getXspeed();
        double ky = sprite1.getYspeed() - sprite2.getYspeed();
        double p = 2.0*(nx*kx + ny*ky)/(sprite1.getMass() + sprite2.getMass());

        sprite1.setXspeed(sprite1.getXspeed() - p * nx * sprite2.getMass());
        sprite1.setYspeed(sprite1.getYspeed() - p * ny * sprite2.getMass());
        sprite2.setXspeed(sprite2.getXspeed() + p * nx * sprite1.getMass());
        sprite2.setYspeed(sprite2.getYspeed() + p * ny * sprite1.getMass());

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
                if(keys[2] || keys[3]){
                    players.get(0).setYspeed(-Constants.SPEED/Math.sqrt(2));
                }
                else
                    players.get(0).setYspeed(-Constants.SPEED);
            }
            if(keys[1]){
                if(keys[2] || keys[3]){
                    players.get(0).setYspeed(Constants.SPEED/Math.sqrt(2));
                } else players.get(0).setYspeed(Constants.SPEED);
            }
            if(keys[2]){
                if(keys[0] || keys[1])
                    players.get(0).setXspeed(Constants.SPEED/Math.sqrt(2));
                    else players.get(0).setXspeed(Constants.SPEED);
            }
            if(keys[3]){
                if(keys[0] || keys[1])
                    players.get(0).setXspeed(-Constants.SPEED/Math.sqrt(2));
                else players.get(0).setXspeed(-Constants.SPEED);
            }
            if(keys[4]){
                if(keys[6] || keys[7]) players.get(1).setYspeed(-Constants.SPEED/Math.sqrt(2));
                else players.get(1).setYspeed(-Constants.SPEED);
            }
            if(keys[5]){
                if(keys[6] || keys[7]) players.get(1).setYspeed(Constants.SPEED/Math.sqrt(2));
                else players.get(1).setYspeed(Constants.SPEED);
            }
            if(keys[6]){
                if(keys[4] || keys[5]) players.get(1).setXspeed(Constants.SPEED/Math.sqrt(2));
                else players.get(1).setXspeed(Constants.SPEED);
            }
            if(keys[7]){
                if(keys[4] || keys[5]) players.get(1).setXspeed(-Constants.SPEED/Math.sqrt(2));
                else players.get(1).setXspeed(-Constants.SPEED);
            }
        }
    }


}
