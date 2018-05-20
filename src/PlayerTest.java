import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
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