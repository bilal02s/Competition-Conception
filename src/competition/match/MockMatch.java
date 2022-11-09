package competition.match;

import competition.match.Match;
import competition.Competitor;

/**
    MockMatch, to be used when testing the winner of a competition in order to force the return value of playMatch. T
 */
public class MockMatch implements Match{

    /**
        The competitor winning the match is the competitor who is in the first parameter of the call to playMatch.
        @param c1 The first competitor
        @param c2 The second competitor
        @return The winner is always the first competitor (c1)
     */
    public Competitor playMatch(Competitor c1, Competitor c2){
        return c1;
    }
}