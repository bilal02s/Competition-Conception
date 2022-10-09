package competition.match;

import competition.*;

public interface Match{

    /**
        plays a match between the two competitors given in parameter.
        return one competitor of the two, the returned one is the winner.
        @param c1 The first competitor.
        @param c2 The second competitor.
        @return The winner out of the two competitors.
     */
    public Competitor playMatch(Competitor c1, Competitor c2);
    //public Comp
}