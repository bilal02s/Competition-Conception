package competition.event;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.*;
import competition.event.*;
import competition.exception.*;
import competition.match.*;
import competition.journalist.*;
import competition.io.displayer.*;

public abstract class CompetitionTest {
    protected Competition comp;
    protected List<Competitor> joueurs;
    protected List<MatchListener> matchListeners;

    protected abstract Competition createComp(List<Competitor> joueurs) throws InsufficientNumberOfPlayersException, WrongNumberOfPlayersException;

    @Before 
    public void init() {
        this.joueurs = new ArrayList<Competitor>();
        this.joueurs.add(new Competitor("toto"));
        this.joueurs.add(new Competitor("tata"));
        this.joueurs.add(new Competitor("tutu"));
        this.joueurs.add(new Competitor("tati"));

        this.matchListeners = new ArrayList<MatchListener>();

        try{
            this.comp = this.createComp(this.joueurs);
        }
        catch(Exception e){
            fail();
        }

        //prevent printing to console
        this.comp.setDisplayer(new DummyDisplayer());
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
    @Test 
    public void test2Constructor() {
        //we need to verify that all match scores are assigned to zero at the beginning.
        Map<Competitor, Float> ranking = this.comp.ranking();
        for (float i : ranking.values()){
            assertTrue(i == 0);
        }
    }

    /**
        the constructor assign to the attribut match by default an instance of RandomWinner.
     */
    @Test 
    public void testMatchDefaultValue(){
        assertTrue(this.comp.getMatch() instanceof RandomWinner);
    }

    /**
        the constructor assign to the attribut displayer by default an instance of PrintConsole.
     */
    @Test 
    public void testDisplayerDefaultValue() throws Exception{
        List<Competitor> joueurs = new ArrayList<Competitor>();
        joueurs.add(new Competitor("toto"));
        joueurs.add(new Competitor("tata"));
        joueurs.add(new Competitor("tutu"));
        joueurs.add(new Competitor("tati"));
        Competition competition = this.createComp(joueurs);

        assertTrue(competition.getDisplayer() instanceof PrintConsole);
    }

    /**
        test if the constructor assigne an emtpy list to the attribut matchListeners 
        during the construction of a competition.
     */
    @Test
    public void testMatchListenersDefaultValue(){
        List<MatchListener> allMatchListeners = this.comp.getMatchListeners();

        assertSame(0, allMatchListeners.size());
    }

    /**
        tests if the method setMatch, sets a new match instance, and if getMatch returns the new setted instance.
     */
    @Test 
    public void testGetSetMatch(){
        Match match = new RandomWinner();
        assertFalse(match == this.comp.getMatch());

        this.comp.setMatch(match);
        assertTrue(match == this.comp.getMatch());
    }

    /**
        tests if the method setDisplayer, sets a new match instance, and if getDisplayer returns the new setted instance.
     */
    @Test 
    public void testGetSetDisplayer(){
        Displayer displayer = new PrintConsole();
        assertTrue(this.comp != null);
        assertFalse(displayer == this.comp.getDisplayer());

        this.comp.setDisplayer(displayer);
        assertTrue(displayer == this.comp.getDisplayer());
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
    public void test3ConstructorException() throws InsufficientNumberOfPlayersException, WrongNumberOfPlayersException {
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

    /**
        tests if the method addMatchListener adds a matchListener to the list of matchListeners
     */
    @Test
    public void testAddMatchListener(){
        MatchListener j1 = new ReportResultsJournalist("foo", new ArrayList<String>());
        MatchListener j2 = new ReportResultsJournalist("boo", new ArrayList<String>());

        this.comp.addMatchListener(j1);
        this.comp.addMatchListener(j2);

        List<MatchListener> allMatchListeners = this.comp.getMatchListeners();
        assertTrue(allMatchListeners.contains(j1));
        assertTrue(allMatchListeners.contains(j2));
    }

    /**
        tests if the method removeMatchListener removes a matchListener from the list of matchListeners
        if it already exists
     */
    @Test
    public void testRemoveMatchListener(){
        MatchListener j1 = new ReportResultsJournalist("foo", new ArrayList<String>());
        MatchListener j2 = new ReportResultsJournalist("boo", new ArrayList<String>());

        this.comp.addMatchListener(j1);
        this.comp.addMatchListener(j2);

        //verify that the matchListeners exists
        List<MatchListener> allMatchListeners = this.comp.getMatchListeners();
        assertTrue(allMatchListeners.contains(j1));
        assertTrue(allMatchListeners.contains(j2));

        //remove the MatchListener j1
        this.comp.removeMatchListener(j1);

        //assert that the MatchListener j1 is removed while j2 always exists
        allMatchListeners = this.comp.getMatchListeners();
        assertFalse(allMatchListeners.contains(j1));
        assertTrue(allMatchListeners.contains(j2));
    }

    /**
        test if the report of each match is diffused to all the matchListeners
        the method diffuseResult must be called a number of times equivilent to the number of match played
        the number of matchs played is the sum of rankings (since every match will increase the ranking by 1, be it a draw or victory)
     */
    @Test 
    public void testFireMatchEvent(){
        this.comp.addMatchListener(new MockJournalist("mock"));
        this.comp.addMatchListener(new MockJournalist("mock"));
        this.comp.play();

        //determine the number of matchs played
        int nbMatchPlayed = 0;
        Map<Competitor, Float> ranking = this.comp.ranking();

        for(float i : ranking.values()){
            nbMatchPlayed += i;
        }

        //test how many times the results have been diffused
        for(MatchListener matchListener : this.comp.getMatchListeners()){
            MockJournalist mockJournalist = (MockJournalist) matchListener;
            assertTrue(mockJournalist.getNbCall() >= nbMatchPlayed);
        }
    }

    @Test 
    public abstract void testPlay();

    /**
        testing if the method ranking returns a hashmap having each players his corresponding result, in descending order.
        test if the ranks are updated after a competition have been played
    */
    @Test
    public void testRanking() {
        //firstly we need to call method play, in order to generate the results.
        this.comp.play();
        Map<Competitor, Float> ranking = this.comp.ranking();
        float prev = Integer.MAX_VALUE; //resultat precedent qu'il faut etre superieur au resultat courant (ranking triee par ordre decroissant)

        // verify that the final results are ordered in descending order
        for (float i : ranking.values()){
            if (i > prev) {
                fail();
            }

            prev = i;
        }
    }
}