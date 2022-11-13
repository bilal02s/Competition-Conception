package competition.event;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.*;
import competition.event.*;
import competition.exception.*;
import competition.io.reader.*;
import competition.io.displayer.*;
import competition.match.mock.MockMatch;
import competition.io.mock.*;

public class MasterTest extends CompetitionTest{
    private Master master;
    private List<Competitor> players;
    private MockDisplayerReader mock;
    private static String question1 = "How many pools do you want to have?";
    private static String question2 = "How many players goes to the final round?";

    protected Competition createComp(List<Competitor> competitors) throws InsufficientNumberOfPlayersException{
        return new Master(competitors);
    }

    private MockDisplayerReader initMock(){
        MockDisplayerReader mock = new MockDisplayerReader();
        mock.putAnswer(this.question1, "2");
        mock.putAnswer(this.question2, "2");
        return mock;
    }

    @Before 
    public void init(){
        super.init();
        
        MockDisplayerReader mock = this.initMock();
        ((Master) this.comp).setReader(mock);
        this.comp.setDisplayer(mock);
    }

    @Before 
    public void initMaster() {
        this.players = new ArrayList<Competitor>();
        this.players.add(new Competitor("toto"));
        this.players.add(new Competitor("tata"));
        this.players.add(new Competitor("tutu"));
        this.players.add(new Competitor("tati"));
        this.players.add(new Competitor("foo1"));
        this.players.add(new Competitor("foo2"));

        try{
            this.master = new Master(this.players);
        }
        catch(Exception e){
            fail();
        }

        //set mock displayer and reader
        this.mock = new MockDisplayerReader();
        this.master.setDisplayer(this.mock);
        this.master.setReader(this.mock);
    }

    /**
        the constructor should raise an exception if the given list contains three or less players.
        we test if the construction throws an exception when giving a list with only three players.
     */
    @Test(expected = InsufficientNumberOfPlayersException.class)
    public void test3ConstructorException() throws InsufficientNumberOfPlayersException, WrongNumberOfPlayersException {
        List<Competitor> players = new ArrayList<Competitor>();
        players.add(new Competitor("toto"));
        players.add(new Competitor("tata"));
        this.comp = this.createComp(players);
    }

    /**
        the constructor should not raise an exception if the given list contains four or more players.
        we test if the construction is successful when giving a list with only two players.
     */
    @Test
    public void test4ConstructorException() throws InsufficientNumberOfPlayersException, WrongNumberOfPlayersException {
        List<Competitor> players = new ArrayList<Competitor>();
        players.add(new Competitor("toto"));
        players.add(new Competitor("tata"));
        players.add(new Competitor("titi"));
        this.comp = this.createComp(players);
    }

    /**
        the constructor assign to the attribut reader by default an instance of ScanTerminal.
     */
    @Test 
    public void testReaderDefaultValue() throws InsufficientNumberOfPlayersException{
        List<Competitor> joueurs = new ArrayList<Competitor>();
        joueurs.add(new Competitor("toto"));
        joueurs.add(new Competitor("tata"));
        joueurs.add(new Competitor("tutu"));
        joueurs.add(new Competitor("tati"));
        Master master = (Master) this.createComp(this.joueurs);

        assertTrue(master.getReader() instanceof ScanTerminal);
    }

    /**
        the constructor assign the attribut leagues an arraylist of league that is initially empty
     */
    @Test 
    public void testLeaguesInitialValue(){
        assertEquals(0, this.master.getLeagues().size());
    }

    /**
        initially the final tournament has not been created yet, its value is null.
     */
    @Test 
    public void testFinalTournamentInitialValue(){
        assertTrue(this.master.getFinalTournament() == null);
    }

    /**
        tests if nbPools and nbFinalRound will be initialised with the correct value
     */
    @Test 
    public void testNbPoolsNbFinalRoundValues(){
        //nbPools must be a divisor of the number of competitors, more than 0, less than the total number of competitors
        this.mock.putAnswer(question1, "3"); // we set it to 3
        //nbFinalRound must be a power of 2, more than 1 and less than the total number of competitors
        this.mock.putAnswer(question2, "4"); // we set it to 4

        assertNotEquals(3, this.master.getNbPools());
        assertNotEquals(4, this.master.getNbFinalRound());
        this.master.play();
        assertEquals(3, this.master.getNbPools());
        assertEquals(4, this.master.getNbFinalRound());
    }

    /**
        this method tests the conditions of the while loop in the private method getGroupsInformation.
     */
    @Test
    public void testNbPoolsWrongValue(){
        //invalid if nbPools is less than 0
        this.mock.putAnswer(this.question1, "0");//setting the answer to nbPools to 0
        this.mock.putAnswer(this.question2, "4");//setting a valid answer to nbFinalRound 
        assertThrows(MockDisplayerReaderException.class, () -> {this.master.play();});

        this.mock.putAnswer(this.question1, "-10");//setting the answer to nbPools to 10
        assertThrows(MockDisplayerReaderException.class, () -> {this.master.play();});

        //invalid if nbPools is equal to the number of competitors
        this.mock.putAnswer(this.question1, Integer.toString(this.players.size()));
        assertThrows(MockDisplayerReaderException.class, () -> {this.master.play();});

        //invalid if nbPools is not a divisor of the number of competitors
        this.mock.putAnswer(this.question1, "5");
        assertTrue(this.players.size() % 5 != 0);
        assertThrows(MockDisplayerReaderException.class, () -> {this.master.play();});
    }

