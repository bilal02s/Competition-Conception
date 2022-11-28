package competition.match;

import competition.*;
import competition.journalist.report.*;

/**
 * Match interface.
 */
public interface Match{

    /**
        plays a match between the two competitors given in parameter.
        return one competitor of the two, the returned one is the winner.
        @param c1 The first competitor.
        @param c2 The second competitor.
        @return a report containing the information about the match
     */
    public Report playMatch(Competitor c1, Competitor c2);
}