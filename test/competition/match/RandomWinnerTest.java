package competition.match;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.event.*;
import competition.exception.*;
import competition.match.*;
import competition.*;

public class RandomWinnerTest{

    /**
        test if playmatch will return the winner which is one instance of the two given to it in parameter.
     */
    @Test
    public void playMatchTest(){
        Competitor player1 = new Competitor("player1");
        Competitor player2 = new Competitor("player2");
        Competitor player3 = new  Competitor("player3");
        Match match = new RandomWinner();

        Competitor winner = match.playMatch(player1, player2);
        
        //the winner is either player1 or player.
        boolean result = player1.equals(winner) || player2.equals(winner);
        assertTrue(result);

        //repeat the process several times.
        winner = match.playMatch(player2, player3);
        result = player2.equals(winner) || player3.equals(winner);
        assertTrue(result);
    }

     public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(competition.match.RandomWinnerTest.class);
    }
}