    /**
        this method tests the conditions of the while loop in the private method getGroupsInformation.
     */
    @Test
    public void testNbFinalRoundWrongValue(){
        //invalid if nbFinalRound is less than 1
        this.mock.putAnswer(question1, "3");
        this.mock.putAnswer(question2, "1");
        assertThrows(MockDisplayerReaderException.class, () -> {this.master.play();});

        this.mock.putAnswer(question2, "-10");
        assertThrows(MockDisplayerReaderException.class, () -> {this.master.play();});

        //invalid if nbFinalRound is not a power of 2
        this.mock.putAnswer(question2, "3");
        assertFalse(Tournoi.isPowerOf2(3));
        assertThrows(MockDisplayerReaderException.class, () -> {this.master.play();});

        //invalid if nbFinalRound is more than or equal to the number of competitors
        this.mock.putAnswer(question2, "7");
        assertTrue(7 > this.players.size());
        assertThrows(MockDisplayerReaderException.class, () -> {this.master.play();});
    }

    /** 
        tests if the attributs firstNplayers and bestNplayers have the correct value
    */
    @Test 
    public void testFirstNBestNPlayersValue(){
        //nbPools must be a divisor of the number of competitors, more than 0, less than the total number of competitors
        this.mock.putAnswer(question1, "3"); // we set it to 3
        //nbFinalRound must be a power of 2, more than 1 and less than the total number of competitors
        this.mock.putAnswer(question2, "4"); // we set it to 4

        //expected values
        int firstNplayers = (int) (4/3); // nbFinalRound/nbPools
        int bestNplayers = (int) (4%3); // nbFinalRound%nbPools

        assertNotEquals(firstNplayers, this.master.getFirstNPlayers());
        assertNotEquals(bestNplayers, this.master.getbestNPlayers());
        this.master.play();
        assertEquals(firstNplayers, this.master.getFirstNPlayers());
        assertEquals(bestNplayers, this.master.getbestNPlayers());
    }

    /**
        tests if the method setReader, sets the new Reader instance, and if getReader returns the new setted instance.
     */
    @Test 
    public void testGetSetReader(){
        Reader reader = new ScanTerminal();
        assertFalse(reader == this.master.getReader());

        this.master.setReader(reader);
        assertTrue(reader == this.master.getReader());
    }

    /**
        tests is the league and initialised correctly, the number of leagues is equal to the number of pools
        and each league contains the same number of players
     */
    @Test 
    public void testInitLeagues(){
        int nbPools = 3;
        this.mock.putAnswer(question1, "3"); 
        this.mock.putAnswer(question2, "4");

        assertEquals(0, this.master.getLeagues().size());
        this.master.play();
        assertEquals(nbPools, this.master.getLeagues().size());

        List<Championnat> leagues = this.master.getLeagues();
        int nbPlayersLeague = (int) (this.players.size()/nbPools);
        for(Championnat league : leagues){
            assertEquals(nbPlayersLeague, league.getNbPlayers());
        }
    }

    /**
        tests verifies if the final tournament is initialised correctly.
        the number of players in the tournament must be equal to the nbFinalRound given by the mock
        the players in the tournament must be the winner of the leagues depending on a certain criteria
     */
    @Test 
    public void testPlay(){
        int nbFinalRound = 4;
        this.mock.putAnswer(question1, "3"); 
        this.mock.putAnswer(question2, "4");
        this.master.setMatch(new MockMatch());

        assertEquals(null, this.master.getFinalTournament());
        this.master.play();
        assertEquals(nbFinalRound, this.master.getFinalTournament().getNbPlayers());

        //creating the exepcted list of competitors in the final tournament
        List<Competitor> playersInTournament = new ArrayList<Competitor>();
        int i = 0;
        for (Championnat league : this.master.getLeagues()){System.out.println(league.getPlayers());
            playersInTournament.add(league.getPlayers().get(0));
            if(i == 1){
                playersInTournament.add(league.getPlayers().get(1));
            }
            i++;
        }

        //assertEquals(playersInTournament, this.master.getFinalTournament().getPlayers());
    }

    /**
        test is the the results of the leagues and final tournament are copied to the master's results
     */
    @Test 
    public void testCopyResults(){
        this.mock.putAnswer(question1, "3"); 
        this.mock.putAnswer(question2, "4");
        this.master.play();

        //expected results
        Map<Competitor, Integer> results= new HashMap<Competitor, Integer>();
        //initialise values
        for (Competitor competitor : this.players){
            results.put(competitor, 0);
        }

        //add all competitions results
        //add leagues results
        for(Championnat league : this.master.getLeagues()){
            Map<Competitor, Integer> leagueResults = league.ranking();
            for (Competitor competitor : leagueResults.keySet()){
                int oldScore = results.get(competitor);
                results.put(competitor, oldScore + leagueResults.get(competitor));
            }
        }
        
        //add final tournament's results
        Map<Competitor, Integer>  finalTournamentResults = this.master.getFinalTournament().ranking();
        for(Competitor competitor : finalTournamentResults.keySet()){
            int oldScore = results.get(competitor);
            results.put(competitor, oldScore + finalTournamentResults.get(competitor));
        }

        assertEquals(results, this.master.ranking());
    }
}