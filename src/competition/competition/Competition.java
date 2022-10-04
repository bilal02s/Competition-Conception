package competition.competition;

import java.util.*;
import util.*;
import competition.*;
import competition.exception.*;
import competition.match.*;

public abstract class Competition {
    protected final List<Competitor> competitors;
    protected Map<Competitor, Integer> results;

    /**
        initialise the attribut competitors with the players given parameter.
        initialise the hashmap results by putting inside of it all the players given in parameter, and assigning a value of zero to their corresponding scores.
        raises an exception if the number of players in the list is less than 2.
        @throws InsufficientNumberOfPlayersException if the number of players in the list is less than 2.
     */
    public Competition(List<Competitor> competitors) throws InsufficientNumberOfPlayersException{
        if(competitors.size() < 2){
            throw new InsufficientNumberOfPlayersException("A competition must have at least two players.");
        }

        this.competitors = competitors;
        this.results = new HashMap<Competitor, Integer>();

        for (Competitor c : competitors){
            results.put(c, 0);
        }
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

    public void play() {
        this.play(this.competitors);
    }

    protected abstract void play(List<Competitor> players);

    /**
        plays a type of match between the two competitors given in parameter.
        after receiving the winner, his score is incremented by one.
        the winner is returned.
        @param c1 first competitor
        @param c2 second competitor
        @return the winner between the two competitors
     */
    protected Competitor playMatch(Competitor c1, Competitor c2) {
        Match match = new RandomWinner();
        Competitor winner = match.playMatch(c1, c2);

        int oldScore = this.results.get(winner);
        this.results.put(winner, oldScore + 1);

        return winner;
    }

    /**
        returns the hashmap which maps each player in the competiton to his result expressed as an integer, sorted in descending order.
        @return sorted map of the results.
     */
    public Map<Competitor, Integer> ranking() {
        return MapUtil.sortByDescendingValue(this.results);
    }
}