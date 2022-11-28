package competition.event;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.event.*;
import competition.exception.*;
import competition.journalist.*;
import competition.*;

public class CompetitionFactoryTest{
    private CompetitionFactory factory;
    private List<Competitor> players;
    private List<Journalist> journalists;

    /**
        initialise the factory attribut with a CompetitionFactory instance.
        initialise players attribut with an array list that contains 4 players.
     */
    @Before
    public void before(){
        this.factory = new CompetitionFactory();

        this.players = new ArrayList<Competitor>();
        this.players.add(new Competitor("toto"));
        this.players.add(new Competitor("tata"));
        this.players.add(new Competitor("tutu"));
        this.players.add(new Competitor("tati"));

        this.journalists = new ArrayList<Journalist>();
    }

    /**
        tests if the method getCompetition of the factory class will return a null value when a null string is given
        independently of the list of players.
     */
    @Test 
    public void nameNullTest() throws WrongNumberOfPlayersException, InsufficientNumberOfPlayersException{
        Competition competition = this.factory.getCompetition(null, null, null);
        
        assertTrue(competition == null);

        competition = this.factory.getCompetition(null, new ArrayList<Competitor>(), new ArrayList<Journalist>());

        assertTrue(competition == null);

        competition = this.factory.getCompetition(null, this.players, this.journalists);

        assertTrue(competition == null);
    }

    /**
        tests if the method getCompetition of the factory class will return a null value when a string is given that doesnt correspond
        to any competition type, independently of the list of players.
     */
    @Test 
    public void wrongNameTest() throws WrongNumberOfPlayersException, InsufficientNumberOfPlayersException{
        Competition competition = this.factory.getCompetition("quelqueChose", null, this.journalists);
        
        assertTrue(competition == null);

        competition = this.factory.getCompetition("quelqueChose", new ArrayList<Competitor>(), this.journalists);
        
        assertTrue(competition == null);

        competition = this.factory.getCompetition("quelqueChose", this.players, this.journalists);
        
        assertTrue(competition == null);
    }

    /**
        test if the method getCompetition will return a tournament instance when given the correct string,
        ignoring case letters.
     */
     @Test 
     public void tournamentInstanceTest() throws WrongNumberOfPlayersException, InsufficientNumberOfPlayersException{
        Competition competition = this.factory.getCompetition("tournament", this.players, this.journalists);
        assertTrue(competition instanceof Tournoi);

        competition = this.factory.getCompetition("TourNamEnt", this.players, this.journalists);
        assertTrue(competition instanceof Tournoi);

        competition = this.factory.getCompetition("TOURNAMENT", this.players, this.journalists);
        assertTrue(competition instanceof Tournoi);
     }

     /**
        test if the method getCompetition will return a league instance when given the correct string.
        ignoring case letters.
     */
     @Test 
     public void leagueInstanceTest() throws WrongNumberOfPlayersException, InsufficientNumberOfPlayersException{
        Competition competition = this.factory.getCompetition("league", this.players, this.journalists);
        assertTrue(competition instanceof Championnat);

        competition = this.factory.getCompetition("LeAgUe", this.players, this.journalists);
        assertTrue(competition instanceof Championnat);

        competition = this.factory.getCompetition("LEAGUE", this.players, this.journalists);
        assertTrue(competition instanceof Championnat);
     }

     /**
        test if the method getCompetition will return a master instance when given the correct string.
        ignoring case letters.
      */
     @Test 
     public void masterInstanceTest() throws WrongNumberOfPlayersException, InsufficientNumberOfPlayersException{
        Competition competition = this.factory.getCompetition("master", this.players, this.journalists);
        assertTrue(competition instanceof Master);

        competition = this.factory.getCompetition("MaStEr", this.players, this.journalists);
        assertTrue(competition instanceof Master);

        competition = this.factory.getCompetition("MASTER", this.players, this.journalists);
        assertTrue(competition instanceof Master);
     }

     /**
        test if the method getCompetition will give the competition type instance the correct list of players given in parameter.
     */
     @Test 
     public void InstancePlayersTest() throws WrongNumberOfPlayersException, InsufficientNumberOfPlayersException{
        Competition competition = this.factory.getCompetition("Tournament", this.players, this.journalists);

        assertSame(this.players, competition.getPlayers());

        competition = this.factory.getCompetition("League", this.players, this.journalists);

        assertSame(this.players, competition.getPlayers());
     }

     public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(competition.event.CompetitionFactoryTest.class);
    }
}