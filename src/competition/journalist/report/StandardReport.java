package competition.journalist.report;

import competition.journalist.report.*;
import competition.match.State;
import competition.*;
import util.Pair;

public class StandardReport implements Report{
    private final Pair<Competitor, Integer> first;
    private final Pair<Competitor, Integer> second;
    private final State state;

    /**
        Constructs a report to save informatition about a match's result and the corresponding score for each competitor
        determine the winner of the two competitors by comparing their scores.
        @param first the first competitor
        @param second the second competitor
        @param score1 the first competitor's score
        @param score2 the second competitor's score
        @param state the match's state
     */
    public StandardReport(Competitor c1, int score1, Competitor c2, int score2, State state){
        //determine the competitor with the highest score and store in the "first" attribut
        if(score1 >= score2){
            this.first = new Pair<Competitor, Integer>(c1, score1);
            this.second = new Pair<Competitor, Integer>(c2, score2);
        }
        else{
            this.first = new Pair<Competitor, Integer>(c2, score2);
            this.second = new Pair<Competitor, Integer>(c1, score1);
        }
        
        this.state = state;
    }

    /**
        returns the state of the match
        @return the match's state
     */
    public State getMatchState(){
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

    /**
        returns the winner, in case of a draw the term winner does not matter.
        @return the winner
     */
    public Competitor getWinner(){
        return this.first.getKey();
    }

    /**
        returns the loser, in case of a draw the term loser does not matter.
        @return the loser
     */
    public Competitor getLoser(){
        return this.second.getKey();
    }
}