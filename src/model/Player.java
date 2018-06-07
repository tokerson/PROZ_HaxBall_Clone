package model;

public class Player extends RoundSprite {

    private final String Imagefile = "player.png";

    public Player(){
        this(0,0,Constants.PLAYER_RADIUS,Constants.PLAYER_MASS);
    }

    public Player(int x, int y, int r, double m) {
        super(x, y, r, m);
        setFriction(1.0);
        setImage(Imagefile);
    }

    public void move(int[] keys) {
        if(keys[2] == 1){
            setRadius(Constants.PLAYER_RADIUS + 2);
            setImage("playerShooting.png");
        }
        else {
            setRadius(Constants.PLAYER_RADIUS);
            setImage(Imagefile);
        }

        if(isAnyKeyPressed(keys)){
            friction = 1.0;

            if(isMovingInTwoDirections(keys)){
                setYSpeed((double)keys[0]*Constants.SPEED / Math.sqrt(2));
                setXSpeed((double)keys[1]*Constants.SPEED / Math.sqrt(2));
            }
            else {
                setYSpeed((double)keys[0]*Constants.SPEED);
                setXSpeed((double)keys[1]*Constants.SPEED);
            }
        }
        else friction = 0.96;
    }

    private boolean isAnyKeyPressed(int[]keys){
        for(int i = 0 ; i < 2 ; ++i){
            if(keys[i] != 0) return true;
        }
        return false;
    }

    private boolean isMovingInTwoDirections(int[] keys){
        for(int i = 0 ; i < 2 ; ++i){
            if(keys[i] == 0) return false;
        }
        return true;
    }
}
