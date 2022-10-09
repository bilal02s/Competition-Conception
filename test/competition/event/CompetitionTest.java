package competition.event;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.*;
import competition.event.*;
import competition.exception.*;

public abstract class CompetitionTest {
    protected Competition comp;
    protected List<Competitor> joueurs;

    protected abstract Competition createComp(List<Competitor> joueurs) throws InsufficientNumberOfPlayersException, WrongNumberOfPlayersException;

    @Before 
    public void init() {
        this.joueurs = new ArrayList<Competitor>();
        this.joueurs.add(new Competitor("toto"));
        this.joueurs.add(new Competitor("tata"));
        this.joueurs.add(new Competitor("tutu"));
        this.joueurs.add(new Competitor("tati"));

        try{
            this.comp = this.createComp(this.joueurs);
        }
        catch(Exception e){
            fail();
        }
        
    }

    /**
        the constructor does two things, initialise attribut players to be equal to the players given in parameter,
        assign to each player a score of zero at the beginning since no matchs have been played.
        we have to test these two things.
        Constructor test1
     */
    @Test 
    public void test1Constructor() {
        //we have to check if we have the correct players in the competition
        //we convert arrayList to sets in order to ignore the initial order between players.
        Set<Competitor> joueursDansCompetition = new HashSet<Competitor>(this.comp.getPlayers());
        Set<Competitor> joueursInitial = new HashSet<Competitor>(this.joueurs);
        assertEquals(joueursInitial, joueursDansCompetition);

        
    }

    /**
        the constructor assign to each player a score of zero at the beginning since no matchs have been played.
        Constructor test2
     */
    @Test public void test2Constructor() {
        //we need to verify that all match scores are assigned to zero at the beginning.
        Map<Competitor, Integer> ranking = this.comp.ranking();
        for (int i : ranking.values()){
            assertTrue(i == 0);
        }
    }

    /**
        the constructor raise an exception if the given list of players is empty.
        we test if the exception is raised
     */
    @Test (expected = InsufficientNumberOfPlayersException.class)
    public void test1ConstructorException() throws InsufficientNumberOfPlayersException, WrongNumberOfPlayersException{
        List<Competitor> players = new ArrayList<Competitor>();
        this.comp = this.createComp(players);
    }

    /**
        the constructor raise an exception if the given list contains less than two players.
        we test if the exception is raised when giving a list with only one player.
     */
    @Test (expected = InsufficientNumberOfPlayersException.class)
    public void test2ConstructorException() throws InsufficientNumberOfPlayersException, WrongNumberOfPlayersException {
        List<Competitor> players = new ArrayList<Competitor>();
        players.add(new Competitor("toto"));
        this.comp = this.createComp(players);
    }

    /**
        the constructor should not raise an exception if the given list contains two or more players.
        we test if the construction is successful when giving a list with only two players.
     */
    @Test
    public void test2ConstructorException() throws InsufficientNumberOfPlayersException, WrongNumberOfPlayersException {
        List<Competitor> players = new ArrayList<Competitor>();
        players.add(new Competitor("toto"));
        players.add(new Competitor("tata"));
        this.comp = this.createComp(players);
    }

    /**
        testing if the method getPlayers returns the correct players that are present is the competition.
     */
    @Test
    public void testGetPlayers() {
        Set<Competitor> joueursDansCompetition = new HashSet<Competitor>(this.comp.getPlayers());
        Set<Competitor> joueursInitial = new HashSet<Competitor>(this.joueurs);
        assertEquals(joueursInitial, joueursDansCompetition);
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

    //@Test
    //public abstract void testPlayMatch();

    /**
        testing if the method ranking returns a hashmap having each players his corresponding result, in descending order.
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