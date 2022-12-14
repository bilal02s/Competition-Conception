package competition.event;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.*;
import competition.event.*;
import competition.exception.*;
import competition.journalist.*;
import competition.io.displayer.*;
import competition.match.mock.MockMatch;

public class TournoiTest extends CompetitionTest {

    protected Competition createComp(List<Competitor> joueurs)throws InsufficientNumberOfPlayersException, WrongNumberOfPlayersException{
        return new Tournoi(joueurs);
    }

    /**
        a tournament cannot be created if we have a number of players that is not a power of two
        this test should raise the WrongNumberOfPlayersException.
     */
    @Test (expected = WrongNumberOfPlayersException.class)
    public void testTournoiConstructorException() throws InsufficientNumberOfPlayersException, WrongNumberOfPlayersException {
        List<Competitor> players = new ArrayList<Competitor>();
        players.add(new Competitor("toto"));
        players.add(new Competitor("tutu"));
        players.add(new Competitor("tata"));
        this.comp = this.createComp(players);
    }

    /**
        Test play method using by asserting on the sum of victories, 
        since no matter the variability of the winners the sum of total victories of all players will be the same.
        the formula is written in the below code.
     */
    @Test
    public void testPlay() {
        //in this type of competition, if we consider that we have n players, then we will have (n-1) matches because each payer plays with all other players twice.
        //in each match played there is always one winner, thus we must have at the end (n-1) wins.
        //thus the sum of all wins must be equal to (n-1)
        this.comp.play();
        float sommeVictoire = 0;
        int n = this.comp.getNbPlayers();
        Map<Competitor, Float> ranking = this.comp.ranking();

        for (float i : ranking.values()){
            sommeVictoire += i;
        }

        assertTrue(sommeVictoire == (n-1));
    }

    /**
        testing play method through the ranking in the case of a mock match, a match where the first competitor is always the winner against the second.
        by forcing the value of the winner, we can predict who will win the competition and thus test and correct functioning of the method.
     */
    @Test 
    public void mockTestPlay() {
        //we set the match type to the mockMatch instance so that we force the winner to be the first competitor in a given match
        this.comp.setMatch(new MockMatch());
        this.comp.play();

        Competitor firstPlayer = this.joueurs.get(0);
        Competitor secondPlayer = this.joueurs.get(1);
        Competitor thirdPlayer = this.joueurs.get(2);
        Competitor fourthPlayer = this.joueurs.get(3);

        //since matchs are paired first two players against each other, and then the winners to the next round are also paired with the first two players againt each other and so on.
        //we expect the winner of the competition to be the first player
        Map<Competitor, Float> ranking = this.comp.ranking();
        float firstPlayerScore = ranking.get(firstPlayer);
        float secondPlayerScore = ranking.get(secondPlayer);
        float thirdPlayerScore = ranking.get(thirdPlayer);
        float fourthPlayerScore = ranking.get(fourthPlayer);

        assertTrue(firstPlayerScore > thirdPlayerScore);
        assertTrue(thirdPlayerScore > secondPlayerScore);
        assertTrue(thirdPlayerScore > fourthPlayerScore);
        assertTrue(secondPlayerScore == fourthPlayerScore);
    }

    /**
        test if the static method isPowerOf2 return true when given an integer that is a power of 2.
     */
    @Test 
    public void test1IsPowerOf2(){
        assertTrue(Tournoi.isPowerOf2(2));
        assertTrue(Tournoi.isPowerOf2(4));
        assertTrue(Tournoi.isPowerOf2(8));
        assertTrue(Tournoi.isPowerOf2(16));
        assertTrue(Tournoi.isPowerOf2(32));
    }

    /**
        test if the static method isPowerOf2 return false when given an integer that is not a power of 2.
     */
    @Test 
    public void test2IsPowerOf2(){
        assertFalse(Tournoi.isPowerOf2(3));
        assertFalse(Tournoi.isPowerOf2(5));
        assertFalse(Tournoi.isPowerOf2(7));
        assertFalse(Tournoi.isPowerOf2(10));
        assertFalse(Tournoi.isPowerOf2(30));
    }

    /**
        test if the static method getPowerOf2 return the value that when 2 is raised to power of that value gives the integer given in the parameter.
        given thaat the integer given in parameter is actually a power of 2.
     */
    @Test 
    public void testGetPowerOfTwo(){
        try{
            assertSame(1, Tournoi.getPowerOf2(2));
            assertSame(2, Tournoi.getPowerOf2(4));
            assertSame(3, Tournoi.getPowerOf2(8));
            assertSame(4, Tournoi.getPowerOf2(16));
            assertSame(5, Tournoi.getPowerOf2(32));
        }
        catch(Exception e){
            fail();
        }
    }

    /**
        test if the static function getPowerOf2 raisse an exception when given an integer that is not a power of 2.
     */
    @Test (expected = IntegerNotPowerOf2Exception.class)
    public void testGetPowerOfTwoException() throws IntegerNotPowerOf2Exception {
        Tournoi.getPowerOf2(3);
    }

    public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(competition.event.TournoiTest.class);
    }
}