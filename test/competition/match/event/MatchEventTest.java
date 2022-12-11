package competition.match.event;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.*;
import competition.event.*;
import competition.match.event.*;
import competition.journalist.report.*;
import competition.match.State;

public class MatchEventTest{
    protected MatchEvent event;
    protected Competition competition;
    protected List<Competitor> competitors;

    @Before
    public void init(){
        this.competitors = new ArrayList<Competitor>();
        this.competitors.add(new Competitor("p1"));
        this.competitors.add(new Competitor("p2"));
        this.competitors.add(new Competitor("p3"));

        try{
            this.competition = new Championnat(this.competitors);
        }
        catch(Exception e){
            fail();
        }
    }

    /**
        tests if get report returns the same instance of report given to it through the constructor.
     */
    @Test
    public void testGetReport(){
        Report report = new StandardReport(new Competitor("toto"), 0, new Competitor("foo"), 0, State.DRAW);
        
        MatchEvent event = new MatchEvent(this.competition, report);

        assertSame(report, event.getReport());
    }
}