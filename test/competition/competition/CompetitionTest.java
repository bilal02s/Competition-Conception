package competition.competition;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.competition.*;

public abstract class CompetitionTest {
    protected Competition comp;
    protected List<Competitor> joueurs;

    protected abstract Competition createComp(List<Competitor> joueurs);

    @Before 
    public void init() {
        this.joueurs = new ArrayList<Competitor>();
        this.joueurs.add(new Competitor("toto"));
        this.joueurs.add(new Competitor("tata"));
        this.joueurs.add(new Competitor("tutu"));
        this.joueurs.add(new Competitor("tati"));
        this.comp = this.createComp(this.joueurs);
    }

    @Test 
    public void testConstructor() {
        Map<Competitor, Integer> ranking = this.comp.ranking();

        //we have to check if we have the correct players in the competition
        Set<Competitor> joueursDansCompetition = ranking.keyset();
        Set<Competitor> joueursInitial = Set<Competitor>(this.joueurs);
        assertEquals(joueursDansCompetition, joueursInitial);

        //we need to verify that all match scores are zero.
        for (int i : ranking.values()){
            assert(i == 0);
        }
    }

    @Test 
    public abstract void testPlay();

    @Test
    public void testRanking() {
        this.comp.play();
        Map<Competitor, Integer> ranking = this.comp.ranking();
        int prev = Integer.MAX_VALUE; //resultat precedent qu'il faut etre superieur au resultat courant (ranking tiree par ordre decroissant)

        // verify that the final results are ordered in descending order
        for (int i : ranking.values()){
            if (i > prev) {
                fail();
            }

            prev = i;
        }
    }
}