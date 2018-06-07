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
    private boolean gamePaused = false;

    GameController(ArrayList<Player> players,ArrayList<Ball> balls, GameView gameView,Stadium stadium){

        this.players = players;
        this.balls = balls;
        this.stadium = stadium;
        this.gameView  = gameView;

        gameObjects.addAll(this.players);
        gameObjects.addAll(this.balls);

        player1Keys = new int[3];
        player2Keys = new int[3];

        gameView.addKeyListener(new InputKeyEvents() {});

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                updateGame();
                gameView.repaint();
            }
        };

        timer.schedule(timerTask,0,10);
    }

    private void updateGame(){
        if(!gamePaused) {
            handleCollision();
            handleCollisionsAgainstTheWalls();

            players.forEach(Player::update);
            balls.forEach(Ball::update);
        }
    }


    private void handleCollisionsAgainstTheWalls(){
        Ball ball = balls.get(0);

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
            ball.setXSpeed(-ball.getXSpeed());
        }
        if (isBallHittingVerticalWalls(ball,stadium)) {
            ball.setYSpeed(-ball.getYSpeed());
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

        if(player1Score == 5){
            gamePaused = true;
            gameView.showWinner(1);
        }
        else if(player2Score == 5){
            gamePaused = true;
            gameView.showWinner(2);
        }

        reset();
    }

    private void reset(){
        players.get(0).setX(WIDTH/4 - PLAYER_RADIUS);
        players.get(0).setY(HEIGHT/2 - PLAYER_RADIUS);

        players.get(1).setX(WIDTH*3/4 - PLAYER_RADIUS);
        players.get(1).setY(HEIGHT/2 - PLAYER_RADIUS);

        balls.get(0).setX(WIDTH/2 - BALL_RADIUS);
        balls.get(0).setY(HEIGHT/2 - BALL_RADIUS);

        gameObjects.forEach(s -> {  s.setXSpeed(0.0);   s.setYSpeed(0.0);   });
    }

    private boolean isBallInTheGoal(){
        Ball ball = balls.get(0);
        return ball.getX() + 2*ball.getRadius() <= stadium.getLeftBorder() || ball.getX() >= stadium.getRightBorder() ;
    }

    private boolean isMoving(RoundSprite sprite){
        return !((int) sprite.getYSpeed() == 0 && (int) sprite.getXSpeed() == 0);
    }

    private void handleCollision(){

        if(players.get(0).isCollidingWith(balls.get(0))){
            if(player1Keys[2] == 1 && isMoving(players.get(0)))
                kick(players.get(0),balls.get(0));
            else{
                performStaticCollision(players.get(0), balls.get(0)); // every object collides statically
                performDynamicCollision(players.get(0), balls.get(0));
            }
        }
        if(players.get(1).isCollidingWith(balls.get(0))){
            if(player2Keys[2] == 1)
                kick(players.get(1),balls.get(0));
            else{
                performStaticCollision(players.get(1), balls.get(0)); // every object collides statically
                performDynamicCollision(players.get(1), balls.get(0));
            }
        }
        if(players.get(0).isCollidingWith(players.get(1))){
            performStaticCollision(players.get(0),players.get(1));
        }
//        if(players.get(1).isCollidingWith(balls.get(0))  && player2Keys[2] == 1){
//            kick(players.get(1),balls.get(0));
//        }
//        for (int i = 0; i < gameObjects.size() - 1; ++i) {
//            for (int j = 1; j < gameObjects.size(); ++j) {
//                if (gameObjects.get(i) != gameObjects.get(j) && gameObjects.get(i).isCollidingWith(gameObjects.get(j))) {
//                    performStaticCollision(gameObjects.get(i), gameObjects.get(j)); // every object collides statically
//                    // every object collides with a ball dynamically
//                    if (gameObjects.get(i) instanceof Ball || gameObjects.get(j) instanceof Ball) {
//                        performDynamicCollision(gameObjects.get(i), gameObjects.get(j));
//                    }
//                }
//            }
//        }

    }

    private void hitThePost(RoundSprite sprite1, Collidable sprite2){
        double distance = calcDistance(sprite1.getXCenter() , sprite2.getXCenter(), sprite1.getYCenter() , sprite2.getYCenter());
        double overlap =  1.0*(distance - sprite1.getRadius() - sprite2.getRadius());

        double sprite1x = sprite1.getX();
        double sprite1y = sprite1.getY();

        //fey physics calculations
        sprite1x -= overlap*(sprite1x - sprite2.getX()) / distance;
        sprite1y -= overlap*(sprite1y - sprite2.getY()) / distance;

        double nx = (sprite2.getX() - sprite1.getX()) / distance;
        double ny = (sprite2.getY() - sprite1.getY()) / distance;
        double kx = sprite1.getXSpeed() ;
        double ky = sprite1.getYSpeed() ;
        double p = 2.0 * (nx * kx + ny * ky) / (2*sprite1.getMass());

        if(!(sprite1y < stadium.getTopBorder() || sprite1y > stadium.getDownBorder())){
            sprite1.setY(sprite1y);
        }
        if(!(sprite1x <= stadium.getLeftBorder() || sprite1x >= stadium.getRightBorder())){
            sprite1.setX(sprite1x);
        }

        sprite1.setXSpeed(sprite1.getXSpeed() - p * nx * sprite1.getMass());
        sprite1.setYSpeed(sprite1.getYSpeed() - p * ny * sprite1.getMass());
    }

    private void kick(RoundSprite sprite1, RoundSprite sprite2){
        double distance = calcDistance(sprite1.getXCenter() , sprite2.getXCenter(), sprite1.getYCenter() , sprite2.getYCenter());

            //fey physics calculations
            double nx = (sprite2.getX() - sprite1.getX()) / distance;
            double ny = (sprite2.getY() - sprite1.getY()) / distance;
            double kx = sprite1.getXSpeed();
            double ky = sprite1.getYSpeed();

            double p = 2.0 * (nx * kx + ny * ky) / (sprite1.getMass() + sprite2.getMass());

            sprite1.setXSpeed(sprite1.getXSpeed());
            sprite1.setYSpeed(sprite1.getYSpeed());

            sprite2.setXSpeed(SHOT_POWER * (p * nx * sprite1.getMass()));
            sprite2.setYSpeed(SHOT_POWER * (p * ny * sprite1.getMass()));
    }

    private double calcDistance(double x1, double x2 , double y1 , double y2){
        return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

    private void performStaticCollision(Collidable sprite1, Collidable sprite2){
        double distance = calcDistance(sprite1.getXCenter() , sprite2.getXCenter(), sprite1.getYCenter() , sprite2.getYCenter());
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

        if(!(sprite1y <= stadium.getTopBorder() || sprite1y >= stadium.getDownBorder())){
            sprite1.setY(sprite1y);
        }
        if(!(sprite2y <= stadium.getTopBorder() || sprite2y >= stadium.getDownBorder())){
            sprite2.setY(sprite2y);
        }
        if(!(sprite1x <= stadium.getLeftBorder() || sprite1x >= stadium.getRightBorder())){
            sprite1.setX(sprite1x);
        }
        if(!(sprite2x <= stadium.getLeftBorder() || sprite2x >= stadium.getRightBorder())){
            sprite2.setX(sprite2x);
        }

    }

    private void performDynamicCollision(RoundSprite sprite1,RoundSprite sprite2){
        double distance = calcDistance(sprite1.getXCenter() , sprite2.getXCenter(), sprite1.getYCenter() , sprite2.getYCenter());

        //fey physics calculations
        double nx = (sprite2.getX() - sprite1.getX()) / distance;
        double ny = (sprite2.getY() - sprite1.getY()) / distance;
        double kx = sprite1.getXSpeed() - sprite2.getXSpeed();
        double ky = sprite1.getYSpeed() - sprite2.getYSpeed();
        double p = 2.0 * (nx * kx + ny * ky) / (sprite1.getMass() + sprite2.getMass());

        sprite1.setXSpeed(sprite1.getXSpeed() - p * nx * sprite2.getMass());
        sprite1.setYSpeed(sprite1.getYSpeed() - p * ny * sprite2.getMass());

        sprite2.setXSpeed(sprite2.getXSpeed() + p * nx * sprite1.getMass());
        sprite2.setYSpeed(sprite2.getYSpeed() + p * ny * sprite1.getMass());

    }

    private void handleMoving(){
        players.get(0).move(player1Keys);
        players.get(1).move(player2Keys);
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
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }


    }

}
