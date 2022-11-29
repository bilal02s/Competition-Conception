package competition.match;

import java.util.*;
import competition.*;
import competition.match.*;
import competition.journalist.report.*;

/**
 * Creates a match where the outcome is random.
 */
public class RandomWinner implements Match{
    private static final Random rand = new Random();

    /**
        plays a match between the two competitors given in parameter.
        return a report containing the state of the match and the corresponding score for each competitor.
        The winner is according the highest score, scores are attributed randomly.
        @param c1 The first competitor.
        @param c2 The second competitor.
        @return A report of the match
     */
    public Report playMatch(Competitor c1, Competitor c2){
        Report report;
        int score1 = RandomWinner.rand.nextInt(11);
        int score2 = RandomWinner.rand.nextInt(11);

        State state = (score1 == score2 ? State.DRAW : State.VICTORY);

        return new StandardReport(c1, score1, c2, score2, state);
    }
}