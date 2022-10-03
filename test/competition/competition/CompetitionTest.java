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

    /**
        the constructor does two things, initialise attribut players to be equal to the players given in parameter,
        assign to each player a score of zero at the beginning since no matchs have been played.
        we have to test these two things.
     */
    @Test 
    public void testConstructor() {
        //we have to check if we have the correct players in the competition
        //we convert arrayList to sets in order to ignore the initial order between players.
        Set<Competitor> joueursDansCompetition = Set<Competitor>(this.comp.getPlayers());
        Set<Competitor> joueursInitial = Set<Competitor>(this.joueurs);
        assertEquals(joueursDansCompetition, joueursInitial);

        //we need to verify that all match scores are zero.
        Map<Competitor, Integer> ranking = this.comp.ranking();
        for (int i : ranking.values()){
            assert(i == 0);
        }
    }

    /**
        testing if the method getPlayers returns the correct players that arer present is the competition.
     */
    @Test
    public void testGetPlayers() {
        Set<Competitor> joueursDansCompetition = Set<Competitor>(this.comp.getPlayers());
        Set<Competitor> joueursInitial = Set<Competitor>(this.joueurs);
        assertEquals(joueursDansCompetition, joueursInitial);
    }

    /**
        testing if the method getNbPlayers returns the correct number of players in the competition;
     */
    @Test
    public void testGetNbPlayers() {
        int initialNumberOfPlayers = this.joueurs.size();
        int numberOfPlayersInCompetition = this.comp.getNbPlayers();

        assertTrue(initialNumberOfPlayers == numberOfPlayersInCompetition);
    }

    @Test 
    public abstract void testPlay();

    /**
        testing if the method ranking returns a hashmap having each players his corresponding result, is descending order.
    */
    @Test
    public void testRanking() {
        //firstly we need to call method play, in order to generate the results.
        this.comp.play();
        Map<Competitor, Integer> ranking = this.comp.ranking();
        int prev = Integer.MAX_VALUE; //resultat precedent qu'il faut etre superieur au resultat courant (ranking triee par ordre decroissant)

        // verify that the final results are ordered in descending order
        for (int i : ranking.values()){
            if (i > prev) {
                fail();
            }

            prev = i;
        }
    }
}