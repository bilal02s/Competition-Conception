package competition.journalist.report;

import static org.junit.Assert.*;
import org.junit.*;
import competition.journalist.report.*;
import competition.match.*;
import competition.*;
import util.Pair;

public class StandardReportTest{

    /**
        test if getWinner and get Loser will return correctly the competitor won and the one who lost
     */
    @Test
    public void testGetWinnerGetLoser(){
        Competitor c1 = new Competitor("foo");
        Competitor c2 = new Competitor("boo");

        Report report = new StandardReport(c1, 1, c2, 0, State.VICTORY);

        assertSame(c1, report.getWinner());
        assertSame(c2, report.getLoser());
    }

    /**
        test if the constructor will determine the winner eventhough the parameter where inversed
     */
    @Test
    public void testConstructor(){
        Competitor c1 = new Competitor("foo");
        Competitor c2 = new Competitor("boo");

        Report report = new StandardReport(c2, 0, c1, 1, State.VICTORY);

        assertSame(c1, report.getWinner());
        assertSame(c2, report.getLoser());
    }

    /**
        test if the firstPair contains as key the winner and as value his score in the match
     */
    @Test
    public void testFirstCompetitorScore(){
        Competitor c1 = new Competitor("foo");
        Competitor c2 = new Competitor("boo");

        Report report = new StandardReport(c1, 1, c2, 0, State.VICTORY);

        Pair<Competitor, Integer> pair = report.getFirstCompetitorScore();
        assertSame(c1, pair.getKey());
        assertSame(1, pair.getValue());
    }

    /**
        test if the secondPair contains as key the loser and as value his score in the match
     */
    @Test
    public void testSecondCompetitorScore(){
        Competitor c1 = new Competitor("foo");
        Competitor c2 = new Competitor("boo");

        Report report = new StandardReport(c1, 1, c2, 0, State.VICTORY);

        Pair<Competitor, Integer> pair = report.getSecondCompetitorScore();
        assertSame(c2, pair.getKey());
        assertSame(0, pair.getValue());
    }
}