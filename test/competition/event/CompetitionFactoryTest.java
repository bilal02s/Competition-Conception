package competition.event;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.event.*;
import competition.exception.*;
import competition.*;

public class CompetitionFactoryTest{
    private CompetitionFactory factory;
    private List<Competitor> players;

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
    }

    /**
        tests if the method getCompetition of the factory class will return a null value when a null string is given
        independently of the list of players.
     */
    @Test 
    public void nameNullTest() throws WrongNumberOfPlayersException, InsufficientNumberOfPlayersException{
        Competition competition = this.factory.getCompetition(null, null);
        
        assertTrue(competition == null);

        competition = this.factory.getCompetition(null, new ArrayList<Competitor>());

        assertTrue(competition == null);

        competition = this.factory.getCompetition(null, this.players);

        assertTrue(competition == null);
    }

    /**
        tests if the method getCompetition of the factory class will return a null value when a string is given that doesnt correspond
        to any competition type, independently of the list of players.
     */
    @Test 
    public void wrongNameTest() throws WrongNumberOfPlayersException, InsufficientNumberOfPlayersException{
        Competition competition = this.factory.getCompetition("quelqueChose", null);
        
        assertTrue(competition == null);

        competition = this.factory.getCompetition("quelqueChose", new ArrayList<Competitor>());
        
        assertTrue(competition == null);

        competition = this.factory.getCompetition("quelqueChose", this.players);
        
        assertTrue(competition == null);
    }

    /**
        test if the method getCompetition will return a tournament instance when given the correct string.
     */
     @Test 
     public void tournamentInstanceTest() throws WrongNumberOfPlayersException, InsufficientNumberOfPlayersException{
        Competition competition = this.factory.getCompetition("Tournament", this.players);

        assertTrue(competition instanceof Tournoi);
     }

     /**
        test if the method getCompetition will return a league instance when given the correct string.
     */
     @Test 
     public void leagueInstanceTest() throws WrongNumberOfPlayersException, InsufficientNumberOfPlayersException{
        Competition competition = this.factory.getCompetition("League", this.players);

        assertTrue(competition instanceof Championnat);
     }

     /**
        test if the method getCompetition will return the competition type instance that contains the correct players given in parameter.
     */
     @Test 
     public void InstancePlayersTest() throws WrongNumberOfPlayersException, InsufficientNumberOfPlayersException{
        Competition competition = this.factory.getCompetition("Tournament", this.players);

        assertSame(competition.getPlayers(), this.players);

        competition = this.factory.getCompetition("League", this.players);

        assertSame(competition.getPlayers(), this.players);
     }

     public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(competition.event.CompetitionFactoryTest.class);
    }
}