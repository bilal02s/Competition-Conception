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

public class ChampionnatTest extends CompetitionTest {

    protected Competition createComp(List<Competitor> joueurs, List<Journalist> journalists)throws InsufficientNumberOfPlayersException{
        return new Championnat(joueurs, journalists);
    }

    /**
        Test play method using by asserting on the sum of victories, 
        since no matter the variability of the winners the sum of total victories of all players will be the same.
        the formula is written in the below code.
     */
    @Test
    public void testPlay() {
        //in this type of competition, if we consider that we have n players, then we will have n*(n-1) matches because each player plays with all other players twice.
        //in each match played there is always one winner, thus we have n*(n-1) wins.
        //thus the sum of all wins must be equal to n*(n-1)
        this.comp.play();
        float sommeVictoire = 0;
        int n = this.comp.getNbPlayers();
        Map<Competitor, Float> ranking = this.comp.ranking();

        for (float i : ranking.values()){
            sommeVictoire += i;
        }

        assertTrue(sommeVictoire == n*(n-1));
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

        //we expect all the final rankings and scores to be the same.
        //since every player will play with every other player, and the winner is the first player, then every player will win against every other player.
        //hence all player's score must be equal to the number of players iin the league minus one.
        Map<Competitor, Float> ranking = this.comp.ranking();
        float playersScore = this.comp.getNbPlayers() - 1;

        for (float i : ranking.values()){
            assertEquals(playersScore, i, 0.01);
        }
    }

    public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(competition.event.ChampionnatTest.class);
    }

}