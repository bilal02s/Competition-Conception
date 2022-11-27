package competition.journalist.report;

import competition.journalist.report.*;
import competition.*;

public class StandardReport implements Report{
    private final Pair<Competitor, Integer> first;
    private final Pair<Competitor, Integer> second;
    private final State state;

    /**
        Constructs a report to save informatition about a match's result and the corresponding score for each competitor
        @param first the first competitor
        @param second the second competitor
        @param score1 the first competitor's score
        @param score2 the second competitor's score
        @param state the match's state
     */
    public StandardReport(Competitor first, int score1, Competitor second, int score2, State state){
        this.first = new Pair<Competitor, Integer>(first, score1);
        this.second = new Pair<Competitor, Integer>(second, score2);
        this.state = state;
    }

    /**
        returns the state of the match
        @return the match's state
     */
    public State getState(){
        return this.state;
    }

    /**
        returns a pair joining the competitor having the highest score with his corresponding score, if the match is a draw, order does not matter.
        @return a pair of competitor and his score
     */
    public Pair<Competitor, Integer> getFirstCompetitorScore(){
        return this.first;
    }

    /**
        returns a pair joining the losing competitor with his corresponding score, if the match is a draw, order does not matter.
        @return a pair of competitor and his score
     */
    public Pair<Competitor, Integer> getSecondCompetitorScore(){
        return this.second;
    }
}