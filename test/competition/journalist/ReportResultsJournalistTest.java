package competition.journalist;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import competition.io.mock.*;
import competition.journalist.*;

public class ReportResultsJournalistTest extends JournalistTest{

    /**
        returns an instance of ReportResultsJournalist
     */
    protected Journalist getJournalist(String name, List<String> news){
        return new ReportResultsJournalist(name, news);
    }

    /**
        tests if the handle event will print to the console a piece of information, through the call
        to the displayer's method writeMessage.
     */
    @Test
    public void testHandleEvent(){
        MockDisplayer mockDisplayer = (MockDisplayer) this.journalist.getDisplayer();
        assertEquals(0, mockDisplayer.getNbCall());

        this.journalist.handleEvent(this.getFakeEvent());

        mockDisplayer = (MockDisplayer) this.journalist.getDisplayer();
        assertEquals(1, mockDisplayer.getNbCall());

        this.journalist.handleEvent(this.getFakeEvent());

        mockDisplayer = (MockDisplayer) this.journalist.getDisplayer();
        assertEquals(2, mockDisplayer.getNbCall());
    }
}