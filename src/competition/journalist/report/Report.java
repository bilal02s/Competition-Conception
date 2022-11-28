package competition.journalist.report;

import competition.match.State;
import competition.*;
import util.Pair;

/**
    Interface modeling the use of reports.
 */
public interface Report{

    /**
        returns the state of the match. (Victory or Draw)
        @return the match's state
     */
    public State getMatchState();

    /**
        returns the first pair of competitor and his score.
        @return a pair containing the victorious competitor and his score
     */
    public Pair<Competitor, Integer> getFirstCompetitorScore();

    /**
        returns the second pair of competitor and his score.
        @return a pair containing the losing competitor and his score
     */
    public Pair<Competitor, Integer> getSecondCompetitorScore();

    /**
        returns the winner of a match between two competitors
        @return the competitor who won
     */
    public Competitor getWinner();

    /**
        returns the loser of a match between two competitors
        @return the competitor who lost
     */
    public Competitor getLoser();
}