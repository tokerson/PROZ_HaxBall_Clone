package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.*;

public class PlayerTest {
    @Test
    public void canCoordinatesBeSet(){
        Player player = new Player(0,0,Constants.PLAYER_RADIUS,2);
        player.setX(100.0);
        player.setY(150.0);
        assertEquals(100.0,player.getX(),0.0);
        assertEquals(150.0,player.getY(),0.0);
    }

    @Test
    public void getCenter(){
        Player player = new Player(0,0,Constants.PLAYER_RADIUS,2);
        assertEquals(Constants.PLAYER_RADIUS,player.getxCenter(),0.0);
        assertEquals(Constants.PLAYER_RADIUS,player.getyCenter(),0.0);
    }

    @Test
    public void isImageSet(){
        Player player = new Player(0,0,Constants.PLAYER_RADIUS,2);
        assertNotEquals(null,player.getImage());
    }

    @Test
    public void movesRightAndDown(){
        Player player = new Player(0,0,Constants.PLAYER_RADIUS,2);
        player.setXspeed(5.0);
        player.setYspeed(5.0);
        player.update();
        assertEquals(5,player.getX(),0.0);
        assertEquals(5,player.getX(),0.0);
    }

    @Test
    public void movesLeftAndUp(){
        Player player = new Player(110,110,Constants.PLAYER_RADIUS,2);
        player.setXspeed(-10.0);
        player.setYspeed(-10.0);
        player.update();
        assertEquals(100,player.getX(),0.0);
        assertEquals(100,player.getX(),0.0);
    }

}