package competition.match;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.event.*;
import competition.exception.*;
import competition.journalist.report.*;
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

        Report report = match.playMatch(player1, player2);
        
        //the winner is either player1 or player.
        boolean winner = player1.equals(report.getWinner()) || player2.equals(report.getWinner());
        boolean loser = player1.equals(report.getLoser()) || player2.equals(report.getLoser());
        boolean result = report.getMatchState() == State.VICTORY || report.getMatchState() == State.DRAW;
        assertTrue(result && winner && loser);

        //repeat the process several times.
        report = match.playMatch(player2, player3);
        winner = player2.equals(report.getWinner()) || player3.equals(report.getWinner());
        loser = player2.equals(report.getLoser()) || player3.equals(report.getLoser());
        result = report.getMatchState() == State.VICTORY || report.getMatchState() == State.DRAW;
        assertTrue(result && winner && loser);
    }

     public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(competition.match.RandomWinnerTest.class);
    }
}