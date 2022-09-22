package competition.competition;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.competition.*;

public class ChampionnatTest extends CompetitionTest {

    protected Competition createComp(List<Competitor> joueurs){
        return new Championnat(joueurs);
    }

    @Test
    public void testPlay() {
        //in this type of competition, if we consider that we have n players, then we will have n*(n-1) matches because each player plays with all other players twice.
        //in each match played there is always one winner, thus we have n*(n-1) wins.
        //thus the sum of all wins must be equal to n*(n-1)

        int sommeVictoire = 0;
        int n = this.competition.getNbPlayers();
        Map<Competitor, Integer> ranking = this.comp.ranking();

        for (int i : ranking.values()){
            sommeVictoire += i;
        }

        assert(sommeVictoire == n*(n-1));
    }
}