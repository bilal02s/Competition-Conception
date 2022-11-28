package competition.match.mock;

import competition.match.*;
import competition.journalist.report.*;
import competition.*;

/**
    MockMatch, to be used when testing the winner of a competition in order to force the return value of playMatch. T
 */
public class MockMatch implements Match{

    /**
        The competitor winning the match is the competitor who is in the first parameter of the call to playMatch.
        @param c1 The first competitor
        @param c2 The second competitor
        @return a report setting the winner to be always the first competitor (c1)
     */
    public Report playMatch(Competitor c1, Competitor c2){
        return new StandardReport(c1, 1, c2, 0, State.VICTORY);
    }
}