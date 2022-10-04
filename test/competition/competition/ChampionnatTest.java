package competition.competition;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.*;
import competition.competition.*;
import competition.exception.*;

public class ChampionnatTest extends CompetitionTest {

    protected Competition createComp(List<Competitor> joueurs)throws InsufficientNumberOfPlayersException{
        return new Championnat(joueurs);
    }

    @Test
    public void testPlay() {
        //in this type of competition, if we consider that we have n players, then we will have n*(n-1) matches because each player plays with all other players twice.
        //in each match played there is always one winner, thus we have n*(n-1) wins.
        //thus the sum of all wins must be equal to n*(n-1)
        this.comp.play();
        int sommeVictoire = 0;
        int n = this.comp.getNbPlayers();
        Map<Competitor, Integer> ranking = this.comp.ranking();

        for (int i : ranking.values()){
            sommeVictoire += i;
        }

        assert(sommeVictoire == n*(n-1));
    }

    /*@Test 
    public void testPlayMatch() {

    }*/

    public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(competition.competition.ChampionnatTest.class);
    }

}