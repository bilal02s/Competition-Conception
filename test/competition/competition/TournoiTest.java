package competition.competition;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.*;
import competition.competition.*;
import competition.exception.*;

public class TournoiTest extends CompetitionTest {

    protected Competition createComp(List<Competitor> joueurs)throws InsufficientNumberOfPlayersException, WrongNumberOfPlayersException{
        return new Tournoi(joueurs);
    }

    /**
        a tournament cannot be created if we have a number of players that is not a power of two
        this test should raise the WrongNumberOfPlayersException.
     */
    @Test (expected = WrongNumberOfPlayersException.class)
    public void test3ConstructorException() throws InsufficientNumberOfPlayersException, WrongNumberOfPlayersException {
        List<Competitor> players = new ArrayList<Competitor>();
        players.add(new Competitor("toto"));
        players.add(new Competitor("tutu"));
        players.add(new Competitor("tata"));
        this.comp = this.createComp(players);
    }

    @Test
    public void testPlay() {
        //in this type of competition, if we consider that we have n players, then we will have (n-1) matches because each payer plays with all other players twice.
        //in each match played there is always one winner, thus we must have at the end (n-1) wins.
        //thus the sum of all wins must be equal to (n-1)
        this.comp.play();
        int sommeVictoire = 0;
        int n = this.comp.getNbPlayers();
        Map<Competitor, Integer> ranking = this.comp.ranking();

        for (int i : ranking.values()){
            sommeVictoire += i;
        }

        assert(sommeVictoire == (n-1));
    }

    public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(competition.competition.TournoiTest.class);
    }
}