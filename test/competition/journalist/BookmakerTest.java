package competition.journalist;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.*;
import competition.io.mock.*;
import competition.journalist.*;
import competition.io.displayer.*;
import competition.match.event.*;
import competition.journalist.report.*;

public class BookmakerTest extends JournalistTest{
    protected Bookmaker bookmaker;
    //protected Bookmaker unmodifiedBookmaker;

    /**
        creates the bookmaker that will be tested, gives it a mockdisplayer
     */
    @Before
    public void initBookmaker(){
        this.bookmaker = new Bookmaker("test", this.news);
        this.bookmaker.setDisplayer(new MockDisplayer());

        //this.unmodifiedBookmaker = new Bookmaker("test", this.news);
    }

    protected Journalist getJournalist(String name, List<String> news){
        return new Bookmaker(name, news);
    }

    /**
        launch all the test corresponding to handle event
     */
    @Test
    public void testHandleEvent(){
        this.testHandleEvent1();
        this.testHandleEvent2();
    }

    /**
        tests that the handle event method will print to the displayer
     */
    private void testHandleEvent1(){
        MockDisplayer mockDisplayer = (MockDisplayer) this.journalist.getDisplayer();
        assertEquals(0, mockDisplayer.getNbCall());

        this.journalist.handleEvent(this.getFakeEvent());

        mockDisplayer = (MockDisplayer) this.journalist.getDisplayer();
        assertEquals(1, mockDisplayer.getNbCall());

        this.journalist.handleEvent(this.getFakeEvent());

        mockDisplayer = (MockDisplayer) this.journalist.getDisplayer();
        assertEquals(2, mockDisplayer.getNbCall());
    }

    /**
        test if the quotations are updated when handle event is called.
     */
    private void testHandleEvent2(){
        //initially quotations are empty
        assertEquals(new HashMap<Competitor, Integer>(), this.bookmaker.getQuotations());

        //handling the event of a victory
        MatchEvent event = this.getFakeEvent();
        Report report = event.getReport();

        this.bookmaker.handleEvent(event);

        Competitor winner = report.getWinner();
        Competitor loser = report.getLoser();

        //retrieve new quotations
        Map<Competitor, Integer> quotations = this.bookmaker.getQuotations();

        assertEquals(1, (int) quotations.get(winner));
        assertEquals(2, (int) quotations.get(loser));
    }

    /**
        tests the initial value of quotations, it is an empty map.
     */
    @Test
    public void testQuotationInitValue(){
        assertEquals(new HashMap<Competitor, Integer>(), this.bookmaker.getQuotations());
    }

    /**
        tests the default value assigned to initValue in the construction of bookmaker
     */
    @Test
    public void testInitValueDefault(){
        assertSame(1, this.bookmaker.getInitValue());
    }

    /**
        tests the default value assigned to deltaValue in the construction of bookmaker
     */
    @Test
    public void testDeltaValueDefault(){
        assertSame(1, this.bookmaker.getDeltaValue());
    }

    /**
        tests the getter and setter of initValue
     */
    @Test
    public void testGetSetInitValue(){
        int newInitValue = 2;

        assertTrue(newInitValue != this.bookmaker.getInitValue());

        this.bookmaker.setInitValue(newInitValue);

        assertTrue(newInitValue == this.bookmaker.getInitValue());
    }

    /**
        tests the getter and setter of deltaValue
     */
    @Test
    public void testGetSetDeltaValue(){
        int deltaValue = 2;

        assertTrue(deltaValue != this.bookmaker.getDeltaValue());

        this.bookmaker.setDeltaValue(deltaValue);

        assertTrue(deltaValue == this.bookmaker.getDeltaValue());
    }
}