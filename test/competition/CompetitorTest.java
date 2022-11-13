package competition;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.event.*;
import competition.exception.*;
import competition.*;

public class CompetitorTest{

    /**
        test if the method getName returns the correct name assigned in the parameter at the construction of competitor.
     */
    @Test 
    public void getNameTest(){
        Competitor player = new Competitor("foo");

        assertEquals("foo", player.getName());
        assertFalse("Boo".equals(player.getName()));

        player = new Competitor("BoO");

        assertEquals("BoO", player.getName());
        assertFalse("foo".equals(player.getName()));
    }

    /**
        test if two players having the same name are equal.
        method equals should return true if the other player given in parameter have the same name as the calling instance.
        method equals should return false otherwise.
     */
    @Test
    public void equalsTest(){
        String name = "player";
        Competitor player1 = new Competitor(name);
        Competitor player2 = new Competitor(name);
        Competitor player3 = new  Competitor("anotherPlayer");

        assertNotSame(player1, player2);
        assertTrue(player1.equals(player2));
        assertTrue(player2.equals(player1));
    
        assertFalse(player1.equals(player3));
        assertFalse(player2.equals(player3));
    }

    /**
        test if the method equals returns false when given another object.
     */
    @Test
    public void notEqualsTest(){
        Competitor player = new Competitor("competitor");

        assertFalse(player.equals(new Object()));
    }

    /**
        test if the method toString returns the correct string version representing a player, which is its name.
     */
    @Test public void toStringTest(){
        Competitor player = new Competitor("foo");

        assertEquals("foo", player.toString());
        assertFalse("Boo".equals(player.toString()));

        player = new Competitor("BoO");

        assertEquals("BoO", player.toString());
        assertFalse("foo".equals(player.toString()));
    }

     public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(competition.CompetitorTest.class);
    }
}