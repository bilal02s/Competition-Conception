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
        test if playmatch will return the correct report of the match.
     */
    @Test
    public void playMatchTest1(){
        Competitor player1 = new Competitor("player1");
        Competitor player2 = new Competitor("player2");
        Competitor player3 = new Competitor("player3");
        Match match = new RandomWinner();

        Report report = match.playMatch(player1, player2);
        
        //the winner is either player1 or player2.
        boolean winner = player1.equals(report.getWinner()) || player2.equals(report.getWinner());
        //the loser is either player1 or player2.
        boolean loser = player1.equals(report.getLoser()) || player2.equals(report.getLoser());
        //the result is either a victory or a draw.
        boolean result = report.getMatchState() == State.VICTORY || report.getMatchState() == State.DRAW;
        assertTrue(result && winner && loser);

        //repeat the process several times.
        report = match.playMatch(player2, player3);
        winner = player2.equals(report.getWinner()) || player3.equals(report.getWinner());
        loser = player2.equals(report.getLoser()) || player3.equals(report.getLoser());
        result = report.getMatchState() == State.VICTORY || report.getMatchState() == State.DRAW;
        assertTrue(result && winner && loser);
    }

    /**
        test if playmatch will return the correct report of the match.
     */
    @Test
    public void playMatchTest2(){
        Competitor player1 = new Competitor("player1");
        Competitor player2 = new Competitor("player2");
        Competitor player3 = new Competitor("player3");
        Match match = new RandomWinner();

        Report report = match.playMatch(player1, player2);
        
        //retrieve scores
        int score1 = report.getFirstCompetitorScore().getValue();
        int score2 = report.getSecondCompetitorScore().getValue();

        //determine the state of the match
        State expected = score1 == score2 ? State.DRAW : State.VICTORY;

        //test if the correct state have been set.
        assertSame(expected, report.getMatchState());

        //repeat the process several times.
        report = match.playMatch(player2, player3);
        score1 = report.getFirstCompetitorScore().getValue();
        score2 = report.getSecondCompetitorScore().getValue();

        expected = score1 == score2 ? State.DRAW : State.VICTORY;

        assertSame(expected, report.getMatchState());
    }

     public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(competition.match.RandomWinnerTest.class);
    }
}