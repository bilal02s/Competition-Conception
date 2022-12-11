package competition.journalist;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.*;
import competition.match.*;
import competition.event.*;
import competition.io.mock.*;
import competition.journalist.*;
import competition.match.event.*;
import competition.io.displayer.*;
import competition.journalist.report.*;

public abstract class JournalistTest{
    protected Journalist journalist;
    protected Journalist unmodifiedJournalist;
    protected String name;
    protected List<String> news;

    protected abstract Journalist getJournalist(String name, List<String> news);

    @Before 
    public void init(){
        this.name = "test";
        this.news = new ArrayList<String>();
        this.news.add("%s %d, %s %d"); //a string format that will not generate an error
        this.journalist = this.getJournalist(this.name, this.news);
        this.unmodifiedJournalist = this.getJournalist(this.name, this.news);
        this.journalist.setDisplayer(new MockDisplayer());
    }

    /**
        Creates a fake event to be able to test the methods handle event.
        @return A fake event
     */
    public MatchEvent getFakeEvent(){
        Competition competition = null;
        List<Competitor> competitors = new ArrayList<Competitor>();
        competitors.add(new Competitor("p1"));
        competitors.add(new Competitor("p2"));
        competitors.add(new Competitor("p3"));

        try{
            competition = new Championnat(competitors);
        }
        catch(Exception e){
            fail();
        }

        Report report = new StandardReport(new Competitor("toto"), 1, new Competitor("foo"), 0, State.VICTORY);
        MatchEvent event = new MatchEvent(competition, report);

        return event;
    }

    /**
        test if getName will return the correct attributed name during the construction.
     */
    @Test
    public void testGetName(){
        assertEquals(this.name, this.journalist.getName());
        assertFalse("Test".equals(this.journalist.getName()));
    }

    /**
        the constructor assign to the attribut displayer by default an instance of PrintConsole.
     */
    @Test 
    public void testDisplayerDefaultValue() throws Exception{
        assertTrue(this.unmodifiedJournalist.getDisplayer() instanceof PrintConsole);
    }

    /**
        tests if the method setDisplayer, sets a new match instance, and if getDisplayer returns the new setted instance.
     */
    @Test 
    public void testGetSetDisplayer(){
        Displayer displayer = new PrintConsole();
        assertTrue(this.journalist != null);
        assertFalse(displayer == this.journalist.getDisplayer());

        this.journalist.setDisplayer(displayer);
        assertTrue(displayer == this.journalist.getDisplayer());
    }

    /**
        test if toString will return the name of the journalist.
     */
    @Test
    public void testToString(){
        assertEquals(this.name, this.journalist.toString());
        assertFalse("Boo".equals(this.journalist.toString()));
    }

    /**
        testa handle event method
     */
    public abstract void testHandleEvent();
}