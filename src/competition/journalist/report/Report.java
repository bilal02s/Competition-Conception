package competition.journalist.report;

import competition.match.State;
import competition.*;
import util.Pair;

public interface Report{

    public State getMatchState();
    public Pair<Competitor, Integer> getFirstCompetitorScore();
    public Pair<Competitor, Integer> getSecondCompetitorScore();
    public Competitor getWinner();
    public Competitor getLoser();
}