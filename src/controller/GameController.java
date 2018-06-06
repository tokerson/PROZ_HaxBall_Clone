package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import model.*;

import static model.Constants.*;

import view.*;

class GameController
{
    private ArrayList<Player> players;
    private ArrayList<Ball> balls;
    private ArrayList<RoundSprite> gameObjects = new ArrayList<>();
    private GameView  gameView;
    private Stadium stadium;
    private int[] player1Keys;
    private int[] player2Keys;
    private int player1Score = 0;
    private int player2Score = 0;

    boolean gamePaused = false;

    GameController(ArrayList<Player> players,ArrayList<Ball> balls, GameView gV,Stadium stadium){

        this.players = players;
        this.balls = balls;
        this.stadium = stadium;

        gameObjects.addAll(this.players);
        gameObjects.addAll(this.balls);

        gameView  = gV;
        player1Keys = new int[3];
        player2Keys = new int[3];

        gameView.addKeyListener(new InputKeyEvents() {});

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                update();
                gameView.repaint();
            }
        };

        timer.schedule(timerTask,0,10);
    }

    private void update(){
        if(!gamePaused) {
            handleCollision();
            handleCollisionsAgainstTheWalls();

            players.forEach(Player::update);
            balls.forEach(Ball::update);
        }
    }

    private void handleCollisionsAgainstTheWalls(){
        Ball ball = balls.get(0);
        Player player1 = players.get(0);
        Player player2 = players.get(1);

        for(int i = 0 ; i < stadium.posts.length; i++){
            if(balls.get(0).isCollidingWith(stadium.posts[i])){
                hitThePost(balls.get(0),stadium.posts[i]);
            }
        }

        if(isBallInTheGoal()){
            goal();
            return;
        }

        if (isBallHittingHorizontalWalls(ball,stadium)){
            ball.setXspeed(-ball.getXspeed());
        }
        if (isBallHittingVerticalWalls(ball,stadium)) {
            ball.setYspeed(-ball.getYspeed());
        }
    }

    private boolean isBallHittingVerticalWalls(Ball ball,Stadium stadium){
        return ball.getY() <= stadium.getTopBorder() || ball.getY() >= stadium.getDownBorder();
    }

    private boolean isBallHittingHorizontalWalls(Ball ball,Stadium stadium){
        return  (ball.getY() + 2*ball.getRadius() - 2*stadium.posts[0].getRadius() < stadium.getTopPost() || ball.getY() + 2*stadium.posts[0].getRadius() > stadium.getBottomPost()) &&
                (ball.getX() <= stadium.getLeftBorder() || ball.getX() + 2*ball.getRadius() >= stadium.getRightBorder());
    }

    private void goal(){
        if (balls.get(0).getX() < WIDTH / 2){
            player2Score++;
        }
        else{
            player1Score++;
        }
        gameView.updateScore(player1Score,player2Score);

        if(player1Score == 1){
            gamePaused = true;
            gameView.showWinner(1);
        }
        else if(player2Score == 1){
            gamePaused = true;
            gameView.showWinner(2);
        }

        reset();
    }

    private void reset(){
        players.get(0).setX(WIDTH/4 - PLAYER_RADIUS);
        players.get(0).setY(HEIGHT/2 - PLAYER_RADIUS);
        players.get(0).setXspeed(0.0);
        players.get(0).setYspeed(0.0);

        players.get(1).setX(WIDTH*3/4 - PLAYER_RADIUS);
        players.get(1).setY(HEIGHT/2 - PLAYER_RADIUS);
        players.get(1).setXspeed(0.0);
        players.get(1).setYspeed(0.0);

        balls.get(0).setX(WIDTH/2 - BALL_RADIUS);
        balls.get(0).setY(HEIGHT/2 - BALL_RADIUS);
        balls.get(0).setXspeed(0.0);
        balls.get(0).setYspeed(0.0);
    }

    private boolean isBallInTheGoal(){
        Ball ball = balls.get(0);
        return ball.getX() + 2*ball.getRadius() <= stadium.getLeftBorder() || ball.getX() >= stadium.getRightBorder() ;
    }

    private void handleCollision(){

//        if(balls.get(0).isCollidingWith(players.get(1))){
//            hitThePost(balls.get(0),players.get(1));
//        }



        if(players.get(0).isCollidingWith(balls.get(0)) && player1Keys[2] == 1){
            kick(players.get(0),balls.get(0));
        }
        else if(players.get(1).isCollidingWith(balls.get(0))  && player2Keys[2] == 1){
            kick(players.get(1),balls.get(0));
        }
        else {
            for (int i = 0; i < gameObjects.size() - 1; ++i) {
                for (int j = 1; j < gameObjects.size(); ++j) {
                    if (gameObjects.get(i) != gameObjects.get(j) && gameObjects.get(i).isCollidingWith(gameObjects.get(j))) {
                        performStaticCollision(gameObjects.get(i), gameObjects.get(j)); // every object collides statically
                        // every object collides with a ball dynamically
                        if (gameObjects.get(i) instanceof Ball || gameObjects.get(j) instanceof Ball) {
                            performDynamicCollision(gameObjects.get(i), gameObjects.get(j));
                        }
                    }
                }
            }
        }
    }

    private void hitThePost(RoundSprite sprite1, Collidable sprite2){
        double distance = calcDistance(sprite1.getxCenter() , sprite2.getxCenter(), sprite1.getyCenter() , sprite2.getyCenter());
        double overlap =  1.0*(distance - sprite1.getRadius() - sprite2.getRadius());

        double sprite1x = sprite1.getX();
        double sprite1y = sprite1.getY();

        //fey physics calculations
        sprite1x -= overlap*(sprite1x - sprite2.getX()) / distance;
        sprite1y -= overlap*(sprite1y - sprite2.getY()) / distance;

        double nx = (sprite2.getX() - sprite1.getX()) / distance;
        double ny = (sprite2.getY() - sprite1.getY()) / distance;
        double kx = sprite1.getXspeed() + sprite1.getXspeed();
        double ky = sprite1.getYspeed() + sprite1.getYspeed();
        double p = 2.0 * (nx * kx + ny * ky) / (2*sprite1.getMass());

        if(!(sprite1y < stadium.getTopBorder() || sprite1y > stadium.getDownBorder())){
            sprite1.setY(sprite1y);
        }
        if(!(sprite1x <= stadium.getLeftBorder() || sprite1x >= stadium.getRightBorder())){
            sprite1.setX(sprite1x);
        }

        sprite1.setXspeed(sprite1.getXspeed() - p * nx * sprite1.getMass());
        sprite1.setYspeed(sprite1.getYspeed() - p * ny * sprite1.getMass());
    }

    private void kick(RoundSprite sprite1, RoundSprite sprite2){
        double distance = calcDistance(sprite1.getxCenter() , sprite2.getxCenter(), sprite1.getyCenter() , sprite2.getyCenter());

        //fey physics calculations
        double nx = (sprite2.getX() - sprite1.getX()) / distance;
        double ny = (sprite2.getY() - sprite1.getY()) / distance;
        double kx = sprite1.getXspeed() ;
        double ky = sprite1.getYspeed() ;

        double p = 2.0 * (nx * kx + ny * ky) / (sprite1.getMass() + sprite2.getMass());

        sprite1.setXspeed(sprite1.getXspeed());
        sprite1.setYspeed(sprite1.getYspeed());
        sprite2.setXspeed(SHOT_POWER*( p * nx * sprite1.getMass()));
        sprite2.setYspeed(SHOT_POWER*( p * ny * sprite1.getMass()));

    }

    private double calcDistance(double x1, double x2 , double y1 , double y2){
        return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

    private void performStaticCollision(Collidable sprite1, Collidable sprite2){
        double distance = calcDistance(sprite1.getxCenter() , sprite2.getxCenter(), sprite1.getyCenter() , sprite2.getyCenter());
        double overlap =  0.5*(distance - sprite1.getRadius() - sprite2.getRadius());

        double sprite1x = sprite1.getX();
        double sprite2x = sprite2.getX();
        double sprite1y = sprite1.getY();
        double sprite2y = sprite2.getY();

        //fey physics calculations
        sprite1x -= overlap*(sprite1x - sprite2x) / distance;
        sprite1y -= overlap*(sprite1y - sprite2y) / distance;
        sprite2x += overlap*(sprite1x - sprite2x) / distance;
        sprite2y += overlap*(sprite1y - sprite2y) / distance;

        if(!(sprite1y < stadium.getTopBorder() || sprite1y > stadium.getDownBorder())){
            sprite1.setY(sprite1y);
        }
        if(!(sprite2y < stadium.getTopBorder() || sprite2y > stadium.getDownBorder())){
            sprite2.setY(sprite2y);
        }
        if(!(sprite1x < stadium.getLeftBorder() || sprite1x > stadium.getRightBorder())){
            sprite1.setX(sprite1x);
        }
        if(!(sprite2x < stadium.getLeftBorder() || sprite2x > stadium.getRightBorder())){
            sprite2.setX(sprite2x);
        }

    }

    private void performDynamicCollision(RoundSprite sprite1,RoundSprite sprite2){
        double distance = calcDistance(sprite1.getxCenter() , sprite2.getxCenter(), sprite1.getyCenter() , sprite2.getyCenter());

        //fey physics calculations
        double nx = (sprite2.getX() - sprite1.getX()) / distance;
        double ny = (sprite2.getY() - sprite1.getY()) / distance;
        double kx = sprite1.getXspeed() - sprite2.getXspeed();
        double ky = sprite1.getYspeed() - sprite2.getYspeed();
        double p = 2.0 * (nx * kx + ny * ky) / (sprite1.getMass() + sprite2.getMass());

        sprite1.setXspeed(sprite1.getXspeed() - p * nx * sprite2.getMass());
        sprite1.setYspeed(sprite1.getYspeed() - p * ny * sprite2.getMass());

        sprite2.setXspeed(sprite2.getXspeed() + p * nx * sprite1.getMass());
        sprite2.setYspeed(sprite2.getYspeed() + p * ny * sprite1.getMass());

    }

    private class InputKeyEvents extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP :
                    player1Keys[0] = -1;
                    break;
                case KeyEvent.VK_DOWN :
                    player1Keys[0] = 1;
                    break;
                case KeyEvent.VK_RIGHT :
                    player1Keys[1] = 1;
                    break;
                case KeyEvent.VK_LEFT :
                    player1Keys[1] = -1;
                    break;
                case KeyEvent.VK_P:
                    player1Keys[2] = 1;
                    break;
                case KeyEvent.VK_W :
                    player2Keys[0] = -1;
                    break;
                case KeyEvent.VK_S :
                    player2Keys[0] = 1;
                    break;
                case KeyEvent.VK_D :
                    player2Keys[1] = 1;
                    break;
                case KeyEvent.VK_A :
                    player2Keys[1] = -1;
                    break;
                case KeyEvent.VK_SPACE:
                    player2Keys[2] = 1;
                    break;
                case KeyEvent.VK_ESCAPE:    // leave the game
                    gameView.backToMainMenu();
                    break;
            }
            handleMoving();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP :
                    player1Keys[0] = 0;
                    break;
                case KeyEvent.VK_DOWN :
                    player1Keys[0] = 0;
                    break;
                case KeyEvent.VK_RIGHT :
                    player1Keys[1] = 0;
                    break;
                case KeyEvent.VK_LEFT :
                    player1Keys[1] = 0;
                    break;
                case KeyEvent.VK_P:
                    player1Keys[2] = 0;
                    break;
                case KeyEvent.VK_W :
                    player2Keys[0] = 0;
                    break;
                case KeyEvent.VK_S :
                    player2Keys[0] = 0;
                    break;
                case KeyEvent.VK_D :
                    player2Keys[1] = 0;
                    break;
                case KeyEvent.VK_A :
                    player2Keys[1] = 0;
                    break;
                case KeyEvent.VK_SPACE:
                    player2Keys[2] = 0;
                    break;
            }
                handleMoving();
//                update();
//                gameView.repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        private void handleMoving(){
            players.get(0).move(player1Keys);
            players.get(1).move(player2Keys);
        }
    }

}
