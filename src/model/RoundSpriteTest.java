package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoundSpriteTest {

    @Test
    public void canCoordinatesBeSet(){
        RoundSprite player = new RoundSprite(0,0,Constants.PLAYER_RADIUS,2);
        player.setX(100.0);
        player.setY(150.0);
        assertEquals(100.0,player.getX(),0.0);
        assertEquals(150.0,player.getY(),0.0);
    }

    @Test
    public void getCenter(){
        RoundSprite player = new RoundSprite(0,0,Constants.PLAYER_RADIUS,2);
        assertEquals(Constants.PLAYER_RADIUS,player.getXCenter(),0.0);
        assertEquals(Constants.PLAYER_RADIUS,player.getYCenter(),0.0);
    }

    @Test
    public void areCollisionsWorking(){
        RoundSprite sprite1 = new RoundSprite(0,0,Constants.PLAYER_RADIUS,2);
        RoundSprite sprite2 = new RoundSprite(0,2*Constants.PLAYER_RADIUS,Constants.PLAYER_RADIUS,2);

        assertTrue(sprite1.isCollidingWith(sprite2));
        //move sprite2 away from sprite1 and check if they don't collide
        sprite2.setY(2*Constants.PLAYER_RADIUS + 1);
        assertFalse(sprite1.isCollidingWith(sprite2));
    }

    @Test
    public void getX(){
        RoundSprite sprite = new RoundSprite(100,150,Constants.PLAYER_RADIUS,2);
        assertEquals(100,sprite.getX(),0.0);
    }

    @Test
    public void getY(){
        RoundSprite sprite = new RoundSprite(100,150,Constants.PLAYER_RADIUS,2);
        assertEquals(150,sprite.getY(),0.0);
    }

    @Test
    public void getXSpeedWhenSpeedNotSet(){
        RoundSprite sprite = new RoundSprite(100,100,Constants.PLAYER_RADIUS,2);
        assertEquals(0,sprite.getXSpeed(),0.0);
    }

    @Test
    public void getXSpeedWhenSpeedSet(){
        RoundSprite sprite = new RoundSprite(100,100,Constants.PLAYER_RADIUS,2);
        sprite.setXSpeed(5.0);
        assertEquals(5.0,sprite.getXSpeed(),0.0);
    }

    @Test
    public void getYSpeedWhenSpeedNotSet(){
        RoundSprite sprite = new RoundSprite(100,100,Constants.PLAYER_RADIUS,2);
        assertEquals(0,sprite.getYSpeed(),0.0);
    }

    @Test
    public void getYSpeedWhenSpeedSet(){
        RoundSprite sprite = new RoundSprite(100,100,Constants.PLAYER_RADIUS,2);
        sprite.setYSpeed(5.0);
        assertEquals(5.0,sprite.getYSpeed(),0.0);
    }

    @Test
    public void isRadiusSet(){
        RoundSprite sprite = new RoundSprite(100,100,Constants.PLAYER_RADIUS,2);
        assertEquals(Constants.PLAYER_RADIUS,sprite.getRadius(),0.0);
    }

    @Test
    public void isFrictionWorking(){
        RoundSprite sprite = new RoundSprite(100,100,Constants.PLAYER_RADIUS,2);

        sprite.setFriction(0.9);
        sprite.setXSpeed(10.0);
        sprite.setYSpeed(10.0);
        sprite.update();

        assertEquals(9.0,sprite.getXSpeed(),0.0);
        assertEquals(9.0,sprite.getYSpeed(),0.0);
    }

}