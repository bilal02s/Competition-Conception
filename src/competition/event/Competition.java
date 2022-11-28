package competition.event;

import java.util.*;
import util.*;
import competition.*;
import competition.exception.*;
import competition.io.displayer.*;
import competition.journalist.report.*;
import competition.journalist.*;
import competition.match.*;

/**
 * competition abstract class defining a competition's behavior.
 */
public abstract class Competition {
    /** List of competitors */
    protected final List<Competitor> competitors; 
    /** Result as integer mapped to each competitor */
    protected Map<Competitor, Float> results;
    /** A displayer to display match's results */
    protected Displayer displayer;
    /** A match instance giving us the result of a match between two competitor */
    protected Match match;
    /** A list of journalist and bookmakers assisting to the competition */
    protected List<Journalist> journalists;

    /**
        initialise the attribut competitors with the players given parameter.
        initialise the hashmap results by putting inside of it all the players given in parameter, and assigning a value of zero to their corresponding scores.
        raises an exception if the number of players in the list is less than 2.
        affects the displayer variable to the displayer given in parameter.
        @param competitors List of participants.
        @throws InsufficientNumberOfPlayersException if the number of players in the list is less than 2.
     */
    public Competition(List<Competitor> competitors) throws InsufficientNumberOfPlayersException{
        if(competitors.size() < 2){
            throw new InsufficientNumberOfPlayersException("A competition must have at least two players.");
        }

        this.competitors = competitors;
        this.journalists = new ArrayList<Journalist>();
        this.results = new HashMap<Competitor, Float>();

        for (Competitor c : competitors){
            results.put(c, 0.0f);
        }

        this.displayer = new PrintConsole();
        this.match = new RandomWinner();
    }

    /**
        returns the list of players in the competition.
        @return the list of players in the competition.
     */
    public List<Competitor> getPlayers() {
        return this.competitors;
    }

    /**
        return the number of players in the competition.
        @return the number of players in the competition.
     */
    public int getNbPlayers() {
        return this.competitors.size();
    }

    /**
        Sets this competition's match type 
        @param match A match type instance 
     */
    public void setMatch(Match match){
        this.match = match;
    }

    /**
        returns this competition's match type instance.
        @return this competition's match
     */
    public Match getMatch(){
        return this.match;
    }

    /**
        Sets this competition's displayer type 
        @param displayer A displayer type instance 
     */
    public void setDisplayer(Displayer displayer){
        this.displayer = displayer; 
    }

    /**
        returns this competition's displayer type instance.
        @return this competition's displayer
     */
    public Displayer getDisplayer(){
        return this.displayer;
    }

    /**
        returns the list of journalists assisting to this competition
        @return the list of journalists
     */
    public List<Journalist> getJournalists(){
        return this.journalists;
    }

    /**
        launch the matchs between all players.
     */
    public void play() {
        this.play(this.competitors);
    }

    /**
        execute the match between players, with respect to the constrain of players matching.
        @param players List of players.
     */
    protected abstract void play(List<Competitor> players);


    /**
        Sends the report given in parameter to all the journalists in this competition.
        the report correspond to one of the matchs played during this competition.
        @param report a match's report
     */
    protected void diffuseReport(Report report){
        for (Journalist journalist : this.getJournalists()){
            journalist.printReport(report);
        }
    }

    /**
        plays a type of match between the two competitors given in parameter.
        after receiving the winner, his score is incremented by one.
        in case of a draw, increment both player's scores by 0.5
        the winner is returned.
        the report is diffused to all the journalists.
        @param c1 first competitor
        @param c2 second competitor
        @return the report of the match, containing the result, scores of each competitor.
     */
    protected Report playMatch(Competitor c1, Competitor c2) {
        //play the match
        Report report = this.match.playMatch(c1, c2);

        //message to display
        String msg = c1 + " vs " + c2 + " --> ";
        //get the winner to announce his name later
        Competitor winner = report.getWinner();

        //announce the winner if there is, if not then announce that it was a draw
        msg += (report.getMatchState() == State.DRAW ? "Its a draw!" : winner + " wins!");

        //print to the console
        this.displayer.writeMessage(msg);

        //diffuse the report to all the journalists
        this.diffuseReport(report);

        return report;
    }

    /**
        This method updates the rankings of the two competitors who are inside the report.
        @param report a report of a match
     */
    protected void updateRanking(Report report){
        Competitor winner = report.getWinner();
        Competitor loser = report.getLoser();
        //update scores
        float oldWinnerScore = this.results.get(winner);
        float oldLoserScore = this.results.get(loser);
        float newWinnerScore;
        float newLoserScore;

        if (report.getMatchState() == State.VICTORY){//in the case of a victory, add one to the winner's score 
            newWinnerScore = oldWinnerScore + 1;
            newLoserScore = oldLoserScore;
        }
        else{//in case of a draw, increase both competitor's score by 0.5
            newWinnerScore = oldWinnerScore + 0.5f;
            newLoserScore = oldLoserScore + 0.5f;
        }

        this.results.put(winner, newWinnerScore);
        this.results.put(loser, newLoserScore);
    }

    /**
        returns the hashmap which maps each player in the competiton to his result expressed as an integer, sorted in descending order.
        @return sorted map of the results.
     */
    public Map<Competitor, Float> ranking() {
        return MapUtil.sortByDescendingValue(this.results);
    }

    /**
        displays through the displayer instance, the ranking of the players in this competition.
     */
    public void displayRanking(){
        Map<Competitor, Float> ranks = this.ranking();

        this.displayer.writeMessage("## THE RANKINGS ##");
        for (Competitor c : ranks.keySet()){
            this.displayer.writeMessage(c + " - " + ranks.get(c));
        }
    }
}