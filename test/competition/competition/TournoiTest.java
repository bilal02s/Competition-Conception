package competition.competition;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.competition.*;

public class TournoiTest extends CompetitionTest {

    protected Competition createComp(List<Competitor> joueurs){
        try{
            Competition comp = new Tournoi(joueurs);
        } 
        catch(Exception e){
            fail(e.message);
        }

        return comp;
    }

    @Test
    public void testPlay() {
        //in this type of competition, if we consider that we have n players, then we will have (n-1) matches because each payer plays with all other players twice.
        //in each match played there is always one winner, thus we must have at the end (n-1) wins.
        //thus the sum of all wins must be equal to (n-1)

        int sommeVictoire = 0;
        int n = this.competition.getNbPlayers();
        Map<Competitor, Integer> ranking = this.comp.ranking();

        for (int i : ranking.values()){
            sommeVictoire += i;
        }

        assert(sommeVictoire == (n-1));
    }
}